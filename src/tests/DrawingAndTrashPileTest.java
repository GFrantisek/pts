package tests;


import cards.Card;
import cards.CardType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pile_and_hand_managment.DrawingAndTrashPile;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class DrawingAndTrashPileTest {

    private DrawingAndTrashPile drawingAndTrashPile;
    private List<Card> cardsToDiscard;
    @Before
    public void setUp() throws Exception {
        drawingAndTrashPile = new DrawingAndTrashPile();
    }

    public void setUpCardsToDiscard() throws Exception {
        cardsToDiscard = new ArrayList<>();
        cardsToDiscard.add(new Card(CardType.Knight, 0));
        cardsToDiscard.add(new Card(CardType.Number, 3));
        cardsToDiscard.add(new Card(CardType.Number, 2));
    }

    @Test
    public void newTurn_base() throws Exception {
        setUp();
        drawingAndTrashPile.newTurn();
        assertTrue(drawingAndTrashPile.getCardsDiscardedThisTurn().isEmpty());
    }

    @Test
    public void newTurn_withAddedCards() throws Exception {
        setUp();
        setUpCardsToDiscard();

        drawingAndTrashPile.discardAndDraw(cardsToDiscard);
        drawingAndTrashPile.newTurn();
        assertTrue(drawingAndTrashPile.getCardsDiscardedThisTurn().isEmpty());
    }

    @Test
    public void discardsAndDrawsSameNumberOfCards() throws Exception {
        setUp();
        setUpCardsToDiscard();

        List<Card> drawnCards = drawingAndTrashPile.discardAndDraw(cardsToDiscard);
        assertEquals(3, drawnCards.size());
        assertEquals(3, drawingAndTrashPile.getCardsDiscardedThisTurn().size());
    }

    @Test
    public void drawFullHandOfCards() throws Exception {
        setUp();
        List<Card> cards = new ArrayList<Card>(drawingAndTrashPile.drawFullHandOf5Cards());
        assertEquals(5,cards.size());
    }

    @Test
    public void correctDiscarding() throws Exception {
        setUp();
        List<Card> cards = drawingAndTrashPile.drawFullHandOf5Cards();
        cards.remove(cards.get(0));
        cards.remove(cards.get(1));
        drawingAndTrashPile.newTurn();
        drawingAndTrashPile.discardAndDraw(cards);
        assertEquals(cards, drawingAndTrashPile.getCardsDiscardedThisTurn());
    }

    @Test
    public void discardAndShuffle() throws Exception {
        setUp();
        List<Card> discard = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            discard.add(new Card(CardType.Number,i));
        }
        List<Card> drawn = drawingAndTrashPile.discardAndDraw(discard);
        assertEquals(20, drawn.size());
        assertEquals(20,drawingAndTrashPile.getCardsDiscardedThisTurn().size());
        assertFalse(drawingAndTrashPile.getDrawingPile().isEmpty());
    }

}
