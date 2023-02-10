package tests;

import cards.Card;
import cards.CardType;

import java.util.ArrayList;
import java.util.List;

public class DeckForHandTesting {
    public List<Card> createDeckForTesting (){
        List<Card> drawingPile = new ArrayList<>();
        drawingPile.add(new Card(CardType.Knight,0));
        drawingPile.add(new Card(CardType.King,0));
        drawingPile.add(new Card(CardType.Dragon,0));
        drawingPile.add(new Card(CardType.King,0));
        drawingPile.add(new Card(CardType.Knight,0));
        drawingPile.add(new Card(CardType.Knight,0));
        drawingPile.add(new Card(CardType.King,0));
        drawingPile.add(new Card(CardType.Dragon,0));
        drawingPile.add(new Card(CardType.King,0));
        drawingPile.add(new Card(CardType.Knight,0));
        drawingPile.add(new Card(CardType.Knight,0));
        drawingPile.add(new Card(CardType.King,0));
        drawingPile.add(new Card(CardType.Dragon,0));
        drawingPile.add(new Card(CardType.King,0));
        drawingPile.add(new Card(CardType.Knight,0));
        drawingPile.add(new Card(CardType.Number,4));
        drawingPile.add(new Card(CardType.MagicWand,0));
        drawingPile.add(new Card(CardType.Dragon,0));
        drawingPile.add(new Card(CardType.Number,8));
        drawingPile.add(new Card(CardType.SleepingPotion,0));
        drawingPile.add(new Card(CardType.Knight,0));
        drawingPile.add(new Card(CardType.King,0));
        return drawingPile;
    }

    public List<Card> getlast5(){
        List<Card> drawingPile = new ArrayList<>();
        drawingPile.add(new Card(CardType.King,0));
        drawingPile.add(new Card(CardType.Knight,0));
        drawingPile.add(new Card(CardType.SleepingPotion,0));
        drawingPile.add(new Card(CardType.Number,8));
        drawingPile.add(new Card(CardType.Dragon,0));
        return drawingPile;


    }
}
