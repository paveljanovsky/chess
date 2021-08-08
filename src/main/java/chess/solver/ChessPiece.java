package chess.solver;

public enum ChessPiece {
    WHITE_KING("WX"), 
    WHITE_QUEEN("WQ"), 
    WHITE_ROOK("WR"), 
    WHITE_BISHOP("WB"), 
    WHITE_KNIGHT("WK"), 
    WHITE_PAWN("WP"),
    BLACK_KING("BX"), 
    BLACK_QUEEN("BQ"), 
    BLACK_ROOK("BR"), 
    BLACK_BISHOP("BB"), 
    BLACK_KNIGHT("BK"), 
    BLACK_PAWN("BP");

    private final String code;

    ChessPiece(String code) {
        this.code = code;
    }
    public String getCode() {
      return code;
    }
}