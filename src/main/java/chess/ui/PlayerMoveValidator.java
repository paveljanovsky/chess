package chess.ui;

import java.util.List;

import chess.solver.ChessMove;
import chess.solver.ChessState;
import chess.solver.InvalidChessMoveException;

public class PlayerMoveValidator {

    public ChessMove validate(ChessMove playerMove, ChessState currentState) {
        List<ChessMove> availableMoves = playerMove.getChessPiece().isWhite() 
          ? currentState.getAvailableWhiteChessMoves()
          : currentState.getAvailableBlackChessMoves();
          if (!availableMoves.contains(playerMove)) {
                throw new InvalidChessMoveException("Invalid chess move");
          }
          return playerMove;
    }
    
}
