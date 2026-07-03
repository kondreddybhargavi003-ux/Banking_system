package model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Represents a single banking transaction.
 */
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;

    private String transactionId;
    private String accountNumber;
    private String transactionType;
    private double amount;
    private LocalDateTime transactionDateTime;

    /**
     * Default constructor.
     */
    public Transaction() {
    }

    /**
     * Creates a transaction with the supplied details.
     *
     * @param transactionId the unique transaction identifier
     * @param accountNumber the affected account number
     * @param transactionType the type of transaction
     * @param amount the transaction amount
     * @param transactionDateTime the date and time of the transaction
     */
    public Transaction(String transactionId, String accountNumber, String transactionType, double amount, LocalDateTime transactionDateTime) {
        this.transactionId = transactionId;
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDateTime = transactionDateTime;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(LocalDateTime transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", amount=" + amount +
                ", transactionDateTime=" + transactionDateTime +
                '}';
    }
}
