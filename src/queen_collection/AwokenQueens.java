package queen_collection;

import cards.Queen;
import player.Player;
import position.AwokenQueenPosition;
import position.Position;
import position.SleepingQueenPosition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AwokenQueens extends QueenCollection{
    private Map<Position,Queen> queens;
    private final Player player;

    AwokenQueens(Player player) {
        queens = new HashMap<>();
        this.player = player;
    }

    @Override
    public Map<Position, Queen> getQueens() {
        return queens;
    }

    @Override
    public void addQueen(Queen Queen) {
        queens.put(new AwokenQueenPosition(queens.size(), player.playerIndex),Queen);
    }

    @Override
    public Optional<Queen> removeQueen(Position position) {
        return Optional.ofNullable(queens.remove(position));
    }

}
