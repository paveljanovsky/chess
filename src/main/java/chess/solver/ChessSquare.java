package chess.solver;

public final class ChessSquare {

    private final char column;
    private final int row;

    public ChessSquare(char column, int row) {
        this.column = column;
        this.row = row;
    }

    public char getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public String toString() {
        return column + "" + row;
    }

}