package states;

import cards.Card;
import cards.Queen;
import position.AwokenQueenPosition;
import position.HandPosition;
import position.SleepingQueenPosition;

import java.util.*;

public class GameState {
    private final int numberOfPlayers;
    private int onTurn;
    private Set<SleepingQueenPosition> sleepingQueens;
    private Map<HandPosition, Optional<Card>> cards;
    private Map<AwokenQueenPosition, Queen> awokenQueens;
    private final List<Card> cardsDiscardedLastTurn;

    public GameState(int numberOfPlayers, int onTurn, Set<SleepingQueenPosition> sleepingQueens, Map<HandPosition, Optional<Card>> cards, Map<AwokenQueenPosition, Queen> awokenQueens, List<Card> cardsDiscardedLastTurn){
        this.numberOfPlayers = numberOfPlayers;
        this.onTurn = onTurn;
        this.cards = cards;
        this.awokenQueens = awokenQueens;
        this.cardsDiscardedLastTurn = cardsDiscardedLastTurn;
        this.sleepingQueens = sleepingQueens;
    }

    //getters
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

    //setters
    public void setOnTurn(int onTurn){
        this.onTurn = onTurn;
    }
    public void setSleepingQueens(Set<SleepingQueenPosition> sleepingQueens){
        this.sleepingQueens = new HashSet<>();
        this.sleepingQueens.addAll(sleepingQueens);
    }
    public void setCards(Map<HandPosition, Optional<Card>> cards){
        this.cards = new HashMap<>();
        this.cards.putAll(cards);
    }
    public void setAwokenQueens(Map<AwokenQueenPosition, Queen> awokenQueens){
        this.awokenQueens = awokenQueens;
        this.awokenQueens.putAll(awokenQueens);
    }
}
