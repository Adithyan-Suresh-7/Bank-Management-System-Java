import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Abstract base class for bank accounts.
 */
public abstract class BankAccount {
    private final String accountNumber;
    private final LocalDate creationDate;
    private String customerName;
    private String mobileNumber;
    private String emailAddress;
    private double currentBalance;
    private final List<Transaction> transactions;

    public BankAccount(String customerName, String mobileNumber, String emailAddress, double initialDeposit) {
        this.accountNumber = AccountNumberGenerator.generateAccountNumber();
        this.creationDate = LocalDate.now();
        this.customerName = customerName;
        this.mobileNumber = mobileNumber;
        this.emailAddress = emailAddress;
        this.currentBalance = initialDeposit;
        this.transactions = new ArrayList<>();
        addTransaction("Account Created", initialDeposit, currentBalance, "Initial deposit");
    }

    public abstract String getAccountType();

    public String getAccountNumber() {
        return accountNumber;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    public void deposit(double amount) {
        deposit(amount, "Deposit");
    }

    public void deposit(double amount, String description) {
        if (!InputValidator.isValidAmount(amount)) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero.");
        }
        currentBalance += amount;
        addTransaction("Deposit", amount, currentBalance, description);
    }

    public void withdraw(double amount) {
        if (!InputValidator.isValidAmount(amount)) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero.");
        }
        if (amount > currentBalance) {
            throw new IllegalArgumentException("Insufficient balance for withdrawal.");
        }
        currentBalance -= amount;
        addTransaction("Withdrawal", amount, currentBalance, "Cash withdrawal");
    }

    protected void addTransaction(String type, double amount, double balanceAfter, String description) {
        transactions.add(new Transaction(type, amount, balanceAfter, description));
    }

    public String getFormattedBalanceSummary() {
        return String.format("%-12s: %s\n%-12s: %s\n%-12s: %s\n%-12s: %.2f",
                "Customer", customerName,
                "Account No", accountNumber,
                "Account Type", getAccountType(),
                "Balance", currentBalance);
    }

    public String getAccountDetails() {
        return String.format("%-12s: %s\n%-12s: %s\n%-12s: %s\n%-12s: %s\n%-12s: %s\n%-12s: %.2f\n",
                "Account No", accountNumber,
                "Name", customerName,
                "Type", getAccountType(),
                "Mobile", mobileNumber,
                "Email", emailAddress,
                "Balance", currentBalance);
    }

    public String getTableRow() {
        return String.format("%-12s | %-20s | %-8s | %-12s | %-25s | %12.2f | %s",
                accountNumber, customerName, getAccountType(), mobileNumber, emailAddress, currentBalance,
                creationDate);
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
