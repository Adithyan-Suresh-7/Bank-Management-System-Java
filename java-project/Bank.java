import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a bank that manages multiple customer accounts.
 */
public class Bank {
    private final List<BankAccount> accounts;

    public Bank() {
        this.accounts = new ArrayList<>();
    }

    public BankAccount createAccount(String customerName, String accountType,
            String mobileNumber, String emailAddress, double initialDeposit) {
        BankAccount account;
        if ("Savings".equalsIgnoreCase(accountType)) {
            account = new SavingsAccount(customerName, mobileNumber, emailAddress, initialDeposit);
        } else {
            account = new CurrentAccount(customerName, mobileNumber, emailAddress, initialDeposit);
        }

        accounts.add(account);
        return account;
    }

    public BankAccount findAccount(String accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber.trim())) {
                return account;
            }
        }
        return null;
    }

    public boolean deleteAccount(String accountNumber) {
        BankAccount account = findAccount(accountNumber);
        if (account != null) {
            accounts.remove(account);
            return true;
        }
        return false;
    }

    public List<BankAccount> getAccounts() {
        return Collections.unmodifiableList(accounts);
    }
}
