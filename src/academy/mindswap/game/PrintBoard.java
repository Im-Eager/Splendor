/**
 * @(#)PrintBoard.java        1.0 28/02/2022
 *
 * Copyright© MindSwap Academy - Diogo Noronha, Luis Faria, Ricardo Paiva, Tiago Miranda
 * All rights reserved.
 *
 * This software was produced to become our first group project.
 */

package academy.mindswap.game;

import academy.mindswap.cards.Lord;
import academy.mindswap.cards.Mine;
import academy.mindswap.server.ClientConnectionHandler;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class PrintBoard {

    public PrintBoard() {
    }

    private String colorCodeCreator(String color) {
        String code;
        if (color.equals("Red")) {
            code = "\u001B[31m";
        } else if (color.equals("Blue")) {
            code = "\u001B[34m";
        } else if (color.equals("White")) {
            code = "\u001B[37m";
        } else if (color.equals("Black")) {
            code = "\u001B[30m";
        } else {
            code = "\u001B[32m";
        }
        return code;
    }

    public String[] boxMakerLord(String key, Lord lord) {
        String[] box = new String[4];
        box[0] = " -----------" + key + "----------- ";
        box[1] = "|Points: " + lord.getPoints() + "                |";
        box[2] = "|Cost: " + "\u001B[37m" + lord.getCost()[0] + "W " + "\u001B[34m" + lord.getCost()[1] + "U " +
                "\u001B[32m" + lord.getCost()[2] + "G " + "\u001B[31m" + lord.getCost()[3] + "R " +
                "\u001B[30m" + lord.getCost()[4] + "K " + "\u001B[0m" + "    |";
        box[3] = " ------------------------- ";

        return box;
    }

    public String[][] makeBoardLord(HashMap<String, Lord> board) {
        int size = board.size();
        String[][] boxArray = new String[size][4];
        for (int i = 0; i < size; i++) {
            String key = board.keySet().stream().sorted().toList().get(i);
            Lord lord = board.get(key);
            boxArray[i][0] = boxMakerLord(key, lord)[0];
            boxArray[i][1] = boxMakerLord(key, lord)[1];
            boxArray[i][2] = boxMakerLord(key, lord)[2];
            boxArray[i][3] = boxMakerLord(key, lord)[3];
        }
        return boxArray;
    }

    public String printBoardLord(HashMap<String, Lord> board) {
        String header = "*._______________________________________________.* LORDS *.______________________________________________.*" + "\n";
        String boardToPrint;
        String[][] bx = makeBoardLord(board);
        String firstLine = "";
        String secondLine = "";
        String thirdLine = "";
        String fourthLine = "";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < board.size(); i++) {
            sb.append(bx[i][0]);
            firstLine = sb.toString();
        }
        sb = new StringBuffer();
        for (int i = 0; i < board.size(); i++) {
            sb.append(bx[i][1]);
            secondLine = sb.toString();
        }
        sb = new StringBuffer();
        for (int i = 0; i < board.size(); i++) {
            sb.append(bx[i][2]);
            thirdLine = sb.toString();
        }
        sb = new StringBuffer();
        for (int i = 0; i < board.size(); i++) {
            sb.append(bx[i][3]);
            fourthLine = sb.toString();
        }
        boardToPrint =
                firstLine + "\n" + secondLine + "\n" + thirdLine + "\n" + fourthLine;

        return header + boardToPrint;
    }

    public String[][] makeBoard(HashMap<String, Mine> board) {
        String[][] boxArray = new String[board.size()][4];
        for (int i = 0; i < board.size(); i++) {
            String key = board.keySet().stream().sorted().toList().get(i);
            Mine mine = board.get(key);
            boxArray[i][0] = boxMaker(key, mine)[0];
            boxArray[i][1] = boxMaker(key, mine)[1];
            boxArray[i][2] = boxMaker(key, mine)[2];
            boxArray[i][3] = boxMaker(key, mine)[3];
        }
        return boxArray;
    }

    public String[] boxMaker(String key, Mine mine) {
        String color = colorCodeCreator(mine.getColor());
        String[] box = new String[4];
        box[0] = color + " -----------" + key + "----------- " + "\u001B[0m";
        box[1] = color + "|Points: " + mine.getPoints() + "                |" + "\u001B[0m";
        box[2] = color + "|Cost: " +
                "\u001B[37m" + mine.getCost()[0] + "W " +
                "\u001B[34m" + mine.getCost()[1] + "U " +
                "\u001B[32m" + mine.getCost()[2] + "G " +
                "\u001B[31m" + mine.getCost()[3] + "R " +
                "\u001B[30m" + mine.getCost()[4] + "K " +
                color + "    |" + "\u001B[0m";
        box[3] = color + " ------------------------- " + "\u001B[0m";
        return box;
    }

    public String printBoard(HashMap<String, Mine> board) {
        String[][] bx = makeBoard(board);
        String boardToPrint =
                "*._______________________________________________.* MINES *.______________________________________________.*" + "\n" +
                        bx[8][0] + bx[9][0] + bx[10][0] + bx[11][0] + "\n" +
                        bx[8][1] + bx[9][1] + bx[10][1] + bx[11][1] + "\n" +
                        bx[8][2] + bx[9][2] + bx[10][2] + bx[11][2] + "\n" +
                        bx[8][3] + bx[9][3] + bx[10][3] + bx[11][3] + "\n" +

                        bx[4][0] + bx[5][0] + bx[6][0] + bx[7][0] + "\n" +
                        bx[4][1] + bx[5][1] + bx[6][1] + bx[7][1] + "\n" +
                        bx[4][2] + bx[5][2] + bx[6][2] + bx[7][2] + "\n" +
                        bx[4][3] + bx[5][3] + bx[6][3] + bx[7][3] + "\n" +

                        bx[0][0] + bx[1][0] + bx[2][0] + bx[3][0] + "\n" +
                        bx[0][1] + bx[1][1] + bx[2][1] + bx[3][1] + "\n" +
                        bx[0][2] + bx[1][2] + bx[2][2] + bx[3][2] + "\n" +
                        bx[0][3] + bx[1][3] + bx[2][3] + bx[3][3];

        return boardToPrint;
    }

    public String printBank(int[] bank) {
        String bankPrint =
                "*._____________________________________________.* GAME BANK *.____________________________________________.*" + "\n" +
                        "                          " +
                        "\u001B[37m (W)hite:" + bank[0] +
                        "\u001B[34m bl(U)e:" + bank[1] +
                        "\u001B[32m (G)reen:" + bank[2] +
                        "\u001B[31m (R)ed:" + bank[3] +
                        "\u001B[30m blac(K):" + bank[4] +
                        "\u001B[33m go(L)d:" + bank[5] +
                        "\u001B[0m";
        return bankPrint;
    }

    public String[] boxMakerPlayers(ClientConnectionHandler players) {
        String[] box = new String[6];
        box[0] = " ------------------------- ";
        box[1] = "|" + players.getPlayer().getName() + "                  |";

        box[2] = "|Points: " + players.getPlayer().getScore() + "                |";
        box[3] = "|Coin: " +
                "\u001B[37m" + players.getPlayer().getBank()[0] + "W " +
                "\u001B[34m" + players.getPlayer().getBank()[1] + "U " +
                "\u001B[32m" + players.getPlayer().getBank()[2] + "G " +
                "\u001B[31m" + players.getPlayer().getBank()[3] + "R " +
                "\u001B[30m" + players.getPlayer().getBank()[4] + "K " +
                "\u001B[33m" + players.getPlayer().getBank()[5] + "L " +
                "\u001B[0m" + " |";
        box[4] = "|Mine: " +
                "\u001B[37m" + players.getPlayer().getOwnedMines()[0] + "W " +
                "\u001B[34m" + players.getPlayer().getOwnedMines()[1] + "U " +
                "\u001B[32m" + players.getPlayer().getOwnedMines()[2] + "G " +
                "\u001B[31m" + players.getPlayer().getOwnedMines()[3] + "R " +
                "\u001B[30m" + players.getPlayer().getOwnedMines()[4] + "K "  +
                "\u001B[0m" + "    |";
        box[5] = " ------------------------- ";
        return box;
    }

    public String[][] makeBoardPlayers(List<ClientConnectionHandler> board) {
        int size = board.size();
        String[][] boxArray = new String[size][6];
        for (int i = 0; i < size; i++) {
            ClientConnectionHandler player = board.get(i);
            boxArray[i][0] = boxMakerPlayers(player)[0];
            boxArray[i][1] = boxMakerPlayers(player)[1];
            boxArray[i][2] = boxMakerPlayers(player)[2];
            boxArray[i][3] = boxMakerPlayers(player)[3];
            boxArray[i][4] = boxMakerPlayers(player)[4];
            boxArray[i][5] = boxMakerPlayers(player)[5];
        }
        return boxArray;
    }

    public String printBoardPlayers(List<ClientConnectionHandler> board) {
        String header = "*.______________________________________________.* PLAYERS *._____________________________________________.*" + "\n";
        String boardToPrint;
        String[][] bx = makeBoardPlayers(board);
        String firstLine = "";
        String secondLine = "";
        String thirdLine = "";
        String fourthLine = "";
        String fifthLine = "";
        String sixthLine = "";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < board.size(); i++) {
            sb.append(bx[i][0]);
            firstLine = sb.toString();
        }
        sb = new StringBuffer();
        for (int i = 0; i < board.size(); i++) {
            sb.append(bx[i][1]);
            secondLine = sb.toString();
        }
        sb = new StringBuffer();
        for (int i = 0; i < board.size(); i++) {
            sb.append(bx[i][2]);
            thirdLine = sb.toString();
        }
        sb = new StringBuffer();
        for (int i = 0; i < board.size(); i++) {
            sb.append(bx[i][3]);
            fourthLine = sb.toString();
        }
        sb = new StringBuffer();
        for (int i = 0; i < board.size(); i++) {
            sb.append(bx[i][4]);
            fifthLine = sb.toString();
        }
        sb = new StringBuffer();
        for (int i = 0; i < board.size(); i++) {
            sb.append(bx[i][5]);
            sixthLine = sb.toString();
        }
        boardToPrint =
                firstLine + "\n" + secondLine + "\n" + thirdLine + "\n" + fourthLine + "\n" + fifthLine +"\n" + sixthLine;

        return header + boardToPrint;
    }

    public String[] boxMakerReserved(Mine mine, int index) {
        String color = colorCodeCreator(mine.getColor());
        String[] box = new String[4];
        box[0] = color + " ------------p"+index+"----------- " + "\u001B[0m";
        box[1] = color + "|Points :" + mine.getPoints() + "                |" + "\u001B[0m";
        box[2] = color + "|Cost: " +
                "\u001B[37m" + mine.getCost()[0] + "W " +
                "\u001B[34m" + mine.getCost()[1] + "U " +
                "\u001B[32m" + mine.getCost()[2] + "G " +
                "\u001B[31m" + mine.getCost()[3] + "R " +
                "\u001B[30m" + mine.getCost()[4] + "K " + color + "    |" +
                "\u001B[0m";
        box[3] = color + " ------------------------- " + "\u001B[0m";
        return box;
    }

    public String[][] makeBoardReserved(LinkedList<Mine> board) {
        int size = board.size();
        String[][] boxArray = new String[size][4];
        for (int i = 0; i < size; i++) {
            Mine mine = board.get(i);
            boxArray[i][0] = boxMakerReserved(mine, i)[0];
            boxArray[i][1] = boxMakerReserved(mine, i)[1];
            boxArray[i][2] = boxMakerReserved(mine, i)[2];
            boxArray[i][3] = boxMakerReserved(mine, i)[3];
        }
        return boxArray;
    }

    public String printBoardReserved(LinkedList<Mine> board) {
        String header = "*.__________________________________________.* CARDS RESERVED *.__________________________________________.*" + "\n";
        String boardToPrint;
        if (board.size() == 0) {
            boardToPrint = "                                         You have no cards reserved";
        } else {
            String[][] bx = makeBoardReserved(board);
            String firstLine = "";
            String secondLine = "";
            String thirdLine = "";
            String fourthLine = "";
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < board.size(); i++) {
                sb.append(bx[i][0]);
                firstLine = sb.toString();
            }
            sb = new StringBuffer();
            for (int i = 0; i < board.size(); i++) {
                sb.append(bx[i][1]);
                secondLine = sb.toString();
            }
            sb = new StringBuffer();
            for (int i = 0; i < board.size(); i++) {
                sb.append(bx[i][2]);
                thirdLine = sb.toString();
            }
            sb = new StringBuffer();
            for (int i = 0; i < board.size(); i++) {
                sb.append(bx[i][3]);
                fourthLine = sb.toString();
            }
            boardToPrint =
                    firstLine + "\n" + secondLine + "\n" + thirdLine + "\n" + fourthLine;
        }
        return header + boardToPrint;
    }
}
