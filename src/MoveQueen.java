import cards.Queen;
import player.Player;
import position.AwokenQueenPosition;
import position.SleepingQueenPosition;
import queen_collection.QueenCollection;

import javax.swing.text.Position;
import java.util.Optional;

public class MoveQueen {
    private QueenCollection queenCollection;
    private Player player;

    public MoveQueen(Player player, QueenCollection queenCollection) {
        this.queenCollection = queenCollection;
        this.player = player;
    }
    public boolean play(Position targetQueen) {
    }
}
