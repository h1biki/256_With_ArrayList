/**
 * Class which performs basic validation for all inputs
 *
 * @author Zixi Zhao, Student ID: 29977703
 * @version 17 Oct 2019
 */
public class Validation
{
    /**
     * Constructor for objects of class Validation
     */
    public Validation()
    {
    }

    /**
     * Method to check if the string is blank
     * @return boolean
     */
    public boolean stringIsBlank(String value)
    {
        boolean blank = true;
        if (value.trim().length() > 0)
            blank = false;
        return blank;
    }

    /**
     * Method to check if the string length is between a range
     * @return boolean
     */
    public boolean stringLengthWithinRange(String value, int min, int max)
    {
        boolean withinRange = false;
        if ((value.trim().length() >= min) && (value.trim().length() <= max))
            withinRange = true;
        return withinRange;
    }
}