package model;

/**
 * Represents a savings account.
 */
public class SavingsAccount extends Account {

    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public SavingsAccount() {
    }

    /**
     * Creates a savings account with the supplied details.
     *
     * @param accountNumber the unique account number
     * @param accountHolderName the account holder's name
     * @param balance the current balance
     */
    public SavingsAccount(String accountNumber, String accountHolderName, double balance) {
        super(accountNumber, accountHolderName, balance, "Savings");
    }
}
