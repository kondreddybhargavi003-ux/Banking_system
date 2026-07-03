package model;

import java.io.Serializable;

/**
 * Represents a generic bank account in the banking system.
 */
public abstract class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private String accountType;

    /**
     * Default constructor.
     */
    public Account() {
    }

    /**
     * Creates an account with the supplied details.
     *
     * @param accountNumber the unique account number
     * @param accountHolderName the account holder's name
     * @param balance the current balance
     * @param accountType the type of account
     */
    public Account(String accountNumber, String accountHolderName, double balance, String accountType) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.accountType = accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", accountHolderName='" + accountHolderName + '\'' +
                ", balance=" + balance +
                ", accountType='" + accountType + '\'' +
                '}';
    }
}
