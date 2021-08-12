package chess.solver;

import static chess.solver.Inputs.PRINT_MOVE_START;

public final class ChessMove {

    private final ChessPiece chessPiece;
    private final ChessSquare fromChessSquare;
    private final ChessSquare toChessSquare;

    public ChessMove(ChessPiece chessPiece, ChessSquare fromChessSquare, ChessSquare toChessSquare) {
        this.chessPiece = chessPiece;
        this.fromChessSquare = fromChessSquare;
        this.toChessSquare = toChessSquare;
    }

    public ChessPiece getChessPiece() {
        return chessPiece;
    }

    public ChessSquare getFromChessSquare() {
        return fromChessSquare;
    }

    public ChessSquare getToChessSquare() {
        return toChessSquare;
    }

    public String toString() {
        return PRINT_MOVE_START ? chessPiece.getCode() + fromChessSquare + "->" + chessPiece.getCode() + toChessSquare
                : chessPiece.getCode() + toChessSquare;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ChessMove 
        && chessPiece == ((ChessMove) other).getChessPiece() 
        && fromChessSquare.equals(((ChessMove) other).getFromChessSquare())
        && toChessSquare.equals(((ChessMove) other).getToChessSquare());
    }

    //public Strint hashCode() {
//
    //}
}