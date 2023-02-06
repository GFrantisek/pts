package queen_collection;

import cards.Queen;
import player.Player;
import position.AwokenQueenPosition;
import position.Position;
import position.SleepingQueenPosition;
import queen_collection.QueenCollection;
import queen_collection.SleepingQueens;

import java.util.Map;
import java.util.Optional;

public class MoveQueen {
    private QueenCollection queenCollection;
    private final Map<Integer,Player> players;
    private final SleepingQueens sleepingQueens;

    public MoveQueen( Map<Integer, Player> players, SleepingQueens sleepingQueens) {
        this.queenCollection = sleepingQueens;
        this.players = players;
        this.sleepingQueens = sleepingQueens;
    }

    public boolean play(Position targetQueen) {
        Optional<Queen> removedQueen = Optional.empty();

        if (targetQueen instanceof SleepingQueenPosition) {
            removedQueen = sleepingQueens.removeQueen(targetQueen);
        }
        else if (targetQueen instanceof AwokenQueenPosition) {
            int targetPlayerIndex = ((AwokenQueenPosition) targetQueen).getPlayerIndex();
            removedQueen = players.get(targetPlayerIndex).getAwokenQueens().removeQueen(targetQueen);
        }

        if (removedQueen.isEmpty()) return false;
        queenCollection.addQueen(removedQueen.get());
        return true;
    }

    public void setQueenCollection(QueenCollection queenCollection) {
        this.queenCollection = queenCollection;
    }
    public void setQueenCollectionToSleepingQueens() {this.queenCollection = sleepingQueens;}
}
