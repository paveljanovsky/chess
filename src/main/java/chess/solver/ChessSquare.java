package chess.solver;

public final class ChessSquare {

    private final char column;
    private final int row;

    public ChessSquare(char column, int row) {
        this.column = column;
        this.row = row;
    }

    public ChessSquare(int rowId, int columnId) {
        this.column = (char) ((int) 'a' + columnId);
        this.row = rowId + 1;
    }

    public char getColumn() {
        return column;
    }

    public int getColumnId() {
        return (int) column - (int) 'a';
    }

    public int getRow() {
        return row;
    }

    public int getRowId() {
        return row - 1;
    }

    public String toString() {
        return column + "" + row;
    }

}