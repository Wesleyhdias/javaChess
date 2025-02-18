package Chess.ChessPieces;

import java.util.List;
import java.lang.Math;


import Chess.ChessPiece;
import Chess.PieceColor;
import ChessBoard.Position;
import ChessBoard.Board;


public class King extends ChessPiece{

    public King(Board board, PieceColor color) {
        super(board, color);
    }
    

    public boolean canMove(Position position, List<ChessPiece> oppPieces){

        boolean vai = false;
            
            
        for(ChessPiece oppPiece : oppPieces){
            if(oppPiece.getClass().getName() == "Chess.ChessPieces.King"){

                if(Math.abs(oppPiece.getChessPosition().toPosition().getRow() - position.getRow()) < 2 && Math.abs(oppPiece.getChessPosition().toPosition().getColumn() - position.getColumn()) < 2){
                    continue;
                }

                vai = true;

            }else{ 

                if(oppPiece.possibleMove(position, false, null, null)){
                    return false;
                }
            }
        }

        return vai;
    }
        
        
    @Override
    public boolean[][] possibleMoves(boolean inCheck, Position kingPosition, Position oppPiece) {


        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        List<ChessPiece> oppPieces = getBoard().getOnBoardColoredPieces(getOppColor(getColor()));

        
        Position pos = new Position(0, 0);

        pos.setPosition(position.getRow() + 1, position.getColumn());
        if(getBoard().hasPosition(pos) && canMove(pos, oppPieces)){
            mat[pos.getRow()][pos.getColumn()] = true;
        }

        pos.setPosition(position.getRow() - 1, position.getColumn());
        if(getBoard().hasPosition(pos) && canMove(pos, oppPieces)){
            mat[pos.getRow()][pos.getColumn()] = true;
        }

        pos.setPosition(position.getRow() + 1, position.getColumn() + 1);
        if(getBoard().hasPosition(pos) && canMove(pos, oppPieces)){
            mat[pos.getRow()][pos.getColumn()] = true;
        }

        pos.setPosition(position.getRow() - 1, position.getColumn() + 1);
        if(getBoard().hasPosition(pos) && canMove(pos, oppPieces)){
            mat[pos.getRow()][pos.getColumn()] = true;
        }

        pos.setPosition(position.getRow() - 1, position.getColumn() - 1);
        if(getBoard().hasPosition(pos) && canMove(pos, oppPieces)){
            mat[pos.getRow()][pos.getColumn()] = true;
        }

        pos.setPosition(position.getRow() + 1, position.getColumn() - 1);
        if(getBoard().hasPosition(pos) && canMove(pos, oppPieces)){
            mat[pos.getRow()][pos.getColumn()] = true;
        }

        pos.setPosition(position.getRow(), position.getColumn() - 1);
        if(getBoard().hasPosition(pos) && canMove(pos, oppPieces)){
            mat[pos.getRow()][pos.getColumn()] = true;
        }

        pos.setPosition(position.getRow(), position.getColumn() + 1);
        if(getBoard().hasPosition(pos) && canMove(pos, oppPieces)){
            mat[pos.getRow()][pos.getColumn()] = true;
        }


        return mat;
    }

    @Override
    public String toString() {
        return "K ";
    }
}
