import cards.Card;
import cards.CardType;
import cards.Queen;
import game.GameObservable;
import game.GameObserver;
import pile_and_hand_managment.DrawingAndTrashPile;
import player.EvaluateNumberedCards;
import position.HandPosition;
import position.SleepingQueenPosition;
import queen_collection.SleepingQueens;
import states.GameState;

import java.io.IOException;
import java.util.*;

public class Test {
    public static void main(String [] args) throws IOException {
        //deal five cards to each player, and place the remaining cards face down in a draw pile in the center of the queens.
        //12 king
        Map<HandPosition, Optional<Card>> player1Cards = new HashMap<>();
        Set<Queen> sleepingQueens = new HashSet<>();

        Card King = new Card(CardType.King,0);
        Card Number = new Card(CardType.Number, 9);
        Card Knight = new Card(CardType.Knight, 0);
        Card Number2 = new Card(CardType.Number, 3);
        Card Number3 = new Card(CardType.Number, 4);

        for (int i = 0; i < 12; i++ ) {
                sleepingQueens.add(new Queen(5));
        }

        player1Cards.put(new HandPosition(1), Optional.of(King));
        player1Cards.put(new HandPosition(2), Optional.of(Number));
        player1Cards.put(new HandPosition(3), Optional.of(Knight));
        player1Cards.put(new HandPosition(4), Optional.of(Number2));
        player1Cards.put(new HandPosition(5), Optional.of(Number3));

        List<Card> ke = new ArrayList<>();
        ke.add(new Card(CardType.Number,2));
        ke.add(new Card(CardType.Number,5));
        ke.add(new Card(CardType.Number,3));
        EvaluateNumberedCards ev = new EvaluateNumberedCards(ke);
        System.out.println(ev.play(ke));

        GameState state = new GameState(2,1,null,player1Cards, null, null);
        GameObservable observable = new GameObservable(state);

        DrawingAndTrashPile d = new DrawingAndTrashPile(5);
        d.frstDraw();
        System.out.println(d.discardAndDraw(ke));

        System.out.println(King.getValue() +"  " +  King.getType());
    }
}
