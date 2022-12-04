package states;

import cards.Card;
import position.HandPosition;
import position.SleepingQueenPosition;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class GameState {
    int numberOfPlayers;
    int onTurn;
    Set<SleepingQueenPosition> sleepingQueens;
    Map<HandPosition, Optional<Card>> cards;
}
