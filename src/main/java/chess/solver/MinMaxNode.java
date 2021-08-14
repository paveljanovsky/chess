package chess.solver;

import static chess.solver.Inputs.PLANNING_DEPTH;

import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.List;

public class MinMaxNode {

    private final ChessState chessState;
    private final boolean isWhite;
    private final boolean isMaxNode;
    private final int depth;
    private final ChessStateEvaluator chessStateEvaluator;

    public MinMaxNode(ChessState chessState, boolean isMaxNode, boolean isWhite, int depth,
            ChessStateEvaluator chessStateEvaluator) {
        this.chessState = chessState;
        this.isWhite = isWhite;
        this.isMaxNode = isMaxNode;
        this.depth = depth;
        this.chessStateEvaluator = chessStateEvaluator;
    }

    public double evaluate() {
        if (depth == PLANNING_DEPTH) {
            return chessStateEvaluator.evaluateChessState(chessState, isWhite);
        } else {
            List<ChessMove> availableMoves = isWhite ? chessState.getAvailableWhiteChessMoves()
                    : chessState.getAvailableBlackChessMoves();
            List<MinMaxNode> children = availableMoves.stream()
                    .map(move -> ChessState.fromParentChessState(chessState, move))
                    .map(chessState -> new MinMaxNode(chessState, !isMaxNode, !isWhite, depth + 1, chessStateEvaluator))
                    .collect(Collectors.toList());
            DoubleStream childrenValues = children.stream().map(node -> node.evaluate())
                    .mapToDouble(Double::doubleValue);
            return isMaxNode ? childrenValues.max().getAsDouble() : childrenValues.min().getAsDouble();
        }
    }

}
