import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Program Name: CollegeTester;
 * Student Name: Piyusha Satija;
 * Student ID: 200001855;
 * Date: Feb 11, 2024;
 * Course: CPSC 1181-003;
 * Compiler: IntelliJ IDEA 2023.2.1 OpenJDK
 */

public class CollegeTester
{
    /**
     * This program tests the College class. The user has the following options that allow testing of all available methods in the College class:
     * 1. Add a student (addStudent).
     * 2. Search for a student (searchStudent).
     * 3. Remove a student (deleteStudent).
     * 4. Add course information for a student (addCourse).
     * 5. Access a student's login ID (getLoginID).
     * 6. Find the student with the highest GPA (getHighestGPA).
     * 7. Print all the students in Langara (toString).
     * <code>langara</code> is the object created of the College class and is used to manipulate the Student objects.
     * @param args not used.
     */

    public static void main(String[] args)
    {
        System.out.println("\nThis program tests the College class.");

        College langara = new College();
        Scanner input = new Scanner(System.in);

        int option;
        String choice;

        do
        {
            try
            {
                System.out.println("\nWhat would you like to do with this program? Choose an option below: " +
                        "\n1. Add a student (addStudent)." +
                        "\n2. Search for a student (searchStudent)." +
                        "\n3. Remove a student (deleteStudent)." +
                        "\n4. Add course information for a student (addCourse)." +
                        "\n5. Access a student's login ID (getLoginID)." +
                        "\n6. Find the student with the highest GPA (getHighestGPA)." +
                        "\n7. Get the tuition fees of a student (getTuitionFees)" +
                        "\n8. Print all the students in Langara (toString).\n");

                option = input.nextInt();
                input.nextLine();

                switch (option)
                {
                    case 1: testAddStudent(input, langara);
                        break;

                    case 2: testSearchStudent(input, langara);
                        break;

                    case 3: testDeleteStudent(input, langara);
                        break;

                    case 4: testAddCourse(input, langara);
                        input.nextLine();
                        break;

                    case 5: testGetLoginID(input, langara);
                        break;

                    case 6: System.out.println("\nStudent with the highest GPA: " + langara.getHighestGPA());
                        break;

                    case 7: testGetTuitionFees(input, langara);
                        break;

                    case 8: System.out.println(langara);
                        break;
                }
            }

            catch (InputMismatchException exception)
            {
                System.out.println("\nERROR: Please enter the appropriate input type.");
                input.nextLine();
            }

            catch (InvalidInputException exception)
            {
                System.out.println(exception.getMessage());
            }

            finally
            {
                System.out.println("\nWould you like to use this program again? (enter \"Yes\" or \"No\"): ");
                choice = input.nextLine().toLowerCase();
            }
        }
        while (!choice.equals("no"));

        System.out.println("\nThank you for using this program!");
    }

    /**
     * This method tests the addStudent method of the College class.
     * The user is asked tif they wish to enter a domestic, international or a graduate student. They are asked to enter
     * the name and address of the student and then passed to the appropriate student type method.
     * @param input is the Scanner object so that the user can enter their choice, full name and address of the student.
     * @param langara is the College object where the student will be added.
     */

    public static void testAddStudent(Scanner input, College langara)
    {
        String name, address;
        int option;

        System.out.println("\nWhat kind of student would you like to add? Please choose below: " +
                "\n1. Domestic Student" +
                "\n2. International Student" +
                "\n3. Graduate Student");

        option = input.nextInt();
        input.nextLine();

        System.out.println("\nPlease enter the student's full name (FirstName LastName): ");
        name = input.nextLine().trim();

        System.out.println("\nPlease enter the student's address (free format): ");
        address = input.nextLine().trim();

        switch (option)
        {
            case 1: System.out.println("\nStudent added?: " + addStudent(langara, name, address));
                break;

            case 2: System.out.println("\nStudent added?: " + addInternationalStudent(input, langara, name, address));
                break;

            case 3: System.out.println("\nStudent added?: " + addGraduateStudent(input, langara, name, address));
                break;
        }
    }

    /**
     * This method adds a domestic student to the college.
     * @param langara is the College object where the student will be addedd.
     * @param name is the name of the student.
     * @param address is the address of the student
     * @return true if the student has been added or false if the input name is in wrong format and student is not added.
     */

    public static boolean addStudent(College langara, String name, String address)
    {
        if (validInput(name))
        {
            name = name.substring(0, name.indexOf(' ')) + name.substring(name.lastIndexOf(' '));
            langara.addStudent(new Student(name, address));
            return true;
        }

        return false;
    }

    /**
     * This method adds an international student to the college.
     * @param input is the scanner so that user can enter the student's origin country.
     * @param langara is the College object where the student will be added.
     * @param name is the full name of the student.
     * @param address is the address of the student.
     * @return true if the student has been added or false if the input name is in wrong format and student is not added.
     */

    public static boolean addInternationalStudent(Scanner input, College langara, String name, String address)
    {
        String country;

        System.out.println("\nWhat is this student's country of origin?: ");
        country = input.nextLine();

        if (validInput(name))
        {
            name = name.substring(0, name.indexOf(' ')) + name.substring(name.lastIndexOf(' '));
            langara.addStudent(new InternationalStudent(name, address, country));
            return true;
        }

        return false;
    }

    /**
     * This method adds a graduate student to the college.
     * @param input is the scanner so that the user can enter the student's research topic and supervisor's name.
     * @param langara is the College object where the student will be added.
     * @param name is the full name of the student.
     * @param address is the address of the student.
     * @return true if the student has been added or false if the input name is in wrong format and student is not added.
     */

    public static boolean addGraduateStudent(Scanner input, College langara, String name, String address)
    {
        String research, supervisor;

        System.out.println("\nWhat is this student's topic of research?: ");
        research = input.nextLine();

        System.out.println("\nWhat is this student's supervisor's full name?: ");
        supervisor = input.nextLine();

        if (validInput(name))
        {
            name = name.substring(0, name.indexOf(' ')) + name.substring(name.lastIndexOf(' '));
            langara.addStudent(new GraduateStudent(name, address, research, supervisor));
            return true;
        }

        return false;
    }

    /**
     * This method tests the searchStudent method of the College class.
     * The user is asked to enter the student's ID number and if found, the student's entire information is displayed.
     * @param input is the Scanner object so that the user can enter student ID number.
     * @param langara is the College object where the student will be searched.
     */

    public static void testSearchStudent(Scanner input, College langara)
    {
        int studentID;

        System.out.println("\nPlease enter the student's ID number to search: ");
        studentID = input.nextInt();
        input.nextLine();

        if (validID(studentID))
        {
            Student result = langara.searchStudent(studentID);

            if (result != null)
            {
                System.out.println(result);
            }
            else
            {
                System.out.println("\nNo student exists with this student ID.");
            }
        }
    }

    /**
     * This method tests the deleteStudent method of the College class.
     * The user is asked to enter the student's ID number and if found, the student is deleted from the ArrayList.
     * @param input is the Scanner object so that the user can enter student ID number.
     * @param langara is the College object where the student will be removed from.
     */

    public static void testDeleteStudent(Scanner input, College langara)
    {
        int studentID;

        System.out.println("\nPlease enter the student's ID number to remove the student: ");
        studentID = input.nextInt();
        input.nextLine();

        if (validID(studentID))
        {
            System.out.println("\nStudent removed: " + langara.deleteStudent(studentID));
        }
    }

    /**
     * This method tests the addCourse method of the College class.
     * The user is asked to enter the student's ID number and if valid, the user is then asked to enter the credits and grade points earned for the course.
     * @param input is the Scanner object so that the user can enter student ID number, their credits and grade points earned.
     * @param langara is the College object where the student will be manipulated.
     */

    public static void testAddCourse(Scanner input, College langara)
    {
        int credits, studentID;
        double gradePoints;

        System.out.println("\nPlease enter the student's ID number to add courses: ");
        studentID = input.nextInt();
        input.nextLine();

        if (validID(studentID))
        {
            System.out.println("\nPlease enter the number of credits for this course: ");
            credits = input.nextInt();

            System.out.println("\nPlease enter the grade points earned for this course: ");
            gradePoints = input.nextDouble();

            if (langara.addCourse(studentID, credits, gradePoints))
            {
                System.out.println("\nCourse has been successfully recorded for this student.");
            }
            else
            {
                System.out.println("\nThe student does not exist in the system.");
            }
        }
    }

    /**
     * This method tests the getLoginID method of the College class.
     * The user is asked to enter the student's ID number and then the login ID is displayed.
     * @param input is the Scanner object so that the user can enter student ID number.
     * @param langara is the College object where the student exists.
     */

    public static void testGetLoginID(Scanner input, College langara)
    {
        int studentID;

        System.out.println("\nPlease enter the student's ID number to access their login ID: ");
        studentID = input.nextInt();
        input.nextLine();

        if (validID(studentID))
        {
            System.out.println(langara.getLoginID(studentID));
        }
    }

    /**
     * This method is used to access the tuition fees of a student.
     * @param input is the scanner so that the user can enter the student's ID number.
     * @param langara is the College object where the student exists.
     */

    public static void testGetTuitionFees(Scanner input, College langara)
    {
        int studentID;

        System.out.println("\nPlease enter the student's ID number: ");
        studentID = input.nextInt();
        input.nextLine();

        if (validID(studentID))
        {
            Student temp = langara.searchStudent(studentID);

            if (temp != null)
            {
                System.out.println("\nThis student's tuition fee is: " + temp.getTuitionFees());
            }
            else
            {
                System.out.println("\nNo student found.");
            }
        }
    }

    /**
     * This method tests the input for the student name. It ensures that the user has entered the student's name in FirstName LastName format.
     * @param name is the input name.
     * @return true if the input is in the correct format and false if not.
     */

    public static boolean validInput(String name)
    {
        int totalWords = 1;
        int indexSpace = name.indexOf(' ');

        while (indexSpace > -1)
        {
            name = name.substring(indexSpace + 1).trim();
            totalWords++;
            indexSpace = name.indexOf(' ');
        }

        if (totalWords == 2)
        {
            return true;
        }
        else
        {
            throw new InvalidInputException("\nERROR: Please enter the student name in this format only -- FirstName LastName.");
        }
    }

    /**
     * This method ensures that the student ID entered is valid. It checks that the entered number is bigger than/equal to the smallest student ID: 10000001.
     * @param studentID is the input student ID.
     * @return true if the student ID number is valid and false if not.
     */

    public static boolean validID(int studentID)
    {
        if (studentID >= 10000001)
        {
            return true;
        }
        else
        {
            throw new InvalidInputException("\nERROR: Invalid student ID number. Student ID number must be larger than/equal to 10000001.");
        }
    }
}