import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Entry point for the Bank Management System console application.
 */
public class Main {
    private static final Bank bank = new Bank();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInteger(scanner, "Enter your choice: ");
            System.out.println();

            switch (choice) {
                case 1 -> handleCreateAccount(scanner);
                case 2 -> handleDeposit(scanner);
                case 3 -> handleWithdraw(scanner);
                case 4 -> handleCheckBalance(scanner);
                case 5 -> handleTransactionHistory(scanner);
                case 6 -> handleDisplayAllAccounts();
                case 7 -> handleSearchAccount(scanner);
                case 8 -> handleDeleteAccount(scanner);
                case 9 -> {
                    System.out.println("Thank you for using the Bank Management System. Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid menu choice. Please choose a number from 1 to 9.");
            }

            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("=====================================");
        System.out.println("        BANK MANAGEMENT SYSTEM       ");
        System.out.println("=====================================");
        System.out.println("1. Create Account");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Check Balance");
        System.out.println("5. Transaction History");
        System.out.println("6. Display All Accounts");
        System.out.println("7. Search Account");
        System.out.println("8. Delete Account");
        System.out.println("9. Exit");
        System.out.println();
    }

    private static void handleCreateAccount(Scanner scanner) {
        System.out.println("--- Create New Account ---");
        String customerName = readNonEmptyString(scanner, "Enter customer name: ");
        String accountType = readAccountType(scanner);
        String mobileNumber = readMobileNumber(scanner);
        String emailAddress = readEmail(scanner);
        double initialDeposit = readPositiveDouble(scanner, "Enter initial deposit amount: ");

        try {
            BankAccount newAccount = bank.createAccount(customerName, accountType, mobileNumber, emailAddress,
                    initialDeposit);
            System.out.println("Account successfully created.");
            System.out.println("Account Number: " + newAccount.getAccountNumber());
        } catch (IllegalArgumentException ex) {
            System.out.println("Error creating account: " + ex.getMessage());
        }
    }

    private static void handleDeposit(Scanner scanner) {
        System.out.println("--- Deposit Money ---");
        BankAccount account = findAccountByNumber(scanner);
        if (account == null) {
            return;
        }

        double amount = readPositiveDouble(scanner, "Enter deposit amount: ");
        try {
            account.deposit(amount);
            System.out.printf("Deposit successful. New balance: %.2f%n", account.getCurrentBalance());
        } catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private static void handleWithdraw(Scanner scanner) {
        System.out.println("--- Withdraw Money ---");
        BankAccount account = findAccountByNumber(scanner);
        if (account == null) {
            return;
        }

        double amount = readPositiveDouble(scanner, "Enter withdrawal amount: ");
        try {
            account.withdraw(amount);
            System.out.printf("Withdrawal successful. Remaining balance: %.2f%n", account.getCurrentBalance());
        } catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private static void handleCheckBalance(Scanner scanner) {
        System.out.println("--- Check Balance ---");
        BankAccount account = findAccountByNumber(scanner);
        if (account == null) {
            return;
        }
        System.out.println(account.getFormattedBalanceSummary());
    }

    private static void handleTransactionHistory(Scanner scanner) {
        System.out.println("--- Transaction History ---");
        BankAccount account = findAccountByNumber(scanner);
        if (account == null) {
            return;
        }

        List<Transaction> transactions = account.getTransactions();
        if (transactions.isEmpty()) {
            System.out.println("No transactions found for this account.");
            return;
        }

        System.out.println("Date and Time         | Type       |     Amount |  Balance   | Description");
        System.out.println("--------------------------------------------------------------------------------");
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    private static void handleDisplayAllAccounts() {
        System.out.println("--- All Accounts ---");
        List<BankAccount> accounts = bank.getAccounts();
        if (accounts.isEmpty()) {
            System.out.println("No accounts have been created yet.");
            return;
        }

        System.out.printf("%-12s | %-20s | %-8s | %-12s | %-25s | %-12s | %s%n",
                "Account No", "Customer Name", "Type", "Mobile", "Email", "Balance", "Created On");
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------");
        for (BankAccount account : accounts) {
            System.out.println(account.getTableRow());
        }
    }

    private static void handleSearchAccount(Scanner scanner) {
        System.out.println("--- Search Account ---");
        BankAccount account = findAccountByNumber(scanner);
        if (account == null) {
            return;
        }
        System.out.println(account.getAccountDetails());
    }

    private static void handleDeleteAccount(Scanner scanner) {
        System.out.println("--- Delete Account ---");
        BankAccount account = findAccountByNumber(scanner);
        if (account == null) {
            return;
        }

        System.out.print("Are you sure you want to delete this account? (yes/no): ");
        String confirmation = scanner.nextLine().trim();
        if ("yes".equalsIgnoreCase(confirmation)) {
            if (bank.deleteAccount(account.getAccountNumber())) {
                System.out.println("Account deleted successfully.");
            } else {
                System.out.println("Unable to delete account.");
            }
        } else {
            System.out.println("Account deletion cancelled.");
        }
    }

    private static BankAccount findAccountByNumber(Scanner scanner) {
        String accountNumber = readNonEmptyString(scanner, "Enter account number: ");
        BankAccount account = bank.findAccount(accountNumber);
        if (account == null) {
            System.out.println("No account found with account number: " + accountNumber);
        }
        return account;
    }

    private static String readNonEmptyString(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (InputValidator.isValidName(input)) {
                return input;
            }
            System.out.println("Input cannot be empty. Please try again.");
        }
    }

    private static String readAccountType(Scanner scanner) {
        while (true) {
            System.out.print("Enter account type (Savings or Current): ");
            String input = scanner.nextLine().trim();
            if (InputValidator.isValidAccountType(input)) {
                return input.equalsIgnoreCase("Savings") ? "Savings" : "Current";
            }
            System.out.println("Invalid account type. Please enter Savings or Current.");
        }
    }

    private static String readMobileNumber(Scanner scanner) {
        while (true) {
            System.out.print("Enter mobile number (10 digits): ");
            String input = scanner.nextLine().trim();
            if (InputValidator.isValidMobileNumber(input)) {
                return input;
            }
            System.out.println("Invalid mobile number. Please enter exactly 10 digits.");
        }
    }

    private static String readEmail(Scanner scanner) {
        while (true) {
            System.out.print("Enter email address: ");
            String input = scanner.nextLine().trim();
            if (InputValidator.isValidEmail(input)) {
                return input;
            }
            System.out.println("Invalid email format. Please enter a valid email address.");
        }
    }

    private static double readPositiveDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = Double.parseDouble(scanner.nextLine().trim());
                if (InputValidator.isValidAmount(value)) {
                    return value;
                }
                System.out.println("Amount must be greater than zero. Please try again.");
            } catch (NumberFormatException ex) {
                System.out.println("Invalid number format. Please enter a valid amount.");
            }
        }
    }

    private static int readInteger(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }
}
