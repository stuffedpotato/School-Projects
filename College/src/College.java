import java.util.ArrayList;

/**
 * Program Name: College;
 * Student Name: Piyusha Satija;
 * Student ID: 200001855;
 * Date: Feb 11, 2024;
 * Course: CPSC 1181-003;
 * Compiler: IntelliJ IDEA 2023.2.1 OpenJDK
 */

public class College
{
    ArrayList<Student> students;

    /**
     * This is the main constructor of this class. It initializes the <code>students</code> ArrayList to hold Student objects.
     */

    public College()
    {
        students = new ArrayList<Student>();
    }

    /**
     * This method adds a student to the <code>students</code> ArrayList.
     * @param student is the student object created by the caller.
     */

    public void addStudent(Student student)
    {
        students.add(student);
    }

    /**
     * This method searches for a student in the College object.
     * @param studentID is the user entered student ID number.
     * @return the found student object to the caller or null if student does not exist.
     */

    public Student searchStudent(int studentID)
    {
        int index = 0;
        int tempStudentID;

        if (students == null || students.size() == 0)
        {
            return null;
        }

        while (index < students.size())
        {
            tempStudentID = students.get(index).getStudentNum();

            if (tempStudentID == studentID)
            {
                return students.get(index);
            }

            index++;
        }

        return null;
    }

    /**
     * This method deletes a student from the College object.
     * @param studentID is the user entered student ID number.
     * @return true if the student was removed and false if not.
     */

    public boolean deleteStudent(int studentID)
    {
        Student toRemove = searchStudent(studentID);

        if (toRemove != null)
        {
            students.remove(toRemove);
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * This method adds a course to a student in the College object.
     * @param studentID is the user entered student ID number.
     * @param credits is the total number of credits for the course.
     * @param gradePoints is the total grade points earned for the course.
     * @return true if the course was added and false if not.
     */

    public boolean addCourse(int studentID, int credits, double gradePoints)
    {
        Student toAdd = searchStudent(studentID);

        if (toAdd != null)
        {
            toAdd.addCourse(credits, gradePoints);
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * This method returns the student's login ID information.
     * @param studentID is the user entered student ID number.
     * @return the login ID of the student as a string.
     */

    public String getLoginID(int studentID)
    {
        Student toSearch = searchStudent(studentID);

        if (toSearch != null)
        {
            return ("\nLogin ID: " + toSearch.getLoginId());
        }
        else
        {
            return ("\nThis student is not in the system.");
        }
    }

    /**
     * This method returns the student with the highest GPA in the College.
     * @return the student object.
     */

    public Student getHighestGPA()
    {
        int index = 0;

        if (students == null || students.size() == 0)
        {
            return null;
        }

        Student largest = students.get(0);

        while (index < students.size())
        {
            if (students.get(index).calculateGPA() > largest.calculateGPA())
            {
                largest = students.get(index);
            }

            index++;
        }

        return largest;
    }

    /**
     * This method is the default method that is called anytime the user wants to print all the students in the college.
     * @return the <code>students</code> arrayList as a String.
     */

    @Override
    public String toString()
    {
        return (students.toString());
    }
}
