package chess.solver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ChessStateTest {
    

    @Test
    public void test_getAvailableWhiteChessMoves_numberOfMoves() {
        ArrayList<ChessMove> chessMoves = 
        ChessState.initialChessState().getAvailableWhiteChessMoves();
        // TODO fix each piece
        chessMoves.forEach(move -> System.out.println(move));
        assertEquals(chessMoves.size(), 5);
    }
}
