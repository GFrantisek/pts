package pile_and_hand_managment;

import cards.Card;

import java.util.List;

public interface ShuffleStrategy {
    List<Card> shuffle(List<Card> drawingCards, List<Card> trashCards, List<Card> toBeDiscardedCards);
    List<Card> getTrashPile();
    List<Card> getDrawingPile();
}
