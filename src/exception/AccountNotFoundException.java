package exception;

/**
 * Thrown when an account cannot be found.
 */
public class AccountNotFoundException extends BankException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new account not found exception with no detail message.
     */
    public AccountNotFoundException() {
        super();
    }

    /**
     * Constructs a new account not found exception with the specified detail message.
     *
     * @param message the detail message
     */
    public AccountNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new account not found exception with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause of the exception
     */
    public AccountNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new account not found exception with the specified cause.
     *
     * @param cause the cause of the exception
     */
    public AccountNotFoundException(Throwable cause) {
        super(cause);
    }
}
