package ChessBoard;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Chess.ChessPiece;
import Chess.PieceColor;

public class Board {

    private int rows;
    private int columns;
    private Piece[][] pieces;
    private List<ChessPiece> onBoardPieces = new ArrayList<>();
    private List<ChessPiece> capturedPieces = new ArrayList<>();

    public Board(int rows, int columns) {
        
        if(rows <= 0 || columns <= 0){
            throw new BoardException("There must be at least one row and one column");
        }
        
        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns];
    }


    public Piece[][] getPieces(){
        return pieces;
    }


    public int getRows() {
        return rows;
    }
    public int getColumns() {
        return columns;
    }


    public Piece getPiece(int row, int column) {
        if(!hasPosition(row, column)){
            throw new BoardException("there no position in the board for informed position");
        }
        return pieces[row][column];
    }
    public Piece getPiece(Position position) {
        
        return getPiece(position.getRow(), position.getColumn());
    }

    public boolean hasPosition(int row, int column) {
        return (row >= 0 && row < rows) && (column >= 0 && column < columns);
    }
    public boolean hasPosition(Position position) {
        return hasPosition(position.getRow(), position.getColumn());
    }

    
    public boolean hasPiace(Position position){
        if(!hasPosition(position)){
            throw new BoardException("there no position in board for informed position");
        }
        return getPiece(position) != null;
    }        

    public void placePiece(Piece piece, Position position){
        if(hasPiace(position)){
            throw new BoardException("There are already a piece at position, cant be to pieces in the same position.");
        }
        pieces[position.getRow()][position.getColumn()] = piece;
        piece.position = position;
        onBoardPieces.add((ChessPiece)piece);
    }

    public Piece removePiece(Position position){
        if(!hasPiace(position)){
            return null;
        }
        Piece aux = getPiece(position);
        aux.position = null;
        pieces[position.getRow()][position.getColumn()] = null;

        capturedPieces.add((ChessPiece)aux);
        onBoardPieces.remove((ChessPiece)aux);
        
        return aux;
    }


    public List<ChessPiece> getOnBoardPieces() {
        return onBoardPieces;
    }
    
    public List<ChessPiece> getOnBoardColoredPieces(PieceColor color) {
        List<ChessPiece> coloredPieces = onBoardPieces.stream().filter(x -> ((ChessPiece) x).getColor() == color).collect(Collectors.toList());
        return coloredPieces;
    }


    public List<ChessPiece> getCapturedPieces() {
        return capturedPieces;
    }

    public List<ChessPiece> getColoredCapturedPieces(PieceColor color) {
        List<ChessPiece> coloredCapturedPieces = capturedPieces.stream().filter(x -> ((ChessPiece) x).getColor() == color).collect(Collectors.toList());
        return coloredCapturedPieces;
    }
}
