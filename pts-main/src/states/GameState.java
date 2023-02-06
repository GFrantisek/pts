package states;

import cards.Card;
import cards.Queen;
import position.AwokenQueenPosition;
import position.HandPosition;
import position.SleepingQueenPosition;

import java.util.*;

public class GameState {
    public int numberOfPlayers;
    public int onTurn;
    public Set<SleepingQueenPosition> sleepingQueens;
    public Map<HandPosition, Optional<Card>> cards;
    public Map<AwokenQueenPosition, Queen> awokenQueens;
    public List<Card> cardsDiscardedLastTurn;
}
