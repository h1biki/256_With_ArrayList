import java.util.ArrayList;

public class Buffer
{
    private int maxElements;
    private ArrayList<Multiple> multiple;

    public Buffer()
    {
        maxElements = 0;
        multiple = new ArrayList<>();
    }

    public Buffer(int maxElements, ArrayList<Multiple> multiple)
    {
        this.maxElements = 0;
        this.multiple = new ArrayList<>();
    }

    public void setMaxElements(int maxElements)
    {
        this.maxElements = maxElements;
    }

    public void setMultiple(ArrayList<Multiple> multiple)
    {
        this.multiple = multiple;
    }

    public int getMaxElements()
    {
        return maxElements;
    }

    public ArrayList<Multiple> getMultiple()
    {
        return multiple;
    }
}
