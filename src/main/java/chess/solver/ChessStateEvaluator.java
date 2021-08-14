package chess.solver;

public interface ChessStateEvaluator {
    
    public double evaluateChessState(ChessState chessState, boolean isWhite);
}
