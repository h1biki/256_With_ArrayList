import java.io.*;
import java.util.ArrayList;
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
                           contentSet += "\n";
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
        ArrayList<Buffer> buffers = new ArrayList<>();

        // lines
        String[] lines = tempContentSet.split("\n");

        // traverse lines
        for (String eachLine : lines ) {
            Buffer buffer = new Buffer();
            // traverse line
            for (String each : eachLine.split(",")) {
                int value;
                try {
                    value = Integer.parseInt(each);
                    buffer.addMultiple(value);
                } catch (Exception e) {
                    System.out.println("Parsing error");
                }
            }
            buffers.add(buffer);
        }
        return buffers;
    }
}
