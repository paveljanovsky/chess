package chess.engine;

import chess.solver.ChessMove;
import chess.solver.ChessMovePicker;
import chess.solver.ChessState;
import chess.ui.ChessDisplay;
import chess.ui.PlayerInput;

public class ChessEngine {

    private final PlayerInput playerInput;
    private final ChessDisplay chessDisplay;
    private final ChessMovePicker chessMovePicker;

    public ChessEngine(PlayerInput playerInput, ChessDisplay chessDisplay) {
        this.playerInput = playerInput;
        this.chessDisplay = chessDisplay;
        this.chessMovePicker = new ChessMovePicker();
    }

    public void run() {
        boolean playerIsWhite = playerInput.getPlayerIsWhite();
        ChessState currentChessState = ChessState.initialChessState();
        if (playerIsWhite) {
            chessDisplay.displayChessState(currentChessState);
            currentChessState = playerMove(currentChessState, playerIsWhite);
        }
        // TODO add while condition
        while (true) {
            chessDisplay.displayChessState(currentChessState);
            currentChessState = aiMove(currentChessState, playerIsWhite);
            chessDisplay.displayChessState(currentChessState);
            currentChessState = playerMove(currentChessState, playerIsWhite);
        }
    }

    private ChessState playerMove(ChessState currentChessState, boolean playerIsWhite) {
        ChessMove playerMove = playerInput.getValidMove(currentChessState);
        chessDisplay.announceNextMove(playerIsWhite, true);
        return ChessState.fromParentChessState(currentChessState, playerMove);
    }

    private ChessState aiMove(ChessState currentChessState, boolean playerIsWhite) {
        chessDisplay.announceNextMove(!playerIsWhite, false);
        ChessMove aiMove = chessMovePicker.pickNextChessMove(currentChessState, !playerIsWhite);
        return ChessState.fromParentChessState(currentChessState, aiMove);
    }

}
