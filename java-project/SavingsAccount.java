/**
 * Represents a savings account.
 */
public class SavingsAccount extends BankAccount {

    public SavingsAccount(String customerName, String mobileNumber, String emailAddress, double initialDeposit) {
        super(customerName, mobileNumber, emailAddress, initialDeposit);
    }

    @Override
    public String getAccountType() {
        return "Savings";
    }
}
