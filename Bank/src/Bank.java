import java.util.ArrayList;

/**
 * Program Name: Bank;
 * Student Name: Piyusha Satija;
 * Student ID: 200001855;
 * Date: Feb 1, 2024;
 * Course: CPSC 1181-003;
 * Compiler: IntelliJ IDEA 2023.2.1 OpenJDK
 */

public class Bank
{
    private ArrayList<BankAccount> accounts;

    /**
     * This is the main constructor of the Bank class -- it initializes the array list of bank accounts.
     */

    public Bank()
    {
        accounts = new ArrayList<BankAccount>();
    }

    /**
     * This method adds a bank account to the array list.
     * @param a is the bank account provided by the user to add to the bank.
     */

    public void addAccount(BankAccount a)
    {
        accounts.add(a);
    }

    /**
     * @return the total balance of all bank accounts.
     */

    public double getTotalBalance()
    {
        double total = 0;
        for(BankAccount a : accounts)
        {
            total = total + a.getBalance();
        }
        return total;
    }

    /**
     Counts the number of bank accounts whose balance is at least a given value.
     @param atLeast the balance required to count an account
     @return the number of accounts having least the given balance
     */

    public int count(double atLeast)
    {
        int matches = 0;

        for(BankAccount a : accounts)
        {
            if (a.getBalance() >= atLeast)
                matches++;
        }
        return matches;
    }

    /**
     Finds a bank account with a given number.
     @param accountNumber the number to find
     @return the account with the given number, or null if there is no such account
     */

    public BankAccount find(int accountNumber)
    {
        for(BankAccount a : accounts)
        {
            if (a.getAccountNumber() == accountNumber)
                return a;
        }

        throw new BankException("\nERROR: Account not found");
    }

    /**
     Gets the bank account with the largest balance.
     @return the account with the largest balance, or null if the bank has no accounts
     */

    public BankAccount getMaximum()
    {
        if (accounts.size() == 0)
        {
            throw new BankException("\nERROR: No bank accounts exist in this bank.");
        }

        BankAccount largest = accounts.get(0);

        for (int i = 1; i < accounts.size(); i++)
        {
            BankAccount a = accounts.get(i);
            if (a.getBalance() > largest.getBalance())
                largest = a;
        }
        return largest;
    }

    /**
     * This method gets the bank account with the lowest balance.
     * @return the account with the lowest balance (null if the bank has no bank accounts).
     */

    public BankAccount getMinimum()
    {
        if (accounts.size() == 0)
        {
            throw new BankException("\nERROR: No bank accounts exist in this bank.");
        }

        BankAccount smallest, temp;

        smallest = accounts.get(0);

        for (int i = 1; i < accounts.size(); i++)
        {
            temp = accounts.get(i);
            if (temp.getBalance() < smallest.getBalance())
                smallest = temp;
        }

        return smallest;
    }

    /**
     * This method finds the account number provided by the user and adds the provided amount to the bank account.
     * @param accountNum is the account number provided by the user.
     * @param amount is the amount provided by the user.
     */

    public void deposit(int accountNum, double amount)
    {
        if (accounts.size() == 0)
        {
            throw new BankException("\nERROR: No bank accounts exist in this bank.");
        }

        BankAccount result = find(accountNum);

        result.deposit(amount);
    }

    /**
     * This method finds the account number provided by the user and withdraws the provided amount from the bank account.
     * @param accountNum is the account number provided by the user.
     * @param amount is the amount provided by the user.
     */

    public void withdraw(int accountNum, double amount)
    {
        if (accounts.size() == 0)
        {
            throw new BankException("\nERROR: No bank accounts exist in this bank.");
        }

        BankAccount result = find(accountNum);

        result.withdraw(amount);
    }

    public String toString()
    {
        return "Bank " + accounts.toString();
    }
}
