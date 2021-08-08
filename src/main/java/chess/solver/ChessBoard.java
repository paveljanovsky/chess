package chess.solver;

public final class ChessBoard {
    ChessPiece [][] chessBoard;

    public ChessBoard() {
        chessBoard = new ChessPiece[Constants.CHESS_BOARD_SIZE][Constants.CHESS_BOARD_SIZE];
    }

    public void put(char column, int row, ChessPiece chessPiece) {
        chessBoard[row - 1][(int) column - (int)'a'] = chessPiece;
    }

    public ChessPiece peek(char column, int row) {
        return chessBoard[row - 1][(int) column - (int)'a'];
    } 

    public void print() {
        for (int i = Constants.CHESS_BOARD_SIZE - 1; i >= 0; i--) {
            for (int j = 0; j < Constants.CHESS_BOARD_SIZE; j++) {
                if (chessBoard[i][j] != null) {
                  System.out.print(chessBoard[i][j].getCode() + " ");
                } else {
                  System.out.print("-- ");

                }
            }    
            System.out.println();
        }
    }

}