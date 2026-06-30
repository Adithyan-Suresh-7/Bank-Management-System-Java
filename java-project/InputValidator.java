import java.util.regex.Pattern;

/**
 * Provides helper methods to validate user input in the bank management system.
 */
public class InputValidator {
    private static final Pattern MOBILE_PATTERN = Pattern.compile("^[0-9]{10}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");

    private InputValidator() {
        // Prevent instantiation
    }

    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    public static boolean isValidMobileNumber(String mobileNumber) {
        return mobileNumber != null && MOBILE_PATTERN.matcher(mobileNumber.trim()).matches();
    }

    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email.trim()).matches();
    }

    public static boolean isValidAmount(double amount) {
        return amount > 0;
    }

    public static boolean isValidAccountType(String accountType) {
        return "Savings".equalsIgnoreCase(accountType) || "Current".equalsIgnoreCase(accountType);
    }
}
