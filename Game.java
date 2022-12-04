import javax.swing.text.Position;
import java.util.List;
import java.util.Optional;

public class Game implements GameFinishedStrategy {
    void play(Integer playerIdx, List<Position> cards) {

    }

    @Override
    public Optional<Integer> isFinished() {
        return Optional.empty();
    }
}
