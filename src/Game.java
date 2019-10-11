import java.util.ArrayList;
import java.util.Scanner;

public class Game
{
    private String playerName;
    private int gameTotal;
    private ArrayList<Buffer> multipleList;

    public Game()
    {
        playerName = "";
        gameTotal = 0;
        multipleList = new ArrayList<Buffer>();
    }

    public void setGameTotal(int gameTotal)
    {
        this.gameTotal = gameTotal;
    }

    public void setPlayerName(String playerName)
    {
        this.playerName = playerName;
    }

    public void setMultipleList(ArrayList<Buffer> multipleList)
    {
        this.multipleList = multipleList;
    }

    public int getGameTotal()
    {
        return gameTotal;
    }

    public String getPlayerName()
    {
        return playerName;
    }

    public ArrayList<Buffer> getMultipleList()
    {
        return multipleList;
    }

    public String nameAssign()
    {
        System.out.println("Please Input Your Name: ");
        Scanner scanner = new Scanner(System.in);
        String userName = scanner.nextLine().trim();
        while (userName.length() > 10 || userName.length() < 3)
        {
            System.out.println("The player's name should contains more than 3 letters and less than 10 letters.");
            System.out.println("Please re-input the name: ");
            userName = scanner.nextLine().trim();
        }
        System.out.println("Gamer name assignment progress successfully finished.");
        System.out.println("Game will start shortly...");
        return userName;
    }

    /**
     * Method for starting the program
     *
     * @return void
     */
    public void startGame()
    {
        boolean isPlayerRegistered = false;//flag of whether human player's name has assigned
        String option = "";//select option from the main menu
        while (!option.equals("4"))//this loop keeps game running until player pressed 2
        {
            displayMenu();
            System.out.println("Please Choose Your Option: ");
            Scanner scanner = new Scanner(System.in);
            option = scanner.nextLine().trim();//receiving option from the player
            switch (option)
            {
                case "1":
                    setPlayerName(nameAssign());
                    isPlayerRegistered = true;//after name assignment then set flag to true
                    break;
                case "2":
                    if (isPlayerRegistered)//check flag
                    {
                        int gameWinTotal = gameWinTotalAssign();
                        while (gameWinTotal < 32 || gameWinTotal % 8 != 0)
                        {
                            System.out.println("The goal of game total should greater than 32 and is the multiple of 8");
                            System.out.println("Please input again.");
                            gameWinTotal = gameWinTotalAssign();
                        }
                        Buffer leftBuffer = new Buffer();
                        Buffer rightBuffer = new Buffer();
                        leftBuffer.setMaxElements(5);
                        rightBuffer.setMaxElements(3);
                        System.out.println("Reading external file...");
                        FileIO ioRead = new FileIO("multiples.txt");
                        multipleList = ioRead.contentSetProcess(ioRead.readFile(ioRead.getFileName()));
                        int bufferNo = 0;
                        for (Buffer each : multipleList)
                        {
                            System.out.println("Buffer: " + ++bufferNo);
                            for ( Multiple mul : each.getList()) {
                                System.out.print(mul.getValue() + " ");
                            }
                            System.out.println();
                        }
                        System.out.println("Please choose a Multiple set: ");
                        Scanner choose = new Scanner(System.in);
                        String chooseMultipleSet = choose.nextLine().trim();
                        Buffer tempMultiple = new Buffer();
                        boolean proceed = true;
                        do
                        {
                            switch (chooseMultipleSet)
                            {
                                case "1":
                                    tempMultiple.getList().add(0, multipleList.get(0).getList().get(0));
                                    tempMultiple.getList().add(1, multipleList.get(0).getList().get(1));
                                    tempMultiple.getList().add(2, multipleList.get(0).getList().get(2));
                                    break;
                                case "2":
                                    tempMultiple.getList().add(0, multipleList.get(1).getList().get(0));
                                    tempMultiple.getList().add(1, multipleList.get(1).getList().get(1));
                                    tempMultiple.getList().add(2, multipleList.get(1).getList().get(2));
                                    break;
                                case "3":
                                    tempMultiple.getList().add(0, multipleList.get(2).getList().get(0));
                                    tempMultiple.getList().add(1, multipleList.get(2).getList().get(1));
                                    tempMultiple.getList().add(2, multipleList.get(2).getList().get(2));
                                    break;
                                default:
                                    System.out.println("Please input a valid option!");
                                    proceed = false;
                                    break;
                            }
                        }
                        while(!proceed);

                        do
                        {
                            RNG rng = new RNG(0, 2);
                            int index = rng.generateNumber();
                            gameTotal = tempMultiple.getList().get(index).getValue();
                            displayNow(leftBuffer.getList(), gameTotal, rightBuffer.getList());
                            actionDisplay();
                            Scanner s = new Scanner(System.in);
                            String choice = s.nextLine().trim();//receiving option from the player
                            switch (choice)
                            {
                                case "1":
                                    mergeLeft(leftBuffer.getList());
                                    gameTotal = gameTotal*2;
                                    break;
                                case "2":
                                    mergeRight(rightBuffer.getList());
                                    gameTotal = gameTotal*2;
                                    break;
                                case "3":
                                    splitLeft(leftBuffer, gameTotal);
                                    int rngSplitL = rng.generateNumber();
                                    gameTotal = tempMultiple.getList().get(rngSplitL).getValue();
                                    break;
                                case "4":
                                    splitRight(rightBuffer, gameTotal);
                                    int rngSplitR = rng.generateNumber();
                                    gameTotal = tempMultiple.getList().get(rngSplitR).getValue();
                                    break;
                                default:
                                    System.out.println("Please input a valid option!");
                                    break;
                            }
                        }while(gameTotal < gameWinTotal);
                        judgeWin(leftBuffer, rightBuffer, leftBuffer.getList(), rightBuffer.getList(), gameTotal, gameWinTotal);
                        isPlayerRegistered = false;//flag set to false, in order to start a fresh new game
                    } else
                    {
                        System.out.println("Please Assign Your Name Before Playing...");//if player not registered then ask to input name
                    }
                    break;
                case "3":
                    help();//display help menu
                    break;
                case "4":
                    System.out.println("Good Bye!");
                    break;
                default:
                    System.out.println("!!Invalid input, please input a valid option listed above!!");//player has a invalid input
                    break;
            }
        }
    }

    private void actionDisplay()
    {
        System.out.println("Press 1 to merge left.<<-");
        System.out.println("Press 2 to merge right.->>");
        System.out.println("Press 3 to split left.<-");
        System.out.println("Press 4 to split right.->");
    }

    /**
     * To display the main menu
     *
     * @return void
     */
    public void displayMenu()//main menu
    {
        System.out.println("");
        System.out.println("============================================");
        System.out.println("*  Welcome To The 256-With-ArrayList Game! *");
        System.out.println("*                                          *");
        System.out.println("* Please select from the following options *");
        System.out.println("*       Press 1 to assign a player         *");
        System.out.println("*        Press 2 to start the game         *");
        System.out.println("*     Press 3 to display the help menu     *");
        System.out.println("*             Press 4 to exit              *");
        System.out.println("============================================");
        System.out.println("");
    }

    /**
     * To display the help menu
     *
     * @return void
     */
    public void help()//menu 3, help menu
    {
        System.out.println("");
        System.out.println("-----------------------------HELP MENU-----------------------------");
        System.out.println("===================================================================");



        System.out.println("===================================================================");
        System.out.println("");
    }

    public int gameWinTotalAssign()
    {
        int userGameTotal = 0;
        Scanner s = new Scanner(System.in);
        System.out.println("Please set the game total you want reach: ");
        userGameTotal = s.nextInt();
        s.nextLine();
        return userGameTotal;
    }

    public ArrayList<Multiple> mergeLeft (ArrayList<Multiple> tempLeftBuffer)
    {
        if(checkMergeAvailability(tempLeftBuffer))
        {
            for (int i = 0; i < tempLeftBuffer.size(); i++)
            {
                if (gameTotal == tempLeftBuffer.get(i).getValue())
                {
                    gameTotal = gameTotal * 2;
                    tempLeftBuffer.remove(i);
                }
            }
        }
        else
        {
            System.out.println("Cannot merge because there is no capable element in buffer");
            System.out.println("Please try other options.");
        }
        return tempLeftBuffer;
    }

    public ArrayList<Multiple> mergeRight (ArrayList<Multiple> tempRightBuffer)
    {
        if(checkMergeAvailability(tempRightBuffer))
        {
            for (int i = 0; i < tempRightBuffer.size(); i++)
            {
                if (gameTotal == tempRightBuffer.get(i).getValue())
                {
                    gameTotal = gameTotal * 2;
                    tempRightBuffer.remove(i);
                }
            }
        }
        else
        {
            System.out.println("Cannot merge because there is no capable element in buffer");
        }
        return tempRightBuffer;
    }

    public ArrayList<Multiple> splitLeft (Buffer tempLeftBuffer, int tempGameTotal)
    {
        if (checkSplitAvailability(tempLeftBuffer))
        {
            tempLeftBuffer.addMultiple(tempGameTotal);
        }
        else
        {
            System.out.println("Cannot split because the buffer is full");
        }
        return tempLeftBuffer.getList();

    }

    public ArrayList<Multiple> splitRight (Buffer tempRightBuffer, int tempGameTotal)
    {
        if (checkSplitAvailability(tempRightBuffer))
        {
            tempRightBuffer.addMultiple(tempGameTotal);
        }
        else
        {
            System.out.println("Cannot split because the buffer is full");
        }
        return tempRightBuffer.getList();
    }

    public void displayNow(ArrayList<Multiple> tempLeftBuffer, int readGameTotal, ArrayList<Multiple> tempRightBuffer)
    {
        // left buffer
        System.out.println("");
        System.out.print("LEFT BUFFER: { ");
        for(Multiple s : tempLeftBuffer)
        {
            System.out.print(s.getValue() + " ");
        }
        System.out.print("}\n");
        System.out.println("");
        // game total
        System.out.println("GAME TOTAL: " + readGameTotal + "\n");
        // right buffer
        System.out.print("RIGHT BUFFER: { ");
        for(Multiple s : tempRightBuffer)
        {
            System.out.print(s.getValue() + " ");
        }
        System.out.print("}\n");

    }

    public void judgeWin(Buffer tempLeftBuffer, Buffer tempRightBuffer, ArrayList<Multiple> tempLeftArray, ArrayList<Multiple> tempRightArray, int tempGameTotal, int tempWinTotal)
    {
        boolean sizeOK = false;
        if(tempLeftArray.size() <  tempLeftBuffer.getMaxElements() && tempRightArray.size() <  tempRightBuffer.getMaxElements())
        {
            sizeOK = true;
        } else
        {
            sizeOK = false;
        }
        if(!sizeOK)
        {
            FileIO ioWrite = new FileIO("output.txt");
            ioWrite.writeFile(ioWrite.getFileName(), "Congratulations! " + getPlayerName() + " Win!");
        }
        else if (sizeOK && tempGameTotal == tempWinTotal)
        {
            FileIO ioWrite = new FileIO("output.txt");
            ioWrite.writeFile(ioWrite.getFileName(), "Sorry, " + getPlayerName() + " Lose...");
        }
    }


    public boolean checkMergeAvailability(ArrayList<Multiple> tempMultiple)
    {
        boolean status = false;
        for (Multiple each : tempMultiple)
        {
            if(each.getValue() == gameTotal)
            {
                status = true;
                break;
            }
            else
            {
                break;
            }
        }
        return status;
    }

    public boolean checkSplitAvailability(Buffer tempBuffer)
    {
        boolean status;
        if(tempBuffer.getList().size() > tempBuffer.getMaxElements())
        {
            status = false;
        }
        else
        {
            status = true;
        }
        return status;
    }
}

