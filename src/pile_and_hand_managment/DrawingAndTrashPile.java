package pile_and_hand_managment;

import cards.Card;
import cards.CardType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DrawingAndTrashPile {

    private List<Card> trashPile;
    private List<Card> drawingPile;
    private List<Card> discardedThisTurn;
    private ShuffleStrategy shuffleStrategy;

   public DrawingAndTrashPile() {
       drawingPile = new ArrayList<>();
       shuffleStrategy = new ShuffleNotEnough();
       trashPile = new ArrayList<>();
       discardedThisTurn = new ArrayList<>();

       for(int i = 1; i <= 4; i++){
           for(int j = 0; j <= 10; j++){
               drawingPile.add(new Card(CardType.Number, j));
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

       Collections.shuffle(drawingPile, new Random());
   }
   public List<Card> discardAndDraw(List<Card> discard){

       discardedThisTurn = new ArrayList<>();
       discardedThisTurn.addAll(discard);
       List<Card> newDraw;

       if(drawingPile.size() < discard.size()){
            newDraw = shuffleStrategy.shuffle(this.drawingPile,this.trashPile, discard);
            this.trashPile = shuffleStrategy.getTrashPile();
            this.drawingPile = shuffleStrategy.getDrawingPile();

       }else{
           trashPile.addAll(discard);
           newDraw = new ArrayList<Card>();

           for(int i = 0; i < discard.size(); i++) {
               newDraw.add(drawingPile.get(drawingPile.size() - 1));
               drawingPile.remove(drawingPile.size() - 1);
           }
       }

       return newDraw;
   }

   public void newTurn() {
       discardedThisTurn.clear();
   }

   public  List<Card> getCardsDiscardedThisTurn(){
     return discardedThisTurn;
   }

   public List<Card> getDrawingPile() {
       return this.drawingPile;
   }

   public List<Card> drawFullHandOf5Cards() {
       List<Card> hand = new ArrayList<>();
       for (int i = 0; i <= 4; i++){
           hand.add(drawingPile.get(drawingPile.size() - 1));
           drawingPile.remove(drawingPile.size() - 1);
       }
       return hand;
   }

    public boolean isEmpty() {
       return this.drawingPile.isEmpty();
    }

    public int size() {
       return this.drawingPile.size();
    }
}
