package academy.mindswap.game;

import academy.mindswap.cards.Card;
import academy.mindswap.cards.Lord;
import academy.mindswap.cards.Mine;
import academy.mindswap.server.ClientConnectionHandler;
import academy.mindswap.utils.Messages;

import java.util.*;

public class Game implements Runnable {

    private String command;

    private Game game;

    private int[] bank;

    private final List<ClientConnectionHandler> players;

    private boolean winner;

    private HashMap<String, Mine> board = new HashMap<>();
    private HashMap<String, Lord> boardLords = new HashMap<>();

    private LinkedList<Lord> deckTier4 = new LinkedList<Lord>();
    private LinkedList<Mine> deckTier3 = new LinkedList<Mine>();
    private LinkedList<Mine> deckTier2 = new LinkedList<Mine>();
    private LinkedList<Mine> deckTier1 = new LinkedList<Mine>();

    PrintBoard printBoard = new PrintBoard();

    public Game(List players, List<Card> deck) {

        Collections.shuffle(players);
        Collections.shuffle(deck);

        this.deckTier1.addAll(deck.stream().filter(t -> t.getTier() == 1).map(p -> (Mine) p).toList());
        this.deckTier2.addAll(deck.stream().filter(t -> t.getTier() == 2).map(p -> (Mine) p).toList());
        this.deckTier3.addAll(deck.stream().filter(t -> t.getTier() == 3).map(p -> (Mine) p).toList());
        this.deckTier4.addAll(deck.stream().filter(t -> t.getTier() == 4).map(p -> (Lord) p).toList());

        this.players = players;

        this.players.forEach(p -> p.setGame(this));
        this.players.forEach(p -> p.setPlayer(new Player(p.getName())));

        gameSetup();

        run();
    }


    private void gameSetup() {
/*
        PrintToTerminalGame.startScreen();
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        PrintToTerminalGame.screenSetup();
*/


        fillBank();


        ArrayList<LinkedList<Mine>> tiersCardGiver = new ArrayList<>();

        tiersCardGiver.add(deckTier1);
        tiersCardGiver.add(deckTier2);
        tiersCardGiver.add(deckTier3);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                Mine cardToGive = tiersCardGiver.get(i).get(j);
                tiersCardGiver.get(i).remove(cardToGive);
                this.board.put("p" + (i + 1) + (j + 1), cardToGive);
            }
        }

        for (int i = 0; i < this.players.size(); i++) {
            Lord cardToGive = this.deckTier4.get(i);
            this.deckTier4.remove(cardToGive);
            this.boardLords.put("p4" + (i + 1), cardToGive);
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


    public Mine reserveCard(String message) {
        return board.get(message);

    }

    public void buyCard(String message, ClientConnectionHandler player) {

        int[] temp = player.getPlayer().getBank();

        for (int i = 0; i < board.get(message).getCost().length; i++) {
            temp[i] -= board.get(message).getCost()[i];

            if (board.get(message).getCost()[0] >= player.getPlayer().getBank()[0] &&
                    board.get(message).getCost()[1] <= player.getPlayer().getBank()[1]
                    || board.get(message).getCost()[1] <= player.getPlayer().getBank()[1] + player.getPlayer().getBank()[5] &&
                    board.get(message).getCost()[2] <= player.getPlayer().getBank()[2]
                    || board.get(message).getCost()[2] <= player.getPlayer().getBank()[2] + player.getPlayer().getBank()[5] &&
                    board.get(message).getCost()[3] <= player.getPlayer().getBank()[4]
                    || board.get(message).getCost()[3] <= player.getPlayer().getBank()[3] + player.getPlayer().getBank()[5] &&
                    board.get(message).getCost()[4] <= player.getPlayer().getBank()[4]
                    || board.get(message).getCost()[4] <= player.getPlayer().getBank()[4] + player.getPlayer().getBank()[5]) {

                player.getPlayer().setScore(player.getPlayer().getScore() + board.get(message).getPoints());
                player.getPlayer().setBank(temp);

            }
        }
    }


    /* public void verifyCommand(String command){
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

    }*/

    @Override
    public void run() {

        ClientConnectionHandler playerPlaying = players.stream().findFirst().get();
//        ClientConnectionHandler nextPlayer = players.stream().iterator().next();

        try {

            while (!winner) {
                playerPlaying.setHasPlayerGivenCommand(false);
                playerPlaying.getPlayer().setPlaying(true);

                players.forEach(p -> p.send("LINHA 4 DO BOARD"));
                players.forEach(p -> p.send(printBoard.printBoard(players, board, boardLords, bank)));
                players.forEach(p -> p.send(printBoard.printBoardReservedCards(p.getPlayer().getReservedCards())));

                players.forEach(p -> p.send("COLOCAR IMPRESSÂO DA MÃO RESPECTIVA DE CADA JOGADOR"));
                players.forEach(p -> p.send("Playing: " + playerPlaying.getName()));
                playerPlaying.send("It is your turn to play!Type /help to receive a list of commands. \nWaiting for your command... ");

                while (true) {
                    Thread.sleep(500);
                    if (playerPlaying.getHasPlayerGivenCommand()) {
                        break;
                    }
                }

                playerPlaying.send("Cheguei");

                switch (command.charAt(1)) {
                    case 'H' -> {
                        playerPlaying.send(Messages.COMMAND_HELP);
                        continue;
                    }

                    case 'B' -> {
                        buyCard(command.substring(3), playerPlaying);
                        // PLAYER PLAYING = NEXT
                        continue;
                    }
                    case 'G' -> {
                        playerPlaying
                                .getPlayer()
                                .grabGems(command.substring(3));
                        // PLAYER PLAYING = NEXT

                        continue;
                    }
                    case 'R' -> {
                        playerPlaying
                                .getPlayer()
                                .getReservedCards()
                                .add(reserveCard(command.substring(3)));
                        // PLAYER PLAYING = NEXT

                        continue;
                    }


                    default -> playerPlaying.send(Messages.IMPOSSIBLE_MOVE);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}