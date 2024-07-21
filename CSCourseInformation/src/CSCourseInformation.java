import java.util.Scanner;
import java.io.*;

/**
 * Program Name: CSCourseInformation;
 * Student Name: Piyusha Satija;
 * Student ID: 200001855;
 * Date: Nov 6, 2023;
 * Course: CPSC 1150-004;
 * Compiler: IntelliJ IDEA 2023.2.1 OpenJDK
 */

public class CSCourseInformation
{
    /**
     * This program reads a hypothetical CS course text file that contains student names, IDs and final grades and asks the user if they would like to:
     * 1. Display a particular student's record.
     * 2. Calculate the final exam average.
     * 3. Find the student with the highest final exam score.
     * 4. Copy all records to another file.
     * The user can choose 5 to terminate the program.
     * @param args
     * @throws IOException
     */

    public static void main(String[] args) throws IOException
    {
        int choice;

        Scanner input = new Scanner(System.in);

        do
        {
            System.out.println("\nPlease enter: " +
                    "\n1. To display a specific student's record." +
                    "\n2. To calculate the final exam average." +
                    "\n3. To find a student with the highest score on the final exam." +
                    "\n4. To copy all the students' records to another file." +
                    "\n5. To terminate the program.");

            choice = input.nextInt();
            input.nextLine(); //only to read the enter character.

            switch (choice)
            {
                case 1: displayRecord(input);
                        break;

                case 2: System.out.printf("\nThe final exam average is: %.5f.\n", calculateAverage());
                        break;

                case 3: getHighest();
                        break;

                case 4: copyRecords(input);
                        break;

                case 5: System.out.println("\nThank you for using this program!");
                        break;
            }
        }
        while (choice != 5);
    }

    /**
     * This method will read the classList.txt file to find out if the entered name exists in the class. If so, the record is displayed on the console.
     * @param input is the scanner being transferred to this method. User is asked to input the student's name.
     * @throws IOException
     */
    
    public static void displayRecord(Scanner input) throws IOException
    {
        String studentInfo, studentID, finalGrade, inputName, name = "";

        Scanner infile = new Scanner(new File("classList.txt"));

        System.out.println("\nPlease enter the student's name you wish to search for: ");
        inputName = input.nextLine().toLowerCase();

        infile.nextLine();
        infile.nextLine();

        while (infile.hasNextLine())
        {
            studentInfo = infile.nextLine();

            if (studentInfo.toLowerCase().contains(inputName))
            {
                name = studentInfo.substring((studentInfo.indexOf(':') + 1), studentInfo.lastIndexOf(':'));
                studentID = studentInfo.substring(0, studentInfo.indexOf(':'));
                finalGrade = studentInfo.substring((studentInfo.lastIndexOf(':') + 1));

                System.out.println("\nStudent's name: " + name +
                        "\nStudent's ID: " + studentID +
                        "\nStudent's final grade: " + finalGrade);
            }
        }

        if (name.isEmpty())
        {
            System.out.println("\nNo match found.");
        }

        infile.close();
    }

    /**
     * This method will calculate the total final exam average in the classList.txt file.
     * @return the average of the class in the final.
     * @throws IOException
     */

    public static double calculateAverage() throws IOException
    {
        Scanner infile = new Scanner(new File("classList.txt"));

        String studentInfo, gradeStr;
        int count = 0;
        double finalGrade, average, sum = 0;

        infile.nextLine();
        infile.nextLine();

        while (infile.hasNextLine())
        {
            studentInfo = infile.nextLine();
            gradeStr = studentInfo.substring((studentInfo.lastIndexOf(':') + 1));
            finalGrade = Integer.parseInt(gradeStr);
            sum = sum + finalGrade;
            count++;
        }

        average = sum / count;

        infile.close();

        return average;
    }

    /**
     * This method will find the student with the highest final score in the classList.txt file and display that student's record.
     * @throws IOException
     */

    public static void getHighest() throws IOException
    {
        Scanner infile = new Scanner(new File("classList.txt"));

        String studentInfo, gradeStr, name = "", studentID = "";
        int finalGrade, highest = 0;

        infile.nextLine();
        infile.nextLine();

        while (infile.hasNextLine())
        {
            studentInfo = infile.nextLine();
            gradeStr = studentInfo.substring((studentInfo.lastIndexOf(':') + 1));
            finalGrade = Integer.parseInt(gradeStr);

            if (finalGrade > highest)
            {
                highest = finalGrade;
                name = studentInfo.substring((studentInfo.indexOf(':') + 1), studentInfo.lastIndexOf(':'));
                studentID = studentInfo.substring(0, studentInfo.indexOf(':'));
            }
        }

        System.out.println("\nThe following student has the highest grade: " +
                "\nStudent's name: " + name +
                "\nStudent's ID: " + studentID +
                "\nStudent's final grade: " + highest);

        infile.close();
    }

    /**
     * This method will copy all the records in classList.txt to another file.
     * @param input is the transfer of scanner to this method from the main.
     * User is asked to input the file name they wish to copy to.
     * @throws IOException
     */

    public static void copyRecords(Scanner input) throws IOException
    {
        String newFile, studentInfo;

        Scanner infile = new Scanner(new File("classList.txt"));

        System.out.println("\nPlease enter the name of the new file: ");
        newFile = input.nextLine();
        PrintWriter outfile = new PrintWriter(new File(newFile));

        while (infile.hasNextLine())
        {
            studentInfo = infile.nextLine();
            outfile.println(studentInfo);
        }

        System.out.println("\nThe students' records have been copied to " + newFile + ".");

        outfile.close();
        infile.close();
    }
}
