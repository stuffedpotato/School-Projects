/**
 * Program Name: BankAccount;
 * Student Name: Piyusha Satija;
 * Student ID: 200001855;
 * Date: Feb 1, 2024;
 * Course: CPSC 1181-003;
 * Compiler: IntelliJ IDEA 2023.2.1 OpenJDK
 */

public class BankAccount
{
    private double balance;
    private int accountNumber;

    /**
     * This is the constructor for a bank account. User can add a 4-digit account number and the initial balance is set to 0.
     * @param anAccountNumber is the 4-digit account number user must enter.
     */

    public BankAccount(int anAccountNumber)
    {
        String acc = "" + anAccountNumber;

        if (acc.length() != 4)
        {
            throw new BankAccountException("\nERROR: Invalid number of digits - please only enter 4-digits for the account number.");
        }

        balance = 0;
        accountNumber = anAccountNumber;
    }

    /**
     * This is another constructor for a bank account. User can set an initial balance for this account.
     * @param anAccountNumber is the 4-digit account number user must enter.
     * @param initialBalance is the initial balance user is starting the account with.
     */

    public BankAccount(int anAccountNumber, double initialBalance)
    {
        String acc = "" + anAccountNumber;

        if (acc.length() != 4)
        {
            throw new BankAccountException("\nERROR: Invalid number of digits - please only enter 4-digits for the account number.");
        }

        if (initialBalance < 0)
        {
            throw new BankAccountException("\nERROR: Invalid initial balance - initial balance cannot be less than $0.");
        }

        balance = initialBalance;
        accountNumber = anAccountNumber;
    }

    /**
     * @return balance of a bank account object.
     */

    public double getBalance()
    {
        return balance;
    }

    /**
     * @return account number of a bank account object.
     */

    public int getAccountNumber()
    {
        return accountNumber;
    }

    /**
     * Lets user deposit an amount to their account.
     * @param amount is the amount user wants to deposit to the bank account object.
     */

    public void deposit(double amount)
    {
        balance += amount;
    }

    /**
     * Lets user withdraw an amount from their account.
     * @param amount is the amount user wants to withdraw from the bank account object.
     */

    public void withdraw(double amount)
    {
        balance -= amount;

        if (balance < 0)
        {
            throw new BankAccountException("\nERROR: Invalid balance - balance after withdrawal is less than $0.");
        }
    }

    @Override
    public String toString()
    {
        return "Account Number: " + accountNumber + "  balance: " + balance;
    }
}
