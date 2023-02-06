package queen_collection;

import cards.Queen;
import player.Player;
import position.AwokenQueenPosition;
import position.Position;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AwokenQueens extends QueenCollection{
    private Map<Position,Queen> queens;
    private final int player;

    public AwokenQueens(int player) {
        queens = new HashMap<>();
        this.player = player;
    }

    @Override
    public Map<Position, Queen> getQueens() {
        return queens;
    }

    @Override
    public void addQueen(Queen Queen) {
        queens.put(new AwokenQueenPosition(queens.size(), player),Queen);
    }

    @Override
    public Optional<Queen> removeQueen(Position position) {
        Optional<Queen> removedQueen = Optional.ofNullable(queens.remove(position));
        int removedCardIndex = position.getCardIndex();
        Map<Position, Queen> map = new HashMap<>();
        for (Map.Entry<Position, Queen> entry : queens.entrySet()) {
            if (entry.getKey().getCardIndex() > removedCardIndex) {
                entry.getKey().setCardIndex(entry.getKey().getCardIndex() - 1);
            }
            map.put(entry.getKey(), entry.getValue());
        }
        this.queens = map;
        return removedQueen;
    }

}
