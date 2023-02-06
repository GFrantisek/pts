package tests;

import cards.Queen;
import org.testng.annotations.Test;
import position.AwokenQueenPosition;
import queen_collection.AwokenQueens;

import java.util.Optional;

import static org.testng.Assert.*;

public class AwokenQueensTest {
        @Test
        public void testAddQueen() {
            AwokenQueens queens = new AwokenQueens(1);
            Queen queen = new Queen( 10);
            queens.addQueen(queen);
            assertTrue(queens.getQueens().containsValue(queen));
        }

        @Test
        public void testRemoveQueen() {
            AwokenQueens queens = new AwokenQueens(1);
            Queen queen = new Queen(20);
            queens.addQueen(queen);
            Optional<Queen> removedQueen = queens.removeQueen(new AwokenQueenPosition(0, 1));
            assertTrue(removedQueen.isPresent());
            assertEquals(queen, removedQueen.get());
            assertFalse(queens.getQueens().containsValue(queen));
        }
    }


