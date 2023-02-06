package tests;

import game.GameObservable;
import game.GameObserver;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class GameObservableTest {
    //done
    @Test
    public void gameTest() {
        GameObservable gameObservable = new GameObservable();
        for (int i = 0; i < 2; i++) {
            gameObservable.addPlayer(i, new GameObserver() {
                @Override
                public void notify(String message) {

                }
            });
        }
        assertEquals(2, gameObservable.getNumberOfObservers());
        assertNotEquals(4, gameObservable.getNumberOfObservers());
    }
}

