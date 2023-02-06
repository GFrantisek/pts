package tests;

import cards.Queen;
import game.GameFinished;
import org.testng.annotations.Test;
import position.AwokenQueenPosition;
import states.GameState;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;

import static org.testng.Assert.assertEquals;

public class GameFinishedTest {
    @Test
    public void testIsFinished() {
        GameState gameState = new GameState();
        gameState.numberOfPlayers = 2;
        gameState.awokenQueens = new HashMap<>();
        gameState.sleepingQueens = new HashSet<>();

        // Test case 1: Game is won by a player with score >= scoreNec
        gameState.awokenQueens.put(new AwokenQueenPosition(0, 0), new Queen(60));
        GameFinished gameFinished = new GameFinished(gameState);
        Optional<Integer> winner = gameFinished.isFinished();
        assertEquals(Optional.of(0), winner);

        // Test case 2: Game is won by a player with number of queens >= queensNec
        gameState.awokenQueens.clear();
        gameState.awokenQueens.put(new AwokenQueenPosition(0, 0), new Queen(0));
        gameState.awokenQueens.put(new AwokenQueenPosition(1, 0), new Queen(0));
        gameState.awokenQueens.put(new AwokenQueenPosition(2, 0), new Queen(0));
        gameState.awokenQueens.put(new AwokenQueenPosition(3, 0), new Queen(0));
        gameState.awokenQueens.put(new AwokenQueenPosition(4, 0), new Queen(0));
        gameFinished = new GameFinished(gameState);
        winner = gameFinished.isFinished();
        assertEquals(Optional.of(0), winner);

        // Test case 3: Game is won by a player with maximum score when all queens have been awoken
        gameState.awokenQueens.clear();
        gameState.awokenQueens.put(new AwokenQueenPosition(0, 0), new Queen(10));
        gameState.awokenQueens.put(new AwokenQueenPosition(1, 1), new Queen(20));
        gameFinished = new GameFinished(gameState);
        winner = gameFinished.isFinished();
        assertEquals(Optional.of(1), winner);


    }
}