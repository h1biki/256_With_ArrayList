import java.util.Scanner;

public class Game
{
    private String playerName;
    private int gameTotal;


    public Game()
    {
        playerName = "";
        gameTotal = 0;

    }



    public void setGameTotal(int gameTotal)
    {
        this.gameTotal = gameTotal;
    }

    public void setPlayerName(String playerName)
    {
        this.playerName = playerName;
    }

    public int getGameTotal()
    {
        return gameTotal;
    }

    public String getPlayerName()
    {
        return playerName;
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
            System.out.println("Please Choose Your Option:");
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
                        Buffer leftBuffer = new Buffer();
                        Buffer rightBuffer = new Buffer();
                        Buffer multipleBuffer = new Buffer();
                        leftBuffer.setMaxElements(5);
                        rightBuffer.setMaxElements(3);
                        multipleBuffer.setMaxElements(3);
                        multipleBuffer.setMultiple(multipleBuffer.multipleAdd(2, 4, 8));
                        RNG rng = new RNG(0, 2);
                        int index = rng.generateNumber();
                        setGameTotal(multipleBuffer.getMultiple().get(index).getValue());










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
        System.out.println("*       Press 1 to start a new game        *");
        System.out.println("*     Press 2 to display the help menu     *");
        System.out.println("*             Press 3 to exit              *");
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
        System.out.println("Option 1 is to start a new name");
        System.out.println("OTHERWISE, THE GAME WON'T START AUTOMATICALLY FOR YOU");
        System.out.println("*******************************************************************");
        System.out.println("Option 2 is to display the help menu");
        System.out.println("THIS IS WHAT YOU ARE READING NOW ^-^");
        System.out.println("*******************************************************************");
        System.out.println("Option 3 is to exit the game");
        System.out.println("Good Bye!");
        System.out.println("===================================================================");
        System.out.println("");
    }

}

