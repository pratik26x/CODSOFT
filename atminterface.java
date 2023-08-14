/**
 * This Java program simulates an advanced ATM interface for interacting with a user's bank account.
 * It includes classes for BankAccount and ATM, allowing users to check their balance, make deposits,
 * and perform withdrawals while providing clear feedback. The code is well-documented and user-friendly.
 */
import java.util.Scanner;

/**
 * Represents a bank account with balance and transaction methods.
 */
class BankAccount 
{
    private double balance;

    /**
     * Initializes the bank account with the given initial balance.
     *
     * @param initialBalance The initial account balance.
     */
    public BankAccount(double initialBalance) 
    {
        balance = initialBalance;
    }

    /**
     * Returns the current account balance.
     *
     * @return The current balance.
     */
    public double getBalance() 
    {
        return balance;
    }

    /**
     * Deposits the specified amount into the account.
     *
     * @param amount The amount to deposit.
     */
    public void deposit(double amount) 
    {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit of $" + amount + " successful.");
        } 
        else 
        {
            System.out.println("Invalid deposit amount. Please enter a positive value.");
        }
    }

    /**
     * Attempts to withdraw the specified amount from the account.
     *
     * @param amount The amount to withdraw.
     * @return True if withdrawal is successful, false if unsuccessful.
     */
    public boolean withdraw(double amount) 
    {
        if (amount > 0 && amount <= balance) 
        {
            balance -= amount;
            System.out.println("Withdrawal of $" + amount + " successful.");
            return true;
        } 
        else 
        {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
            return false;
        }
    }
}

/**
 * Represents an ATM interface for interacting with a bank account.
 */
class ATM 
{
    private BankAccount account;

    /**
     * Initializes the ATM with the given bank account.
     *
     * @param account The user's bank account.
     */
    public ATM(BankAccount account) 
    {
        this.account = account;
    }

    /**
     * Displays the main menu options to the user.
     */
    public void displayMenu() 
    {
        System.out.println("\n*** Welcome to Our Advanced ATM ***");
        System.out.println("1. Check Balance");
        System.out.println("2. Make a Deposit");
        System.out.println("3. Perform a Withdrawal");
        System.out.println("4. Exit");
    }

    /**
     * Runs the ATM interaction loop.
     */
    public void run() 
    {
        Scanner scanner = new Scanner(System.in);
        int choice;

        System.out.println("Welcome to Our Advanced ATM!");
        do 
        {
            displayMenu();
            System.out.print("Please select an option: ");
            choice = scanner.nextInt();

            switch (choice) 
            {
                case 1:
                    System.out.println("Current Balance: $" + account.getBalance());
                    break;
                case 2:
                    System.out.print("Enter deposit amount: $");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: $");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 4:
                    System.out.println("Thank you for choosing our ATM. Have a great day!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        } 
        while (choice != 4);

        scanner.close();    // Close the scanner
    }
    
}

/**
 * The main class to initiate the ATM simulation.
 */
public class atminterface 
{
    public static void main(String[] args) 
    {
        BankAccount userAccount = new BankAccount(1500.0); // Initial balance
        ATM atm = new ATM(userAccount);
        atm.run();
    }
    
}