package util;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Generates unique account numbers for the banking system.
 */
public final class AccountNumberGenerator {

    private static final String ACCOUNT_PREFIX = "ACC";
    private static final AtomicLong SEQUENCE = new AtomicLong(1001L);

    private AccountNumberGenerator() {
    }

    /**
     * Generates the next unique account number.
     *
     * @return a unique account number as a string
     */
    public static String generateAccountNumber() {
        return ACCOUNT_PREFIX + SEQUENCE.getAndIncrement();
    }
}
