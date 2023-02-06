package tests;

import cards.Card;
import cards.CardType;
import org.junit.Before;
import org.junit.Test;
import pile_and_hand_managment.DrawingAndTrashPile;
import pile_and_hand_managment.Hand;
import position.HandPosition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.testng.Assert.*;

public class HandTest {
    private Hand hand;
    private int playerIndex;
    private List<HandPosition> positions;

    //Trying AI to generate automatised deter tests it didnt add setup but all in all lovely
    //had to do some changes cuz it put some wrong assumptions but about 90% correct
    //i know it looks better with setup but i like it this way just because i want to leave at least one test raw ai generated


    @Test
    public void testGetCards() {
        DrawingAndTrashPile drawingAndTrashPile = new DrawingAndTrashPile();
        Hand hand = new Hand(drawingAndTrashPile, 1);
        assertTrue(hand.getCards().size() > 0);
    }

    @Test
    public void testPickCards() {
        DrawingAndTrashPile drawingAndTrashPile = new DrawingAndTrashPile();
        Hand hand = new Hand(drawingAndTrashPile, 1);
        List<HandPosition> positions = new ArrayList<>();
        positions.add(new HandPosition(0, 1));
        Optional<List<Card>> pickedCards = hand.pickCards(positions);
        assertTrue(pickedCards.isPresent());
        assertEquals(1, pickedCards.get().size());
    }

    @Test
    public void testPickCardsNoPositions() {
        DrawingAndTrashPile drawingAndTrashPile = new DrawingAndTrashPile();
        Hand hand = new Hand(drawingAndTrashPile, 1);
        List<HandPosition> positions = new ArrayList<>();
        Optional<List<Card>> pickedCards = hand.pickCards(positions);
        assertFalse(pickedCards.isPresent());
    }

    @Test
    public void testRemovePickedCardsAndRedraw() {
        DrawingAndTrashPile drawingAndTrashPile = new DrawingAndTrashPile();
        Hand hand = new Hand(drawingAndTrashPile, 1);
        List<HandPosition> positions = new ArrayList<>();
        positions.add(new HandPosition(0, 1));
        hand.pickCards(positions);
        Map<HandPosition, Card> result = hand.removePickedCardsAndRedraw();
        assertEquals(result.size(), 1);
        assertEquals(hand.getCards().size(), 5);
    }

    @Test
    public void testReturnPickedCards() {
        DrawingAndTrashPile drawingAndTrashPile = new DrawingAndTrashPile();
        Hand hand = new Hand(drawingAndTrashPile, 1);
        List<HandPosition> positions = new ArrayList<>();
        positions.add(new HandPosition(0, 1));
        hand.pickCards(positions);
        hand.returnPickedCards();
        assertEquals(0, hand.getPickedCards().size());
    }

    @Test
    public void testHasCardOfType() {
        DrawingAndTrashPile drawingAndTrashPile = new DrawingAndTrashPile();
        Hand hand = new Hand(drawingAndTrashPile, 0);
        List<Card> fullHand = hand.getCards();
        boolean cardOfTypeFound = false;
        for (Card card : fullHand) {
            if (card.getType() == CardType.Number) {
                cardOfTypeFound = true;
                break;
            }
        }
        assertTrue(cardOfTypeFound);

        HandPosition handPosition = hand.hasCardOfType(CardType.Number);
        assertNotNull(handPosition);
        assertEquals(handPosition.getPlayerIndex(), 0);
    }

}
