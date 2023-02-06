package pile_and_hand_managment;

import cards.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ShuffleEnoughCards implements ShuffleStrategy {

    private List<Card>  newTrashCards;
    private List<Card> newDrawCards;

    @Override
    public List<Card> shuffle(List<Card> drawingCards, List<Card> trashCards, List<Card> toBeDiscardedCards) {
        List<Card> cardsToReturn = new ArrayList<>(drawingCards);

        newDrawCards =  new ArrayList<>();
        newDrawCards.addAll(trashCards);
        newDrawCards.addAll(drawingCards);
        Collections.shuffle(newDrawCards, new Random());

        for(int i = 0; i < toBeDiscardedCards.size(); i++){
            cardsToReturn.add(newDrawCards.get(newDrawCards.size()-1));
            newDrawCards.remove(newDrawCards.size() - 1 );
        }

        newTrashCards.addAll(toBeDiscardedCards);
        return cardsToReturn;
    }
    @Override
    public List<Card> getTrashPile() {
        return newTrashCards;
    }

    @Override
    public List<Card> getDrawingPile() {
        return newDrawCards;
    }
}

