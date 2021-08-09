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
        System.out.println("First state:" + chessState);
        ChessMove move = new ChessMove(WHITE_PAWN, new ChessSquare('d', 2), new ChessSquare('d', 4));
        System.out.println("Move: " + move);
        ChessState secondState = ChessState.fromParentChessState(chessState, move);
        System.out.println("Second state:" + secondState);
        ChessMove secondMove = new ChessMove(BLACK_PAWN, new ChessSquare('e', 7), new ChessSquare('e', 5));
        System.out.println("Move: " + secondMove);
        ChessState thirdState = ChessState.fromParentChessState(secondState, secondMove);
        System.out.println("Third state:" + thirdState);
        ChessMove thirdMove = new ChessMove(WHITE_PAWN, new ChessSquare('d', 4), new ChessSquare('e', 5));
        System.out.println("Move: " + thirdMove);
        ChessState fourthState = ChessState.fromParentChessState(thirdState, thirdMove);
        System.out.println("Fourth state:" + fourthState);
    }
}
