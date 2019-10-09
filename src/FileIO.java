import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileIO
{
    private String fileName;

    public FileIO()
    {
        fileName = "";
    }
    public FileIO(String fileName)
    {
        this.fileName = fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getFileName()
    {
        return fileName;
    }

    public void writeFile(String filename, String tempMessage)
    {
        if (filename.trim().length() > 0)
        {
            try {
                PrintWriter outputFile = new PrintWriter(filename);
                outputFile.println(tempMessage);
                outputFile.close();
            }
            catch (IOException exception) {
                System.out.println("File read ERROR");
            }
        }
        else
        {
            System.out.println("Unexpected ERROR");
        }
    }

    public String readFile(String tempFileName)
    {
        String contentSet = "";
        if (tempFileName.trim().length() > 0)
        {
            try {
                FileReader inputFile = new FileReader(tempFileName);
                try {
                        Scanner parser = new Scanner(inputFile);
                        while (parser.hasNextLine())
                        {
                           contentSet += parser.nextLine();
                        }
                } finally {
                    System.out.println("Reading finished... Now closing file");
                    inputFile.close();
                }
            }
            catch (FileNotFoundException exception)
            {
                System.out.println(tempFileName + " not found");
            }
            catch (IOException exception)
            {
                System.out.println("Unexpected I/O exception occurs");
            }
        }
        else
        {
            System.out.println("Unexpected ERROR");
        }
        return contentSet;
    }

    public ArrayList<Buffer> contentSetProcess(String tempContentSet)
    {
        ArrayList<Buffer> tempMultipleList= new ArrayList<Buffer>();
        String[] buffer = tempContentSet.split(",");
        Multiple set0index0 = new Multiple(Integer.parseInt(buffer[0]));
        Multiple set0index1 = new Multiple(Integer.parseInt(buffer[1]));
        Multiple set0index2 = new Multiple(Integer.parseInt(buffer[2]));
        Multiple set1index0 = new Multiple(Integer.parseInt(buffer[3]));
        Multiple set1index1 = new Multiple(Integer.parseInt(buffer[4]));
        Multiple set1index2 = new Multiple(Integer.parseInt(buffer[5]));
        Multiple set2index0 = new Multiple(Integer.parseInt(buffer[6]));
        Multiple set2index1 = new Multiple(Integer.parseInt(buffer[7]));
        Multiple set2index2 = new Multiple(Integer.parseInt(buffer[8]));
        tempMultipleList.get(0).getList().add(0, set0index0);
        tempMultipleList.get(0).getList().add(1, set0index1);
        tempMultipleList.get(0).getList().add(2, set0index2);
        tempMultipleList.get(1).getList().add(0, set1index0);
        tempMultipleList.get(1).getList().add(1, set1index1);
        tempMultipleList.get(1).getList().add(2, set1index2);
        tempMultipleList.get(2).getList().add(0, set2index0);
        tempMultipleList.get(2).getList().add(1, set2index1);
        tempMultipleList.get(2).getList().add(2, set2index2);
        return tempMultipleList;
    }

}
