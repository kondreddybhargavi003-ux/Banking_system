package exception;

/**
 * Thrown when an operation exceeds the available balance.
 */
public class InsufficientBalanceException extends BankException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new insufficient balance exception with no detail message.
     */
    public InsufficientBalanceException() {
        super();
    }

    /**
     * Constructs a new insufficient balance exception with the specified detail message.
     *
     * @param message the detail message
     */
    public InsufficientBalanceException(String message) {
        super(message);
    }

    /**
     * Constructs a new insufficient balance exception with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause of the exception
     */
    public InsufficientBalanceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new insufficient balance exception with the specified cause.
     *
     * @param cause the cause of the exception
     */
    public InsufficientBalanceException(Throwable cause) {
        super(cause);
    }
}
