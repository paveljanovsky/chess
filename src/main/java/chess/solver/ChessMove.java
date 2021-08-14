package chess.solver;

import static chess.solver.Inputs.PRINT_MOVE_START;

import java.util.Arrays;
import java.util.NoSuchElementException;

public final class ChessMove {

    private final ChessPiece chessPiece;
    private final ChessSquare fromChessSquare;
    private final ChessSquare toChessSquare;

    public ChessMove(ChessPiece chessPiece, ChessSquare fromChessSquare, ChessSquare toChessSquare) {
        this.chessPiece = chessPiece;
        this.fromChessSquare = fromChessSquare;
        this.toChessSquare = toChessSquare;
    }

    public ChessMove(String stringRepresentation) throws InvalidChessMoveException {
        try {
            String chessPieceCode = stringRepresentation.substring(0, 2);
            this.chessPiece = Arrays.asList(ChessPiece.values()).stream()
                    .filter(value -> value.getCode().equals(chessPieceCode)).findFirst().get();
            this.fromChessSquare = new ChessSquare(stringRepresentation.charAt(2),
                    Integer.parseInt(stringRepresentation.substring(3, 4)));
            this.toChessSquare = new ChessSquare(stringRepresentation.charAt(4),
                    Integer.parseInt(stringRepresentation.substring(5, 6)));
        } catch (IndexOutOfBoundsException | NoSuchElementException | NumberFormatException e) {
            throw new InvalidChessMoveException("Can't parse chess move: " + e);
        }
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
        return other instanceof ChessMove && chessPiece == ((ChessMove) other).getChessPiece()
                && fromChessSquare.equals(((ChessMove) other).getFromChessSquare())
                && toChessSquare.equals(((ChessMove) other).getToChessSquare());
    }

    // public Strint hashCode() {
    //
    // }
}