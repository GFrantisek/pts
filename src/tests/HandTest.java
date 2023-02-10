package tests;

import cards.Card;
import cards.CardType;
import org.junit.Before;
import org.junit.Test;
import pile_and_hand_managment.BasicDeck;
import pile_and_hand_managment.DrawingAndTrashPile;
import pile_and_hand_managment.FakeShuffle;
import pile_and_hand_managment.Hand;
import position.HandPosition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.*;

public class HandTest {
    private Hand hand;
    private int playerIndex;
    private List<HandPosition> positions;
    private DrawingAndTrashPile drawingAndTrashPile;
    @Before
    public void setUp() {
        BasicDeck basicDeck = new BasicDeck();
        List<Card> basicCard = basicDeck.createBasicDeck();
        drawingAndTrashPile = new DrawingAndTrashPile(basicCard, new FakeShuffle());
    }

    @Test
    public void testGetCards() {
        DeckForHandTesting dec = new DeckForHandTesting();
        List<Card> cardForPile = dec.createDeckForTesting();
        DrawingAndTrashPile drawP = new DrawingAndTrashPile(cardForPile,new FakeShuffle());

        Hand hand = new Hand(drawP, 1);
        List<Card> handCards = hand.getCards();
        List<Card> testC = dec.getlast5();

        int i = 0;
        for (Card card : handCards) {
            assertEquals(card.getType(),testC.get(i).getType());
            i = i + 1;
        }

    }

    @Test
    public void testPickCards() {
        //viem co ma padnut teda viem sa pozriet do handy a na pozicie
        DeckForHandTesting dec = new DeckForHandTesting();
        List<Card> cardForPile = dec.createDeckForTesting();
        DrawingAndTrashPile drawP = new DrawingAndTrashPile(cardForPile,new FakeShuffle());

        Hand hand = new Hand(drawP, 0);
        List<HandPosition> positions = new ArrayList<>();
        positions.add(new HandPosition(0, 1));
        Optional<List<Card>> pickedCards = hand.pickCards(positions);
       // System.out.println(pickedCards.get());

        List<Card> checkPick = new ArrayList<>();
        checkPick.add(hand.getCards().get(1));

        assertEquals(checkPick,pickedCards.get());
    }

    @Test
    public void testPickCardsNoPositions() {
        Hand hand = new Hand(drawingAndTrashPile, 1);
        List<HandPosition> positions = new ArrayList<>();
        Optional<List<Card>> pickedCards = hand.pickCards(positions);
        assertFalse(pickedCards.isPresent());
    }

    @Test
    public void testRemovePickedCardsAndRedraw() {
        //Testing whether i actaully draw something different and throw away correct card
        DeckForHandTesting dec = new DeckForHandTesting();
        List<Card> cardForPile = dec.createDeckForTesting();
        DrawingAndTrashPile drawP = new DrawingAndTrashPile(cardForPile,new FakeShuffle());


        Hand hand = new Hand(drawP, 1);

        List<HandPosition> positions = new ArrayList<>();
        positions.add(new HandPosition(1, 1));
        hand.pickCards(positions);
        System.out.println(hand.getCards().get(1).getType());
        CardType throwingAway = hand.getCards().get(1).getType();


        Map<HandPosition, Card> result = hand.removePickedCardsAndRedraw();
        CardType recieving12 =hand.getCards().get(1).getType();

        assertNotEquals(throwingAway,recieving12);

        //stare equals
        assertEquals(result.size(), 1);
        assertEquals(hand.getCards().size(), 5);
    }

    @Test
    public void testReturnPickedCards() {
        //tento test nechavam tak lebo nehladiac na pile zalezi mi len ci je prazdna dana ruka
        Hand hand = new Hand(drawingAndTrashPile, 1);
        List<HandPosition> positions = new ArrayList<>();
        positions.add(new HandPosition(0, 1));
        hand.pickCards(positions);
        hand.returnPickedCards();
        assertEquals(0, hand.getPickedCards().size());
    }

    @Test
    public void testHasCardOfType() {
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
