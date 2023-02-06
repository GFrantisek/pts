package tests;

import java.util.*;


import cards.Card;
import cards.CardType;
import org.testng.annotations.Test;
import player.EvaluateNumberedCards;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class EvaluateNumberedCardsTest {
    private EvaluateNumberedCards evaluateNumberedCards;
    private List<Card> list;

    private void setUp(){
        evaluateNumberedCards = new EvaluateNumberedCards();
        list = new ArrayList<>();
    }

    @Test
    public void test1() {
        setUp();
        list.add(new Card(CardType.Number,6));
        assertTrue(evaluateNumberedCards.play(list));
    }

    @Test
    public void test2() {
        setUp();
        list.add(new Card(CardType.Number,5));
        list.add(new Card(CardType.Number,5));
        assertTrue(evaluateNumberedCards.play(list));
    }

    @Test
    public void test3() {
        setUp();
        list.add(new Card(CardType.Number,4));
        list.add(new Card(CardType.Number,2));
        list.add(new Card(CardType.Number,6));
        assertTrue(evaluateNumberedCards.play(list));
    }

    @Test
    public void test4() {
        setUp();
        list.add(new Card(CardType.Number,1));
        list.add(new Card(CardType.Number,4));
        list.add(new Card(CardType.Number,1));
        assertFalse(evaluateNumberedCards.play(list));
    }

    @Test
    public void test5() {
        setUp();
        list.add(new Card(CardType.Number,1));
        list.add(new Card(CardType.Number,4));
        assertFalse(evaluateNumberedCards.play(list));
    }

    @Test
    public void test6() {
        setUp();
        list.add(new Card(CardType.Number,1));
        list.add(new Card(CardType.Number,1));
        list.add(new Card(CardType.Number,1));
        list.add(new Card(CardType.Number,1));
        list.add(new Card(CardType.Number,4));
        assertTrue(evaluateNumberedCards.play(list));
    }


}

