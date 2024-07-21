/**
 * Program Name: Student;
 * Student Name: Piyusha Satija;
 * Student ID: 200001855;
 * Date: Feb 11, 2024;
 * Course: CPSC 1181-003;
 * Compiler: IntelliJ IDEA 2023.2.1 OpenJDK
 */

public class Student
{
    private String name, address, loginId;
    private int studentNum, totalCredits;
    private double totalGradePoints;
    private final double TUITION_FEE = 107.62;
    private static int tempNum = 10000001;

    /**
     * This is the main constructor of this class.
     * @param inputName is the student name user has input.
     * @param inputAddress is the address of the student user has input.
     * By default, the total credits and total grade points are set to 0.
     * The constructor also invokes methods to create the student number & login ID.
     */

    public Student(String inputName, String inputAddress)
    {
        name = inputName;
        address = inputAddress;
        totalCredits = 0;
        totalGradePoints = 0;
        calculateStudentNum();
        createLoginID();
    }

    /**
     * This method calculates and initializes the student number when invoked by the constructor.
     */

    private void calculateStudentNum()
    {
        studentNum = tempNum;
        tempNum = tempNum + 1;
    }

    /**
     * This method creates and initializes the student's login ID when invoked by the constructor.
     */

    private void createLoginID()
    {
        String tempName = name.toLowerCase();
        String tempStudentNum = "" + studentNum;

        String lastName = tempName.substring(tempName.indexOf(' ') + 1);

        if (lastName.length() <= 3)
        {
            loginId = tempName.charAt(0) + lastName + tempStudentNum.substring(tempStudentNum.length() - 2);
        }
        else
        {
            loginId = tempName.charAt(0) + lastName.substring(0, 3) + tempStudentNum.substring(tempStudentNum.length() - 2);
        }
    }

    /**
     * This method is used as a getter for the name of the student.
     * @return the name of the student.
     */

    public String getName()
    {
        return name;
    }

    /**
     * This method is used as a getter for the address of the student.
     * @return the address of the student.
     */

    public String getAddress()
    {
        return address;
    }

    /**
     * This method is used as a getter for the student number.
     * @return student number.
     */

    public int getStudentNum()
    {
        return studentNum;
    }

    /**
     * This method is used as a getter for the login ID of the student.
     * @return login ID of the student.
     */

    public String getLoginId()
    {
        return loginId;
    }

    /**
     * This method is used to add course information for the student.
     * @param credits is the total number of credits for the course.
     * @param gradePoints is the total number of grade points earned for the course.
     * The method then adds the input information to the pre-existing information to sum up the total number of credits and grade points earned thus far.
     */

    public void addCourse(int credits, double gradePoints)
    {
        totalCredits = totalCredits + credits;
        totalGradePoints = totalGradePoints + (gradePoints * credits);
    }

    /**
     * This method calculates the total GPA for the student using the total credits and grade points earned.
     * @return total GPA.
     */

    public double calculateGPA()
    {
        double gpa;

        if (totalCredits == 0 && totalGradePoints == 0)
        {
            return 0.00;
        }

        gpa = totalGradePoints / totalCredits;
        gpa = (int)(gpa * 100);
        gpa = gpa / 100;

        return gpa;
    }

    /**
     * This method can be accessed to get the total tuition fee based on the total number of credits.
     * @return total tuition fee.
     */

    public double getTuitionFees()
    {
        return (TUITION_FEE * totalCredits);
    }

    /**
     * This method can be used to access the total number of credits logged for a student.
     * @return the total number of credits.
     */

    public int getTotalCredits()
    {
        return totalCredits;
    }

    /**
     * This method checks if two objects of Student are equal by comparing their name and address.
     * @param anObject is the object user wants to compare to.
     * @return true if equal and false if not.
     */

    @Override
    public boolean equals(Object anObject)
    {
        if (anObject == null)
        {
            return false;
        }

        if (getClass() != anObject.getClass())
        {
            return false;
        }

        Student temp = (Student) anObject;

        if (temp.name.equalsIgnoreCase(name) && temp.address.equalsIgnoreCase(address))
        {
            return true;
        }

        return false;
    }

    /**
     * This method prints the student object's components: name, address, student number, login ID & total GPA
     * @return a string of all the above noted components.
     */

    @Override
    public String toString()
    {
        return ("\n" + getClass().getName() + "[Name: " + getName() +
                " | Address: " + getAddress() +
                " | Student Number: " + getStudentNum() +
                " | Login ID: " + getLoginId() +
                " | GPA: " + calculateGPA() + "]");
    }
}