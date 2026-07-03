package validator;

import exception.BankException;
import exception.InvalidAmountException;
import util.InputValidator;

/**
 * Validates banking-related input values for account creation and transaction preparation.
 */
public final class AccountValidator {

    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 100;
    private static final String ACCOUNT_NUMBER_PREFIX = "ACC";
    private static final int MIN_ACCOUNT_NUMBER_LENGTH = 6;
    private static final int MAX_ACCOUNT_NUMBER_LENGTH = 15;

    private AccountValidator() {
    }

    /**
     * Validates an account number.
     *
     * @param accountNumber the account number to validate
     * @return true if the account number is valid
     * @throws BankException if the account number is invalid
     */
    public static boolean validateAccountNumber(String accountNumber) throws BankException {
        if (InputValidator.isNullOrEmpty(accountNumber)) {
            throw new InvalidAmountException("Account number cannot be null or empty.");
        }

        String trimmed = accountNumber.trim();

        if (!trimmed.startsWith(ACCOUNT_NUMBER_PREFIX)) {
            throw new InvalidAmountException("Account number must start with 'ACC'.");
        }

        if (trimmed.length() < MIN_ACCOUNT_NUMBER_LENGTH || trimmed.length() > MAX_ACCOUNT_NUMBER_LENGTH) {
            throw new InvalidAmountException("Account number must be between " + MIN_ACCOUNT_NUMBER_LENGTH + " and " + MAX_ACCOUNT_NUMBER_LENGTH + " characters.");
        }

        String suffix = trimmed.substring(ACCOUNT_NUMBER_PREFIX.length());
        if (suffix.isEmpty() || !suffix.matches("[A-Za-z0-9]+")) {
            throw new InvalidAmountException("Account number must contain only letters and digits after the prefix.");
        }

        return true;
    }

    /**
     * Validates a customer name.
     *
     * @param customerName the customer name to validate
     * @return true if the customer name is valid
     * @throws BankException if the customer name is invalid
     */
    public static boolean validateCustomerName(String customerName) throws BankException {
        if (InputValidator.isNullOrEmpty(customerName)) {
            throw new InvalidAmountException("Customer name cannot be null or blank.");
        }

        String trimmedName = customerName.trim();
        int length = trimmedName.length();
        if (length < MIN_NAME_LENGTH || length > MAX_NAME_LENGTH) {
            throw new InvalidAmountException("Customer name length must be between 2 and 100 characters.");
        }

        return true;
    }

    /**
     * Validates a transaction amount.
     *
     * @param amount the amount to validate
     * @return true if the amount is valid
     * @throws BankException if the amount is invalid
     */
    public static boolean validateAmount(double amount) throws BankException {
        if (!InputValidator.isPositiveAmount(amount)) {
            throw new InvalidAmountException("Amount must be greater than zero.");
        }

        return true;
    }

    /**
     * Validates an initial balance.
     *
     * @param initialBalance the balance to validate
     * @return true if the initial balance is valid
     * @throws BankException if the initial balance is invalid
     */
    public static boolean validateInitialBalance(double initialBalance) throws BankException {
        if (initialBalance < 0) {
            throw new InvalidAmountException("Initial balance cannot be negative.");
        }

        return true;
    }

    /**
     * Validates an account type.
     *
     * @param accountType the account type to validate
     * @return true if the account type is valid
     * @throws BankException if the account type is invalid
     */
    public static boolean validateAccountType(String accountType) throws BankException {
        if (InputValidator.isNullOrEmpty(accountType)) {
            throw new InvalidAmountException("Account type cannot be null or empty.");
        }

        String normalizedType = accountType.trim();
        if (!"Savings".equalsIgnoreCase(normalizedType)
                && !"Current".equalsIgnoreCase(normalizedType)
                && !"Fixed Deposit".equalsIgnoreCase(normalizedType)) {
            throw new InvalidAmountException("Account type must be Savings, Current, or Fixed Deposit.");
        }

        return true;
    }
}
