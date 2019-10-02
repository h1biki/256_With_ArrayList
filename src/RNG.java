import java.util.Random;
/**
 * class RNG is the random number generator
 *
 * @author (Zixi Zhao)
 * @version (14-9-2019)
 */

public class RNG
{
    private int maximumValue;//the maximum bound of generate number
    private int minimumValue;//the minimum bound of generate number

    /**
     * Default constructor for objects of class RNG
     */
    public RNG ()
    {
        maximumValue = 0;
        minimumValue = 0;
    }

    /**
     * Non-default constructor for objects of class RNG
     */
    public RNG (int newMinimumValue, int newMaximumValue)
    {
        maximumValue = newMaximumValue;
        minimumValue = newMinimumValue;
    }

    /**
     * To genarate an integer random number, inclusive range from minimumValue to maximumValue
     *
     * @return    an integer random number
     */
    public int generateNumber()
    {
        Random random = new Random();//create a Random() object
        int randomNumber = minimumValue + random.nextInt(maximumValue - minimumValue + 1);
        return randomNumber;
    }

    /**
     * Accessor of maximumValue
     *
     * @return    maximumValue
     */
    public int getMaximumValue()
    {
        return maximumValue;
    }

    /**
     * Accessor of minimumValue
     *
     * @return    minimumValue
     */
    public int getMinimumValue()
    {
        return minimumValue;
    }

    /**
     * Mutator of maximumValue
     *
     * @param  newMaximumValue  the new value of maximumValue
     * @return    void
     */
    public void setMaximumValue(int newMaximumValue)
    {
        maximumValue = newMaximumValue;
    }

    /**
     * Mutator of minimumValue
     *
     * @param  int newMinimumValue  the new value of minimumValue
     * @return    void
     */
    public void setMinimumValue(int newMinimumValue)
    {
        minimumValue = newMinimumValue;
    }
}
