package chess.solver;

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
        return chessPiece.getCode() + toChessSquare;
    }

}