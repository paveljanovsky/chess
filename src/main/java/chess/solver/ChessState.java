package chess.solver;

import static chess.solver.Constants.CHESS_BOARD_SIZE;

import java.util.ArrayList;

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
    final ArrayList<ChessPiece> whiteCapturedPieces;
    final ArrayList<ChessPiece> blackCapturedPieces;

    public static ChessState initialChessState() {
        ChessBoard chessBoard = new ChessBoard();
        for (int i = 0; i < CHESS_BOARD_SIZE; i++) {
            chessBoard.put(new ChessSquare((char) ((int) 'a' + i), 2), WHITE_PAWN);
            chessBoard.put(new ChessSquare((char) ((int) 'a' + i), 7), BLACK_PAWN);
        }

        chessBoard.put(new ChessSquare('a', 1), WHITE_ROOK);
        chessBoard.put(new ChessSquare('h', 1), WHITE_ROOK);
        chessBoard.put(new ChessSquare('a', 8), BLACK_ROOK);
        chessBoard.put(new ChessSquare('h', 8), BLACK_ROOK);

        chessBoard.put(new ChessSquare('b', 1), WHITE_KNIGHT);
        chessBoard.put(new ChessSquare('g', 1), WHITE_KNIGHT);
        chessBoard.put(new ChessSquare('b', 8), BLACK_KNIGHT);
        chessBoard.put(new ChessSquare('g', 8), BLACK_KNIGHT);

        chessBoard.put(new ChessSquare('c', 1), WHITE_BISHOP);
        chessBoard.put(new ChessSquare('f', 1), WHITE_BISHOP);
        chessBoard.put(new ChessSquare('c', 8), BLACK_BISHOP);
        chessBoard.put(new ChessSquare('f', 8), BLACK_BISHOP);

        chessBoard.put(new ChessSquare('d', 1), WHITE_QUEEN);
        chessBoard.put(new ChessSquare('d', 8), BLACK_QUEEN);

        chessBoard.put(new ChessSquare('e', 1), WHITE_KING);
        chessBoard.put(new ChessSquare('e', 8), BLACK_KING);

        return new ChessState(chessBoard, new ArrayList<>(), new ArrayList<>());
    }

    public static ChessState fromStorageRepresentation(String storageRepresentation) {
        return null;
    }

    public static ChessState fromParentChessState(ChessState parentChessState, ChessMove chessMove) {
        ChessBoard chessBoard = new ChessBoard(parentChessState.getChessBoard());
        ArrayList<ChessPiece> whiteCapturedPieces = new ArrayList<>(parentChessState.getWhiteCapturedPieces());
        ArrayList<ChessPiece> blackCapturedPieces = new ArrayList<>(parentChessState.getBlackCapturedPieces());

        if (chessBoard.peek(chessMove.getFromChessSquare()) != chessMove.getChessPiece()) {
            throw new InvalidChessMoveException(
                    String.format("Invalid chess move. Trying to move [%s], but found [%s].", chessMove.getChessPiece(),
                            chessBoard.peek(chessMove.getFromChessSquare())));
        }
        chessBoard.remove(chessMove.getFromChessSquare());
        ChessPiece targetChessPiece = chessBoard.peek(chessMove.getToChessSquare());
        if (targetChessPiece != null) {
            if ((targetChessPiece.isWhite() && chessMove.getChessPiece().isWhite())
                    || (!targetChessPiece.isWhite() && !chessMove.getChessPiece().isWhite())) {
                throw new InvalidChessMoveException(
                        "Invalid chess move, target square contains piece with the same color.");
            }
            if (targetChessPiece.isWhite()) {
                whiteCapturedPieces.add(targetChessPiece);
            } else {
                blackCapturedPieces.add(targetChessPiece);
            }
        }
        chessBoard.put(chessMove.getToChessSquare(), chessMove.getChessPiece());
        return new ChessState(chessBoard, whiteCapturedPieces, blackCapturedPieces);
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public ArrayList<ChessPiece> getWhiteCapturedPieces() {
        return whiteCapturedPieces;
    }

    public ArrayList<ChessPiece> getBlackCapturedPieces() {
        return blackCapturedPieces;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!whiteCapturedPieces.isEmpty()) {
            sb.append("Captured white pieces: ");
        }
        for (ChessPiece piece : whiteCapturedPieces) {
            sb.append(piece.getUnicode() + " ");
        }
        sb.append("\n");

        sb.append(chessBoard.toString());

        if (!blackCapturedPieces.isEmpty()) {
            sb.append("Captured black pieces: ");
        }
        for (ChessPiece piece : blackCapturedPieces) {
            sb.append(piece.getUnicode() + " ");
        }
        sb.append("\n");

        return sb.toString();
    }

    private ChessState(ChessBoard chessBoard, ArrayList<ChessPiece> whiteCapturedPieces,
            ArrayList<ChessPiece> blackCapturedPieces) {
        this.chessBoard = chessBoard;
        this.whiteCapturedPieces = whiteCapturedPieces;
        this.blackCapturedPieces = blackCapturedPieces;
    }
}