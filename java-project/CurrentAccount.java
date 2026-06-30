/**
 * Represents a current account.
 */
public class CurrentAccount extends BankAccount {

    public CurrentAccount(String customerName, String mobileNumber, String emailAddress, double initialDeposit) {
        super(customerName, mobileNumber, emailAddress, initialDeposit);
    }

    @Override
    public String getAccountType() {
        return "Current";
    }
}
