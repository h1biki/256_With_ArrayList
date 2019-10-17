import java.util.ArrayList;

/**
 * The buffer part of the 256-With-arrayList game.
 *
 * @author (Zixi Zhao, Student ID: 29977703)
 * @version (17 Oct 2019)
 */
public class Buffer
{
    private int maxElements;
    private ArrayList<Multiple> list;

    /**
     * Default constructor for objects of class Game
     */
    public Buffer()
    {
        maxElements = 0;
        list = new ArrayList<Multiple>();
    }

    /**
     * Non-default constructor for objects of class Game
     */
    public Buffer(int maxElements, ArrayList<Multiple> list)
    {
        this.maxElements = maxElements;
        this.list =  list;
    }

    /**
     * too add the multiple elements to ArrayList<Multiple> list
     *
     * @param  mulValue  the multiple elements want to add
     * @return void
     */
    public void addMultiple(int mulValue)
    {
        list.add(new Multiple(mulValue));
    }

    /**
     * Mutator of maxElements
     *
     * @param  maxElements  the new value of maxElements
     * @return void
     */
    public void setMaxElements(int maxElements)
    {
        this.maxElements = maxElements;
    }

    /**
     * Mutator of list
     *
     * @param  list  the new value of list
     * @return void
     */
    public void setList(ArrayList<Multiple> list)
    {
        this.list = list;
    }

    /**
     * Accessor of maxElements
     *
     * @return  maxElements
     */
    public int getMaxElements()
    {
        return maxElements;
    }

    /**
     * Accessor of list
     *
     * @return  list
     */
    public ArrayList<Multiple> getList()
    {
        return list;
    }
}
