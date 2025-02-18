package ChessBoard;

public abstract class Piece {
    
    protected Position position;
    private Board board;


    public Piece(Board board) {
        this.board = board;
        position = null;
    }


    protected Board getBoard() {
        return board;
    }
 
    public abstract boolean[][] possibleMoves(boolean inCheck, Position kingPosition, Position oppPiece);

    public boolean possibleMove(Position position, boolean inCheck, Position kingPosition, Position oppPiece) {
        return possibleMoves(inCheck, kingPosition, oppPiece)[position.getRow()][position.getColumn()];
    }

    public boolean hasPossibleMove(boolean inCheck, Position kingPosition, Position oppPiece) {
        boolean[][] mat = possibleMoves(inCheck, kingPosition, oppPiece);

        for(int i = 0; i < mat.length; i++) {
            for(int j = 0; j < mat.length; j++) {
                if(mat[i][j]){
                    return true;
                }
            }
        }        
        return false;
    }
}
