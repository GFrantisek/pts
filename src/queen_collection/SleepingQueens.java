package queen_collection;

import cards.Queen;
import position.Position;
import position.SleepingQueenPosition;

import java.awt.*;
import java.util.*;
import java.util.List;

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
        System.out.println(queens);
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
        return Optional.ofNullable(queens.remove(position));
    }

    // kinda looking wrong
}
