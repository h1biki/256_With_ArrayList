import java.util.ArrayList;

public class Buffer
{
    private int maxElements;
    private ArrayList<Multiple> multiple;

    public Buffer()
    {
        maxElements = 0;
        multiple = new ArrayList<Multiple>();
    }

    public Buffer(int maxElements, ArrayList<Multiple> multiple)
    {
        this.maxElements = maxElements;
        this.multiple =  multiple;
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

    public ArrayList<Multiple> multipleAdd(int first, int second, int third)
    {
        Multiple multiple1 = new Multiple(first);
        Multiple multiple2 = new Multiple(second);
        Multiple multiple3 = new Multiple(third);
        ArrayList<Multiple> multipleArrayList = new ArrayList<Multiple>();
        multipleArrayList.add(multiple1);
        multipleArrayList.add(multiple2);
        multipleArrayList.add(multiple3);
        return multipleArrayList;
    }







}
