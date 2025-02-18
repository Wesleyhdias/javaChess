package Chess;

// import java.util.stream.Collectors;

import Chess.ChessPieces.King;
import Chess.ChessPieces.Rook;

// import java.util.ArrayList;
import java.util.List;

import ChessBoard.Position;
import ChessBoard.Board;
import ChessBoard.Piece;

public class ChessMatch {
    
    private PieceColor currentPlayer;
    private boolean inCheck;
    private Board board;
    private int turn;

    // protected static List<ChessPiece> onBoardPieces = new ArrayList<>();
    // protected List<ChessPiece> capturedPieces = new ArrayList<>();
    protected Position oppCheckingPiece = null;

    
    public ChessMatch() {
        board = new Board(8, 8);
        currentPlayer = PieceColor.white;
        inCheck = false;
        turn = 1;

        initialSetup();
    }
    
    public int getTurn() {
        return turn;
    }
    public void setTurn(int turn) {
        this.turn = turn;
    }
    
    public PieceColor getCurrentPlayer() {
        return currentPlayer;
    }
    public void setCurrentPlayer(PieceColor currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    
    public Board getBoard() {
        return board;
    }


    private PieceColor getOppColor(PieceColor color) {
        return (color == PieceColor.white) ? PieceColor.black : PieceColor.white;
    }

    private void nextTurn(){
        turn++;

        currentPlayer = (currentPlayer == PieceColor.white) ? PieceColor.black : PieceColor.white;

        inCheck = isInCheck();
    }


    public ChessPiece[][] getPieces(){
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()]; 

        for(int i = 0; i < board.getRows(); i++){
            for(int j = 0; j < board.getColumns(); j++){
                mat[i][j] = (ChessPiece) board.getPiece(i, j);
            }
        }
    
        return mat;
    }
    
    public void placeNewPiece(char colunm, int row, ChessPiece piece){
        board.placePiece(piece, new ChessPosition(colunm, row).toPosition());
    }

    private ChessPiece getKing(PieceColor color){

        for(ChessPiece piece : board.getOnBoardColoredPieces(color)){
            if(piece.getClass().getName() == "Chess.ChessPieces.King"){
                return piece;
            }
        }

        throw new ChessException("como que nÃ£o tem rei na partida????");

    }


    private boolean isInCheck(){
        Position king = getKing(currentPlayer).getChessPosition().toPosition();
        List<ChessPiece> oppPieces = board.getOnBoardColoredPieces(getOppColor(currentPlayer));


        for(ChessPiece piece : oppPieces){
            boolean[][] pMoves = piece.possibleMoves(false, getKing(currentPlayer).getChessPosition().toPosition(), null);

            if(pMoves[king.getRow()][king.getColumn()]){
                oppCheckingPiece = piece.getChessPosition().toPosition();
                return true;
            }
        }
        return false;
        
    }


    private void ableToMove(Position position){
        if(!board.hasPiace(position)){
            throw new ChessException("there is no piece to be moved in informed position");
        }
        if(currentPlayer != ((ChessPiece)board.getPiece(position)).getColor()){
            throw new ChessException("you can move only your pieces.");
        }
        if(!board.getPiece(position).hasPossibleMove(inCheck, getKing(currentPlayer).getChessPosition().toPosition(), oppCheckingPiece)){
            throw new ChessException("there is no move available for the informed piece");
        }
    }

    private void ableToMoveTo(Position inPosition, Position outPosition){
        if(!board.getPiece(inPosition).possibleMove(outPosition, inCheck, getKing(currentPlayer).getChessPosition().toPosition(), oppCheckingPiece)){
            throw new ChessException("this piece cant move to informed position");
        }
        // if(isInCheck() && board.getPiece(inPosition).){
        //     throw new ChessException("King is in check! protect him!");
        // }
    }

    private ChessPiece move(Position inPosition, Position outPosition){
        Piece p = board.removePiece(inPosition);
        Piece capturedPiece = board.removePiece(outPosition);
        board.placePiece(p, outPosition);

        nextTurn();

        return (ChessPiece)capturedPiece;
    }

    public ChessPiece movePiece(ChessPosition inChessPosition, ChessPosition outChessPosition){

        Position inPosition = inChessPosition.toPosition();
        Position outPosition = outChessPosition.toPosition();

        
        ableToMove(inPosition);
        ableToMoveTo(inPosition, outPosition);

        ChessPiece capturedPiece = move(inPosition, outPosition);
        return capturedPiece;


    }


    public boolean[][] gamePossibleMoves(ChessPosition inChessPosition){
        Position inPosition = inChessPosition.toPosition();
        ableToMove(inPosition);

        return board.getPiece(inPosition).possibleMoves(inCheck, getKing(currentPlayer).getChessPosition().toPosition(), oppCheckingPiece);
    }

    private void initialSetup(){
        placeNewPiece('a', 1, new Rook(board, PieceColor.white));
        placeNewPiece('e', 1, new King(board, PieceColor.white));
        placeNewPiece('h', 3, new Rook(board, PieceColor.white));
        placeNewPiece('a', 8, new Rook(board, PieceColor.black));
        placeNewPiece('h', 6, new Rook(board, PieceColor.black));
        placeNewPiece('e', 8, new King(board, PieceColor.black));
    }

}
