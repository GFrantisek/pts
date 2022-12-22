package game;

import states.GameState;
//finish asap
public class GameObservable implements GameObserver{
    GameState currentStateOfGame;

    @Override
    public String notify(String message) {
        return null;
    }

    public GameObservable(GameState currentStateOfGame){
        this.currentStateOfGame = currentStateOfGame;
    }

    void add(){}

    void addPlayer(){}

    void remove(){}

    public void notifyAlls(){
        System.out.println(
                "Current GAME STATE: \n" +
                "Number of PLAYERS: " + currentStateOfGame.getNumberOfPlayers() + "\n" +
                "On TURN: " + currentStateOfGame.getOnTurn() + "\n" +
                "Sleeping QUEENS: " + currentStateOfGame.getSleepingQueens().toString() + "\n" +
                "Awoken QUEENS: " + currentStateOfGame.getAwokenQueens().toString() + "\n" +
                "CARDS: " + currentStateOfGame.getCards().toString() + "\n" +
                "Discarded CARDS" + currentStateOfGame.getCardsDiscardedLastTurn()
        );
    }
}
