package chess.engine;

import chess.solver.ChessMove;
import chess.solver.ChessState;
import chess.ui.ChessDisplay;
import chess.ui.PlayerInput;
import chess.ui.PlayerMoveValidator;

public class ChessEngine {

    private final PlayerInput playerInput;
    private final ChessDisplay chessDisplay;

    public ChessEngine(PlayerInput playerInput, ChessDisplay chessDisplay) {
        this.playerInput = playerInput;
        this.chessDisplay = chessDisplay;
    }

    public void run() {
        boolean playerIsWhite = playerInput.getPlayerIsWhite();
        ChessState state = ChessState.initialChessState();
        if (playerIsWhite) {
            chessDisplay.displayChessState(state);
            ChessMove playerMove = playerInput.getValidMove(state);
            state = ChessState.fromParentChessState(state, playerMove);
            chessDisplay.displayChessState(state);

        }
    }
    
}
