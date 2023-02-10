package tests;


import cards.Card;
import cards.CardType;
import org.junit.Before;
import org.junit.Test;
import pile_and_hand_managment.BasicDeck;
import pile_and_hand_managment.DrawingAndTrashPile;
import pile_and_hand_managment.FakeShuffle;
import pile_and_hand_managment.RealShuffle;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;


public class DrawingAndTrashPileTest {

    private DrawingAndTrashPile drawingAndTrashPile;
    private List<Card> cardsToDiscard;
    @Before
    public void setUp() throws Exception {
        BasicDeck basicDeck = new BasicDeck();
        List<Card> basicCard = basicDeck.createBasicDeck();
        drawingAndTrashPile = new DrawingAndTrashPile(basicCard,new FakeShuffle());
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

    //pridal som 3 nove testy 1 na funkcionalitu, 1 na metodu tahania 5 kariet, 1 na metodu kedy discardne 2 karty a potiahnem 2 nove
    @Test
    public void controlOverShuffle() throws Exception {
        //test overujuci tento aspekt funkcionality
        //real a fake mi spravia to ze real ma shufflovat fake nie
        // notsame -> potiahni 5 karat porovnaj vie to failnut v pripade ze nahodou miesanie sa dostane do rovnakej pozicie ako pred meisanim ale sanca je prilis mala
        // assertEqual -> porovna ci sa skutocne aplikoval FakeShuffle a zabranilo sa zamiesaniu


        BasicDeck basicDeck = new BasicDeck();
        List<Card> basicCard = basicDeck.createBasicDeck();


        DrawingAndTrashPile drawPile1 = new DrawingAndTrashPile(basicCard, new RealShuffle());
        DrawingAndTrashPile drawPile2 = new DrawingAndTrashPile(basicCard, new FakeShuffle());

        assertNotSame(drawPile1.drawFullHandOf5Cards(), drawPile2.drawFullHandOf5Cards());
        assertEquals(basicCard,drawPile2.getDrawingPile());

    }

    @Test
    public  void correctDrawOfExact5(){
        //vdaka kontrole nad shufflom viem skontrolovat lepsie metodu drawFullHands
        //kusok divne porovnanie ale v podstate sa len chcem pozriet ci fakt vytiahne poslednych 5 kariet pridanych do drawing pilu
        //teda porovnam veci vytiahnute z pile s compare a pomocou toho sa presvedcim ze by metoda mala fungovat
        List<Card> pile = new ArrayList<Card>();
        pile.add(new Card(CardType.Number,2));
        pile.add(new Card(CardType.Number,1));
        pile.add(new Card(CardType.Number,3));
        pile.add(new Card(CardType.Number,5));
        pile.add(new Card(CardType.King,0));
        pile.add(new Card(CardType.Knight,0));
        pile.add(new Card(CardType.SleepingPotion,0));


        List<Card> compare = new ArrayList<Card>();
        compare.add(new Card(CardType.Number,3));
        compare.add(new Card(CardType.Number,5));
        compare.add(new Card(CardType.King,0));
        compare.add(new Card(CardType.Knight,0));
        compare.add(new Card(CardType.SleepingPotion,0));

        DrawingAndTrashPile drawPile = new DrawingAndTrashPile(pile, new FakeShuffle());
        List<Card> draw5 = drawPile.drawFullHandOf5Cards();

        int i = 4;
        //divna metoda na assert ale pozeram od poslednej karty a kedze vidim co je v com moezm porovnavat cez types.
        for (Card card : draw5) {
            assertEquals(card.getType().toString(),compare.get(i).getType().toString());
            i=i-1;
        }
    }


    @Test
    public void correctDiscardingWithExactPile(){
        List<Card> pile = new ArrayList<Card>();
        pile.add(new Card(CardType.Number,2));
        pile.add(new Card(CardType.Number,1));
        pile.add(new Card(CardType.Number,3));
        pile.add(new Card(CardType.Number,5));
        pile.add(new Card(CardType.King,0));
        pile.add(new Card(CardType.Knight,0));
        pile.add(new Card(CardType.SleepingPotion,0));

        List<Card> discard = new ArrayList<Card>();
        discard.add(new Card(CardType.Number, 7));
        discard.add(new Card(CardType.Number, 7));

        List<Card> newDrawCorrect = new ArrayList<Card>();
        newDrawCorrect.add(new Card(CardType.Knight,0));
        newDrawCorrect.add(new Card(CardType.SleepingPotion,0));

        DrawingAndTrashPile drawPile = new DrawingAndTrashPile(pile, new FakeShuffle());
        List<Card> newDrawTest = drawPile.discardAndDraw(discard);

        int i = 1;
        for (Card card : newDrawTest) {
            assertEquals(card.getType().toString(),newDrawCorrect.get(i).getType().toString());
            i = 0;


    }
    }

}
