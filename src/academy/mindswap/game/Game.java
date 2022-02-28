/**
 * @(#)Game.java 1.0 28/02/2022
 * <p>
 * Copyright© MindSwap Academy - Diogo Noronha, Luis Faria, Ricardo Paiva, Tiago Miranda
 * All rights reserved.
 * <p>
 * This software was produced to become our first group project.
 */

package academy.mindswap.game;

import academy.mindswap.cards.Card;
import academy.mindswap.cards.Lord;
import academy.mindswap.cards.Mine;
import academy.mindswap.server.ClientConnectionHandler;
import academy.mindswap.utils.Messages;
import academy.mindswap.utils.PrintToTerminalGame;

import java.util.*;

public class Game implements Runnable {

    private int threadSleep;
    private String command;
    private int[] bank;
    private final List<ClientConnectionHandler> players;
    private boolean winner;
    private boolean validCommand;
    private PrintBoard printBoard;

    private HashMap<String, Mine> board = new HashMap<>();
    private HashMap<String, Lord> boardLords = new HashMap<>();

    private LinkedList<Lord> deckTier4 = new LinkedList<Lord>();
    private LinkedList<Mine> deckTier3 = new LinkedList<Mine>();
    private LinkedList<Mine> deckTier2 = new LinkedList<Mine>();
    private LinkedList<Mine> deckTier1 = new LinkedList<Mine>();


    public Game(List players, List<Card> deck) {

        this.threadSleep = 2000;

        this.printBoard = new PrintBoard();
        this.validCommand = true;

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

        for (int i = 0; i < this.players.size() + 1; i++) {
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


    private void boardPrints(ClientConnectionHandler playerPlaying) {
        players.forEach(p -> p.send(printBoard.printBoardLord(boardLords)));
        players.forEach(p -> p.send(printBoard.printBoard(board)));
        players.forEach(p -> p.send(printBoard.printBank(bank)));
        players.forEach(p -> p.send(printBoard.printBoardPlayers(players)));
        players.forEach(p -> p.send(printBoard.printBoardReserved(p.getPlayer().getReservedCards())));
        players.forEach(p -> p.send("Playing: " + playerPlaying.getName()));
        playerPlaying.send("It is your turn to play! Type /H to receive a list of commands. \nWaiting for your command... ");
    }

    private void grabGems(String message, ClientConnectionHandler player) throws InterruptedException {
        this.validCommand = false;

        if (message.matches("[0-2]+") &&
                message.chars().map(a -> Character.digit(a, 10)).sum() == 2 &&
                message.length() == 5) {
            this.validCommand = true;
        }
        if (message.matches("[0-1]+") &&
                message.chars().map(a -> Character.digit(a, 10)).sum() <= 3 &&
                message.length() == 5) {
            this.validCommand = true;
        }

        if (!validCommand){
            player.send("Insert a valid command.");
            Thread.sleep(threadSleep);
            return;
        }

        int[] grabOrder = new int[5];

        for (int i = 0; i < grabOrder.length - 1; i++) {
            grabOrder[i] += Integer.parseInt(String.valueOf(message.charAt(i)));
        }

        for (int i = 0; i < grabOrder.length; i++) {
            int toTest = 0;
            toTest = bank[i] - grabOrder[i];
            if (toTest < 0){
                this.validCommand = false;
                player.send("The bank does not have enough chips.");
                Thread.sleep(threadSleep);
                return;
            }
        }

        int[] playerBank = player.getPlayer().getBank();

        for (int i = 0; i < playerBank.length - 1; i++) {
            playerBank[i] += Integer.parseInt(String.valueOf(message.charAt(i)));
        }

        if (Arrays.stream(playerBank).reduce(0, Integer::sum) > 10) {
            player.send(Messages.MORE_THAN_10);
        } else {
            player.getPlayer().setBank(playerBank);
            for (int i = 0; i < playerBank.length - 1; i++) {
                this.bank[i] -= Integer.parseInt(String.valueOf(message.charAt(i)));
            }
        }
    }

    private void buyCard(String message, ClientConnectionHandler player) throws InterruptedException {

        HashMap<String, Mine> boardSum = board;
        player.getPlayer().getReservedCards().
                forEach(p -> boardSum.put(("p" + player.getPlayer().getReservedCards().indexOf(p)),p));

        if (!(boardSum.keySet().stream().anyMatch(p -> p.equals(message)))){
            player.send("Chose a valid position on the board.");
            Thread.sleep(threadSleep);
            this.validCommand = false;
            return;
        }

        boolean isPayed = true;
        int index = 0;
        int[] mineCost = boardSum.get(message).getCost();
        int[] playerBank = player.getPlayer().getBank();
        int[] playerMines = player.getPlayer().getOwnedMines();
        int[] mineCostBuffer = new int[5];

        switch (boardSum.get(message).getColor()) {
            case "White" -> index = 0;
            case "Blue" -> index = 1;
            case "Green" -> index = 2;
            case "Red" -> index = 3;
            case "Black" -> index = 4;
        }

        for (int i = 0; i < mineCost.length; i++) {
            mineCost[i] -= playerMines[i];
            if (mineCost[i] > 0) {
                isPayed = false;
            }
            if (mineCost[i] < 0) {
                mineCost[i] = 0;
            }
            mineCostBuffer[i] = mineCost[i];
        }
        if (isPayed) {
            player.getPlayer().setOwnedMines(index);
            replaceCard(message);
            return;
        }

        isPayed = true;
        for (int i = 0; i < mineCost.length; i++) {
            mineCost[i] -= playerBank[i];
            if (mineCost[i] > 0) {
                isPayed = false;
            }
            if (mineCost[i] < 0) {
                mineCost[i] = 0;
            }
        }
        if (isPayed) {
            for (int i = 0; i < mineCost.length; i++) {
                playerBank[i] -= mineCostBuffer[i];
            }
            player.getPlayer().setBank(playerBank);
            player.getPlayer().setOwnedMines(index);
            replaceCard(message);
            return;
        }
        if (playerBank[5] - Arrays.stream(mineCost).reduce(0, Integer::sum) >= 0) {
            player.getPlayer().setGold(-1 * (playerBank[5] - Arrays.stream(mineCost).reduce(0, Integer::sum)));
            player.getPlayer().setOwnedMines(index);
            replaceCard(message);
        } else {
            player.send(Messages.CANT_BUY);
            this.validCommand = false;
        }
    }

    private void reserveCard(String message, ClientConnectionHandler player) throws InterruptedException {
        if (!(board.keySet().stream().anyMatch(p -> p.equals(message)))){
            player.send("Chose a valid position on the board.");
            Thread.sleep(threadSleep);
            this.validCommand = false;
            return;
        }

        if (player.getPlayer().getReservedCards().size() == 3) {
            player.send("You already have three cards reserved.");
            Thread.sleep(threadSleep);
            this.validCommand = false;
            return;
        }

        player.getPlayer().setReservedCard(board.get(message));
        player.getPlayer().setGold(1);
        replaceCard(message);
        player.send(Messages.GOLD_TOKEN_AWARDED);
    }

    private void replaceCard(String message) {
        if (message.charAt(2) == '1') {
            Mine card = this.deckTier1.get(0);
            this.deckTier1.remove(card);
            System.out.println(message);
            this.board.put(message, card);
        }
        if (message.charAt(2) == '2') {
            Mine card = this.deckTier2.get(0);
            this.deckTier2.remove(card);
            this.board.put(message, card);
        }
        if (message.charAt(2) == '3') {
            Mine card = this.deckTier3.get(0);
            this.deckTier3.remove(card);
            this.board.put(message, card);
        }
    }

    private void checkLord(ClientConnectionHandler player){
        for (int i = 1; i == boardLords.size(); i++) {
            boolean isBuyable = true;
            for (int j = 0; j < player.getPlayer().getOwnedMines().length; j++) {
                if (boardLords.get("p4" + i).getCost()[j] <= player.getPlayer().getOwnedMines()[j]){
                    isBuyable = false;
                }
            }
            if (isBuyable){
                player.getPlayer().setOwnedLord(boardLords.get(i));
                boardLords.remove("p4" + i);
                return;
            }
        }
    }

    public void setCommand(String command) {
        this.command = command;
    }

    private boolean checkWinner(ClientConnectionHandler player) {
        return player.getPlayer().getScore() >= 15;
    }


    @Override
    public void run() {
        int counter = 0;

        players.forEach(p -> p.send(PrintToTerminalGame.startScreen()));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            while (!winner) {
                this.validCommand = true;

                ClientConnectionHandler playerPlaying = players.stream().toList().get(counter);

                playerPlaying.setHasPlayerGivenCommand(false);
                playerPlaying.getPlayer().setPlaying(true);

                boardPrints(playerPlaying);

                while (true) {
                    Thread.sleep(threadSleep);
                    if (playerPlaying.getHasPlayerGivenCommand()) {
                        break;
                    }
                }

                switch (command.charAt(1)) {
                    case 'H' -> {playerPlaying.send(Messages.COMMAND_HELP);
                                Thread.sleep(threadSleep);}
                    case 'B' -> buyCard(command.substring(2), playerPlaying);
                    case 'G' -> grabGems(command.substring(2), playerPlaying);
                    case 'R' -> reserveCard(command.substring(2), playerPlaying);
                    default -> this.validCommand = false;
                }

                checkLord(playerPlaying);

                if (checkWinner(playerPlaying)) {
                    this.winner = true;
                    playerPlaying.send(Messages.I_WIN_MOTHERFUCKERS);
                    break;
                }

                if (validCommand) {
                    counter++;
                }

                if (counter > players.size()) {
                    counter = 0;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}