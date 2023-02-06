package queen_collection;

import cards.Queen;
import position.Position;
import position.SleepingQueenPosition;

import java.util.*;

public class SleepingQueens extends QueenCollection {
    private Map<Position,Queen> queens;
    private ArrayList<Queen> indexes;

    public SleepingQueens() {
        queens = new HashMap<Position, Queen>();
        indexes = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            indexes.add(new Queen(5));
        }
        for (int i = 0; i < 4; i++) {
            indexes.add(new Queen(10));
        }
        for (int i = 0; i < 4; i++) {
            indexes.add(new Queen(15));
        }
        indexes.add(new Queen(20));
        Collections.shuffle(indexes);
        for(int i = 0; i < indexes.size(); i++){
            queens.put(new SleepingQueenPosition(i),indexes.get(i));
        }

    }

    @Override
    public Map<Position, Queen> getQueens() {
        return queens;
    }

    @Override
    public void addQueen(Queen Queen) {
        queens.put(new SleepingQueenPosition(queens.size()),Queen);
    }

    @Override
    public Optional<Queen>
    removeQueen(Position position) {
        int indx = position.getCardIndex();
        Optional<Queen> removedQueen = Optional.ofNullable(queens.remove(indx));
        for(Map.Entry<Position, Queen> queen : queens.entrySet()){
            if (queen.getKey().getCardIndex() > indx) queen.getKey().setCardIndex(queen.getKey().getCardIndex() - 1);
        }
        return removedQueen;
    }

}
