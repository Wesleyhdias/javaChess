package ChessBoard;

public class Position {
    
    private int row;
    private int column;


    public Position(int i, int j) {
        row = i;
        column = j;
    }


    public void setRow(int row) {
        this.row = row;
    }
    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }
    public int getColumn() {
        return column;
    }

    public void setPosition(int row, int column) {
        this.column = column;
        this.row = row;
    }


    @Override
    public String toString() {
        return "[" + row + ", " + column + "]";
    }
}
