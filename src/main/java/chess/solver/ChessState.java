package chess.solver;

import static chess.solver.Constants.CHESS_BOARD_SIZE;
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

public final class ChessState {

    final ChessBoard chessBoard;
    
    public static ChessState initialChessState() {
        ChessBoard chessBoard = new ChessBoard();
        for (int i = 0; i < CHESS_BOARD_SIZE; i ++) {
            chessBoard.put((char)((int)'a' + i), 2, WHITE_PAWN);
            chessBoard.put((char)((int)'a' + i), 7, BLACK_PAWN);
        }

        chessBoard.put('a',1, WHITE_ROOK);
        chessBoard.put('h',1, WHITE_ROOK);
        chessBoard.put('a',8, BLACK_ROOK);
        chessBoard.put('h',8, BLACK_ROOK);

        chessBoard.put('b',1, WHITE_KNIGHT);
        chessBoard.put('g',1, WHITE_KNIGHT);
        chessBoard.put('b',8, BLACK_KNIGHT);
        chessBoard.put('g',8, BLACK_KNIGHT);

        chessBoard.put('c',1, WHITE_BISHOP);
        chessBoard.put('f',1, WHITE_BISHOP);
        chessBoard.put('c',8, BLACK_BISHOP);
        chessBoard.put('f',8, BLACK_BISHOP);

        chessBoard.put('d',1, WHITE_QUEEN);
        chessBoard.put('d',8, BLACK_QUEEN);

        chessBoard.put('e',1, WHITE_KING);
        chessBoard.put('e',8, BLACK_KING);

        return new ChessState(chessBoard);
    }

    public static ChessState fromStorageRepresentation(String storageRepresentation) {
        return null;
    }

    public static ChessState fromParentChessState(ChessState parentChessState, ChessMove chessMove) {
        return null;
    }

    public void print() {
        chessBoard.print();
    }

    private ChessState(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }
}