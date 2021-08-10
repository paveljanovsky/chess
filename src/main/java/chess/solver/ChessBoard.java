package chess.solver;

import java.util.ArrayList;
import java.util.Optional;

public final class ChessBoard {
    ChessPiece[][] chessBoard;

    public ChessBoard() {
        chessBoard = new ChessPiece[Constants.CHESS_BOARD_SIZE][Constants.CHESS_BOARD_SIZE];
    }

    public ChessBoard(ChessBoard otherChessBoard) {
        chessBoard = new ChessPiece[Constants.CHESS_BOARD_SIZE][Constants.CHESS_BOARD_SIZE];
        for (int i = 0; i < Constants.CHESS_BOARD_SIZE; i++) {
            for (int j = 0; j < Constants.CHESS_BOARD_SIZE; j++) {
                chessBoard[i][j] = otherChessBoard.chessBoard[i][j];
            }
        }
    }

    public void put(ChessSquare chessSquare, ChessPiece chessPiece) {
        chessBoard[chessSquare.getRowId()][(int) chessSquare.getColumnId()] = chessPiece;
    }

    public void remove(ChessSquare chessSquare) {
        chessBoard[chessSquare.getRowId()][(int) chessSquare.getColumnId()] = null;
    }

    public Optional<ChessPiece> peek(ChessSquare chessSquare) {
        int rowId = chessSquare.getRowId();
        int columnId = (int) chessSquare.getColumnId();
        return rowId >= 0 && rowId < Constants.CHESS_BOARD_SIZE && columnId >= 0
                && columnId < Constants.CHESS_BOARD_SIZE ? Optional.ofNullable(chessBoard[rowId][columnId])
                        : Optional.empty();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Constants.CHESS_BOARD_SIZE; i++) {
            for (int j = 0; j < Constants.CHESS_BOARD_SIZE; j++) {
                if (chessBoard[i][j] != null) {
                    sb.append(chessBoard[i][j].getUnicode() + " ");
                } else {
                    sb.append("o ");

                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public ArrayList<ChessSquare> getWhitePieceLocations() {
        ArrayList<ChessSquare> locations = new ArrayList<>();
        for (int i = 0; i < Constants.CHESS_BOARD_SIZE; i++) {
            for (int j = 0; j < Constants.CHESS_BOARD_SIZE; j++) {
                if (chessBoard[i][j] != null && chessBoard[i][j].isWhite()) {
                    locations.add(new ChessSquare(i, j));
                }
            }
        }
        return locations;
    }

    public ArrayList<ChessSquare> getBlackPieceLocations() {
        ArrayList<ChessSquare> locations = new ArrayList<>();
        for (int i = 0; i < Constants.CHESS_BOARD_SIZE; i++) {
            for (int j = 0; j < Constants.CHESS_BOARD_SIZE; j++) {
                if (chessBoard[i][j] != null && !chessBoard[i][j].isWhite()) {
                    locations.add(new ChessSquare(i, j));
                }
            }
        }
        return locations;
    }

    public boolean isAvailable(ChessSquare chessSquare) {
        return !peek(chessSquare).isEmpty();
    }

    public boolean isTarget(ChessSquare chessSquare, boolean isWhite) {
        Optional<ChessPiece> target = peek(chessSquare);
        if (target.isPresent()) {
            return (isWhite && !target.get().isWhite()) || (!isWhite && target.get().isWhite());
        } else {
            return false;
        }
    }

    public boolean isAvailableOrTarget(ChessSquare chessSquare, boolean isWhite) {
        return isAvailable(chessSquare) || isTarget(chessSquare, isWhite);
    }

}