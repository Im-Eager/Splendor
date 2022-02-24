package academy.mindswap;

import academy.mindswap.cards.Card;
import academy.mindswap.game.Player;
import academy.mindswap.server.ClientConnectionHandler;

import java.util.HashMap;
import java.util.List;

public class PrintBoard{

    private List<ClientConnectionHandler> players;
    private HashMap<String, Card> board;
    private int[] bank;

    public PrintBoard() {}

    public String printBoard(List<ClientConnectionHandler> playersList, HashMap<String, Card> board, int[] bank) {
        players = playersList;

        Player player1 = players.get(0).getPlayer();
        Player player2 = players.get(1).getPlayer();
        Player player3 = players.get(2).getPlayer();
        Player player4 = players.get(3).getPlayer();
        this.board = board;
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


        //Position of bank index[0] = (W)hite, index[1] = Bl(U)e, index[2] = (G)reen, index[3] = (R)ed, index[4] = Blac(K), index[5] = Go(L)d

        int[] card11 = new int[]{board.get("p11").getCostWhite(), board.get("p11").getCostBlue(), board.get("p11").getCostGreen(), board.get("p11").getCostRed(), board.get("p11").getCostBlack()};
        int[] card12 = new int[]{board.get("p12").getCostWhite(), board.get("p12").getCostBlue(), board.get("p12").getCostGreen(), board.get("p12").getCostRed(), board.get("p12").getCostBlack()};
        int[] card13 = new int[]{board.get("p13").getCostWhite(), board.get("p13").getCostBlue(), board.get("p13").getCostGreen(), board.get("p13").getCostRed(), board.get("p13").getCostBlack()};
        int[] card14 = new int[]{board.get("p14").getCostWhite(), board.get("p14").getCostBlue(), board.get("p14").getCostGreen(), board.get("p14").getCostRed(), board.get("p14").getCostBlack()};

        int[] card21 = new int[]{board.get("p21").getCostWhite(), board.get("p21").getCostBlue(), board.get("p21").getCostGreen(), board.get("p21").getCostRed(), board.get("p21").getCostBlack()};
        int[] card22 = new int[]{board.get("p22").getCostWhite(), board.get("p22").getCostBlue(), board.get("p22").getCostGreen(), board.get("p22").getCostRed(), board.get("p22").getCostBlack()};
        int[] card23 = new int[]{board.get("p23").getCostWhite(), board.get("p23").getCostBlue(), board.get("p23").getCostGreen(), board.get("p23").getCostRed(), board.get("p23").getCostBlack()};
        int[] card24 = new int[]{board.get("p24").getCostWhite(), board.get("p24").getCostBlue(), board.get("p24").getCostGreen(), board.get("p24").getCostRed(), board.get("p24").getCostBlack()};

        int[] card31 = new int[]{board.get("p31").getCostWhite(), board.get("p31").getCostBlue(), board.get("p31").getCostGreen(), board.get("p31").getCostRed(), board.get("p31").getCostBlack()};
        int[] card32 = new int[]{board.get("p32").getCostWhite(), board.get("p32").getCostBlue(), board.get("p32").getCostGreen(), board.get("p32").getCostRed(), board.get("p32").getCostBlack()};
        int[] card33 = new int[]{board.get("p33").getCostWhite(), board.get("p33").getCostBlue(), board.get("p33").getCostGreen(), board.get("p33").getCostRed(), board.get("p33").getCostBlack()};
        int[] card34 = new int[]{board.get("p34").getCostWhite(), board.get("p34").getCostBlue(), board.get("p34").getCostGreen(), board.get("p34").getCostRed(), board.get("p34").getCostBlack()};

        int[] card41 = new int[]{board.get("p41").getCostWhite(), board.get("p41").getCostBlue(), board.get("p41").getCostGreen(), board.get("p41").getCostRed(), board.get("p41").getCostBlack()};
        int[] card42 = new int[]{board.get("p42").getCostWhite(), board.get("p42").getCostBlue(), board.get("p42").getCostGreen(), board.get("p42").getCostRed(), board.get("p42").getCostBlack()};
        int[] card43 = new int[]{board.get("p43").getCostWhite(), board.get("p43").getCostBlue(), board.get("p43").getCostGreen(), board.get("p43").getCostRed(), board.get("p43").getCostBlack()};
        int[] card44 = new int[]{board.get("p44").getCostWhite(), board.get("p44").getCostBlue(), board.get("p44").getCostGreen(), board.get("p44").getCostRed(), board.get("p44").getCostBlack()};

        String[] cardMine = new String[]{board.get("p11").getColor(), board.get("p12").getColor(), board.get("p13").getColor(), board.get("p14").getColor(),
                board.get("p21").getColor(), board.get("p22").getColor(), board.get("p23").getColor(), board.get("p24").getColor(),
                board.get("p31").getColor(), board.get("p32").getColor(), board.get("p33").getColor(), board.get("p34").getColor()};

        for (int i = 0; i < cardMine.length; i++) {
            if(cardMine[i].equals("Red")){
                cardMine[i] = "\u001B[31mMINE";
            }else if (cardMine[i].equals("Blue")){
                cardMine[i] = "\u001B[34mMINE";
            }else if(cardMine[i].equals("White")){
                cardMine[i] = "\u001B[37mMINE";
            }else if (cardMine[i].equals("Black")){
                cardMine[i] = "\u001B[30mMINE";
            }else {
                cardMine[i] = "\u001B[32mMINE";
            }
        }



        //Position of bank index[0] = (W)hite, index[1] = Bl(U)e, index[2] = (G)reen, index[3] = (R)ed, index[4] = Blac(K), index[5] = Go(L)d




        return ("\u001B[37m----------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "\u001B[37mTABLE\n\u001B[37m----------------------------------------------------------------------------------------------------------------------------------------------------------------------" +
                "\n\u001B[37mBANK \t\t\t\t\t\t\t\t\u001B[33mGo(L)d  -> " +bank[5]+ "\u001B[37m\t\u001B[30mBlac(K) -> " +bank[4]+ "\u001B[37m\t\u001B[34mBl(U)e  -> " +bank[1]+ "\u001B[37m\t(W)hite -> " +bank[0]+ "\u001B[32m\t(G)reen -> " +bank[3]+ "\u001B[31m\t(R)ed   -> " +bank[4]+ "\u001B[37m\n" +
                "----------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "TIER 4\n\t\tPOS41\t\u001B[33mLORD\u001B[37m\tP00 \u001B[37m"+card41[0]+"W \u001B[34m"+card41[1]+"B \u001B[32m"+card41[2]+"G \u001B[31m"+card41[3]+"R \u001B[30m"+card41[4]+"K\t\u001B[37m |\t POS42\t\u001B[33mLORD\u001B[37m\tP00 \u001B[37m"+card42[0]+"W \u001B[34m"+card42[1]+"B \u001B[32m"+card42[2]+"G \u001B[31m"+card42[3]+"R \u001B[30m"+card42[4]+"K\t\u001B[37m |\t POS43\t\u001B[33mLORD\u001B[37m\tP00 \u001B[37m"+card43[0]+"W \u001B[34m"+card43[1]+"B \u001B[32m"+card43[2]+"G \u001B[31m"+card43[3]+"R \u001B[30m"+card43[1]+"K\t\u001B[37m |\t POS44\t\u001B[33mLORD\u001B[37m\tP00 \u001B[37m"+card44[0]+"W \u001B[34m"+card44[1]+"B \u001B[32m"+card44[2]+"G \u001B[31m"+card44[3]+"R \u001B[30m"+card44[4]+"K\t\u001B[37m |\t\n" +
                "TIER 4\n\t\tPOS31\t"+cardMine[8]+"\u001B[37m\tP00 \u001B[37m"+card31[0]+"W \u001B[34m"+card31[1]+"B \u001B[32m"+card31[2]+"G \u001B[31m"+card31[3]+"R \u001B[30m"+card31[4]+"K\t\u001B[37m |\t POS32\t"+cardMine[9]+"\u001B[37m\tP00 \u001B[37m"+card32[0]+"W \u001B[34m"+card42[1]+"B \u001B[32m"+card32[2]+"G \u001B[31m"+card32[3]+"R \u001B[30m"+card32[4]+"K\t\u001B[37m |\t POS43\t"+cardMine[10]+"\u001B[37m\tP00 \u001B[37m"+card33[0]+"W \u001B[34m"+card33[1]+"B \u001B[32m"+card33[2]+"G \u001B[31m"+card33[3]+"R \u001B[30m"+card33[1]+"K\t\u001B[37m |\t POS44\t"+cardMine[11]+"\u001B[37m\tP00 \u001B[37m"+card34[0]+"W \u001B[34m"+card34[1]+"B \u001B[32m"+card34[2]+"G \u001B[31m"+card34[3]+"R \u001B[30m"+card34[4]+"K\t\u001B[37m |\t\n" +
                "TIER 4\n\t\tPOS21\t"+cardMine[4]+"\u001B[37m\tP00 \u001B[37m"+card21[0]+"W \u001B[34m"+card21[1]+"B \u001B[32m"+card21[2]+"G \u001B[31m"+card21[3]+"R \u001B[30m"+card21[4]+"K\t\u001B[37m |\t POS22\t"+cardMine[5]+"\u001B[37m\tP00 \u001B[37m"+card22[0]+"W \u001B[34m"+card42[1]+"B \u001B[32m"+card22[2]+"G \u001B[31m"+card22[3]+"R \u001B[30m"+card22[4]+"K\t\u001B[37m |\t POS43\t"+cardMine[6]+"\u001B[37m\tP00 \u001B[37m"+card23[0]+"W \u001B[34m"+card23[1]+"B \u001B[32m"+card23[2]+"G \u001B[31m"+card23[3]+"R \u001B[30m"+card23[1]+"K\t\u001B[37m |\t POS44\t"+cardMine[7]+"\u001B[37m\tP00 \u001B[37m"+card24[0]+"W \u001B[34m"+card24[1]+"B \u001B[32m"+card24[2]+"G \u001B[31m"+card24[3]+"R \u001B[30m"+card24[4]+"K\t\u001B[37m |\t\n" +
                "TIER 4\n\t\tPOS11\t"+cardMine[0]+"\u001B[37m\tP00 \u001B[37m"+card11[0]+"W \u001B[34m"+card11[1]+"B \u001B[32m"+card11[2]+"G \u001B[31m"+card11[3]+"R \u001B[30m"+card11[4]+"K\t\u001B[37m |\t POS12\t"+cardMine[1]+"\u001B[37m\tP00 \u001B[37m"+card12[0]+"W \u001B[34m"+card42[1]+"B \u001B[32m"+card12[2]+"G \u001B[31m"+card12[3]+"R \u001B[30m"+card12[4]+"K\t\u001B[37m |\t POS13\t"+cardMine[2]+"\u001B[37m\tP00 \u001B[37m"+card13[0]+"W \u001B[34m"+card13[1]+"B \u001B[32m"+card13[2]+"G \u001B[31m"+card13[3]+"R \u001B[30m"+card13[1]+"K\t\u001B[37m |\t POS14\t"+cardMine[3]+"\u001B[37m\tP00 \u001B[37m"+card14[0]+"W \u001B[34m"+card14[1]+"B \u001B[32m"+card14[2]+"G \u001B[31m"+card14[3]+"R \u001B[30m"+card14[4]+"K\t\u001B[37m |\t\n" +
                "----------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "\t\t\t\tPLAYER 1 HAND\t\t\t     |\t\t\t\tPLAYER 2 HAND\t\t\t |\t\t\t\tPLAYER 3 HAND\t\t\t |\t\t\t\tPLAYER 4 HAND\t\t\t |\n" +
                "\t\t\t\t  Points: " + p1Pts + "\t\t\t\t     |\t\t\t\t  Points: " + p2Pts + "\t\t\t     |\t\t\t\t  Points: " + p3Pts + "\t\t\t     |\t\t\t\t  Points: " + p4Pts + "\t\t\t     |\n" +
                "\t\t\t\t\u001B[37m" + cp1[0] + "W \u001B[34m" + cp1[1] + "B \u001B[32m" + cp1[2] + "G \u001B[31m" + cp1[3] + "R \u001B[30m" + cp1[4] + "K\t\u001B[37m\t\t\t |\t\t\t\t\u001B[37m" + cp2[0] + "W \u001B[34m" + cp2[1] + "B \u001B[32m" + cp2[2] + "G \u001B[31m" + cp2[3] + "R \u001B[30m" + cp2[4] + "K\t\u001B[37m\t\t |\t\t\t\t\u001B[37m" + cp3[0] + "W \u001B[34m" + cp3[1] + "B \u001B[32m" + cp3[2] + "G \u001B[31m" + cp3[3] + "R \u001B[30m" + cp3[4] + "K\t\u001B[37m\t\t |\t\t\t\t\u001B[37m" + cp4[0] + "W \u001B[34m" + cp4[1] + "B \u001B[32m" + cp4[2] + "G \u001B[31m" + cp4[3] + "R \u001B[30m" + cp4[4] + "K\t\u001B[37m\t\t |\n" +
                "\t\t\t\t\u001B[37m" + pb1[0] + "W \u001B[34m" + pb1[1] + "B \u001B[32m" + pb1[2] + "G \u001B[31m" + pb1[3] + "R \u001B[30m" + pb1[4] + "K\t\u001B[37m\t\t\t |\t\t\t\t\u001B[37m" + pb2[0] + "W \u001B[34m" + pb2[1] + "B \u001B[32m" + pb2[2] + "G \u001B[31m" + pb2[3] + "R \u001B[30m" + pb2[4] + "K\t\u001B[37m\t\t |\t\t\t\t\u001B[37m" + pb3[0] + "W \u001B[34m" + pb3[1] + "B \u001B[32m" + pb3[2] + "G \u001B[31m" + pb3[3] + "R \u001B[30m" + pb3[4] + "K\t\u001B[37m\t\t |\t\t\t\t\u001B[37m" + pb4[0] + "W \u001B[34m" + pb4[1] + "B \u001B[32m" + pb4[2] + "G \u001B[31m" + pb4[3] + "R \u001B[30m" + pb4[4] + "K\t\u001B[37m\t\t |\n" +
                "----------------------------------------------------------------------------------------------------------------------------------------------------------------------");

    }
}
