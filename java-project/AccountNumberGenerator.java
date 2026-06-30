/**
 * Generates unique account numbers for new bank accounts.
 */
public class AccountNumberGenerator {
    private static long nextAccountNumber = 1000000000L;

    private AccountNumberGenerator() {
        // Prevent instantiation
    }

    public static synchronized String generateAccountNumber() {
        return String.valueOf(nextAccountNumber++);
    }
}
