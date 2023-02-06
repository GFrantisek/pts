package queen_collection;

import cards.Queen;
import position.Position;

import java.util.Map;
import java.util.Optional;

public abstract class QueenCollection {

    public abstract Map<Position,Queen> getQueens();

    public abstract void addQueen(Queen Queen);

    public abstract Optional<Queen> removeQueen(Position position);


}
