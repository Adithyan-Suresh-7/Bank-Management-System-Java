import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a bank transaction record.
 */
public class Transaction {
    private final LocalDateTime timestamp;
    private final String type;
    private final double amount;
    private final double balanceAfter;
    private final String description;

    public Transaction(String type, double amount, double balanceAfter, String description) {
        this.timestamp = LocalDateTime.now();
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.description = description;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalanceAfter() {
        return balanceAfter;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("%s | %-10s | %10.2f | %10.2f | %s",
                timestamp.format(formatter), type, amount, balanceAfter, description);
    }
}
