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
    public static final String I_WIN_MOTHERFUCKERS = ANSI_YELLOW + "Fuck you all! I won! " + ANSI_RESET;
    public static final String KEEP_PLAYING = ANSI_YELLOW + "Nex player turn " + ANSI_RESET;

    public static final String WELCOME_MESSAGE1 = "  /$$$$$$            /$$                           /$$                   ";
    public static final String WELCOME_MESSAGE2 = "  /$$$$$$            /$$                           /$$                   ";
    public static final String WELCOME_MESSAGE3 = " /$$__  $$          | $$                          | $$                    ";
    public static final String WELCOME_MESSAGE4 = "| $$  \\__/  /$$$$$$ | $$  /$$$$$$  /$$$$$$$   /$$$$$$$  /$$$$$$   /$$$$$$ ";
    public static final String WELCOME_MESSAGE5 = "|  $$$$$$  /$$__  $$| $$ /$$__  $$| $$__  $$ /$$__  $$ /$$__  $$ /$$__  $$";
    public static final String WELCOME_MESSAGE6 = " \\____  $$| $$  \\ $$| $$| $$$$$$$$| $$  \\ $$| $$  | $$| $$  \\ $$| $$  \\__/";
    public static final String WELCOME_MESSAGE7 = " /$$  \\ $$| $$  | $$| $$| $$_____/| $$  | $$| $$  | $$| $$  | $$| $$      ";
    public static final String WELCOME_MESSAGE8 = "|  $$$$$$/| $$$$$$$/| $$|  $$$$$$$| $$  | $$|  $$$$$$$|  $$$$$$/| $$      ";
    public static final String WELCOME_MESSAGE9 = " \\______/ | $$____/ |__/ \\_______/|__/  |__/ \\_______/ \\______/ |__/      ";
    public static final String WELCOME_MESSAGE10 ="          | $$                                                            ";
    public static final String WELCOME_MESSAGE11 ="          | $$                                                            ";
    public static final String WELCOME_MESSAGE12 ="          |__/                                                            ";




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

        //Game Board
        System.err.println("                                        _____                        ____                      _ ");
        System.err.println("                                       / ____|                      |  _ \\                    | |");
        System.err.println("                                      | |  __  __ _ _ __ ___   ___  | |_) | ___   __ _ _ __ __| |");
        System.err.println("                                      | | |_ |/ _` | '_ ` _ \\ / _ \\ |  _ < / _ \\ / _` | '__/ _` |");
        System.err.println("                                      | |__| | (_| | | | | | |  __/ | |_) | (_) | (_| | | | (_| |");
        System.err.println("                                       \\_____|\\__,_|_| |_| |_|\\___| |____/ \\___/ \\__,_|_|  \\__,_|");
        System.err.println("                                                            ");
        System.out.println("");
        System.out.println("                   _____________________________________________________________________________________________________");
        System.out.println("                   ----------P41---------    ----------P42---------    ---------P43----------    ----------P44---------");
        System.out.println("Bank:             |Points[ ]             |  |Points[ ]             |  |Points[ ]             |  |Points[ ]             |");
        System.out.println("                  |1w + 1B + 0G + 0R +1B |  |1w + 1B + 0G + 0R +1B |  |1w + 1B + 0G + 0R +1B |  |1w + 1B + 0G + 0R +1B |");
        System.out.println("(W)hite -> 7      ----------------------    ----------------------    ----------------------    ----------------------");
        System.out.println("Bl(U)e  -> 7      ______________________________________________________________________________________________________");
        System.out.println("(G)reen -> 7       ----------P31---------    ----------P32---------    ---------P33----------    ----------P34---------");
        System.out.println("(R)ed	-> 7      |Points[ ]             |  |Points[ ]             |  |Points[ ]             |  |Points[ ]             |");
        System.out.println("Blac(K) -> 7	  |1w + 1B + 0G + 0R +1B |  |1w + 1B + 0G + 0R +1B |  |1w + 1B + 0G + 0R +1B |  |1w + 1B + 0G + 0R +1B |");
        System.out.println("                   ----------------------    ----------------------    ----------------------    ----------------------");
        System.out.println("Go(L)d  -> 5	   ----------P21---------    ----------P22---------    ---------P23----------    ----------P24---------");
        System.out.println("                  |Points[ ]             |  |Points[ ]             |  |Points[ ]             |  |Points[ ]             |");
        System.out.println("	              |1w + 1B + 0G + 0R +1B |  |1w + 1B + 0G + 0R +1B |  |1w + 1B + 0G + 0R +1B |  |1w + 1B + 0G + 0R +1B |");
        System.out.println("                   ----------------------    ----------------------    ----------------------    ----------------------");
        System.out.println("                   ----------P11---------    ----------P12---------    ---------P13----------    ----------P14---------");
        System.out.println("                  |Points[ ]             |  |Points[ ]             |  |Points[ ]             |  |Points[ ]             |");
        System.out.println("	              |1w + 1B + 0G + 0R +1B |  |1w + 1B + 0G + 0R +1B |  |1w + 1B + 0G + 0R +1B |  |1w + 1B + 0G + 0R +1B |");
        System.out.println("                   ----------------------    ----------------------    ----------------------    ----------------------");



    }
}
