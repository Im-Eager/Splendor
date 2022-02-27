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
import java.util.List;

public class PrintBoard {

    private List<ClientConnectionHandler> players;
    private HashMap<String, Mine> board;
    private HashMap<String, Lord> boardLords;
    private int[] bank;

    public PrintBoard() {
    }


    public String colorCodeCreator(String color) {
        String code;
        if (color.equals("Red")) {
            code = "\u001B[31mMINE";
        } else if (color.equals("Blue")) {
            code = "\u001B[34mMINE";
        } else if (color.equals("White")) {
            code = "\u001B[37mMINE";
        } else if (color.equals("Black")) {
            code = "\u001B[30mMINE";
        } else {
            code = "\u001B[32mMINE";
        }
        return code;
    }

    public String printBoardReservedCards(List<Mine> reservedCards) {

        int numberOfCards = reservedCards.size();

        String toReturn1 = "You have no cards reserved.";
        String toReturn2 = "";
        String toReturn3 = "";

        switch (numberOfCards) {
            case 1 -> {
                int[] card1 = reservedCards.get(0).getCost();
                toReturn1 = colorCodeCreator(reservedCards.get(0).getColor()) + "\u001B[37m\tP00 \u001B[37m" + card1[0] + "W \u001B[34m" + card1[1] + "B \u001B[32m" + card1[2] + "G \u001B[31m" + card1[3] + "R \u001B[30m" + card1[4] + "K\t\u001B[37m |";
            }
            case 2 -> {
                int[] card1 = reservedCards.get(1).getCost();
                toReturn1 = colorCodeCreator(reservedCards.get(1).getColor()) + "\u001B[37m\tP00 \u001B[37m" + card1[0] + "W \u001B[34m" + card1[1] + "B \u001B[32m" + card1[2] + "G \u001B[31m" + card1[3] + "R \u001B[30m" + card1[4] + "K\t\u001B[37m |";
            }
            case 3 -> {
                int[] card1 = reservedCards.get(2).getCost();
                toReturn1 = colorCodeCreator(reservedCards.get(2).getColor()) + "\u001B[37m\tP00 \u001B[37m" + card1[0] + "W \u001B[34m" + card1[1] + "B \u001B[32m" + card1[2] + "G \u001B[31m" + card1[3] + "R \u001B[30m" + card1[4] + "K\t\u001B[37m |";
            }
        }

        return toReturn1 + toReturn2 + toReturn3;
    }


    public String printBoard(List<ClientConnectionHandler> playersList, HashMap<String, Mine> board, HashMap<String, Lord> boardLords, int[] bank) {
        players = playersList;

        Player player1 = players.get(0).getPlayer();
        Player player2 = players.get(1).getPlayer();
        Player player3 = players.get(2).getPlayer();
        Player player4 = players.get(3).getPlayer();

        this.board = board;
        this.boardLords = boardLords;
        this.bank = bank;
        int p1Pts = player1.getScore();
        int p2Pts = player2.getScore();
        int p3Pts = player3.getScore();
        int p4Pts = player4.getScore();

        int[] cp1 = player1.getOwnedMines();
        int[] cp2 = player2.getOwnedMines();
        int[] cp3 = player3.getOwnedMines();
        int[] cp4 = player4.getOwnedMines();

        int[] pb1 = player1.getBank();
        int[] pb2 = player1.getBank();
        int[] pb3 = player1.getBank();
        int[] pb4 = player1.getBank();

        String[] cardMine = new String[]{board.get("p11").getColor(), board.get("p12").getColor(), board.get("p13").getColor(), board.get("p14").getColor(),
                board.get("p21").getColor(), board.get("p22").getColor(), board.get("p23").getColor(), board.get("p24").getColor(),
                board.get("p31").getColor(), board.get("p32").getColor(), board.get("p33").getColor(), board.get("p34").getColor()};

        for (int i = 0; i < cardMine.length; i++) {
            if (cardMine[i].equals("Red")) {
                cardMine[i] = "\u001B[31mMINE";
            } else if (cardMine[i].equals("Blue")) {
                cardMine[i] = "\u001B[34mMINE";
            } else if (cardMine[i].equals("White")) {
                cardMine[i] = "\u001B[37mMINE";
            } else if (cardMine[i].equals("Black")) {
                cardMine[i] = "\u001B[30mMINE";
            } else {
                cardMine[i] = "\u001B[32mMINE";
            }
        }

        //Position of bank index[0] = (W)hite, index[1] = Bl(U)e, index[2] = (G)reen, index[3] = (R)ed, index[4] = Blac(K), index[5] = Go(L)d

        return ("\u001B[37m----------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "\u001B[37mTABLE\n\u001B[37m----------------------------------------------------------------------------------------------------------------------------------------------------------------------" +
                "\n\u001B[37mBANK \t\t\t\t\t\t\t\t\u001B[33mGo(L)d  -> " + bank[5] + "\u001B[37m\t\u001B[30mBlac(K) -> " + bank[4] + "\u001B[37m\t\u001B[34mBl(U)e  -> " + bank[1] + "\u001B[37m\t(W)hite -> " + bank[0] + "\u001B[32m\t(G)reen -> " + bank[3] + "\u001B[31m\t(R)ed   -> " + bank[4] + "\u001B[37m\n" +
                "----------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "TIER 4\n\t\tPOS41\t\u001B[33mLORD\u001B[37m\tP00 \u001B[37m" + boardLords.get("p41").getCost()[0] + "W \u001B[34m" + boardLords.get("p41").getCost()[1] + "B \u001B[32m" + boardLords.get("p41").getCost()[2] + "G \u001B[31m" + boardLords.get("p41").getCost()[3] + "R \u001B[30m" + boardLords.get("p41").getCost()[4] + "K\t\u001B[37m |\t POS42\t\u001B[33mLORD\u001B[37m\tP00 \u001B[37m" + boardLords.get("p42").getCost()[0] + "W \u001B[34m" + boardLords.get("p42").getCost()[1] + "B \u001B[32m" + boardLords.get("p42").getCost()[2] + "G \u001B[31m" + boardLords.get("p42").getCost()[3] + "R \u001B[30m" + boardLords.get("p42").getCost()[4] + "K\t\u001B[37m |\t POS43\t\u001B[33mLORD\u001B[37m\tP00 \u001B[37m" + boardLords.get("p43").getCost()[0] + "W \u001B[34m" + boardLords.get("p43").getCost()[1] + "B \u001B[32m" + boardLords.get("p43").getCost()[2] + "G \u001B[31m" + boardLords.get("p43").getCost()[3] + "R \u001B[30m" + boardLords.get("p43").getCost()[4] + "K\t\u001B[37m |\t POS44\t\u001B[33mLORD\u001B[37m\tP00 \u001B[37m" + boardLords.get("p44").getCost()[0] + "W \u001B[34m" + boardLords.get("p44").getCost()[1] + "B \u001B[32m" + boardLords.get("p44").getCost()[2] + "G \u001B[31m" + boardLords.get("p44").getCost()[3] + "R \u001B[30m" + boardLords.get("p44").getCost()[4] + "K\t\u001B[37m |\t\n" +
                "TIER 3\n\t\tPOS31\t" + cardMine[8] + "\u001B[37m\tP00 \u001B[37m" + board.get("p31").getCost()[0] + "W \u001B[34m" + board.get("p31").getCost()[1] + "B \u001B[32m" + board.get("p31").getCost()[2] + "G \u001B[31m" + board.get("p31").getCost()[3] + "R \u001B[30m" + board.get("p31").getCost()[4] + "K\t\u001B[37m |\t POS32\t" + cardMine[9] + "\u001B[37m\tP00 \u001B[37m" + board.get("p32").getCost()[0] + "W \u001B[34m" + board.get("p32").getCost()[1] + "B \u001B[32m" + board.get("p32").getCost()[2] + "G \u001B[31m" + board.get("p32").getCost()[3] + "R \u001B[30m" + board.get("p32").getCost()[4] + "K\t\u001B[37m |\t POS43\t" + cardMine[10] + "\u001B[37m\tP00 \u001B[37m" + board.get("p33").getCost()[0] + "W \u001B[34m" + board.get("p33").getCost()[1] + "B \u001B[32m" + board.get("p33").getCost()[2] + "G \u001B[31m" + board.get("p33").getCost()[3] + "R \u001B[30m" + board.get("p33").getCost()[4] + "K\t\u001B[37m |\t POS44\t" + cardMine[11] + "\u001B[37m\tP00 \u001B[37m" + board.get("p34").getCost()[0] + "W \u001B[34m" + board.get("p34").getCost()[1] + "B \u001B[32m" + board.get("p34").getCost()[2] + "G \u001B[31m" + board.get("p34").getCost()[3] + "R \u001B[30m" + board.get("p34").getCost()[4] + "K\t\u001B[37m |\t\n" +
                "TIER 2\n\t\tPOS21\t" + cardMine[4] + "\u001B[37m\tP00 \u001B[37m" + board.get("p21").getCost()[0] + "W \u001B[34m" + board.get("p21").getCost()[1] + "B \u001B[32m" + board.get("p21").getCost()[2] + "G \u001B[31m" + board.get("p21").getCost()[3] + "R \u001B[30m" + board.get("p21").getCost()[4] + "K\t\u001B[37m |\t POS22\t" + cardMine[5] + "\u001B[37m\tP00 \u001B[37m" + board.get("p22").getCost()[0] + "W \u001B[34m" + board.get("p22").getCost()[1] + "B \u001B[32m" + board.get("p22").getCost()[2] + "G \u001B[31m" + board.get("p22").getCost()[3] + "R \u001B[30m" + board.get("p22").getCost()[4] + "K\t\u001B[37m |\t POS43\t" + cardMine[6] + "\u001B[37m\tP00 \u001B[37m" + board.get("p23").getCost()[0] + "W \u001B[34m" + board.get("p23").getCost()[1] + "B \u001B[32m" + board.get("p23").getCost()[2] + "G \u001B[31m" + board.get("p23").getCost()[3] + "R \u001B[30m" + board.get("p23").getCost()[4] + "K\t\u001B[37m |\t POS44\t" + cardMine[7] + "\u001B[37m\tP00 \u001B[37m" + board.get("p24").getCost()[0] + "W \u001B[34m" + board.get("p24").getCost()[1] + "B \u001B[32m" + board.get("p24").getCost()[2] + "G \u001B[31m" + board.get("p24").getCost()[3] + "R \u001B[30m" + board.get("p24").getCost()[4] + "K\t\u001B[37m |\t\n" +
                "TIER 1\n\t\tPOS11\t" + cardMine[0] + "\u001B[37m\tP00 \u001B[37m" + board.get("p11").getCost()[0] + "W \u001B[34m" + board.get("p11").getCost()[1] + "B \u001B[32m" + board.get("p11").getCost()[2] + "G \u001B[31m" + board.get("p11").getCost()[3] + "R \u001B[30m" + board.get("p11").getCost()[4] + "K\t\u001B[37m |\t POS12\t" + cardMine[1] + "\u001B[37m\tP00 \u001B[37m" + board.get("p12").getCost()[0] + "W \u001B[34m" + board.get("p12").getCost()[1] + "B \u001B[32m" + board.get("p12").getCost()[2] + "G \u001B[31m" + board.get("p12").getCost()[3] + "R \u001B[30m" + board.get("p12").getCost()[4] + "K\t\u001B[37m |\t POS13\t" + cardMine[2] + "\u001B[37m\tP00 \u001B[37m" + board.get("p13").getCost()[0] + "W \u001B[34m" + board.get("p13").getCost()[1] + "B \u001B[32m" + board.get("p13").getCost()[2] + "G \u001B[31m" + board.get("p13").getCost()[3] + "R \u001B[30m" + board.get("p13").getCost()[4] + "K\t\u001B[37m |\t POS14\t" + cardMine[3] + "\u001B[37m\tP00 \u001B[37m" + board.get("p14").getCost()[0] + "W \u001B[34m" + board.get("p14").getCost()[1] + "B \u001B[32m" + board.get("p14").getCost()[2] + "G \u001B[31m" + board.get("p14").getCost()[3] + "R \u001B[30m" + board.get("p14").getCost()[4] + "K\t\u001B[37m |\t\n" +
                "----------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "\t\t\t\tPLAYER 1 HAND\t\t\t     |\t\t\t\tPLAYER 2 HAND\t\t\t |\t\t\t\tPLAYER 3 HAND\t\t\t |\t\t\t\tPLAYER 4 HAND\t\t\t |\n" +
                "\t\t\t\t  Points: " + p1Pts + "\t\t\t\t     |\t\t\t\t  Points: " + p2Pts + "\t\t\t     |\t\t\t\t  Points: " + p3Pts + "\t\t\t     |\t\t\t\t  Points: " + p4Pts + "\t\t\t     |\n" +
                "\t\t\t\t\u001B[37m" + cp1[0] + "W \u001B[34m" + cp1[1] + "B \u001B[32m" + cp1[2] + "G \u001B[31m" + cp1[3] + "R \u001B[30m" + cp1[4] + "K\t\u001B[37m\t\t\t |\t\t\t\t\u001B[37m" + cp2[0] + "W \u001B[34m" + cp2[1] + "B \u001B[32m" + cp2[2] + "G \u001B[31m" + cp2[3] + "R \u001B[30m" + cp2[4] + "K\t\u001B[37m\t\t |\t\t\t\t\u001B[37m" + cp3[0] + "W \u001B[34m" + cp3[1] + "B \u001B[32m" + cp3[2] + "G \u001B[31m" + cp3[3] + "R \u001B[30m" + cp3[4] + "K\t\u001B[37m\t\t |\t\t\t\t\u001B[37m" + cp4[0] + "W \u001B[34m" + cp4[1] + "B \u001B[32m" + cp4[2] + "G \u001B[31m" + cp4[3] + "R \u001B[30m" + cp4[4] + "K\t\u001B[37m\t\t |\n" +
                "\t\t\t\t\u001B[37m" + pb1[0] + "W \u001B[34m" + pb1[1] + "B \u001B[32m" + pb1[2] + "G \u001B[31m" + pb1[3] + "R \u001B[30m" + pb1[4] + "K\t\u001B[37m\t\t\t |\t\t\t\t\u001B[37m" + pb2[0] + "W \u001B[34m" + pb2[1] + "B \u001B[32m" + pb2[2] + "G \u001B[31m" + pb2[3] + "R \u001B[30m" + pb2[4] + "K\t\u001B[37m\t\t |\t\t\t\t\u001B[37m" + pb3[0] + "W \u001B[34m" + pb3[1] + "B \u001B[32m" + pb3[2] + "G \u001B[31m" + pb3[3] + "R \u001B[30m" + pb3[4] + "K\t\u001B[37m\t\t |\t\t\t\t\u001B[37m" + pb4[0] + "W \u001B[34m" + pb4[1] + "B \u001B[32m" + pb4[2] + "G \u001B[31m" + pb4[3] + "R \u001B[30m" + pb4[4] + "K\t\u001B[37m\t\t |\n" +
                "----------------------------------------------------------------------------------------------------------------------------------------------------------------------");

    }

//    public String[] boxMaker (HashMap<String, Mine> board){
//        String[] box;
//        return box;
//    }
}
