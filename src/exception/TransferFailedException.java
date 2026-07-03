package exception;

/**
 * Thrown when a transfer operation cannot be completed successfully.
 */
public class TransferFailedException extends BankException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new transfer failed exception with no detail message.
     */
    public TransferFailedException() {
        super();
    }

    /**
     * Constructs a new transfer failed exception with the specified detail message.
     *
     * @param message the detail message
     */
    public TransferFailedException(String message) {
        super(message);
    }

    /**
     * Constructs a new transfer failed exception with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause of the exception
     */
    public TransferFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new transfer failed exception with the specified cause.
     *
     * @param cause the cause of the exception
     */
    public TransferFailedException(Throwable cause) {
        super(cause);
    }
}
