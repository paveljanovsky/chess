package chess.solver;

import static chess.solver.Inputs.PLANNING_DEPTH;

import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class MinMaxNode {

    private static final Comparator<MinMaxNode> CHILD_COMPARATOR 
      = Comparator.comparing(MinMaxNode::getValue);


    private final ChessState chessState;
    private final boolean isWhite;
    private final boolean isMaxNode;
    private final int depth;
    private final ChessStateEvaluator chessStateEvaluator;

    private Double value;
    private MinMaxNode bestChild; // TODO do we need this?
    private ChessMove moveToBestChild;

    public MinMaxNode(ChessState chessState, boolean isMaxNode, boolean isWhite, int depth,
            ChessStateEvaluator chessStateEvaluator) {
        this.chessState = chessState;
        this.isWhite = isWhite;
        this.isMaxNode = isMaxNode;
        this.depth = depth;
        this.chessStateEvaluator = chessStateEvaluator;
    }

    public void evaluate() {
        if (depth == PLANNING_DEPTH) {
            value = chessStateEvaluator.evaluateChessState(chessState, isWhite);
        } else {
            List<ChessMove> availableMoves = isWhite ? chessState.getAvailableWhiteChessMoves()
                    : chessState.getAvailableBlackChessMoves();
            Map<MinMaxNode, ChessMove> children = availableMoves.stream()
            .collect(Collectors.toMap(move -> 
              new MinMaxNode(ChessState.fromParentChessState(chessState, move), 
                             !isMaxNode, !isWhite, depth + 1, chessStateEvaluator), 
              Function.identity()));
                   // .map(move -> ChessState.fromParentChessState(chessState, move))
                    //.map(chessState -> new MinMaxNode(chessState, !isMaxNode, !isWhite, depth + 1, chessStateEvaluator))
                    //.collect(Collectors.toList());
            children.keySet().forEach(MinMaxNode::evaluate);
            bestChild = isMaxNode ? children.keySet().stream().max(CHILD_COMPARATOR).get() 
              : children.keySet().stream().min(CHILD_COMPARATOR).get();
            value = bestChild.getValue();
            moveToBestChild = children.get(bestChild);
        }
    }

    public double getValue() {
        if (value == null) {
            throw new IllegalStateException("Getting value before the node was evaluated.");
        }
        return value;
    }

    public MinMaxNode getBestChild() {
        if (bestChild == null) {
            throw new IllegalStateException("Getting bestChild before the node was evaluated.");
        }
        return bestChild;
    }

    public ChessMove getMoveToBestChild() {
        if (moveToBestChild == null) {
            throw new IllegalStateException("Getting moveToBestChild before the node was evaluated.");
        }
        return moveToBestChild;
    }

}
