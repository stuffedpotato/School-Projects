/**
 * Program Name: InternationalStudent (extends Student);
 * Student Name: Piyusha Satija;
 * Student ID: 200001855;
 * Date: Feb 11, 2024;
 * Course: CPSC 1181-003;
 * Compiler: IntelliJ IDEA 2023.2.1 OpenJDK
 */

public class InternationalStudent extends Student
{
    private String country;
    private final double TUITION_FEE_INTL = 637.91;

    /**
     * This is the main constructor of the InternationalStudent class.
     * @param inputName is the name of the student.
     * @param inputAddress is the address of the student.
     * @param country is the country of origin of the student.
     */

    public InternationalStudent(String inputName, String inputAddress, String country)
    {
        super(inputName, inputAddress);
        this.country = country;
    }

    /**
     * This method can be used to access the country of the student.
     * @return the country of the student.
     */

    public String getCountry()
    {
        return country;
    }

    /**
     * This method can be used to access the total tuition fee of the student.
     * @return the tuition fee of the student.
     */

    @Override
    public double getTuitionFees()
    {
        return (TUITION_FEE_INTL * getTotalCredits());
    }

    /**
     * This method can be used to compare two international students (IS). It compares name, address and country of two IS objects.
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

        InternationalStudent temp = (InternationalStudent) anObject;

        if (temp.country.equalsIgnoreCase((country)))
        {
            return true;
        }

        return false;
    }

    /**
     * This method is automatically called when user tries to print an international student object.
     * @return string with parameters from the super class in addition to the country of the student.
     */

    @Override
    public String toString()
    {
        return (super.toString() + "[Country: " + country + "]");
    }
}