
/**
 * Class which performs basic validation for all inputs
 *
 * @author Mark Creado
 * @version 26 Jul 2019
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
     */
    public boolean stringLengthWithinRange(String value, int min, int max)
    {
        boolean withinRange = false;
        if ((value.trim().length() >= min) && (value.trim().length() <= max))
            withinRange = true;
        return withinRange;
    }
}
