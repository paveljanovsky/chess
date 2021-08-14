package chess.ui;

import chess.solver.ChessMove;
import chess.solver.ChessState;

public interface PlayerInput {

    public ChessMove getValidMove(ChessState currentState);

    public boolean getPlayerIsWhite();
    
}
