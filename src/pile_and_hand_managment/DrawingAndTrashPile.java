package pile_and_hand_managment;

import cards.Card;
import cards.CardType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DrawingAndTrashPile {
    List<Card> allCards;
    List<Card> trashPile;
    List<Card> drawingPile;
    List<Card> discardedThisTurn;
    int cardsPerPlayer;

    public DrawingAndTrashPile() {
        this.drawingPile = new ArrayList<>();
        this.allCards = new ArrayList<>();
        this.discardedThisTurn = new ArrayList<>();
        this.cardsPerPlayer = 5;
        for(int i = 1; i <= 10; i++){
            for(int j = 0; j < 4; j++){
                drawingPile.add(new Card(CardType.Number, i));
            }
        }
        for (int i = 0; i < 8; i++){
            drawingPile.add(new Card(CardType.King, 0));
        }
        for(int i = 0; i < 4; i++){
            drawingPile.add(new Card(CardType.Knight, 0));
            drawingPile.add(new Card(CardType.SleepingPotion, 0));
        }
        for(int i = 0; i < 3; i++){
            drawingPile.add(new Card(CardType.Dragon, 0));
            drawingPile.add(new Card(CardType.MagicWand, 0));
        }
        allCards.addAll(drawingPile);

        Collections.shuffle(drawingPile);
        for (int i = 0; i < 3; i++) {
            System.out.println(allCards.get(i).getType());
        }
        for(int i = 0; i < 10; i++){
            System.out.print(drawingPile.get(i).getValue() + " ");
        }
        System.out.println();
        trashPile = new ArrayList<>();
    }

    public List<Card> frstDraw(){
        //neriesim keby hra vela hracov a nemame dostatok kariet

        List<Card> ret = new ArrayList<>();
        for(int i = 0; i < cardsPerPlayer; i++){
                ret.add(drawingPile.get(0));
            drawingPile.remove(0);
        }

        return ret;
    }

    void reShuffle(){
        drawingPile = new ArrayList<>();
        drawingPile.addAll(allCards);
        Collections.shuffle(drawingPile);
        discardedThisTurn = new ArrayList<>();
        trashPile = new ArrayList<>();
    }

    public List<Card> discardAndDraw(List<Card> discard){
        List<Card> ret = new ArrayList<>();

        if(discard.size()>drawingPile.size()) {
            // nie velmi pekna metoda na reshuffle
            reShuffle();
        }
        trashPile.addAll(discard);
        discardedThisTurn.addAll(discard);
        if(discard != null){
            trashPile.addAll(discard);
            discardedThisTurn.addAll(discard);

            for(int i = 0; i <discard.size(); i++){
                if(drawingPile.size() == 0) reShuffle();
                ret.add(drawingPile.get(0));
                drawingPile.remove(0);
            }
        }
        return ret;
    }

    void newTurn(){
        discardedThisTurn = null;
    }

    List<Card> getCardsDiscardedThisTurn(){
        return discardedThisTurn;
    }
}
