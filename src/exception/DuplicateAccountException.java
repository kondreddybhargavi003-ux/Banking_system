package exception;

/**
 * Thrown when an account with the same identifier already exists.
 */
public class DuplicateAccountException extends BankException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new duplicate account exception with no detail message.
     */
    public DuplicateAccountException() {
        super();
    }

    /**
     * Constructs a new duplicate account exception with the specified detail message.
     *
     * @param message the detail message
     */
    public DuplicateAccountException(String message) {
        super(message);
    }

    /**
     * Constructs a new duplicate account exception with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause of the exception
     */
    public DuplicateAccountException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new duplicate account exception with the specified cause.
     *
     * @param cause the cause of the exception
     */
    public DuplicateAccountException(Throwable cause) {
        super(cause);
    }
}
