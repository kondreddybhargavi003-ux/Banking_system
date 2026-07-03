package model;

/**
 * Represents a fixed deposit account.
 */
public class FixedDepositAccount extends Account {

    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public FixedDepositAccount() {
    }

    /**
     * Creates a fixed deposit account with the supplied details.
     *
     * @param accountNumber the unique account number
     * @param accountHolderName the account holder's name
     * @param balance the current balance
     */
    public FixedDepositAccount(String accountNumber, String accountHolderName, double balance) {
        super(accountNumber, accountHolderName, balance, "Fixed Deposit");
    }
}
