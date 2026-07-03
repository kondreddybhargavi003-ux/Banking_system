package config;

/**
 * Centralizes static application configuration values.
 */
public final class AppConfig {

    public static final String DATA_DIRECTORY = "data";
    public static final String ACCOUNTS_FILE_NAME = "accounts.dat";
    public static final String TRANSACTIONS_FILE_NAME = "transactions.dat";
    public static final boolean AUTO_SAVE_ENABLED = true;
    public static final String APPLICATION_ENCODING = "UTF-8";
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private AppConfig() {
    }
}
