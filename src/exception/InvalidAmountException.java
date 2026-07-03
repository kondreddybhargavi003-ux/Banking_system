package exception;

/**
 * Thrown when an amount is invalid, zero, or negative.
 */
public class InvalidAmountException extends BankException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new invalid amount exception with no detail message.
     */
    public InvalidAmountException() {
        super();
    }

    /**
     * Constructs a new invalid amount exception with the specified detail message.
     *
     * @param message the detail message
     */
    public InvalidAmountException(String message) {
        super(message);
    }

    /**
     * Constructs a new invalid amount exception with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause of the exception
     */
    public InvalidAmountException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new invalid amount exception with the specified cause.
     *
     * @param cause the cause of the exception
     */
    public InvalidAmountException(Throwable cause) {
        super(cause);
    }
}
