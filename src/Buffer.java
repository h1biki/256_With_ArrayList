import java.util.ArrayList;

public class Buffer
{
    private int maxElements;
    private ArrayList<Multiple> list;

    public Buffer()
    {
        maxElements = 0;
        list = new ArrayList<Multiple>();
    }

    public Buffer(int maxElements, ArrayList<Multiple> list)
    {
        this.maxElements = maxElements;
        this.list =  list;
    }

    public void addMultiple(int mulValue)
    {
        list.add(new Multiple(mulValue));
    }

    public void setMaxElements(int maxElements)
    {
        this.maxElements = maxElements;
    }

    public void setList(ArrayList<Multiple> list)
    {
        this.list = list;
    }

    public int getMaxElements()
    {
        return maxElements;
    }

    public ArrayList<Multiple> getList()
    {
        return list;
    }
}
