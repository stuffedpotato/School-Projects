/**
 * Program Name: GraduateStudent (extends Student);
 * Student Name: Piyusha Satija;
 * Student ID: 200001855;
 * Date: Feb 11, 2024;
 * Course: CPSC 1181-003;
 * Compiler: IntelliJ IDEA 2023.2.1 OpenJDK
 */

public class GraduateStudent extends Student
{
    private String research, supervisor;

    /**
     * This is the main constructor of the GraduateStudent class.
     * @param inputName is the name of the student.
     * @param inputAddress is the address of the student.
     * @param research is the research topic of the student.
     * @param supervisor is the name of the supervisor of the student.
     */

    public GraduateStudent(String inputName, String inputAddress, String research, String supervisor)
    {
        super(inputName, inputAddress);
        this.research = research;
        this.supervisor = supervisor;
    }

    /**
     * This method can be used to access the research topic of the student.
     * @return the research topic of the student.
     */

    public String getResearch()
    {
        return research;
    }

    /**
     * This method can be used to access the supervisor's name.
     * @return the supervisor's name.
     */

    public String getSupervisor()
    {
        return supervisor;
    }

    /**
     * This method is used to compare two Graduate Students (GS). It compares the name, address (from super), research and supervisor's name.
     * @param anObject is the object user wants to compare to.
     * @return true if equal and false if not.
     */

    @Override
    public boolean equals(Object anObject)
    {
        if (!super.equals(anObject))
        {
            return false;
        }

        GraduateStudent temp = (GraduateStudent) anObject;

        if (temp.research.equalsIgnoreCase(research) && temp.supervisor.equalsIgnoreCase(supervisor))
        {
            return true;
        }

        return false;
    }

    /**
     * This method is automatically called when the user wants to print a graduate student object.
     * @return the parameters returned by super in addition to research and supervisor of the student.
     */

    @Override
    public String toString()
    {
        return (super.toString() + "[Research Topic: " + research + " | Supervisor's Name: " + supervisor +  "]");
    }
}