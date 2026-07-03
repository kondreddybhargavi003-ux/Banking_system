package model;

/**
 * Represents a current account.
 */
public class CurrentAccount extends Account {

    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public CurrentAccount() {
    }

    /**
     * Creates a current account with the supplied details.
     *
     * @param accountNumber the unique account number
     * @param accountHolderName the account holder's name
     * @param balance the current balance
     */
    public CurrentAccount(String accountNumber, String accountHolderName, double balance) {
        super(accountNumber, accountHolderName, balance, "Current");
    }
}
