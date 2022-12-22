package player;

import game.Game;
import pile_and_hand_managment.Hand;
import position.AwokenQueenPosition;
import position.Position;
import position.SleepingQueenPosition;
import queen_collection.AwokenQueens;
import queen_collection.SleepingQueens;
import states.PlayerState;

import java.util.List;

public class Player {
    public PlayerState playerState;
    public Hand hand;
    public int playerIndex;
    public Game game;
    public AwokenQueens awokenQueens;
    public SleepingQueens sleepingQueens;

    public Player(Game game, int PlayerIndex){
        this.game = game;
        this.playerIndex = PlayerIndex;
        hand = new Hand(this);
    }

    public boolean play (List<Position> cards){
        return true;
    }

    public boolean play (List<Position> cards, SleepingQueenPosition sleepPos, Position king){
        return true;

    }

    public boolean play (List<Position> cards, AwokenQueenPosition sleepPos, Position attackCard, Player targetPlayer){
        return true;

    }

    public PlayerState getPlayerState(){
        return state;
    }
}
