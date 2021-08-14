package chess.ui;

import chess.solver.ChessState;

public class ConsoleChessDisplay implements ChessDisplay {

    @Override
    public void displayChessState(ChessState currentState) {
        System.out.println(currentState);
    }
    
}
