package chess.solver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static chess.solver.ChessPiece.BLACK_KNIGHT;
import static chess.solver.ChessPiece.BLACK_PAWN;
import static chess.solver.ChessPiece.WHITE_KNIGHT;
import static chess.solver.ChessPiece.WHITE_PAWN;
import static chess.solver.ChessPiece.WHITE_QUEEN;

import java.util.ArrayList;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ChessStateTest {

    @Test
    public void test_getAvailableWhiteChessMoves_fromInitialState() {
        ArrayList<ChessMove> chessMoves = ChessState.initialChessState().getAvailableWhiteChessMoves();

        ArrayList<ChessMove> expectedMoves = new ArrayList<>();
        expectedMoves.add(new ChessMove(WHITE_PAWN, new ChessSquare('a', 2), new ChessSquare('a', 4)));
        expectedMoves.add(new ChessMove(WHITE_PAWN, new ChessSquare('a', 2), new ChessSquare('a', 3)));
        expectedMoves.add(new ChessMove(WHITE_PAWN, new ChessSquare('b', 2), new ChessSquare('b', 4)));
        expectedMoves.add(new ChessMove(WHITE_PAWN, new ChessSquare('b', 2), new ChessSquare('b', 3)));
        expectedMoves.add(new ChessMove(WHITE_PAWN, new ChessSquare('c', 2), new ChessSquare('c', 4)));
        expectedMoves.add(new ChessMove(WHITE_PAWN, new ChessSquare('c', 2), new ChessSquare('c', 3)));
        expectedMoves.add(new ChessMove(WHITE_PAWN, new ChessSquare('d', 2), new ChessSquare('d', 4)));
        expectedMoves.add(new ChessMove(WHITE_PAWN, new ChessSquare('d', 2), new ChessSquare('d', 3)));
        expectedMoves.add(new ChessMove(WHITE_PAWN, new ChessSquare('e', 2), new ChessSquare('e', 4)));
        expectedMoves.add(new ChessMove(WHITE_PAWN, new ChessSquare('e', 2), new ChessSquare('e', 3)));
        expectedMoves.add(new ChessMove(WHITE_PAWN, new ChessSquare('f', 2), new ChessSquare('f', 4)));
        expectedMoves.add(new ChessMove(WHITE_PAWN, new ChessSquare('f', 2), new ChessSquare('f', 3)));
        expectedMoves.add(new ChessMove(WHITE_PAWN, new ChessSquare('g', 2), new ChessSquare('g', 4)));
        expectedMoves.add(new ChessMove(WHITE_PAWN, new ChessSquare('g', 2), new ChessSquare('g', 3)));
        expectedMoves.add(new ChessMove(WHITE_PAWN, new ChessSquare('h', 2), new ChessSquare('h', 4)));
        expectedMoves.add(new ChessMove(WHITE_PAWN, new ChessSquare('h', 2), new ChessSquare('h', 3)));

        expectedMoves.add(new ChessMove(WHITE_KNIGHT, new ChessSquare('b', 1), new ChessSquare('c', 3)));
        expectedMoves.add(new ChessMove(WHITE_KNIGHT, new ChessSquare('b', 1), new ChessSquare('a', 3)));
        expectedMoves.add(new ChessMove(WHITE_KNIGHT, new ChessSquare('g', 1), new ChessSquare('h', 3)));
        expectedMoves.add(new ChessMove(WHITE_KNIGHT, new ChessSquare('g', 1), new ChessSquare('f', 3)));

        Assertions.assertThat(chessMoves).hasSameElementsAs(expectedMoves);
    }

    @Test
    public void test_getAvailableBlackChessMoves_fromInitialState() {
        ArrayList<ChessMove> chessMoves = ChessState.initialChessState().getAvailableBlackChessMoves();

        ArrayList<ChessMove> expectedMoves = new ArrayList<>();
        expectedMoves.add(new ChessMove(BLACK_PAWN, new ChessSquare('a', 7), new ChessSquare('a', 5)));
        expectedMoves.add(new ChessMove(BLACK_PAWN, new ChessSquare('a', 7), new ChessSquare('a', 6)));
        expectedMoves.add(new ChessMove(BLACK_PAWN, new ChessSquare('b', 7), new ChessSquare('b', 5)));
        expectedMoves.add(new ChessMove(BLACK_PAWN, new ChessSquare('b', 7), new ChessSquare('b', 6)));
        expectedMoves.add(new ChessMove(BLACK_PAWN, new ChessSquare('c', 7), new ChessSquare('c', 5)));
        expectedMoves.add(new ChessMove(BLACK_PAWN, new ChessSquare('c', 7), new ChessSquare('c', 6)));
        expectedMoves.add(new ChessMove(BLACK_PAWN, new ChessSquare('d', 7), new ChessSquare('d', 5)));
        expectedMoves.add(new ChessMove(BLACK_PAWN, new ChessSquare('d', 7), new ChessSquare('d', 6)));
        expectedMoves.add(new ChessMove(BLACK_PAWN, new ChessSquare('e', 7), new ChessSquare('e', 5)));
        expectedMoves.add(new ChessMove(BLACK_PAWN, new ChessSquare('e', 7), new ChessSquare('e', 6)));
        expectedMoves.add(new ChessMove(BLACK_PAWN, new ChessSquare('f', 7), new ChessSquare('f', 5)));
        expectedMoves.add(new ChessMove(BLACK_PAWN, new ChessSquare('f', 7), new ChessSquare('f', 6)));
        expectedMoves.add(new ChessMove(BLACK_PAWN, new ChessSquare('g', 7), new ChessSquare('g', 5)));
        expectedMoves.add(new ChessMove(BLACK_PAWN, new ChessSquare('g', 7), new ChessSquare('g', 6)));
        expectedMoves.add(new ChessMove(BLACK_PAWN, new ChessSquare('h', 7), new ChessSquare('h', 5)));
        expectedMoves.add(new ChessMove(BLACK_PAWN, new ChessSquare('h', 7), new ChessSquare('h', 6)));

        expectedMoves.add(new ChessMove(BLACK_KNIGHT, new ChessSquare('b', 8), new ChessSquare('c', 6)));
        expectedMoves.add(new ChessMove(BLACK_KNIGHT, new ChessSquare('b', 8), new ChessSquare('a', 6)));
        expectedMoves.add(new ChessMove(BLACK_KNIGHT, new ChessSquare('g', 8), new ChessSquare('h', 6)));
        expectedMoves.add(new ChessMove(BLACK_KNIGHT, new ChessSquare('g', 8), new ChessSquare('f', 6)));

        Assertions.assertThat(chessMoves).hasSameElementsAs(expectedMoves);
    }

    @Test
    public void test_getAvailableWhiteChessMoves_testAllChessPieces() {
        ArrayList<ChessMove> setupMoves = new ArrayList<>();
        setupMoves.add(new ChessMove(WHITE_PAWN, null, new ChessSquare('d', 4)));
        setupMoves.add(new ChessMove(BLACK_PAWN, null, new ChessSquare('e', 5)));
        setupMoves.add(new ChessMove(BLACK_PAWN, null, new ChessSquare('d', 5)));
        setupMoves.add(new ChessMove(WHITE_QUEEN, null, new ChessSquare('e', 3)));
        setupMoves.add(new ChessMove(WHITE_KNIGHT, null, new ChessSquare('g', 4)));
        // TODO test king moves

        ChessState chessState = ChessState.fromChessMoves(setupMoves);
        System.err.println(chessState);
        ArrayList<ChessMove> chessMoves = chessState.getAvailableWhiteChessMoves();

        ArrayList<ChessMove> expectedMoves = new ArrayList<>();
        expectedMoves.add(new ChessMove(WHITE_PAWN, new ChessSquare('d', 4), new ChessSquare('e', 5)));
        expectedMoves.add(new ChessMove(WHITE_KNIGHT, new ChessSquare('g', 4), new ChessSquare('h', 2)));
        expectedMoves.add(new ChessMove(WHITE_KNIGHT, new ChessSquare('g', 4), new ChessSquare('f', 2)));
        expectedMoves.add(new ChessMove(WHITE_KNIGHT, new ChessSquare('g', 4), new ChessSquare('h', 6)));
        expectedMoves.add(new ChessMove(WHITE_KNIGHT, new ChessSquare('g', 4), new ChessSquare('f', 6)));
        expectedMoves.add(new ChessMove(WHITE_KNIGHT, new ChessSquare('g', 4), new ChessSquare('e', 5)));
        expectedMoves.add(new ChessMove(WHITE_QUEEN, new ChessSquare('e', 3), new ChessSquare('e', 2)));
        expectedMoves.add(new ChessMove(WHITE_QUEEN, new ChessSquare('e', 3), new ChessSquare('e', 1)));
        expectedMoves.add(new ChessMove(WHITE_QUEEN, new ChessSquare('e', 3), new ChessSquare('e', 4)));
        expectedMoves.add(new ChessMove(WHITE_QUEEN, new ChessSquare('e', 3), new ChessSquare('e', 5)));
        expectedMoves.add(new ChessMove(WHITE_QUEEN, new ChessSquare('e', 3), new ChessSquare('f', 3)));
        expectedMoves.add(new ChessMove(WHITE_QUEEN, new ChessSquare('e', 3), new ChessSquare('g', 3)));
        expectedMoves.add(new ChessMove(WHITE_QUEEN, new ChessSquare('e', 3), new ChessSquare('h', 3)));
        expectedMoves.add(new ChessMove(WHITE_QUEEN, new ChessSquare('e', 3), new ChessSquare('d', 3)));
        expectedMoves.add(new ChessMove(WHITE_QUEEN, new ChessSquare('e', 3), new ChessSquare('c', 3)));
        expectedMoves.add(new ChessMove(WHITE_QUEEN, new ChessSquare('e', 3), new ChessSquare('b', 3)));
        expectedMoves.add(new ChessMove(WHITE_QUEEN, new ChessSquare('e', 3), new ChessSquare('a', 3)));
        expectedMoves.add(new ChessMove(WHITE_QUEEN, new ChessSquare('e', 3), new ChessSquare('f', 2)));
        expectedMoves.add(new ChessMove(WHITE_QUEEN, new ChessSquare('e', 3), new ChessSquare('g', 1)));
        expectedMoves.add(new ChessMove(WHITE_QUEEN, new ChessSquare('e', 3), new ChessSquare('d', 2)));
        expectedMoves.add(new ChessMove(WHITE_QUEEN, new ChessSquare('e', 3), new ChessSquare('c', 1)));
        expectedMoves.add(new ChessMove(WHITE_QUEEN, new ChessSquare('e', 3), new ChessSquare('f', 4)));
        expectedMoves.add(new ChessMove(WHITE_QUEEN, new ChessSquare('e', 3), new ChessSquare('g', 5)));
        expectedMoves.add(new ChessMove(WHITE_QUEEN, new ChessSquare('e', 3), new ChessSquare('h', 6)));

        Assertions.assertThat(chessMoves).hasSameElementsAs(expectedMoves);
    }
}
