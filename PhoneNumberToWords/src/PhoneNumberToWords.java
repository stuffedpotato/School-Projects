import java.util.Scanner;
import java.io.*;

/**
 * Program Name: PhoneNumberToWords;
 * Student Name: Piyusha Satija;
 * Student ID: 200001855;
 * Date: Nov 15, 2023;
 * Course: CPSC 1150-004;
 * Compiler: IntelliJ IDEA 2023.2.1 OpenJDK
 */

public class PhoneNumberToWords
{

    /**
     * This program reads a 7-digit phone number and finds a word that can represent the number (as a mnemonic device).
     * User can input the phone number (containing digits between 2-9 only). If a word match is found, it is printed on the console and in the phonesText.txt file.
     * Each digit represents a group of alphabets (like it used to on older cell phones).
     * @param args
     * @throws IOException
     */

    public static void main(String[] args) throws IOException
    {
        String number, readWord, choice;
        boolean flag = false;

        Scanner input = new Scanner(System.in);

        Scanner infile;

        PrintWriter outfile = new PrintWriter(new File("phonesText.txt"));

        char[][] letters = {
                {'A', 'B', 'C'},
                {'D', 'E', 'F'},
                {'G', 'H', 'I'},
                {'J', 'K', 'L'},
                {'M', 'N', 'O'},
                {'P', 'Q', 'R', 'S'},
                {'T', 'U', 'V'},
                {'W', 'X', 'Y', 'Z'}
        };

        System.out.println("\nThis program translates a 7-digit phone number to words that can be used as a mnemonic device for the number.");

        do
        {
            infile = new Scanner(new File("words.txt"));

            System.out.println("\nPlease enter a 7-digit phone number: ");
            number = input.nextLine();

            if (verifyInput(number))
            {
                System.out.println("\nThe number " + number + " matches: ");
                outfile.println("\nThe number " + number + " matches: ");

                while (infile.hasNextLine())
                {
                    readWord = infile.nextLine();

                    if(checkWord(letters, readWord, number, outfile))
                    {
                        flag = true;
                    }
                }

                if (!flag)
                {
                    System.out.println("Sorry, no match found.");
                    outfile.println("Sorry, no match found.");
                }
            }

            flag = false;

            System.out.println("\nWould you like to use this program again? (Please enter \"Yes\" or \"No\"): ");
            choice = input.nextLine().toLowerCase();

        }
        while (choice.equals("yes"));

        System.out.println("\nThank you for using this program!");

        infile.close();
        outfile.close();
    }

    /**
     * This method verifies that the user input is a 7-digit number, and it does not contain 0 or 1.
     * @param number is the phone number user inputs.
     * @return a boolean value: True if the input is valid; False if the input is invalid.
     */

    public static boolean verifyInput(String number)
    {
        int index = 0;
        boolean flag = true;

        if (number.length() != 7)
        {
            System.out.println("\nERROR: Please enter a phone number with 7 digits.");
            flag = false;
        }

        while (index < number.length())
        {
            if (number.charAt(index) == '0' || number.charAt(index) == '1')
            {
                System.out.println("\nERROR: Please only enter a phone number including the digits 2-9 AND excluding 0-1.");
                flag = false;
                break;
            }

            index++;
        }

        return flag;
    }

    /**
     * This method checks if any of the words in the words.txt file are a match for user input number. It will also print the matches to the console and copy to phonesText.txt file.
     * It reads the word from the .txt file, makes the phone number combination for the word and checks if it matches with what user has input.
     * @param letters is the multidimensional array that contains the alphabets. Each number/digit can be calculated by (index + 2).
     * @param readWord is the word that is read from the words.txt file.
     * @param number is the phone number user inputs.
     * @param outfile is the output .txt file that the results will copy to in addition to being printed to the console.
     * @return a boolean value: True if a word match is found; False if not.
     * @throws IOException
     */

    public static boolean checkWord(char[][] letters, String readWord, String number, PrintWriter outfile) throws IOException
    {
        int row, column, index = 0;
        String found = "";
        boolean flag = false;

        while (index < readWord.length())
        {
            for (row = 0; row < letters.length; row++)
            {
                for (column = 0; column < letters[row].length; column++)
                {
                    if (letters[row][column] == readWord.charAt(index))
                    {
                            found = found + (row + 2);
                    }
                }
            }
            index++;
        }

        if (found.equals(number))
        {
            System.out.println(readWord);
            outfile.println(readWord);
            flag = true;
        }

        return flag;
    }
}
