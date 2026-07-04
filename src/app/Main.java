package app;

import config.AppConfig;
import java.nio.file.Path;
import menu.Menu;
import model.Account;
import repository.BankRepository;
import service.TransactionService;
import util.AccountNumberGenerator;
import util.FileUtil;

/**
 * Entry point for the console banking management application.
 */
public class Main {

    /**
     * Starts the banking system, loads persisted data, launches the menu, and saves data on exit.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println("     BANKING MANAGEMENT SYSTEM");
        System.out.println("         Phase 1 - PS106");
        System.out.println("====================================");

        FileUtil.createDirectoryIfMissing(Path.of(AppConfig.DATA_DIRECTORY));
        FileUtil.createFileIfMissing(Path.of(AppConfig.DATA_DIRECTORY, AppConfig.ACCOUNTS_FILE_NAME));
        FileUtil.createFileIfMissing(Path.of(AppConfig.DATA_DIRECTORY, AppConfig.TRANSACTIONS_FILE_NAME));

        BankRepository bankRepository = new BankRepository();
        int loadedAccounts = bankRepository.loadAccounts();
        AccountNumberGenerator.initializeFromExistingAccountNumbers(
                bankRepository.getAllAccounts().stream().map(Account::getAccountNumber).toList()
        );

        TransactionService transactionService = new TransactionService();
        int loadedTransactions = transactionService.loadTransactions();

        if (loadedAccounts > 0) {
            System.out.println("Loaded " + loadedAccounts + " accounts.");
        } else {
            System.out.println("No saved accounts found.");
        }

        if (loadedTransactions > 0) {
            System.out.println("Loaded " + loadedTransactions + " transactions.");
        } else {
            System.out.println("No saved transactions found.");
        }

        Menu menu = new Menu(bankRepository, transactionService);
        menu.start();

        boolean accountsSaved = bankRepository.saveAccounts();
        boolean transactionsSaved = transactionService.saveTransactions();

        if (accountsSaved && transactionsSaved) {
            System.out.println("Data saved successfully.");
        } else {
            System.out.println("Warning: Some data could not be saved.");
        }
    }

}
