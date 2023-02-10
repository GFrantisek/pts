package pile_and_hand_managment;

import cards.Card;
import cards.CardType;

import java.util.ArrayList;
import java.util.List;

public class BasicDeck {
    public List<Card> createBasicDeck (){
        List<Card> drawingPile = new ArrayList<>();
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
        return drawingPile;
    }
}
