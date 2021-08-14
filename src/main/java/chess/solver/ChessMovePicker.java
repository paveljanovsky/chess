package chess.solver;

public class ChessMovePicker {
    
    public ChessMove pickNextChessMove(ChessState currentChessState, boolean isWhite) {
        MinMaxNode currentNode 
          = new MinMaxNode(currentChessState, true, isWhite, 0, new CapturedPiecesChessStateEvaluator());
        currentNode.evaluate();
        return currentNode.getMoveToBestChild();
    }
}
