package chess.solver;

import static chess.solver.Inputs.PRINT_PRETTY_COORDINATES;

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
        return chessSquare.isInBounds()
                ? Optional.ofNullable(chessBoard[chessSquare.getRowId()][chessSquare.getColumnId()])
                : Optional.empty();
    }

    public String toString() {
        String horizontalTicks = "  a b c d e f g h\n";
        StringBuilder sb = new StringBuilder();
        sb.append(horizontalTicks);
        for (int i = 0; i < Constants.CHESS_BOARD_SIZE; i++) {
            String verticalTick = "" + (Constants.CHESS_BOARD_SIZE - i);
            sb.append(verticalTick + " ");
            for (int j = 0; j < Constants.CHESS_BOARD_SIZE; j++) {
                if (chessBoard[i][j] != null) {
                    sb.append(chessBoard[i][j].getUnicode() + " ");
                } else {
                    sb.append("o ");

                }
            }
            sb.append(" " + verticalTick + "\n");
        }
        sb.append(horizontalTicks);
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
        return chessSquare.isInBounds() && peek(chessSquare).isEmpty();
    }

    /**
     * Returns true if the square is occupied by a piece of the other color.
     */
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