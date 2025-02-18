package Chess.ChessPieces;

import ChessBoard.Position;
import Chess.ChessPiece;
import Chess.PieceColor;
import ChessBoard.Board;

public class Rook extends ChessPiece{

    public boolean[][] between;

    public Rook(Board board, PieceColor color) {
        super(board, color);
        between = new boolean[getBoard().getRows()][getBoard().getColumns()];

    }

    private boolean hasOppKing(Position pos){

        return (getBoard().getPiece(pos).getClass().getName() == "Chess.ChessPieces.King" && !hasOpponentPiece(pos)) || !(getBoard().getPiece(pos).getClass().getName() == "Chess.ChessPieces.King");
    }


    private boolean betweenKingColumn(Position pos, Position king, Position oppPiece){

        return (king.getColumn() >= oppPiece.getColumn() && pos.getColumn() >= oppPiece.getColumn() && pos.getColumn() <= king.getColumn()) || (king.getColumn() <= oppPiece.getColumn() && pos.getColumn() <= oppPiece.getColumn() && pos.getColumn() >= king.getColumn());

    }

    private boolean betweenKingRow(Position pos, Position king, Position oppPiece){

        return (king.getRow() >= oppPiece.getRow() && pos.getRow() >= oppPiece.getRow() && pos.getRow() <= king.getRow()) || (king.getRow() <= oppPiece.getRow() && pos.getRow() <= oppPiece.getRow() && pos.getRow() >= king.getRow());

    }

    @Override
    public boolean[][] possibleMoves(boolean inCheck, Position king, Position oppPiece) {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position pos = new Position(0, 0);

        for(int i = 0; i < getBoard().getRows(); i++){
            for(int j = 0; j < getBoard().getColumns(); j++){
                between[i][j] = false;
            }
        } // uma precaução


        // cima
        pos.setPosition(position.getRow() - 1, position.getColumn());
        while(getBoard().hasPosition(pos)){
            

            if(getBoard().hasPiace(pos) && (!hasOpponentPiece(pos) 
            || hasOppKing(pos))){
                break;             
            }

            if(inCheck && oppPiece != null){ 
                
                if(betweenKingColumn(pos, king, oppPiece)){
                    between[pos.getRow()][pos.getColumn()] = true;
                    continue;
                }
                
            }

            mat[pos.getRow()][pos.getColumn()] = true;
            pos.setRow(pos.getRow() - 1);
        }
        if(getBoard().hasPosition(pos)){
            if(hasOpponentPiece(pos) && !inCheck) {
                mat[pos.getRow()][pos.getColumn()] = true;
                between[pos.getRow()][pos.getColumn()] = true;
            }

            if(inCheck && (betweenKingColumn(pos, king, oppPiece))){
                between[pos.getRow()][pos.getColumn()] = true;
            }
        }


        // baixo
        pos.setPosition(position.getRow() + 1, position.getColumn());
        while(getBoard().hasPosition(pos)){
            

            if(getBoard().hasPiace(pos) && (!hasOpponentPiece(pos) 
            || hasOppKing(pos))){
                break;
            }
            
            if(inCheck && oppPiece != null){ 
                
                if(betweenKingColumn(pos, king, oppPiece)){
                    between[pos.getRow()][pos.getColumn()] = true;
                    continue;
                }
                
            }

            mat[pos.getRow()][pos.getColumn()] = true;
            pos.setRow(pos.getRow() + 1);
        }
        if(getBoard().hasPosition(pos)){
            if(hasOpponentPiece(pos) && !inCheck){
                mat[pos.getRow()][pos.getColumn()] = true;
            }

            if(inCheck && (betweenKingColumn(pos, king, oppPiece))){
                between[pos.getRow()][pos.getColumn()] = true;
            }
        }

        

        // direita
        pos.setPosition(position.getRow(), position.getColumn() + 1);
        while(getBoard().hasPosition(pos)){
            

            
            if(getBoard().hasPiace(pos) && (!hasOpponentPiece(pos) 
            || hasOppKing(pos))){
                break;
            }
            
            if(inCheck && oppPiece != null){ 
                
                if(betweenKingColumn(pos, king, oppPiece)){
                    between[pos.getRow()][pos.getColumn()] = true;
                    continue;
                }
                
            }
            
            mat[pos.getRow()][pos.getColumn()] = true;
            pos.setColumn(pos.getColumn() + 1);
        }
        if(getBoard().hasPosition(pos)){
            
            if(inCheck && (betweenKingRow(pos, king, oppPiece))){
                between[pos.getRow()][pos.getColumn()] = true;
            }
            if(hasOpponentPiece(pos)){
                mat[pos.getRow()][pos.getColumn()] = true;
                between[pos.getRow()][pos.getColumn()] = true;
            }
        }


        // esquerda
        pos.setPosition(position.getRow(), position.getColumn() - 1);
        while(getBoard().hasPosition(pos)){
            

            if(getBoard().hasPiace(pos) && (!hasOpponentPiece(pos) 
            || hasOppKing(pos))){
                break;
            }

            if(inCheck && oppPiece != null){ 
                System.out.println("aa");
                if(betweenKingColumn(pos, king, oppPiece)){
                    between[pos.getRow()][pos.getColumn()] = true;
                }
                
                pos.setColumn(pos.getColumn() - 1);
                continue;
            }

            mat[pos.getRow()][pos.getColumn()] = true;
            pos.setColumn(pos.getColumn() - 1);
        }
        if(getBoard().hasPosition(pos)){
            if(hasOpponentPiece(pos) && !inCheck){
                mat[pos.getRow()][pos.getColumn()] = true;
                between[pos.getRow()][pos.getColumn()] = true;
            }

            if(inCheck && (betweenKingRow(pos, king, oppPiece))){
                between[pos.getRow()][pos.getColumn()] = true;
            }
        }
        
        
        if(inCheck){
            return between;
        }
        return mat;
    }


    public boolean[][] pieceToKingMoves(){
        return between;
    }

    @Override
    public String toString() {
        return "R ";
    }

}
