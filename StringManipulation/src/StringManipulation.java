import java.util.Scanner;

/**
 * Program Name: StringManipulation;
 * Student Name: Piyusha Satija;
 * Student ID: 200001855;
 * Date: Oct 18, 2023;
 * Course: CPSC 1150-004;
 * Compiler: IntelliJ IDEA 2023.2.1 OpenJDK
 */

public class StringManipulation
{
    /**
     * This program asks the user to input a sentence and can:
     * 1. Display the number of words in the sentence.
     * 2. Display the number of vowels in the sentence.
     * 3. Display the word with the most number of characters.
     * 4. Display the word with the most vowels.
     * User can also choose to input a new statement and do these operations and choose to terminate eventually.
     */

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        System.out.println("\nPlease enter a sentence: ");
        String sentence = input.nextLine().trim();

        String choice;

        do
        {
            System.out.println("\nPlease enter: " +
                    "\n1. To display the number of words in the sentence." +
                    "\n2. To display the number of vowel characters in the sentence." +
                    "\n3. To display the word with the most number of characters." +
                    "\n4. To display the word with the most vowels." +
                    "\n5. To enter a new statement." +
                    "\n6. To terminate the program.");

            choice = input.nextLine();

            switch (choice)
            {
                case "1":
                    System.out.println("The number of words in \"" + sentence + "\"" + " are " + numberOfWords(sentence) + ".");
                    break;

                case "2":
                    numberOfVowels(sentence);
                    break;

                case "3":
                    largestWord(sentence);
                    break;

                case "4":
                    mostVowels(sentence);
                    break;

                case "5":
                    System.out.println("Please enter a new sentence: ");
                    sentence = input.nextLine().trim();
                    break;

                case "6":
                    System.out.println("\nThank you for using this program.");
                    break;
            }
        }
        while (!choice.equals("6"));
    }

    /**
     * This method will commence when the user chooses option 1. It will compute the total number of words in the sentence.
     * @param sentence is the string that user has input.
     * @return count that is the integer for total number of words.
     * precondition: numberOfWords is called in the main method.
     * postcondition: count (total number of words) is printed for the user.
     */

    public static int numberOfWords(String sentence)
    {
        int count = 1;
        int indexOfSpace = sentence.indexOf(" ");

        while (indexOfSpace > -1)
        {
            sentence = sentence.substring(indexOfSpace + 1).trim();
            indexOfSpace = sentence.indexOf(" ");
            count++;
        }

        return count;
    }

    /**
     * This method will commence when the user chooses option 2. It will compute the total number for each vowel in the sentence.
     * @param sentence is the string that user has input.
     * precondition: numberOfVowels is called in the main method.
     * postcondition: total number for each vowel is printed in this method.
     */

    public static void numberOfVowels(String sentence)
    {
        String lowerCaseSentence = sentence.toLowerCase();
        int vowelA = 0, vowelE = 0, vowelI = 0, vowelO = 0, vowelU = 0, index;

        for (index = 0; index < lowerCaseSentence.length(); index++)
        {
            if (lowerCaseSentence.charAt(index) == 'a')
            {
                vowelA++;
            } else if (lowerCaseSentence.charAt(index) == 'e')
            {
                vowelE++;
            } else if (lowerCaseSentence.charAt(index) == 'i')
            {
                vowelI++;
            } else if (lowerCaseSentence.charAt(index) == 'o')
            {
                vowelO++;
            } else if (lowerCaseSentence.charAt(index) == 'u')
            {
                vowelU++;
            }
        }

        System.out.println("\nThe sentence \"" + sentence + "\" has: " +
                "\n" + vowelA + " a's." +
                "\n" + vowelE + " e's." +
                "\n" + vowelI + " i's." +
                "\n" + vowelO + " o's." +
                "\n" + vowelU + " u's.");
    }

    /**
     * This method will commence when the user chooses option 3. It will find the largest word in the sentence.
     * @param sentence is the string that user has input.
     * precondition: largestWord is called in the main method.
     * postcondition: the largest word in the sentence will be printed in this method.
     */

    public static void largestWord(String sentence)
    {
        int index = 0, startIndex = 0;
        String newSentence = sentence + " ";
        String largestString = "";
        String newString;

        do
        {
            if (newSentence.charAt(index) == ' ')
            {
                newString = newSentence.substring(startIndex, index).trim();

                if (newString.length() > largestString.length())
                {
                    largestString = newString;
                }

                startIndex = index;
            }
            index++;
        }
        while (index < newSentence.length());

        System.out.println("\nThe word with most characters in \"" + sentence + "\" is " + largestString + ".");
        return;
    }

    /**
     * This method will commence if the user chooses option 4. It finds the word with the most number of vowels.
     * @param sentence is the string that user has input.
     * precondition: mostVowels is called in the main method.
     * postcondition: the word with the most number of vowels is printed in this method.
     */

    public static void mostVowels(String sentence)
    {
        int currentVowels = 0, mostVowels = 0, index = 0, startIndex = 0, newIndex;
        String newSentence = sentence + " ";
        String wordMostVowels = "";
        String newString;

        do
        {
            if (newSentence.charAt(index) == ' ')
            {
                newString = newSentence.substring(startIndex, index).trim().toLowerCase();

                for (newIndex = 0; newIndex < newString.length(); newIndex++)
                {
                    if (newString.charAt(newIndex) == 'a' || newString.charAt(newIndex) == 'e' || newString.charAt(newIndex) == 'i' || newString.charAt(newIndex) == 'o' || newString.charAt(newIndex) == 'u')
                    {
                        currentVowels++;
                    }
                }

                if (currentVowels > mostVowels)
                {
                    mostVowels = currentVowels;
                    wordMostVowels = newString;
                }

                startIndex = index;
            }

            index++;
            currentVowels = 0;
        }
        while (index < newSentence.length());

        if (mostVowels == 0)
        {
            System.out.println("\nThere are no vowels in this sentence.");
        }
        else
        {
            System.out.println("\nThe word with most number of vowels in \"" + sentence + "\" is " + wordMostVowels + ".");
        }

        return;
    }
}