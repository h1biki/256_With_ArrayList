import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The external file IO part of the 256-With-arrayList game.
 *
 * @author (Zixi Zhao, Student ID: 29977703)
 * @version (17 Oct 2019)
 */
public class FileIO
{
    private String fileName;

    /**
     * Default constructor for objects of class Game
     */
    public FileIO()
    {
        fileName = "";
    }

    /**
     * Non-default constructor for objects of class Game
     */
    public FileIO(String fileName)
    {
        this.fileName = fileName;
    }

    /**
     * Mutator of fileName
     *
     * @param  fileName  the new value of fileName
     * @return void
     */
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    /**
     * Accessor of fileName
     *
     * @return  fileName
     */
    public String getFileName()
    {
        return fileName;
    }

    /**
     * To write the external file
     *
     * @param filename the file name which you want to write
     * @param tempMessage the content you want to write in the file
     * @return    void
     */
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

    /**
     * To write the external file
     *
     * @param tempFileName the file name which you want to write
     * @return    the file content as a whole string
     */
    public String readFile(String tempFileName)
    {
        String contentSet = "";
        if (tempFileName.trim().length() > 0)
        {
            try
            {
                FileReader inputFile = new FileReader(tempFileName);
                try
                {
                    Scanner parser = new Scanner(inputFile);
                    while (parser.hasNextLine())
                    {
                        contentSet += parser.nextLine();
                        contentSet += "\n";
                    }
                }
                finally
                {
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

    /**
     * To process the file content string to separate and storage into ArrayList<Buffer>
     *
     * @param tempContentSet the file name which you want to write
     * @return    the file content as a whole string
     */
    public ArrayList<Buffer> contentSetProcess(String tempContentSet)
    {
        ArrayList<Buffer> buffers = new ArrayList<>();
        // lines
        String[] lines = tempContentSet.split("\n");
        // traverse lines
        for (String eachLine : lines )
        {
            Buffer buffer = new Buffer();
            // traverse line
            for (String each : eachLine.split(","))
            {
                int value;
                try
                {
                    value = Integer.parseInt(each);
                    buffer.addMultiple(value);
                } catch (Exception e)
                {
                    System.out.println("Parsing error");
                }
            }
            buffers.add(buffer);
        }
        return buffers;
    }
}
