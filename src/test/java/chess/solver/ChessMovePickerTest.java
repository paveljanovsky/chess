package chess.solver;

import static chess.solver.ChessPiece.BLACK_BISHOP;
import static chess.solver.ChessPiece.BLACK_KING;
import static chess.solver.ChessPiece.BLACK_KNIGHT;
import static chess.solver.ChessPiece.BLACK_PAWN;
import static chess.solver.ChessPiece.BLACK_QUEEN;
import static chess.solver.ChessPiece.BLACK_ROOK;
import static chess.solver.ChessPiece.WHITE_BISHOP;
import static chess.solver.ChessPiece.WHITE_KING;
import static chess.solver.ChessPiece.WHITE_KNIGHT;
import static chess.solver.ChessPiece.WHITE_PAWN;
import static chess.solver.ChessPiece.WHITE_QUEEN;
import static chess.solver.ChessPiece.WHITE_ROOK;

import java.util.ArrayList;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnit4.class)
public class ChessMovePickerTest {

    private final ChessMovePicker chessMovePicker = new ChessMovePicker();

    
    @Test
    public void test_noSuchElementException() {
        ArrayList<ChessMove> setupMoves = new ArrayList<>();
        setupMoves.add(new ChessMove(WHITE_PAWN, null, new ChessSquare('a', 3)));
        setupMoves.add(new ChessMove(WHITE_PAWN, null, new ChessSquare('b', 3)));
        setupMoves.add(new ChessMove(WHITE_PAWN, null, new ChessSquare('c', 2)));
        setupMoves.add(new ChessMove(WHITE_PAWN, null, new ChessSquare('d', 4)));
        setupMoves.add(new ChessMove(WHITE_PAWN, null, new ChessSquare('e', 3)));
        setupMoves.add(new ChessMove(WHITE_PAWN, null, new ChessSquare('f', 2)));
        setupMoves.add(new ChessMove(WHITE_PAWN, null, new ChessSquare('g', 2)));
        setupMoves.add(new ChessMove(WHITE_PAWN, null, new ChessSquare('h', 2)));
        setupMoves.add(new ChessMove(WHITE_ROOK, null, new ChessSquare('a', 1)));
        setupMoves.add(new ChessMove(WHITE_ROOK, null, new ChessSquare('h', 1)));
        setupMoves.add(new ChessMove(WHITE_KNIGHT, null, new ChessSquare('b', 1)));
        setupMoves.add(new ChessMove(WHITE_KNIGHT, null, new ChessSquare('f', 3)));
        setupMoves.add(new ChessMove(WHITE_BISHOP, null, new ChessSquare('f', 4)));
        setupMoves.add(new ChessMove(WHITE_BISHOP, null, new ChessSquare('f', 1)));
        setupMoves.add(new ChessMove(WHITE_QUEEN, null, new ChessSquare('d', 3)));
        setupMoves.add(new ChessMove(WHITE_KING, null, new ChessSquare('e', 1)));

        setupMoves.add(new ChessMove(BLACK_PAWN, null, new ChessSquare('a', 7)));
        setupMoves.add(new ChessMove(BLACK_PAWN, null, new ChessSquare('b', 7)));
        setupMoves.add(new ChessMove(BLACK_PAWN, null, new ChessSquare('c', 6)));
        setupMoves.add(new ChessMove(BLACK_PAWN, null, new ChessSquare('d', 7)));
        setupMoves.add(new ChessMove(BLACK_PAWN, null, new ChessSquare('e', 7)));
        setupMoves.add(new ChessMove(BLACK_PAWN, null, new ChessSquare('f', 6)));
        setupMoves.add(new ChessMove(BLACK_PAWN, null, new ChessSquare('g', 7)));
        setupMoves.add(new ChessMove(BLACK_PAWN, null, new ChessSquare('h', 5)));
        setupMoves.add(new ChessMove(BLACK_ROOK, null, new ChessSquare('a', 8)));
        setupMoves.add(new ChessMove(BLACK_ROOK, null, new ChessSquare('h', 8)));
        setupMoves.add(new ChessMove(BLACK_KNIGHT, null, new ChessSquare('d', 5)));
        setupMoves.add(new ChessMove(BLACK_KNIGHT, null, new ChessSquare('g', 8)));
        setupMoves.add(new ChessMove(BLACK_BISHOP, null, new ChessSquare('c', 8)));
        setupMoves.add(new ChessMove(BLACK_BISHOP, null, new ChessSquare('f', 8)));
        setupMoves.add(new ChessMove(BLACK_QUEEN, null, new ChessSquare('b', 6)));
        setupMoves.add(new ChessMove(BLACK_KING, null, new ChessSquare('e', 8)));

        ChessState chessState = ChessState.fromChessMoves(setupMoves);
        System.err.println(chessState);
        ChessMove nextMove = chessMovePicker.pickNextChessMove(chessState, false);
        System.out.println(nextMove);
    }
}
