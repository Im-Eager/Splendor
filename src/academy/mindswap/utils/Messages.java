package academy.mindswap.utils;

public class Messages {




    //Colors utilized in our game
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";

    //Color to be used if we want to
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public static final String ANSI_RESET = "\u001B[0m";

    //PLAYER MESSAGES
    public static final String IMPOSSIBLE_MOVE = ANSI_RED + "Not possible babe... " + ANSI_RESET;
    public static final String CANT_RESERVE = ANSI_RED + "You can't reserve more than three cards " + ANSI_RESET;
    public static final String CANT_BUY = ANSI_RED + "Not enough credits " + ANSI_RESET;
    public static final String MORE_THAN_10 = ANSI_RED + "You can only have 10 Tokens  " + ANSI_RESET;
    public static final String TOKEN_STACK_LOW = ANSI_RED + "Token stack is low. You can only pick one token of this kind " + ANSI_RESET;
    public static final String GOLD_TOKEN_AWARDED = ANSI_YELLOW + "You just received a GOLDEN TOKEN " + ANSI_RESET;






    //psvm to test if colors are working, delete after
    public static void main(String[] args) {


        System.out.println(ANSI_BLACK + "This text is black!" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "This text is blue!" + ANSI_RESET);
        System.out.println(ANSI_WHITE + "This text is white!" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "This text is green!" + ANSI_RESET);
        System.out.println(ANSI_RED + "This text is red!" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "This text is yellow!" + ANSI_RESET);




        //2 Versions to chose

        //Version 1:
        System.err.println("          /$$$$$$            /$$                           /$$                            ");
        System.err.println("         /$$__  $$          | $$                          | $$                            ");
        System.err.println("        | $$  \\__/  /$$$$$$ | $$  /$$$$$$  /$$$$$$$   /$$$$$$$  /$$$$$$   /$$$$$$         ");
        System.err.println(" /$$$$$$|  $$$$$$  /$$__  $$| $$ /$$__  $$| $$__  $$ /$$__  $$ /$$__  $$ /$$__  $$ /$$$$$$");
        System.err.println("|______/ \\____  $$| $$  \\ $$| $$| $$$$$$$$| $$  \\ $$| $$  | $$| $$  \\ $$| $$  \\__/|______/");
        System.err.println("         /$$  \\ $$| $$  | $$| $$| $$_____/| $$  | $$| $$  | $$| $$  | $$| $$              ");
        System.err.println("        |  $$$$$$/| $$$$$$$/| $$|  $$$$$$$| $$  | $$|  $$$$$$$|  $$$$$$/| $$              ");
        System.err.println("         \\______/ | $$____/ |__/ \\_______/|__/  |__/ \\_______/ \\______/ |__/              ");
        System.err.println("                  | $$                                                                    ");
        System.err.println("                  | $$                                                                    ");
        System.err.println("                  |__/                                                                    ");


        //Version 2:
        System.err.println("  /$$$$$$            /$$                           /$$                   ");
        System.err.println(" /$$__  $$          | $$                          | $$                    ");
        System.err.println("| $$  \\__/  /$$$$$$ | $$  /$$$$$$  /$$$$$$$   /$$$$$$$  /$$$$$$   /$$$$$$ ");
        System.err.println("|  $$$$$$  /$$__  $$| $$ /$$__  $$| $$__  $$ /$$__  $$ /$$__  $$ /$$__  $$");
        System.err.println(" \\____  $$| $$  \\ $$| $$| $$$$$$$$| $$  \\ $$| $$  | $$| $$  \\ $$| $$  \\__/");
        System.err.println(" /$$  \\ $$| $$  | $$| $$| $$_____/| $$  | $$| $$  | $$| $$  | $$| $$      ");
        System.err.println("|  $$$$$$/| $$$$$$$/| $$|  $$$$$$$| $$  | $$|  $$$$$$$|  $$$$$$/| $$      ");
        System.err.println(" \\______/ | $$____/ |__/ \\_______/|__/  |__/ \\_______/ \\______/ |__/      ");
        System.err.println("          | $$                                                            ");
        System.err.println("          | $$                                                            ");
        System.err.println("          |__/                                                            ");

    }
}
