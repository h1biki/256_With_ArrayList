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

    public static ArrayList<Multiple> readMultiple()
    {
        Multiple multiple1 = new Multiple(2);
        Multiple multiple2 = new Multiple(4);
        Multiple multiple3 = new Multiple(8);
        ArrayList<Multiple> multipleArrayList = new ArrayList<Multiple>();
        multipleArrayList.add(multiple1);
        multipleArrayList.add(multiple2);
        multipleArrayList.add(multiple3);
        return multipleArrayList;
    }








}
