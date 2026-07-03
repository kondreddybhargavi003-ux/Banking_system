package constant;

/**
 * Centralizes shared application constants used across the banking system.
 */
public final class AppConstants {

    public static final String APPLICATION_NAME = "BankingSystem";
    public static final String APPLICATION_VERSION = "1.0.0";
    public static final String ORGANIZATION_NAME = "PS106 Banking";

    public static final String ACCOUNT_DATA_FILE = "accounts.dat";
    public static final String TRANSACTION_DATA_FILE = "transactions.dat";

    public static final String MAIN_MENU_TITLE = "BANKING MANAGEMENT SYSTEM";

    public static final String SUCCESS_MESSAGE = "Operation completed successfully.";
    public static final String ERROR_MESSAGE = "An error occurred.";

    public static final String DEFAULT_CURRENCY = "USD";

    public static final String DEFAULT_STRING = "N/A";
    public static final int DEFAULT_DECIMAL_SCALE = 2;

    private AppConstants() {
    }
}
