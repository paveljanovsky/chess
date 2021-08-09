package chess.solver;

public final class ChessBoard {
    ChessPiece [][] chessBoard;

    public ChessBoard() {
        chessBoard = new ChessPiece[Constants.CHESS_BOARD_SIZE][Constants.CHESS_BOARD_SIZE];
    }

    public ChessBoard(ChessBoard otherChessBoard) {
        chessBoard = new ChessPiece[Constants.CHESS_BOARD_SIZE][Constants.CHESS_BOARD_SIZE];
        for (int i = 0; i < Constants.CHESS_BOARD_SIZE; i++) {
            for (int j = 0; j < Constants.CHESS_BOARD_SIZE; j++) {
                chessBoard[i][j] = otherChessBoard.chessBoard[i][j];
            }
        }
    }

    public void put(ChessSquare chessSquare, ChessPiece chessPiece) {
        chessBoard[chessSquare.getRow() - 1][(int) chessSquare.getColumn() - (int)'a'] = chessPiece;
    }

    public void remove(ChessSquare chessSquare) {
        chessBoard[chessSquare.getRow() - 1][(int) chessSquare.getColumn() - (int)'a'] = null;
    }

    public ChessPiece peek(ChessSquare chessSquare) {
        return chessBoard[chessSquare.getRow() - 1][(int) chessSquare.getColumn() - (int)'a'];
    } 

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = Constants.CHESS_BOARD_SIZE - 1; i >= 0; i--) {
            for (int j = 0; j < Constants.CHESS_BOARD_SIZE; j++) {
                if (chessBoard[i][j] != null) {
                  sb.append(chessBoard[i][j].getUnicode() + " ");
                } else {
                  sb.append("o ");

                }
            }    
            sb.append("\n");
        }
        return sb.toString();
    }

}