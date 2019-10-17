/**
 * The multiple part of the 256-With-arrayList game.
 *
 * @author (Zixi Zhao, Student ID: 29977703)
 * @version (17 Oct 2019)
 */
public class Multiple
{
    private int value;

    /**
     * Default constructor for objects of class Game
     */
    public Multiple()
    {
        value = 0;
    }

    /**
     * Non-default constructor for objects of class Game
     */
    public Multiple(int value)
    {
        this.value = value;
    }

    /**
     * Mutator of value
     *
     * @param  value  the new value of value
     * @return void
     */
    public void setValue(int value)
    {
        this.value = value;
    }

    /**
     * Accessor of value
     *
     * @return  value
     */
    public int getValue()
    {
        return value;
    }
}
