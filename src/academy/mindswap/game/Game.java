package academy.mindswap.game;

import academy.mindswap.PrintBoard;
import academy.mindswap.cards.Card;
import academy.mindswap.server.ClientConnectionHandler;
import academy.mindswap.utils.Messages;
import academy.mindswap.utils.PrintToTerminalGame;

import java.util.*;

public class Game implements Runnable {

    private String command;

    private Game game;

    private int[] bank;

    private final List<ClientConnectionHandler> players;

    private boolean winner;

    private HashMap<String, Card> table = new HashMap<>();

    private LinkedList<Card> deckTier4 = new LinkedList<Card>();
    private LinkedList<Card> deckTier3 = new LinkedList<Card>();
    private LinkedList<Card> deckTier2 = new LinkedList<Card>();
    private LinkedList<Card> deckTier1 = new LinkedList<Card>();

    PrintBoard printBoard = new PrintBoard();

    public Game(List players, List<Card> deck) {

        Collections.shuffle(players);
        Collections.shuffle(deck);

        this.deckTier1.addAll(deck.stream().filter(t -> t.getTier() == 1).toList());
        this.deckTier2.addAll(deck.stream().filter(t -> t.getTier() == 2).toList());
        this.deckTier3.addAll(deck.stream().filter(t -> t.getTier() == 3).toList());
        this.deckTier4.addAll(deck.stream().filter(t -> t.getTier() == 4).toList());

        this.players = players;

        this.players.forEach(p -> p.setGame(this));
        this.players.forEach(p -> p.setPlayer(new Player(p.getName())));

        gameSetup();

        run();
    }

    private void gameSetup() {
        PrintToTerminalGame.startScreen();
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        PrintToTerminalGame.screenSetup();

        fillBank();

        ArrayList<LinkedList<Card>> tiersCardGiver = new ArrayList<>();

        tiersCardGiver.add(deckTier1);
        tiersCardGiver.add(deckTier2);
        tiersCardGiver.add(deckTier3);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                Card cardToGive = tiersCardGiver.get(i).get(j);
                tiersCardGiver.get(i).remove(cardToGive);
                this.table.put("p" + (i + 1) + (j + 1), cardToGive);
            }
        }

        for (int i = 0; i < this.players.size(); i++) {
            Card cardToGive = this.deckTier4.get(i);
            this.deckTier4.remove(cardToGive);
            this.table.put("p4" + (i + 1), cardToGive);
        }
    }

    private void fillBank() {
        int numOfChips = 7;
        if (players.size() == 3) {
            numOfChips -= 2;
        }
        if (players.size() == 2) {
            numOfChips -= 3;
        }
        this.bank = new int[]{numOfChips, numOfChips, numOfChips, numOfChips, numOfChips, 5};
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void verifyCommand(String command){
        switch (command.charAt(1)) {
            case 'B' -> players.stream()
                    .findFirst().get()
                    .getPlayer()
                    .buyCard(command.substring(3));

            case 'G' -> players.stream()
                    .findFirst().get()
                    .getPlayer()
                    .grabGems(command.substring(3));

            case 'R' -> players.stream()
                    .findFirst().get()
                    .getPlayer()
                    .reserveCard(command.substring(3));
        }

    }

    @Override
    public void run() {

        gameSetup();

        ClientConnectionHandler playerPlaying = players.stream().findFirst().get();

        try {

            while (!winner) {
                this.command = null;

//                playerPlaying.getPlayer().setPlaying(true);

                players.forEach(p -> p.send(printBoard.printBoard(players, table, bank)));
                players.forEach(p -> p.send("COLOCAR IMPRESSÂO DA MÃO RESPECTIVA DE CADA JOGADOR"));

                playerPlaying.send("It is your turn to play! \n Waiting for your command... \n Type /help to receive a list of commands.");
                ClientConnectionHandler finalPlayerPlaying = playerPlaying; //final variable to introduce lambda function.
                players.forEach(p -> p.send("Playing: " + finalPlayerPlaying.getName()));


                // change to broadcast

            synchronized (this) {
                try {
                    while (this.command == null) {
                        wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
                wait();

                switch (command.charAt(1)) {
                    case 'H' -> System.out.println(Messages.COMMAND_HELP);

                    case 'B' -> playerPlaying
                            .getPlayer()
                            .buyCard(command.substring(3));

                    case 'G' -> playerPlaying
                            .getPlayer()
                            .grabGems(command.substring(3));

                    case 'R' -> playerPlaying
                            .getPlayer()
                            .reserveCard(command.substring(3));

                    default -> System.out.println(Messages.IMPOSSIBLE_MOVE);
                }


                playerPlaying = players.stream().findFirst().stream().skip(1).findFirst().get();;
                playerPlaying.send("Cheguei aqui2");


            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
