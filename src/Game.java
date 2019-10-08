//file IO WIP, multipleList selection WIP, hard level selection WIP, Help() WIP
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
                        Buffer leftBuffer = new Buffer();
                        Buffer rightBuffer = new Buffer();
                        leftBuffer.setMaxElements(5);
                        rightBuffer.setMaxElements(3);
                        RNG rng = new RNG(0, 2);
                        int index = rng.generateNumber();








                        setGameTotal(multipleList.get(0).getList().get(index).getValue());








                        do
                        {
                            displayNow(leftBuffer.getList(), gameTotal, rightBuffer.getList());
                            actionDisplay();
                            Scanner s = new Scanner(System.in);
                            String choice = s.nextLine().trim();//receiving option from the player
                            switch (choice)
                            {
                                case "1":
                                    mergeLeft(leftBuffer.getList());
                                    break;
                                case "2":
                                    mergeRight(rightBuffer.getList());
                                    break;
                                case "3":
                                    splitLeft(leftBuffer.getList());
                                    break;
                                case "4":
                                    splitRight(rightBuffer.getList());
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
        System.out.println("Press 1 to merge left.");
        System.out.println("Press 2 to merge right.");
        System.out.println("Press 3 to split left.");
        System.out.println("Press 4 to split right.");
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
        s.nextInt();
        s.nextLine();
        System.out.println("Please set the game total you want reach: ");
        userGameTotal = s.nextInt();
        return userGameTotal;
    }

    public int mergeLeft (ArrayList<Multiple> tempLeftBuffer)
    {
        for (int i = 0; i < tempLeftBuffer.size(); i++)
        {
            if (gameTotal == tempLeftBuffer.get(i).getValue())
            {
                gameTotal = gameTotal*2;
                tempLeftBuffer.remove(i);
            }
        }
        return gameTotal;
    }

    public int mergeRight (ArrayList<Multiple> tempRightBuffer)
    {
        for (int i = 0; i < tempRightBuffer.size(); i++)
        {
            if (gameTotal == tempRightBuffer.get(i).getValue())
            {
                gameTotal = gameTotal*2;
                tempRightBuffer.remove(i);
            }
        }
        return gameTotal;
    }

    public int splitLeft (ArrayList<Multiple> tempLeftBuffer)
    {

        return gameTotal;
    }

    public int splitRight (ArrayList<Multiple> tempLeftBuffer)
    {

        return gameTotal;
    }

    public void displayNow(ArrayList<Multiple> tempLeftBuffer, int readGameTotal, ArrayList<Multiple> tempRightBuffer)
    {
        for(Multiple s : tempLeftBuffer)
        {
            System.out.println(s);
        }

        System.out.println(readGameTotal);

        for(Multiple s : tempRightBuffer)
        {
            System.out.println(s);
        }
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
            System.out.println("Sorry! " + getPlayerName() + " Lose!");
        }
        if (sizeOK && tempGameTotal == tempWinTotal)
        {
            System.out.println("Congratulations! " + getPlayerName() + " Win!");
        }
    }
}

