package chess.ui;

import chess.solver.ChessState;

public interface ChessDisplay {

    public void displayChessState(ChessState currentState);

    public void announceNextMove(boolean isWhite, boolean isPlayer);
    
}
