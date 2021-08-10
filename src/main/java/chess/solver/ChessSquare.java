package chess.solver;

import static chess.solver.Constants.CHESS_BOARD_SIZE;

public final class ChessSquare {

    private final int rowId;
    private final int columnId;

    public ChessSquare(char column, int row) {
        this.columnId = (int) column - (int) 'a';
        this.rowId = CHESS_BOARD_SIZE - row;
    }

    public ChessSquare(int rowId, int columnId) {
        this.rowId = rowId;
        this.columnId = columnId;
    }

    public int getRowId() {
        return rowId;
    }

    public int getColumnId() {
        return columnId;
    }

    public int getRow() {
        return CHESS_BOARD_SIZE - rowId;
    }

    public char getColumn() {
        return (char) ((int) 'a' + columnId);
    }

    public String toString() {
        return getColumn() + "" + getRow();
    }

}