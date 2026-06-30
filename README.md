# Bank-Management-System-Java
Java 17 console-based Bank Management System using OOP, Collections, and Exception Handling.

## Project Overview

This is a beginner-friendly console-based Bank Management System built using Core Java. It demonstrates object-oriented design, encapsulation, inheritance, collections, exception handling, and a menu-driven user interface.

## Folder Structure

- `Main.java` - Application entry point and menu controller.
- `Bank.java` - Manages creation, search, display, and deletion of accounts.
- `BankAccount.java` - Abstract base class for bank account data and transaction operations.
- `SavingsAccount.java` - Concrete account type for savings accounts.
- `CurrentAccount.java` - Concrete account type for current accounts.
- `Transaction.java` - Represents transaction history entries.
- `AccountNumberGenerator.java` - Generates unique account numbers.
- `InputValidator.java` - Validates user input for names, email, mobile, and amounts.

## Class Explanations

### `Main`
- Displays the main menu.
- Reads user choices and routes operations.
- Handles account creation, deposits, withdrawals, balance checks, transaction history, account listing, search, and deletion.
- Uses helper methods to validate input and prevent invalid actions.

### `Bank`
- Holds a list of `BankAccount` objects.
- Creates new accounts using account type.
- Finds accounts by number.
- Deletes accounts.
- Provides a read-only list of accounts.

### `BankAccount`
- Abstract class with common account fields: number, name, mobile, email, balance, creation date, and transactions.
- Implements deposit and withdrawal logic.
- Records each operation in transaction history.
- Formats output for balance summary, account details, and account table rows.

### `SavingsAccount` and `CurrentAccount`
- Concrete subclasses of `BankAccount`.
- Provide the `getAccountType()` implementation.

### `Transaction`
- Represents a single account transaction.
- Stores timestamp, type, amount, resulting balance, and description.
- Formats history entries for console output.

### `AccountNumberGenerator`
- Generates unique sequential account numbers.
- Uses a static synchronized method to guarantee uniqueness.

### `InputValidator`
- Validates customer name, mobile number, email, account type, and amount.
- Uses regex patterns for email and mobile formats.

## Important Methods

### `Main` helper methods
- `handleCreateAccount(Scanner)` - Creates a new account after validating input.
- `handleDeposit(Scanner)` - Deposits money into an account.
- `handleWithdraw(Scanner)` - Withdraws money with balance checks.
- `handleCheckBalance(Scanner)` - Displays name, account number, type, and balance.
- `handleTransactionHistory(Scanner)` - Prints the transaction history.
- `handleDisplayAllAccounts()` - Prints all accounts in a formatted table.
- `handleSearchAccount(Scanner)` - Searches and prints account details.
- `handleDeleteAccount(Scanner)` - Deletes an account after confirmation.

### `BankAccount` transaction methods
- `deposit(double)` - Validates amount, updates balance, and records a transaction.
- `withdraw(double)` - Validates amount and balance, updates balance, and records a transaction.

### `InputValidator`
- `isValidName(String)` - Ensures the name is not empty.
- `isValidMobileNumber(String)` - Ensures a 10-digit numeric mobile number.
- `isValidEmail(String)` - Ensures basic email format.
- `isValidAmount(double)` - Ensures an amount greater than zero.
- `isValidAccountType(String)` - Limits account type to Savings or Current.

## Sample Input and Output

Example flow:

```
=====================================
        BANK MANAGEMENT SYSTEM       
=====================================
1. Create Account
2. Deposit Money
3. Withdraw Money
4. Check Balance
5. Transaction History
6. Display All Accounts
7. Search Account
8. Delete Account
9. Exit

Enter your choice: 1
--- Create New Account ---
Enter customer name: Alice Patel
Enter account type (Savings or Current): Savings
Enter mobile number (10 digits): 9876543210
Enter email address: alice@example.com
Enter initial deposit amount: 1000
Account successfully created.
Account Number: 1000000000

Press Enter to continue...
```

Then selecting `4` and entering the account number displays the balance summary.

## Compile and Run

Open a terminal in the project folder and run:

```bash
javac *.java
java Main
```

If you use Windows PowerShell, the same commands apply.

## Future Enhancements

- Add transfer money between accounts.
- Add file-based persistence to save/load accounts.
- Add admin login and PIN-based authentication.
- Add interest calculation for savings accounts.
- Export transaction history to text files.
- Add fixed deposit account support.
