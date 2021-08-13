package chess.ui;

import chess.solver.ChessMove;

public interface PlayerInput {

    public ChessMove getMove();

    public boolean getPlayerIsWhite();
    
}
