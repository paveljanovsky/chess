package chess.solver;

public final class InvalidChessMoveException extends IllegalStateException {

    public InvalidChessMoveException(String message) {
        super(message);
    }

}