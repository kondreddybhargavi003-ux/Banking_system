package menu;

import exception.BankException;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import model.Account;
import model.Transaction;
import repository.BankRepository;
import service.BankService;
import service.TransactionService;
import util.AccountNumberGenerator;

/**
 * Console-based presentation layer for the banking system.
 */
public class Menu {

    private final Scanner scanner;
    private final BankService bankService;

    /**
     * Creates the menu with console input and the banking service.
     */
    public Menu(BankRepository bankRepository, TransactionService transactionService) {
        this.scanner = new Scanner(System.in);
        this.bankService = new BankService(bankRepository, transactionService);
    }

    /**
     * Starts the interactive menu loop.
     */
    public void start() {
        boolean running = true;

        while (running) {
            displayMenu();
            System.out.print("Enter your choice: ");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1" -> createAccount("Savings");
                case "2" -> createAccount("Current");
                case "3" -> createAccount("Fixed Deposit");
                case "4" -> viewAccount();
                case "5" -> viewAllAccounts();
                case "6" -> deposit();
                case "7" -> withdraw();
                case "8" -> transfer();
                case "9" -> viewTransactions();
                case "10" -> deleteAccount();
                case "11" -> {
                    boolean dataSaved = bankService.saveData();
                    if (dataSaved) {
                        System.out.println("Data saved successfully.");
                    } else {
                        System.out.println("Warning: Data could not be saved.");
                    }
                }
                case "12" -> {
                    running = false;
                    System.out.println("Thank you for using Banking Management System.");
                }
                default -> System.out.println("Invalid option. Please try again.");
            }

            if (running) {
                System.out.println();
            }
        }
    }

    private void displayMenu() {
        System.out.println("========================================");
        System.out.println("BANKING MANAGEMENT SYSTEM");
        System.out.println("========================================");
        System.out.println("1. Create Savings Account");
        System.out.println("2. Create Current Account");
        System.out.println("3. Create Fixed Deposit Account");
        System.out.println("4. View Account");
        System.out.println("5. View All Accounts");
        System.out.println("6. Deposit");
        System.out.println("7. Withdraw");
        System.out.println("8. Transfer Money");
        System.out.println("9. View Transaction History");
        System.out.println("10. Delete Account");
        System.out.println("11. Save Data");
        System.out.println("12. Exit");
    }

    private void createAccount(String accountType) {
        try {
            System.out.print("Enter customer name: ");
            String customerName = scanner.nextLine().trim();

            System.out.print("Enter phone number: ");
            String phoneNumber = scanner.nextLine().trim();

            System.out.print("Enter email: ");
            String email = scanner.nextLine().trim();

            System.out.print("Enter address: ");
            String address = scanner.nextLine().trim();

            System.out.print("Enter initial balance: ");
            double initialBalance = readDoubleInput();

            String accountNumber = AccountNumberGenerator.generateAccountNumber();
            Account account;

            switch (accountType) {
                case "Savings" -> account = bankService.createSavingsAccount(accountNumber, customerName, initialBalance);
                case "Current" -> account = bankService.createCurrentAccount(accountNumber, customerName, initialBalance);
                case "Fixed Deposit" -> account = bankService.createFixedDepositAccount(accountNumber, customerName, initialBalance);
                default -> throw new IllegalArgumentException("Unsupported account type.");
            }

            System.out.println("Account created successfully.");
            System.out.println("Generated Account Number: " + accountNumber);
            System.out.println(account);
            System.out.println("Customer details: " + customerName + ", " + phoneNumber + ", " + email + ", " + address);
        } catch (BankException | IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewAccount() {
        try {
            System.out.print("Enter account number: ");
            String accountNumber = scanner.nextLine().trim();
            Account account = bankService.getAccount(accountNumber);
            System.out.println(account);
        } catch (BankException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewAllAccounts() {
        Collection<Account> accounts = bankService.getAllAccounts();
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }

        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    private void deposit() {
        try {
            System.out.print("Enter account number: ");
            String accountNumber = scanner.nextLine().trim();
            System.out.print("Enter deposit amount: ");
            double amount = readDoubleInput();
            Account account = bankService.deposit(accountNumber, amount);
            System.out.println("Deposit successful.");
            System.out.println(account);
        } catch (BankException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void withdraw() {
        try {
            System.out.print("Enter account number: ");
            String accountNumber = scanner.nextLine().trim();
            System.out.print("Enter withdrawal amount: ");
            double amount = readDoubleInput();
            Account account = bankService.withdraw(accountNumber, amount);
            System.out.println("Withdrawal successful.");
            System.out.println(account);
        } catch (BankException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void transfer() {
        try {
            System.out.print("Enter sender account number: ");
            String fromAccount = scanner.nextLine().trim();
            System.out.print("Enter receiver account number: ");
            String toAccount = scanner.nextLine().trim();
            System.out.print("Enter transfer amount: ");
            double amount = readDoubleInput();
            Account account = bankService.transfer(fromAccount, toAccount, amount);
            System.out.println("Transfer successful.");
            System.out.println(account);
        } catch (BankException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewTransactions() {
        System.out.print("Enter account number (leave blank for all): ");
        String accountNumber = scanner.nextLine().trim();
        TransactionService transactionService = bankService.getTransactionService();
        List<Transaction> transactions;

        if (accountNumber.isEmpty()) {
            transactions = transactionService.getAllTransactions();
        } else {
            transactions = transactionService.getTransactions(accountNumber);
        }

        if (transactions.isEmpty()) {
            System.out.println("No transaction history found.");
            return;
        }

        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    private void deleteAccount() {
        try {
            System.out.print("Enter account number: ");
            String accountNumber = scanner.nextLine().trim();
            bankService.deleteAccount(accountNumber);
            System.out.println("Account deleted successfully.");
        } catch (BankException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private double readDoubleInput() {
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid number. Please enter again: ");
            }
        }
    }
}
