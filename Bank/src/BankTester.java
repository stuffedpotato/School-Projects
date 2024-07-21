import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Program Name: BankTester;
 * Student Name: Piyusha Satija;
 * Student ID: 200001855;
 * Date: Feb 1, 2024;
 * Course: CPSC 1181-003;
 * Compiler: IntelliJ IDEA 2023.2.1 OpenJDK
 */

public class BankTester
{
    /**
     * This program tests the Bank class. The user has the following options that allow testing of all available methods in the Bank/BankAccount class:
     * 1. Add a new bank account.
     * 2. Get balance of a bank account.
     * 3. Deposit money to an existing bank account.
     * 4. Withdraw money from an existing bank account.
     * 5. Find the bank account with the highest balance.
     * 6. Find the bank account with the lowest balance.
     * 7. Print all bank accounts.
     * <code>scotia</code> is the object created of the Bank class and is used to manipulate the BankAccount objects.
     * @param args not used.
     */

    public static void main(String[] args)
    {
        int choice;
        String repeat;

        System.out.println("\nThis program tests the Bank Class.");

        Bank scotia = new Bank();

        do
        {
            Scanner input = new Scanner(System.in);

            System.out.println("\nPlease choose from the following options for Scotiabank: " +
                    "\n1. Add a new bank account." +
                    "\n2. Get balance of a bank account." +
                    "\n3. Deposit money to an existing bank account." +
                    "\n4. Withdraw money from an existing bank account." +
                    "\n5. Find the bank account with the highest balance." +
                    "\n6. Find the bank account with the lowest balance." +
                    "\n7. Print all bank accounts.");

            try
            {
                choice = input.nextInt();

                switch (choice)
                {
                    case 1: addAccountTest(scotia, input);
                        break;

                    case 2: getBalanceTest(scotia, input);
                        break;

                    case 3: depositTest(scotia, input);
                        break;

                    case 4: withdrawTest(scotia, input);
                        break;

                    case 5: System.out.println("\nThe bank account with the highest balance is: " + scotia.getMaximum());
                        input.nextLine();
                        break;

                    case 6: System.out.println("\nThe bank account with the lowest balance is: " + scotia.getMinimum());
                        input.nextLine();
                        break;

                    case 7: System.out.println("\n" + scotia);
                        input.nextLine();
                        break;

                    default: System.out.println("\nInvalid choice.");
                        input.nextLine();
                        break;
                }
            }

            catch (InputMismatchException exception)
            {
                System.out.println("\nERROR: Invalid input -- please enter the appropriate input type.");
                input.nextLine();
            }

            catch (BankAccountException | BankException exception)
            {
                System.out.println(exception.getMessage());
            }

            finally
            {
                System.out.println("\nWould you like to use this program again? Please enter \"Yes\" or \"No\": ");
                repeat = input.nextLine();
            }

        }
        while (!repeat.equalsIgnoreCase("no"));

        System.out.println("\nThank you for using this program!");
    }

    /**
     * This method tests the addAccount method of the Bank class.
     * User is asked to provide the 4-digit account number and initial balance (if any).
     * @param scotia is the Bank object to which the bank account will be added.
     * @param input is scanner object.
     */

    public static void addAccountTest(Bank scotia, Scanner input)
    {
        int accountNum;
        double balance;

        System.out.println("\nPlease enter the 4-digit account number: ");
        accountNum = input.nextInt();
        input.nextLine();

        System.out.println("\nWould you like to add a pre-existing balance to this account? Enter \"Yes\" or \"No\": ");

        if (input.nextLine().equalsIgnoreCase("yes"))
        {
            System.out.println("\nPlease enter the initial balance: ");

            balance = input.nextDouble();
            scotia.addAccount(new BankAccount(accountNum, balance));
            input.nextLine();
        }
        else
        {
            scotia.addAccount(new BankAccount(accountNum));
        }

        System.out.println("\nYour account has been successfully added to Scotiabank.");
    }

    /**
     * This method tests the getBalance method of the BankAccount and uses the find method of the Bank class to find the bank account first.
     * User is asked to provide the 4-digit account number.
     * @param scotia is the Bank object.
     * @param input is scanner object.
     */

    public static void getBalanceTest(Bank scotia, Scanner input)
    {
        int accountNum;
        BankAccount result;

        System.out.println("\nPlease enter the 4-digit account number: ");
        accountNum = input.nextInt();
        input.nextLine();

        result = scotia.find(accountNum);

        System.out.println("\nThe balance of account number " + accountNum + " is " + result.getBalance() + ".");
    }

    /**
     * This method tests the deposit method of the Bank/BankAccount class by searching the account using the find method of Bank class.
     * User is asked to provide the 4-digit account number and the amount they would like to add.
     * @param scotia is the Bank object.
     * @param input is scanner object.
     */

    public static void depositTest(Bank scotia, Scanner input)
    {
        int accountNum;
        double amount;

        System.out.println("\nPlease enter the 4-digit account number: ");
        accountNum = input.nextInt();

        System.out.println("\nPlease enter the amount you would like to deposit: ");
        amount = input.nextDouble();
        input.nextLine();

        scotia.deposit(accountNum, amount);

        System.out.println("\nThe amount has been successfully deposited to the account provided.");
    }

    /**
     * This method tests the withdraw method of the Bank/BankAccount class by searching the account using the find method of Bank class.
     * User is asked to provide the 4-digit account number and the amount they would like to withdraw.
     * @param scotia is the Bank object.
     * @param input is scanner object.
     */

    public static void withdrawTest(Bank scotia, Scanner input)
    {
        int accountNum;
        double amount;

        System.out.println("\nPlease enter the 4-digit account number: ");
        accountNum = input.nextInt();

        System.out.println("\nPlease enter the amount you would like to withdraw: ");
        amount = input.nextDouble();
        input.nextLine();

        scotia.withdraw(accountNum, amount);

        System.out.println("\nThe amount has been successfully withdrawn from the account provided.");
    }
}
