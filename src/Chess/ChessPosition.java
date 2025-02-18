package Chess;

import ChessBoard.Position;

public class ChessPosition {
    private char column;
    private int row;
    
    public ChessPosition(char column, int row) {
        if(row < 0 || row > 8 || column < 'a' || column > 'h'){
            throw new ChessException("Ivalid chess position, position does not exist");
        }

        this.column = column;
        this.row = row;
    }


    public char getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }


    public Position toPosition() {
        
        return new Position(8 - row, column - 'a');
    }


    protected ChessPosition toChessPosition(Position position) {
        return new ChessPosition((char)(position.getColumn() + 'a'), position.getRow() + 8);

    }
    


    @Override
    public String toString() {
        return "" + column + row;
    }

    
}
