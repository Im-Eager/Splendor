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

import java.util.*;

public class Game implements Runnable {

    private String command;
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
            Thread.sleep(3000);
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

    public void setCommand(String command) {
        this.command = command;
    }


    public boolean bankHasChips() {
        for (int i = 0; i < bank.length; i++) {
            if (bank[i] > 1) {
                return true;
            }
        }
        return false;
    }

    public void reserveCard(String message, ClientConnectionHandler player) {
        player.getPlayer().setReservedCard(board.get(message));
        player.getPlayer().setGold(1);
        replaceCard(message);
        player.send(Messages.GOLD_TOKEN_AWARDED);
    }

    public void replaceCard(String message) {
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

    public void buyCard(String message, ClientConnectionHandler player) {
        boolean isPayed = true;
        int index = 0;
        int[] mineCost = board.get(message).getCost();
        int[] playerBank = player.getPlayer().getBank();
        int[] playerMines = player.getPlayer().getOwnedMines();
        int[] mineCostBuffer = new int[5];

        switch (board.get(message).getColor()) {
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
            player.send(mineCostBuffer[i] + " minebuffer1");
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
                player.send(playerBank[i] + " playerbank");
                player.send(mineCostBuffer[i] + " minebuffer2");
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
        }
    }

    public void grabGems(String message, ClientConnectionHandler player) {

        if (bankHasChips()) {

            int[] temp = player.getPlayer().getBank();

            for (int i = 0; i < temp.length - 1; i++) {
                temp[i] += Integer.parseInt(String.valueOf(message.substring(2).charAt(i)));
            }

            if (Arrays.stream(temp).reduce(0, Integer::sum) > 10) {
                player.send(Messages.MORE_THAN_10);
                return;
            } else {
                player.getPlayer().setBank(temp);
                for (int i = 0; i < temp.length - 1; i++) {
                    this.bank[i] -= Integer.parseInt(String.valueOf(message.substring(2).charAt(i)));
                }
                return;
            }
        }
        player.send(Messages.TOKEN_STACK_LOW);
    }

    public boolean checkWinner(ClientConnectionHandler player) {
        return player.getPlayer().getScore() >= 15;
    }

    @Override
    public void run() {
        int counter = 0;
        try {
            while (!winner) {
                ClientConnectionHandler playerPlaying = players.stream().toList().get(counter);

                playerPlaying.setHasPlayerGivenCommand(false);
                playerPlaying.getPlayer().setPlaying(true);

                players.forEach(p -> p.send(printBoard.printBoardLord(boardLords)));
                players.forEach(p -> p.send(printBoard.printBoard(board)));
                players.forEach(p -> p.send(printBoard.printBank(bank)));
                players.forEach(p -> p.send(printBoard.printBoardPlayers(players)));
                players.forEach(p -> p.send(printBoard.printBoardReserved(p.getPlayer().getReservedCards())));
                players.forEach(p -> p.send("Playing: " + playerPlaying.getName()));
                playerPlaying.send("It is your turn to play! Type /help to receive a list of commands. \nWaiting for your command... ");

                while (true) {
                    Thread.sleep(500);
                    if (playerPlaying.getHasPlayerGivenCommand()) {
                        break;
                    }
                }
                switch (command.charAt(1)) {
                    case 'H' -> {
                        playerPlaying.send(Messages.COMMAND_HELP);
                    }
                    case 'B' -> {
                        buyCard(command.substring(2), playerPlaying);
                        // PLAYER PLAYING = NEXT
                    }
                    case 'G' -> {
                        grabGems(command, playerPlaying);
                        // PLAYER PLAYING = NEXT
                    }
                    case 'R' -> {
                        reserveCard(command.substring(2), playerPlaying);
                    }
                    default -> {
                        playerPlaying.send(Messages.IMPOSSIBLE_MOVE);
                    }
                }
                if (checkWinner(playerPlaying)) {
                    playerPlaying.send(Messages.I_WIN_MOTHERFUCKERS);
                }

                counter++;
                if (counter > players.size()){
                    counter = 0;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}