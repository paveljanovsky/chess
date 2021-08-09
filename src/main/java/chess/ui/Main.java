package chess.ui;

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

import chess.solver.ChessMove;
import chess.solver.ChessState;
import chess.solver.ChessSquare;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n\n\nLet's play.");
        ChessState chessState = ChessState.initialChessState();
        System.out.println("First state:\n" + chessState);
        ChessMove move = new ChessMove(WHITE_PAWN, new ChessSquare('d', 2), new ChessSquare('d', 4));
        System.out.println("Move: " + move);
        ChessState secondState = ChessState.fromParentChessState(chessState, move);
        System.out.println("Second state:\n" + secondState);
    }
}
