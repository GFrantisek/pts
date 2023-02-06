package game;

import cards.Card;
import position.HandPosition;
import states.GameState;

import java.util.*;

//finish asap
public class GameObservable {
    private List<GameObserver> observers;
    private Map<Integer, GameObserver> observersOfPlayers;

    public GameObservable(){
        this.observers = new ArrayList<>();
        this.observersOfPlayers = new HashMap<>();
    }

    public void add(GameObserver observer){ observers.add(observer); }
    //dont know if i will use it, but just to be sure thats its here if i need
    public void remove(GameObserver observer){ observers.remove(observer);}

    public void addPlayer(int playerIndex, GameObserver observer){
        if( (observersOfPlayers.size() <= 5) && (playerIndex >= 0 || playerIndex < 4)) {
            observersOfPlayers.put(playerIndex, observer);
            observers.add(observer);
            return;
        }

        System.out.println("Wrong index / too many players ... unable to add");
        //could implement in try catch
    }

    public void notifyAll(GameState message){
        String res = "Current game-state: \n";
        res += "num of players: " + message.numberOfPlayers + "\n";
        res += "on turn: " + message.onTurn + "\n";
        res += "sleeping queens positions: " + message.sleepingQueens + "\n";
        res += "awoken queens: " + message.awokenQueens + "\n";
        res += "cards discarded this turn: " + message.cardsDiscardedLastTurn + "\n";

        for (GameObserver observer : observers){
            for (Map.Entry<Integer, GameObserver> playerObs : observersOfPlayers.entrySet()) {
                if (playerObs.getValue().equals(observer)) {
                    int playerIdx = playerObs.getKey();
                    Map<Integer, Optional<Card>> playerCards = new HashMap<>();
                    for (Map.Entry<HandPosition, Optional<Card>> cards : message.cards.entrySet()) {
                        if (cards.getKey().getPlayerIndex() == playerIdx) {
                            playerCards.put(cards.getKey().getCardIndex(), cards.getValue());
                        }
                    }
                    res += "\nYour Cards: " + playerCards;
                }
            }
            observer.notify(res);
        }
    }
    public int getNumberOfObservers() {return observersOfPlayers.size();}
    public List<GameObserver> getObservers(){
        return observers;
    }
    public Map<Integer, GameObserver> getObserversOfPlayers() {
        return observersOfPlayers;
    }
}
