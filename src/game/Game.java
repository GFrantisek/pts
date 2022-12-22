package game;

import pile_and_hand_managment.DrawingAndTrashPile;
import player.Player;
import queen_collection.SleepingQueens;
import states.PlayerState;

import javax.swing.text.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


//NOT FINISHED .... DO IT ASAP
public class Game implements GameFinishedStrategy {
    public DrawingAndTrashPile pile;
    public List<Player> players;
    public int numOfPlayers;
    public int onTurn;
    public SleepingQueens sleepingQueens;

    public Game (int numOfPlayers){
        this.pile = new DrawingAndTrashPile();
        this.players = new ArrayList<>();
        this.numOfPlayers = numOfPlayers;

        for (int i = 0; i < numOfPlayers; i++) {
            Player addingPlayer;
        }
    }

    void play(Integer playerIdx, List<Position> cards) {

    }

    @Override
    public Optional<Integer> isFinished() {
        return Optional.empty();

    }
}
