package test.tempcheck;

import java.nio.file.Path;
import model.SavingsAccount;
import repository.BankRepository;
import service.TransactionService;

public class TempCheck {
    public static void main(String[] args) {
        BankRepository repo = new BankRepository();
        repo.saveAccount(new SavingsAccount("ACC100001", "Alice", 100.0));
        repo.saveAccounts();

        TransactionService tx = new TransactionService();
        tx.recordTransaction("ACC100001", "DEPOSIT", 50.0);
        tx.saveTransactions();

        BankRepository repo2 = new BankRepository();
        int loadedAccounts = repo2.loadAccounts();

        TransactionService tx2 = new TransactionService();
        int loadedTransactions = tx2.loadTransactions();

        System.out.println("loadedAccounts=" + loadedAccounts);
        System.out.println("loadedTransactions=" + loadedTransactions);
        System.out.println("accountsFile=" + java.nio.file.Files.exists(Path.of("data", "accounts.dat")));
        System.out.println("transactionsFile=" + java.nio.file.Files.exists(Path.of("data", "transactions.dat")));
    }
}
