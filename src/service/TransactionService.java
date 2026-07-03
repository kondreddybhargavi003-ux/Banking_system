package service;

import config.AppConfig;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import model.Transaction;
import util.FileUtil;

/**
 * Maintains the transaction history for the banking system.
 */
public class TransactionService {

    private final List<Transaction> transactions;
    private final Path transactionsFilePath;

    /**
     * Creates an empty transaction history.
     */
    public TransactionService() {
        this.transactions = Collections.synchronizedList(new ArrayList<>());
        this.transactionsFilePath = Path.of(AppConfig.DATA_DIRECTORY, AppConfig.TRANSACTIONS_FILE_NAME);
    }

    /**
     * Records a new transaction.
     *
     * @param accountNumber the affected account number
     * @param transactionType the type of transaction
     * @param amount the transaction amount
     * @return the created transaction
     */
    public Transaction recordTransaction(String accountNumber, String transactionType, double amount) {
        Transaction transaction = new Transaction(
                UUID.randomUUID().toString(),
                accountNumber,
                transactionType,
                amount,
                LocalDateTime.now()
        );
        transactions.add(transaction);
        return transaction;
    }

    /**
     * Returns transactions for a specific account.
     *
     * @param accountNumber the account number to filter by
     * @return an unmodifiable list of matching transactions
     */
    public List<Transaction> getTransactions(String accountNumber) {
        synchronized (transactions) {
            return transactions.stream()
                    .filter(transaction -> accountNumber.equals(transaction.getAccountNumber()))
                    .collect(Collectors.toUnmodifiableList());
        }
    }

    /**
     * Returns all recorded transactions.
     *
     * @return an unmodifiable list of all transactions
     */
    public List<Transaction> getAllTransactions() {
        synchronized (transactions) {
            return List.copyOf(transactions);
        }
    }

    /**
     * Clears all recorded transactions.
     */
    public void clearHistory() {
        synchronized (transactions) {
            transactions.clear();
        }
    }

    /**
     * Loads all transactions from disk into memory.
     *
     * @return the number of loaded transactions
     */
    public int loadTransactions() {
        FileUtil.createDirectoryIfMissing(Path.of(AppConfig.DATA_DIRECTORY));
        FileUtil.createFileIfMissing(transactionsFilePath);

        Object loaded = FileUtil.loadObject(transactionsFilePath);
        if (loaded instanceof List<?> loadedTransactions) {
            synchronized (transactions) {
                transactions.clear();
                for (Object item : loadedTransactions) {
                    if (item instanceof Transaction transaction) {
                        transactions.add(transaction);
                    }
                }
            }
            return transactions.size();
        }
        return 0;
    }

    /**
     * Saves all transactions to disk.
     *
     * @return true if the save operation succeeded
     */
    public boolean saveTransactions() {
        FileUtil.createDirectoryIfMissing(Path.of(AppConfig.DATA_DIRECTORY));
        FileUtil.createFileIfMissing(transactionsFilePath);
        synchronized (transactions) {
            return FileUtil.saveObject(transactionsFilePath, new ArrayList<>(transactions));
        }
    }
}
