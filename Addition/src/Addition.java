import java.util.Scanner;

/**
 * Program Name: Addition;
 * Student Name: Piyusha Satija;
 * Student ID: 200001855;
 * Date: Nov 22, 2023;
 * Course: CPSC 1150-004;
 * Compiler: IntelliJ IDEA 2023.2.1 OpenJDK
 */

public class Addition
{

    /**
     * This program can add two positive integers of up to 40 digits. The user is asked to enter both numbers and the sum is displayed on the console.
     * User can choose to repeat or end the program by entering "Yes" or "No".
     * @param args
     */

    public static void main(String[] args)
    {
        String stringNum1, stringNum2, stringSum, choice;
        int index;

        Scanner input = new Scanner(System.in);

        System.out.println("\n** This program can add two positive integers with up to 40 digits. **");

        do
        {
            System.out.println("\nPlease enter a positive integer number with up to 40 digits: ");
            stringNum1 = input.nextLine().trim();

            System.out.println("\nPlease enter another positive integer number with up to 40 digits: ");
            stringNum2 = input.nextLine().trim();

            if (verifyInput(stringNum1, stringNum2))
            {
                if (stringNum1.length() > stringNum2.length())
                {
                    stringSum = sum(stringNum1, stringNum2);
                }
                else
                {
                    stringSum = sum(stringNum2, stringNum1);
                }

                stringNum1 = formatNum(stringNum1);
                stringNum2 = formatNum(stringNum2);
                stringSum = formatNum(stringSum);

                System.out.println();

                for (index = 0; index < stringSum.length() - stringNum1.length(); index++)
                {
                    System.out.print(" ");
                }
                System.out.print(stringNum1 + " +" + "\n");

                for (index = 0; index < stringSum.length() - stringNum2.length(); index++)
                {
                    System.out.print(" ");
                }
                System.out.print(stringNum2 + "\n");

                for (index = 0; index < stringSum.length(); index++)
                {
                    System.out.print("-");
                }
                System.out.println("\n" + stringSum);
            }
            System.out.println("\nWould you like to run this program again? (Please enter \"Yes\" or \"No\"): ");
            choice = input.nextLine().toLowerCase().trim();
        }
        while(choice.equals("yes"));

        System.out.println("\nThank you for using this program!");
    }

    /**
     * This boolean method will verify if both entered numbers only contain up to 40 numerical characters (and only positive integers).
     * @param stringNum1 is the first number input.
     * @param stringNum2 is the second number input.
     * @return True if the inputs are correct and False if the inputs are incorrect.
     */

    public static boolean verifyInput(String stringNum1, String stringNum2)
    {
        int index = 0;
        boolean flag = true;

        if (stringNum1.length() > 40 || stringNum2.length() > 40)
        {
            System.out.println("\nERROR: Please only enter up to 40 digits.");
            flag = false;
        }

        while (index < stringNum1.length())
        {
            if (!Character.isDigit(stringNum1.charAt(index)))
            {
                System.out.println("\nERROR: Please only enter numerical characters (no negative numbers).");
                return false;
            }
            index++;
        }

        index = 0;

        while (index < stringNum2.length())
        {
            if (!Character.isDigit(stringNum2.charAt(index)))
            {
                System.out.println("\nERROR: Please only enter numerical characters (no negative numbers).");
                return false;
            }
            index++;
        }

        return flag;
    }

    /**
     * This method will add both the numbers (after verification) and return the sum.
     * @param stringNum1 is the input number that has more digits.
     * @param stringNum2 is the input number that has fewer digits. If both numbers have the same number of digits, the order is then irrelevant.
     * @return sum as a string.
     */

    public static String sum(String stringNum1, String stringNum2)
    {
        String stringSum = "", tempSum;
        int num1, num2 = 0, index1 = stringNum1.length() - 1, index2 = stringNum2.length() - 1, carry = 0, sum;

        while (index1 >= 0)
        {
            num1 = Integer.parseInt(String.valueOf(stringNum1.charAt(index1)));

            if (index2 >= 0)
            {
                num2 = Integer.parseInt(String.valueOf(stringNum2.charAt(index2)));
                index2--;
            }

            sum = num1 + num2 + carry;
            tempSum = "" + sum;

            if (tempSum.length() == 2)
            {
                stringSum = tempSum.charAt(1) + stringSum;
                carry = 1;
            }

            else
            {
                stringSum = tempSum + stringSum;
                carry = 0;
            }

            num2 = 0;
            index1--;
        }

        if (carry != 0)
        {
            stringSum = carry + stringSum;
        }

        return stringSum;
    }

    /**
     * This method will take any integer number and format it so that a comma is placed after every third digit from the right.
     * @param stringNum is the number passed by main that needs formatting (adds commas).
     * @return the formatted number.
     */

    public static String formatNum(String stringNum)
    {
        int index = stringNum.length();
        String format = "";

        while (index >= 0)
        {
            if ((index - 3) > 0)
            {
                format = "," + stringNum.substring((index - 3), index) + format;
                index = index - 3;
            }
            else
            {
                format = stringNum.substring(0, index) + format;
                break;
            }
        }

        return format;
    }
}
