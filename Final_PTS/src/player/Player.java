package player;

import position.AwokenQueenPosition;
import position.Position;
import position.SleepingQueenPosition;
import states.PlayerState;

import java.util.List;

public class Player {
    PlayerState state;

    Player(PlayerState state){
        this.state=state;
    }

    boolean play (List<Position> cards){
        // hate towards this method
    }

    boolean play  (List<Position> cards){

    }

    boolean play (List<Position> cards, SleepingQueenPosition sleepPos, Position king){

    }

    boolean play (List<Position> cards, AwokenQueenPosition sleepPos, Position attackCard, Player targetPlayer){

    }

    PlayerState getPlayerState(){
        return state;
    }
}
