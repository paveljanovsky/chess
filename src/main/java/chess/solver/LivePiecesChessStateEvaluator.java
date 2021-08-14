package chess.solver;

import java.util.List;
import java.util.Map;

public class LivePiecesChessStateEvaluator implements ChessStateEvaluator {

    private final Map<ChessPiece, Double> chessPieceValues = Map.of(
        ChessPiece.WHITE_PAWN, 1.0,
        ChessPiece.BLACK_PAWN, 1.0,
        ChessPiece.WHITE_KNIGHT, 3.0,
        ChessPiece.BLACK_KNIGHT, 3.0,
        ChessPiece.WHITE_BISHOP, 3.5,
        ChessPiece.BLACK_BISHOP, 3.5,
        ChessPiece.WHITE_ROOK, 5.0,
        ChessPiece.BLACK_ROOK, 5.0,
        ChessPiece.WHITE_QUEEN, 7.0,
        ChessPiece.BLACK_QUEEN, 7.0
    );

    @Override
    public double evaluateChessState(ChessState chessState, boolean isWhite) {
        // TODO add check mate
        double value = evaluatePieces(chessState.getBlackCapturedPieces()) 
        - evaluatePieces(chessState.getWhiteCapturedPieces()); 
        return isWhite ? value : - value;
    }

    private double evaluatePieces(List<ChessPiece> chessPieces) {
        return chessPieces.stream().map(chessPieceValues::get).mapToDouble(Double::doubleValue).sum();
    }
    
}
