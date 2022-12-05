package states;

import cards.Card;
import cards.Queen;
import position.AwokenQueenPosition;
import position.HandPosition;
import position.SleepingQueenPosition;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class GameState {
    private int numberOfPlayers;
    private int onTurn;
    private Set<SleepingQueenPosition> sleepingQueens;
    private Map<HandPosition, Optional<Card>> cards;
    private Map<AwokenQueenPosition, Queen> awokenQueens;
    private List<Card> cardsDiscardedLastTurn;

    public GameState(int numberOfPlayers, int onTurn, Set<SleepingQueenPosition> sleepingQueens, Map<HandPosition, Optional<Card>> cards, Map<AwokenQueenPosition, Queen> awokenQueens, List<Card> cardsDiscardedLastTurn){
        this.numberOfPlayers = numberOfPlayers;
        this.onTurn = onTurn;
        this.cards = cards;
        this.awokenQueens = awokenQueens;
        this.cardsDiscardedLastTurn = cardsDiscardedLastTurn;
        this.sleepingQueens = sleepingQueens;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public int getOnTurn() {
        return onTurn;
    }

    public Set<SleepingQueenPosition> getSleepingQueens() {
        return sleepingQueens;
    }


    public Map<HandPosition, Optional<Card>> getCards() {
        return cards;
    }

    public Map<AwokenQueenPosition, Queen> getAwokenQueens() {
        return awokenQueens;
    }

    public List<Card> getCardsDiscardedLastTurn() {
        return cardsDiscardedLastTurn;
    }
}
