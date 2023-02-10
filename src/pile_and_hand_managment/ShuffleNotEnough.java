package pile_and_hand_managment;

import cards.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ShuffleNotEnough implements ShuffleStrategy{
    private List<Card>  newTrashCards;
    private List<Card> newDrawCards;
    private ShuffleInterface shuffleInterface;
    public ShuffleNotEnough(ShuffleInterface shuffleInterface) {
        this.shuffleInterface = shuffleInterface;

    }
    @Override
    public List<Card> shuffle(List<Card> drawingCards, List<Card> trashCards, List<Card> toBeDiscardedCards) {
        newDrawCards =  new ArrayList<>();
        newDrawCards.addAll(trashCards);
        newDrawCards.addAll(toBeDiscardedCards);


        this.shuffleInterface.shuffleCards(newDrawCards);


        List<Card> cardsToReturn = new ArrayList<>(drawingCards);

        for (int i = 0; i < toBeDiscardedCards.size() - drawingCards.size(); i++) {
            cardsToReturn.add(newDrawCards.get(newDrawCards.size() - 1));
            newDrawCards.remove(newDrawCards.size() - 1);
        }

        newTrashCards = new ArrayList<>();

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
