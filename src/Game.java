import java.util.ArrayList;

/**
 * The core part of the 256-With-arrayList game.
 *
 * @author (Zixi Zhao, Student ID: 29977703)
 * @version (17 Oct 2019)
 */
public class Game
{
    private String playerName;
    private int gameTotal;
    private ArrayList<Buffer> multipleList;

    /**
     * Default constructor for objects of class Game
     */
    public Game()
    {
        playerName = "";
        gameTotal = 0;
        multipleList = new ArrayList<Buffer>();
    }

    /**
     * Non-default constructor for objects of class Game
     */
    public void Game(String playerName, int gameTotal, ArrayList<Buffer> multipleList)
    {
        this.playerName = playerName;
        this.gameTotal = gameTotal;
        this.multipleList = multipleList;
    }

    /**
     * Mutator of gameTotal
     *
     * @param  gameTotal  the new value of gameTotal
     * @return void
     */
    public void setGameTotal(int gameTotal)
    {
        this.gameTotal = gameTotal;
    }

    /**
     * Mutator of playerName
     *
     * @param  playerName  the new value of playerName
     * @return void
     */
    public void setPlayerName(String playerName)
    {
        this.playerName = playerName;
    }

    /**
     * Mutator of multipleList
     *
     * @param  multipleList  the new value of multipleList
     * @return void
     */
    public void setMultipleList(ArrayList<Buffer> multipleList)
    {
        this.multipleList = multipleList;
    }

    /**
     * Accessor of gameTotal
     *
     * @return  gameTotal
     */
    public int getGameTotal()
    {
        return gameTotal;
    }

    /**
     * Accessor of playerName
     *
     * @return  playerName
     */
    public String getPlayerName()
    {
        return playerName;
    }

    /**
     * Accessor of multipleList
     *
     * @return  multipleList
     */
    public ArrayList<Buffer> getMultipleList()
    {
        return multipleList;
    }

    /**
     * To assign a valid name to the player
     *
     * @return    the player name
     */
    public String nameAssign()
    {
        Input input = new Input();
        Validation validation = new Validation();
        String userName = input.acceptStringInput("Please Input Your Name: ");
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
                                    proceed = true;
                                    break;
                                case "2":
                                    tempMultiple.getList().add(0, multipleList.get(1).getList().get(0));
                                    tempMultiple.getList().add(1, multipleList.get(1).getList().get(1));
                                    tempMultiple.getList().add(2, multipleList.get(1).getList().get(2));
                                    proceed = true;
                                    break;
                                case "3":
                                    tempMultiple.getList().add(0, multipleList.get(2).getList().get(0));
                                    tempMultiple.getList().add(1, multipleList.get(2).getList().get(1));
                                    tempMultiple.getList().add(2, multipleList.get(2).getList().get(2));
                                    proceed = true;
                                    break;
                                default:
                                    System.out.println("Please input a valid option!");
                                    proceed = false;
                                    chooseMultipleSet = input.acceptStringInput("Please choose a Multiple set: ");
                                    break;
                            }
                        }
                        while(!proceed);
                        displayLevel();
                        String chooseLevel = input.acceptStringInput("Please select the game hard level: ");
                        boolean levelFlag = true;
                        do
                        {
                            switch (chooseLevel)
                            {
                                case "1":
                                   leftBuffer.setMaxElements(7);
                                   rightBuffer.setMaxElements(5);
                                    levelFlag = true;
                                    break;
                                case "2":
                                    leftBuffer.setMaxElements(5);
                                    rightBuffer.setMaxElements(3);
                                    levelFlag = true;
                                    break;
                                case "3":
                                    leftBuffer.setMaxElements(4);
                                    rightBuffer.setMaxElements(2);
                                    levelFlag = true;
                                    break;
                                default:
                                    System.out.println("Please select a valid game level!");
                                    levelFlag = false;
                                    chooseLevel = input.acceptStringInput("Please select the game hard level: ");
                                    break;
                            }
                        }
                        while(!levelFlag);
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

    /**
     * To display the action menu
     *
     * @return void
     */
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
        System.out.println("=============================HELP MENU=============================");
        System.out.println("");
        System.out.println("The game sequence is as follows:\n" +
                "1. The game starts by registering a player to play the game.\n" +
                "2. The player is prompted to enter the game total upto which the game will be played. The game total must be greater than 32 and a multiple of 8.\n" +
                "3. The player is then prompted to select from the possible list of multiple with which to play the game. (These have been read from the file).\n" +
                "4. Both the Arraylist buffers (b1 and b2) are empty.\n" +
                "5. The game then provides a random multiple to the player which is stored in the game total.\n" +
                "6. The player can then perform the following actions:\n" +
                "=> Split Right (->)\n" +
                "This allows the player to add the game total (t) number to the right arraylist " +
                "buffer (b2)\n" +
                "A new random multiple is then generated and put in the game total (t)\n" +
                "=> Merge Right (->>)\n" +
                "This allows the player to merge the number in the game total box with a matching number in any position in the right arraylist buffer. \nThe total is then put in the game total box and the number is removed from the arraylist.\n" +
                "After the total is added, the game total (t) now shows the new number. \nNo new random number is generated and the game goes back to point 6 above to allow the player to continue.\n" +
                "=> Split Left (<-)\n" +
                "This allows the player to add the game total (t) number to the left arraylist buffer (b1)\n A new random multiple is then generated and put in the game total (t)\n" +
                "=> Merge Left (<<-)\n" +
                "This allows the player to merge the number in the game total box with a matching number in any position in the left arraylist buffer. \nThe total is then put in the game total box and the number is removed from the arraylist.\n" +
                "After the total is added, the game total (t) now shows the new number. No new random number is generated and the game goes back to point 6 above to allow the player to continue\nThe game ends when either of the following occur:\n" +
                "1. The game total (t) reaches the total of whatever the user entered in point 2 above or higher.\n" +
                "2. The arraylist buffers (b1 and b2) have reached the maximum size limit allowed (depending on the difficulty level)\n and no more numbers can be stored and none of the existing numbers can be merged to the game total (t).\n" +
                "At the end of the game, the program will write the final outcome to the file “outcome.txt” \nwhich will show the player name and the highest score achieved.\n" +
                "The following are some of the requirements for the game:\n" +
                "1. Player name must be between 3 and 10 characters, cannot be blank and cannot contain only spaces.\n" +
                "2. The arraylist buffers will not allow more elements to be stored beyond the assigned size limit (defined by the difficulty level).\n");
        System.out.println("===================================================================");
        System.out.println("");
    }

    /**
     * To display the level selection menu
     *
     * @return void
     */
    public void displayLevel()
    {
        System.out.println("HARD LEVEL SELECTION: ");
        System.out.println("1. Easy (Left Buffer size 7, Right Buffer size 5)");
        System.out.println("2. Normal (Left Buffer size 5, Right Buffer size 3)");
        System.out.println("3. Hard (Left Buffer size 4, Right Buffer size 2)");
    }

    /**
     * To assign the desired target gameTotal which the player want reach
     *
     * @return the desired target gameTotal
     */
    public int gameWinTotalAssign()
    {
        String userGameTotal = "";
        int intUserGameTotal = 0;
        Input input = new Input();
        do
        {
            userGameTotal = input.acceptStringInput("Please set the game total you want reach: ");
            if (isNumeric(userGameTotal) && userGameTotal.length() > 0)
            {
                intUserGameTotal = Integer.parseInt(userGameTotal);
            }
            else if (!isNumeric(userGameTotal))
            {
                System.out.println("Please a valid number! ");
            }
        } while (!isNumeric(userGameTotal));
        return intUserGameTotal;
    }

    /**
     * To check the input is a number
     *
     * @return boolean
     */
    public static boolean isNumeric(String str)
    {
        for(int i = 0; i < str.length(); i++)
        {
            int chr = str.charAt(i);
            if(chr<48 || chr>57)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * the action of merge left
     *
     * @param tempLeftBuffer the buffer which is currently interacting
     * @return void
     */
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

    /**
     * the action of merge right
     *
     * @param tempRightBuffer the buffer which is currently interacting
     * @return void
     */
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

    /**
     * the action of split left
     *
     * @param tempLeftBuffer the buffer which is currently interacting
     * @param tempGameTotal the current gameTotal
     * @return void
     */
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

    /**
     * the action of split right
     *
     * @param tempRightBuffer the buffer which is currently interacting
     * @param tempGameTotal the current gameTotal
     * @return void
     */
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

    /**
     * to display the current game status, include both side buffers and the game total value
     *
     * @param tempLeftBuffer the buffer which is currently interacting
     * @param tempRightBuffer the buffer which is currently interacting
     * @param readGameTotal the current gameTotal
     * @return void
     */
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

    /**
     * to judge the win
     *
     * @param tempLeftBuffer the buffer which is currently interacting
     * @param tempRightBuffer the buffer which is currently interacting
     * @param tempWinTotal the target gameTotal the player want to reach
     * @return void
     */
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
            System.out.println("The game total has reached " + gameTotal +
                    ", which is equal to or more than the target game total." );
            System.out.println("Result saved to output.txt");
            FileIO ioWrite = new FileIO("output.txt");
            ioWrite.writeFile(ioWrite.getFileName(), "Congratulations! " + getPlayerName() + " Win!\nThe game total has reached " + gameTotal +
                    ", which is equal to or more than the target game total." );
        }
        else if (!sizeOK && !checkMergeAvailability(tempLeftBuffer) && !checkMergeAvailability(tempRightBuffer))
        {
            System.out.println("Sorry, " + getPlayerName() + " Lose...");
            System.out.println("Result saved to output.txt");
            FileIO ioWrite = new FileIO("output.txt");
            ioWrite.writeFile(ioWrite.getFileName(), "Sorry, " + getPlayerName() + " Lose...");
        }
    }


    /**
     * to check whether the buffer can be merge
     *
     * @param tempMultiple the buffer which is currently interacting
     * @return boolean
     */
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

    /**
     * to check whether the buffer can be split to
     *
     * @param tempBuffer the buffer which is currently interacting
     * @return boolean
     */
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