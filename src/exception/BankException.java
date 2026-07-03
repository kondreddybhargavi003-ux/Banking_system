package exception;

/**
 * Base exception for all banking application exceptions.
 */
public class BankException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new bank exception with no detail message.
     */
    public BankException() {
        super();
    }

    /**
     * Constructs a new bank exception with the specified detail message.
     *
     * @param message the detail message
     */
    public BankException(String message) {
        super(message);
    }

    /**
     * Constructs a new bank exception with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause of the exception
     */
    public BankException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new bank exception with the specified cause.
     *
     * @param cause the cause of the exception
     */
    public BankException(Throwable cause) {
        super(cause);
    }
}
