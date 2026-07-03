package service;

import exception.AccountNotFoundException;
import exception.BankException;
import exception.DuplicateAccountException;
import exception.InsufficientBalanceException;
import exception.TransferFailedException;
import java.util.Collection;
import java.util.Optional;
import model.Account;
import model.CurrentAccount;
import model.FixedDepositAccount;
import model.SavingsAccount;
import repository.BankRepository;
import validator.AccountValidator;

/**
 * Provides the core banking business operations for account management and transactions.
 */
public class BankService {

    private final BankRepository bankRepository;
    private final TransactionService transactionService;

    /**
     * Creates a bank service with an existing repository and transaction history.
     *
     * @param bankRepository the repository to use
     * @param transactionService the transaction history service to use
     */
    public BankService(BankRepository bankRepository, TransactionService transactionService) {
        this.bankRepository = bankRepository;
        this.transactionService = transactionService;
    }

    /**
     * Persists the current repository and transaction state to disk.
     *
     * @return true if both persistence operations succeeded
     */
    public boolean saveData() {
        boolean accountsSaved = bankRepository.saveAccounts();
        boolean transactionsSaved = transactionService.saveTransactions();
        return accountsSaved && transactionsSaved;
    }

    private void persistState() {
        saveData();
    }

    /**
     * Creates a savings account.
     *
     * @param accountNumber the account number to assign
     * @param customerName the account holder name
     * @param initialBalance the starting balance
     * @return the created savings account
     * @throws BankException if validation or duplication fails
     */
    public Account createSavingsAccount(String accountNumber, String customerName, double initialBalance) throws BankException {
        AccountValidator.validateAccountNumber(accountNumber);
        AccountValidator.validateCustomerName(customerName);
        AccountValidator.validateInitialBalance(initialBalance);
        AccountValidator.validateAccountType("Savings");

        if (bankRepository.accountExists(accountNumber)) {
            throw new DuplicateAccountException("Account already exists: " + accountNumber);
        }

        Account account = new SavingsAccount(accountNumber, customerName, initialBalance);
        bankRepository.saveAccount(account);
        persistState();
        return account;
    }

    /**
     * Creates a current account.
     *
     * @param accountNumber the account number to assign
     * @param customerName the account holder name
     * @param initialBalance the starting balance
     * @return the created current account
     * @throws BankException if validation or duplication fails
     */
    public Account createCurrentAccount(String accountNumber, String customerName, double initialBalance) throws BankException {
        AccountValidator.validateAccountNumber(accountNumber);
        AccountValidator.validateCustomerName(customerName);
        AccountValidator.validateInitialBalance(initialBalance);
        AccountValidator.validateAccountType("Current");

        if (bankRepository.accountExists(accountNumber)) {
            throw new DuplicateAccountException("Account already exists: " + accountNumber);
        }

        Account account = new CurrentAccount(accountNumber, customerName, initialBalance);
        bankRepository.saveAccount(account);
        persistState();
        return account;
    }

    /**
     * Creates a fixed deposit account.
     *
     * @param accountNumber the account number to assign
     * @param customerName the account holder name
     * @param initialBalance the starting balance
     * @return the created fixed deposit account
     * @throws BankException if validation or duplication fails
     */
    public Account createFixedDepositAccount(String accountNumber, String customerName, double initialBalance) throws BankException {
        AccountValidator.validateAccountNumber(accountNumber);
        AccountValidator.validateCustomerName(customerName);
        AccountValidator.validateInitialBalance(initialBalance);
        AccountValidator.validateAccountType("Fixed Deposit");

        if (bankRepository.accountExists(accountNumber)) {
            throw new DuplicateAccountException("Account already exists: " + accountNumber);
        }

        Account account = new FixedDepositAccount(accountNumber, customerName, initialBalance);
        bankRepository.saveAccount(account);
        persistState();
        return account;
    }

    /**
     * Retrieves an account by account number.
     *
     * @param accountNumber the account number to find
     * @return the matching account if present
     * @throws BankException if the account does not exist
     */
    public Account getAccount(String accountNumber) throws BankException {
        Optional<Account> account = bankRepository.findAccountByNumber(accountNumber);
        if (account.isEmpty()) {
            throw new AccountNotFoundException("Account not found: " + accountNumber);
        }
        return account.get();
    }

    /**
     * Returns all accounts managed by the repository.
     *
     * @return an unmodifiable collection of accounts
     */
    public Collection<Account> getAllAccounts() {
        return bankRepository.getAllAccounts();
    }

    /**
     * Deposits money into an account.
     *
     * @param accountNumber the target account number
     * @param amount the amount to deposit
     * @return the updated account
     * @throws BankException if the account is missing or the amount is invalid
     */
    public Account deposit(String accountNumber, double amount) throws BankException {
        Account account = getAccount(accountNumber);
        AccountValidator.validateAmount(amount);

        double currentBalance = account.getBalance();
        account.setBalance(currentBalance + amount);
        bankRepository.updateAccount(account);
        transactionService.recordTransaction(accountNumber, "DEPOSIT", amount);
        persistState();
        return account;
    }

    /**
     * Withdraws money from an account.
     *
     * @param accountNumber the source account number
     * @param amount the amount to withdraw
     * @return the updated account
     * @throws BankException if the account is missing, the amount is invalid, or balance is insufficient
     */
    public Account withdraw(String accountNumber, double amount) throws BankException {
        Account account = getAccount(accountNumber);
        AccountValidator.validateAmount(amount);

        if (account.getBalance() < amount) {
            throw new InsufficientBalanceException("Insufficient balance for withdrawal.");
        }

        account.setBalance(account.getBalance() - amount);
        bankRepository.updateAccount(account);
        transactionService.recordTransaction(accountNumber, "WITHDRAW", amount);
        persistState();
        return account;
    }

    /**
     * Transfers money between two accounts.
     *
     * @param fromAccount the sender account number
     * @param toAccount the receiver account number
     * @param amount the transfer amount
     * @return the sender account after transfer
     * @throws BankException if validation or balance checks fail
     */
    public Account transfer(String fromAccount, String toAccount, double amount) throws BankException {
        Account sender = getAccount(fromAccount);
        Account receiver = getAccount(toAccount);
        AccountValidator.validateAmount(amount);

        if (sender.getBalance() < amount) {
            throw new InsufficientBalanceException("Insufficient balance for transfer.");
        }

        if (fromAccount.equals(toAccount)) {
            throw new TransferFailedException("Transfer between the same account is not allowed.");
        }

        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        bankRepository.updateAccount(sender);
        bankRepository.updateAccount(receiver);
        transactionService.recordTransaction(fromAccount, "TRANSFER_OUT", amount);
        transactionService.recordTransaction(toAccount, "TRANSFER_IN", amount);
        persistState();
        return sender;
    }

    /**
     * Deletes an account by number.
     *
     * @param accountNumber the account number to delete
     * @throws BankException if the account does not exist
     */
    public void deleteAccount(String accountNumber) throws BankException {
        if (!bankRepository.accountExists(accountNumber)) {
            throw new AccountNotFoundException("Account not found: " + accountNumber);
        }
        bankRepository.deleteAccount(accountNumber);
        persistState();
    }

    /**
     * Checks whether an account exists.
     *
     * @param accountNumber the account number to check
     * @return true if the account exists
     */
    public boolean accountExists(String accountNumber) {
        return bankRepository.accountExists(accountNumber);
    }

    /**
     * Returns the transaction history service used by this service.
     *
     * @return the transaction service instance
     */
    public TransactionService getTransactionService() {
        return transactionService;
    }
}
