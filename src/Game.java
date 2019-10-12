import java.util.ArrayList;

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
        Input input = new Input();
        Validation validation = new Validation();
        String userName = input.acceptStringInput("Please Input Your Name: ");
        //while (userName.length() > 10 || userName.length() < 3)
        while (validation.stringIsBlank(userName) || !validation.stringLengthWithinRange(userName, 3, 10))
        {
            System.out.println("The player's name should contains more than 3 letters and less than 10 letters.");
            userName = input.acceptStringInput("Please re-Input Your Name: ");
        }
        System.out.println("Gamer name assignment progress successfully finished.");
        System.out.println("Now you can choose to start game.");
        System.out.println("If not satisfied with the name you just typed, simply choose option 1 again to assign a new name!");
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
        Input input = new Input();
        String option = "";//select option from the main menu
        while (!option.equals("4"))//this loop keeps game running until player pressed 2
        {
            displayMenu();
            option = input.acceptStringInput("Please Choose Your Option: ");//receiving option from the player
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
                        while (gameWinTotal <= 32 || gameWinTotal % 8 != 0)
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
                            System.out.println("Multiple set " + ++bufferNo + ": ");
                            for(int i = 0; i<each.getList().size(); i++)
                            {
                                System.out.print(each.getList().get(i).getValue() + " ");
                            }
                            System.out.println();
                        }
                        String chooseMultipleSet = input.acceptStringInput("Please choose a Multiple set: ");
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
                            if (gameTotal == 0) {
                                int index = rng.generateNumber();
                                gameTotal = tempMultiple.getList().get(index).getValue();
                            }
                            displayNow(leftBuffer, gameTotal, rightBuffer);
                            actionDisplay();
                            String choice = input.acceptStringInput("Please choose an option: ");
                            switch (choice)
                            {
                                case "1":
                                    mergeLeft(leftBuffer);
                                    break;
                                case "2":
                                    mergeRight(rightBuffer);
                                    break;
                                case "3":
                                    splitLeft(leftBuffer, gameTotal);
                                    break;
                                case "4":
                                    splitRight(rightBuffer, gameTotal);
                                    break;
                                default:
                                    System.out.println("Please input a valid option!");
                                    break;
                            }
                        }while(gameTotal < gameWinTotal);
                        judgeWin(leftBuffer, rightBuffer, gameWinTotal);
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
        Input input = new Input();
        userGameTotal = input.acceptIntegerInput("Please set the game total you want reach: ");
        return userGameTotal;
    }

    public void mergeLeft (Buffer tempLeftBuffer)
    {
        if(checkMergeAvailability(tempLeftBuffer))
        {
            for (int i = 0; i < tempLeftBuffer.getList().size(); i++)
            {
                if (gameTotal == tempLeftBuffer.getList().get(i).getValue())
                {
                    gameTotal = gameTotal * 2;
                    tempLeftBuffer.getList().remove(i);
                    break;
                }
            }
        }
        else
        {
            System.out.println("Cannot merge because there is no capable element in buffer");
            System.out.println("Please try other options.");
        }
    }

    public void mergeRight (Buffer tempRightBuffer)
    {
        if(checkMergeAvailability(tempRightBuffer))
        {
            for (int i = 0; i < tempRightBuffer.getList().size(); i++)
            {
                if (gameTotal == tempRightBuffer.getList().get(i).getValue())
                {
                    gameTotal = gameTotal * 2;
                    tempRightBuffer.getList().remove(i);
                    break;
                }
            }
        }
        else
        {
            System.out.println("Cannot merge because there is no capable element in buffer");
        }
    }

    public void splitLeft (Buffer tempLeftBuffer, int tempGameTotal)
    {
        if (checkSplitAvailability(tempLeftBuffer))
        {
            tempLeftBuffer.addMultiple(tempGameTotal);
            gameTotal = 0;
        }
        else
        {
            System.out.println("Cannot split because the buffer is full");
        }
    }

    public void splitRight (Buffer tempRightBuffer, int tempGameTotal)
    {
        if (checkSplitAvailability(tempRightBuffer))
        {
            tempRightBuffer.addMultiple(tempGameTotal);
            gameTotal = 0;
        }
        else
        {
            System.out.println("Cannot split because the buffer is full");
        }
    }

    public void displayNow(Buffer tempLeftBuffer, int readGameTotal, Buffer tempRightBuffer)
    {
        // left buffer
        System.out.println("");
        System.out.print("LEFT BUFFER: { ");
        for(int i = 0; i < tempLeftBuffer.getList().size(); i++)
        {
            System.out.print(tempLeftBuffer.getList().get(i).getValue() + " ");
        }
        System.out.print("}\n");
        System.out.println("");
        // game total
        System.out.println("GAME TOTAL: " + readGameTotal + "\n");
        // right buffer
        System.out.print("RIGHT BUFFER: { ");
        for(int i = 0; i < tempRightBuffer.getList().size(); i++)
        {
            System.out.print(tempRightBuffer.getList().get(i).getValue() + " ");
        }
        System.out.print("}\n");
    }

    public void judgeWin(Buffer tempLeftBuffer, Buffer tempRightBuffer, int tempWinTotal)
    {
        boolean sizeOK = false;
        if(tempLeftBuffer.getList().size() <  tempLeftBuffer.getMaxElements() && tempRightBuffer.getList().size() <  tempRightBuffer.getMaxElements())
        {
            sizeOK = true;
        }
        else
        {
            sizeOK = false;
        }
        if(gameTotal >= tempWinTotal)
        {
            System.out.println("Congratulations! " + getPlayerName() + " Win!");
            System.out.println("Result saved to output.txt");
            FileIO ioWrite = new FileIO("output.txt");
            ioWrite.writeFile(ioWrite.getFileName(), "Congratulations! " + getPlayerName() + " Win!");
        }
        else if (!sizeOK && !checkMergeAvailability(tempLeftBuffer) && !checkMergeAvailability(tempRightBuffer))
        {
            System.out.println("Sorry, " + getPlayerName() + " Lose...");
            System.out.println("Result saved to output.txt");
            FileIO ioWrite = new FileIO("output.txt");
            ioWrite.writeFile(ioWrite.getFileName(), "Sorry, " + getPlayerName() + " Lose...");
        }
    }


    public boolean checkMergeAvailability(Buffer tempMultiple)
    {
        boolean status = false;
        for(int i = 0; i < tempMultiple.getList().size(); i++)
        {
            if(tempMultiple.getList().get(i).getValue() == gameTotal)
            {
                status = true;
                break;
            }
        }
        return status;
    }

    public boolean checkSplitAvailability(Buffer tempBuffer)
    {
        boolean status;
        if(tempBuffer.getList().size() >= tempBuffer.getMaxElements())
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