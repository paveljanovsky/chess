package chess.solver;

import java.util.Arrays;

public enum ChessPiece {
  WHITE_KING("WK", "\u2654", true), WHITE_QUEEN("WQ", "\u2655", true), WHITE_ROOK("WR", "\u2656", true),
  WHITE_BISHOP("WB", "\u2657", true), WHITE_KNIGHT("WN", "\u2658", true), WHITE_PAWN("WP", "\u2659", true),
  BLACK_KING("BK", "\u265A", false), BLACK_QUEEN("BQ", "\u265B", false), BLACK_ROOK("BR", "\u265C", false),
  BLACK_BISHOP("BB", "\u265D", false), BLACK_KNIGHT("BN", "\u265E", false), BLACK_PAWN("BP", "\u265F", false);

  private final String code;
  private final String unicode;
  private final boolean isWhite;

  ChessPiece(String code, String unicode, boolean isWhite) {
    this.code = code;
    this.unicode = unicode;
    this.isWhite = isWhite;
  }

  public String getCode() {
    return code;
  }

  public String getUnicode() {
    return unicode;
  }

  public boolean isWhite() {
    return isWhite;
  }
}