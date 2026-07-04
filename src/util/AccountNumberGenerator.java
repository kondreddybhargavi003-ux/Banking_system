package util;

import java.util.Collection;
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
     * Initializes the sequence based on existing account numbers so generated values remain unique after reload.
     *
     * @param existingAccountNumbers account numbers already present in storage
     */
    public static void initializeFromExistingAccountNumbers(Collection<String> existingAccountNumbers) {
        long nextValue = 1001L;

        if (existingAccountNumbers != null) {
            for (String accountNumber : existingAccountNumbers) {
                if (accountNumber != null && accountNumber.startsWith(ACCOUNT_PREFIX)) {
                    String suffix = accountNumber.substring(ACCOUNT_PREFIX.length());
                    try {
                        long parsedValue = Long.parseLong(suffix);
                        if (parsedValue >= nextValue) {
                            nextValue = parsedValue + 1;
                        }
                    } catch (NumberFormatException ignored) {
                        // Ignore malformed account numbers and keep the default sequence.
                    }
                }
            }
        }

        SEQUENCE.set(nextValue);
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
