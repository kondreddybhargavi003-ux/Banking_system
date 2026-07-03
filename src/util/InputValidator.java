package util;

import java.util.regex.Pattern;

/**
 * Provides reusable validation helpers for common string and numeric inputs.
 */
public final class InputValidator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^[0-9+\\-() ]{7,15}$");

    private InputValidator() {
    }

    /**
     * Checks whether a string is null, empty, or blank.
     *
     * @param value the string to validate
     * @return true if the value is null, empty, or blank
     */
    public static boolean isNullOrEmpty(String value) {
        return value == null || value.isBlank();
    }

    /**
     * Checks whether an amount is positive.
     *
     * @param amount the amount to validate
     * @return true if the amount is greater than zero
     */
    public static boolean isPositiveAmount(double amount) {
        return amount > 0;
    }

    /**
     * Checks whether the provided string is a valid email address.
     *
     * @param email the email address to validate
     * @return true if the email matches the expected pattern
     */
    public static boolean isValidEmail(String email) {
        return !isNullOrEmpty(email) && EMAIL_PATTERN.matcher(email).matches();
    }

    /**
     * Checks whether the provided string is a valid phone number.
     *
     * @param phoneNumber the phone number to validate
     * @return true if the phone number matches the expected pattern
     */
    public static boolean isValidPhoneNumber(String phoneNumber) {
        return !isNullOrEmpty(phoneNumber) && PHONE_PATTERN.matcher(phoneNumber).matches();
    }
}
