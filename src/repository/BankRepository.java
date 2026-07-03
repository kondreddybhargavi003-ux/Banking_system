package repository;

import config.AppConfig;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import model.Account;
import util.FileUtil;

/**
 * In-memory repository responsible for storing and retrieving bank accounts.
 */
public class BankRepository {

    private final Map<String, Account> accountsByNumber;
    private final Path accountsFilePath;

    /**
     * Creates an empty repository.
     */
    public BankRepository() {
        this.accountsByNumber = new HashMap<>();
        this.accountsFilePath = Path.of(AppConfig.DATA_DIRECTORY, AppConfig.ACCOUNTS_FILE_NAME);
    }

    /**
     * Saves an account in the repository.
     *
     * @param account the account to store
     */
    public void saveAccount(Account account) {
        if (account != null && account.getAccountNumber() != null) {
            accountsByNumber.put(account.getAccountNumber(), account);
        }
    }

    /**
     * Finds an account by its account number.
     *
     * @param accountNumber the account number to search for
     * @return an Optional containing the account if present
     */
    public Optional<Account> findAccountByNumber(String accountNumber) {
        if (accountNumber == null || accountNumber.isBlank()) {
            return Optional.empty();
        }
        return Optional.ofNullable(accountsByNumber.get(accountNumber));
    }

    /**
     * Checks whether an account exists in the repository.
     *
     * @param accountNumber the account number to check
     * @return true if the account exists, otherwise false
     */
    public boolean accountExists(String accountNumber) {
        return findAccountByNumber(accountNumber).isPresent();
    }

    /**
     * Updates an existing account in the repository.
     *
     * @param account the updated account data
     */
    public void updateAccount(Account account) {
        if (account != null && account.getAccountNumber() != null) {
            accountsByNumber.put(account.getAccountNumber(), account);
        }
    }

    /**
     * Deletes an account by its number.
     *
     * @param accountNumber the account number to remove
     */
    public void deleteAccount(String accountNumber) {
        if (accountNumber != null && !accountNumber.isBlank()) {
            accountsByNumber.remove(accountNumber);
        }
    }

    /**
     * Returns all accounts stored in the repository.
     *
     * @return an unmodifiable collection of accounts
     */
    public java.util.Collection<Account> getAllAccounts() {
        return Collections.unmodifiableCollection(accountsByNumber.values());
    }

    /**
     * Returns the number of stored accounts.
     *
     * @return the account count
     */
    public int countAccounts() {
        return accountsByNumber.size();
    }

    /**
     * Clears all accounts from the repository.
     */
    public void clearRepository() {
        accountsByNumber.clear();
    }

    /**
     * Loads all accounts from disk into the repository.
     *
     * @return the number of loaded accounts
     */
    public int loadAccounts() {
        FileUtil.createDirectoryIfMissing(Path.of(AppConfig.DATA_DIRECTORY));
        FileUtil.createFileIfMissing(accountsFilePath);

        Object loaded = FileUtil.loadObject(accountsFilePath);
        if (loaded instanceof Map<?, ?> loadedAccounts) {
            accountsByNumber.clear();
            for (Map.Entry<?, ?> entry : loadedAccounts.entrySet()) {
                if (entry.getKey() instanceof String accountNumber && entry.getValue() instanceof Account account) {
                    accountsByNumber.put(accountNumber, account);
                }
            }
            return accountsByNumber.size();
        }

        return 0;
    }

    /**
     * Saves all accounts from the repository to disk.
     *
     * @return true if the save operation succeeded
     */
    public boolean saveAccounts() {
        FileUtil.createDirectoryIfMissing(Path.of(AppConfig.DATA_DIRECTORY));
        FileUtil.createFileIfMissing(accountsFilePath);
        return FileUtil.saveObject(accountsFilePath, new HashMap<>(accountsByNumber));
    }
}
