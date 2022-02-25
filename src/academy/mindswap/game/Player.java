package academy.mindswap.game;
import academy.mindswap.cards.Card;
import academy.mindswap.utils.Messages;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class Player {

    private String name;
    private LinkedList<Card> playerReservedCards;
    private int[] ownedMines;
    private int[] bank;
    private int score;
    private boolean playerCanBuy;
    private boolean hasPlayed;
    private boolean isPlaying;
    private HashMap<String, Card> table;


    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.ownedMines = new int[]{0, 0, 0, 0, 0};
        this.bank = new int[]{0, 0, 0, 0, 0, 0};
        this.hasPlayed = false;
        //Position of bank index[0] = (W)hite, index[1] = Bl(U)e, index[2] = (G)reen, index[3] = (R)ed, index[4] = Blac(K), index[5] = Go(L)d

    }

    public void reserveCard(String positionOfCard) {

        if( playerReservedCards.size() == 3){
            System.out.println(Messages.CANT_RESERVE);
            return;
        }
        this.bank[5] += 1;
        playerReservedCards.add(table.get(positionOfCard));
    }


    public void grabGems(String gems) {

        int[] temp = this.bank;

        for (int i = 0; i < gems.length(); i++) {
            temp[i] = gems.charAt(i);
        }

        int bankTotal = Arrays.stream(temp).reduce(0, Integer::sum);

        if (bankTotal > 10){
            System.out.println(Messages.MORE_THAN_10);
            return;
        }
        this.bank = temp;
    }


    public void buyCard(String cardToBuy) {

        if(canBuyWhite(table.get(cardToBuy))
                && canBuyBlue(table.get(cardToBuy))
                && canBuyGreen(table.get(cardToBuy))
                && canBuyRed(table.get(cardToBuy))
                && canBuyBlack(table.get(cardToBuy))) {

            this.ownedMines[0] -= table.get(cardToBuy).getCostWhite();
            this.ownedMines[1] -= table.get(cardToBuy).getCostBlue();
            this.ownedMines[2] -= table.get(cardToBuy).getCostGreen();
            this.ownedMines[3] -= table.get(cardToBuy).getCostRed();
            this.ownedMines[4] -= table.get(cardToBuy).getCostBlue();

            this.score += table.get(cardToBuy).getPoints();

        }

        System.out.println(Messages.CANT_BUY);
    }


    private boolean canBuyWhite( Card card) {
        return card.getCostWhite() <= ownedMines[0]
                || card.getCostWhite() <= ownedMines[0] + bank[0]
                || card.getCostWhite() <= ownedMines[0] + bank[0] + bank[5];
    }

    private boolean canBuyBlue(Card card) {
        return card.getCostBlue() <= ownedMines[1]
                || card.getCostBlue() <= ownedMines[1] + bank[1]
                || card.getCostBlue() <= ownedMines[1] + bank[1] + bank[5];
    }

    private boolean canBuyGreen(Card card) {
        return card.getCostGreen() <= ownedMines[2]
                || card.getCostGreen() <= ownedMines[2] + bank[2]
                || card.getCostGreen() <= ownedMines[2] + bank[2] + bank[5];
    }

    private boolean canBuyRed(Card card) {
        return card.getCostRed() <= ownedMines[3]
                || card.getCostRed() <= ownedMines[3] + bank[3]
                || card.getCostRed() <= ownedMines[3] + bank[3] + bank[5];
    }

    private boolean canBuyBlack(Card card) {
        return card.getCostBlack() <= ownedMines[4]
                || card.getCostBlack() <= ownedMines[4] + bank[4]
                || card.getCostBlack() <= ownedMines[4] + bank[4] + bank[5];
    }

    private String win(){
        return score >= 15 ? Messages.I_WIN_MOTHERFUCKERS : Messages.KEEP_PLAYING;
    }

    public void setPlaying(Boolean isPlaying){
        this.isPlaying = isPlaying;
    }

    public boolean isPlaying(){
        return isPlaying;
    }

    public int getScore() {
        return score;
    }


    public int[] getOwnedMines() {
        return ownedMines;
    }

    public int[] getBank() {
        return bank;
    }

    public String getName() {
        return name;
    }
}
