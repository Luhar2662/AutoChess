public class King extends Piece{
    private boolean canCastle = true;
    private boolean inCheck;
    
    public King(boolean white){
        super(white);
    }

    public boolean canCastle(){
        return canCastle;
    }

    public void setCastling(boolean can){
        canCastle = can;
    }

    public void setCheck(boolean check){
        inCheck = check;
    }

    public boolean inCheck(Board board, Square position){

        return false;
    }

    @Override
    public String token(){
        return "K";
    }


    @Override
    public boolean canMove(Board board, Square start, Square end)
    {
     
        if(end.getPiece() != null){
        if (end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }
    }

        
  
        int x = Math.abs(start.getPos()[0] - end.getPos()[0]);
        int y = Math.abs(start.getPos()[1] - end.getPos()[1]);

        //check castling queenside
        if(canCastle && end.getPos()[0] == 2 && start.getPos()[0] == 4 && board.inThreat(start,this.isWhite())==false && end.getPos()[1] == start.getPos()[1]){
            int currentY = start.getPos()[1];
            if(board.getSquare(0,currentY).getPiece() instanceof Rook && board.getSquare(1,currentY).getPiece() == null && board.getSquare(2,currentY).getPiece() == null && board.getSquare(3,currentY).getPiece() == null){
                if(board.inThreat(board.getSquare(0,currentY), this.isWhite())==false && board.inThreat(board.getSquare(1,currentY), this.isWhite())==false && board.inThreat(board.getSquare(2,currentY),this.isWhite())==false && board.inThreat(board.getSquare(3,currentY),this.isWhite())==false){
                    return true;
                }
            }
            return false;

        }

        //check kingside
        if(canCastle && end.getPos()[0] == 6 && start.getPos()[0] == 4 && board.inThreat(start,this.isWhite())==false && end.getPos()[1] == start.getPos()[1]){
            int currentY = start.getPos()[1];
            if(board.getSquare(7,currentY).getPiece() instanceof Rook && board.getSquare(6,currentY).getPiece() == null && board.getSquare(5,currentY).getPiece() == null ){
                if(board.inThreat(board.getSquare(7,currentY),this.isWhite())==false && board.inThreat(board.getSquare(6,currentY),this.isWhite())==false && board.inThreat(board.getSquare(5,currentY),this.isWhite())==false){
                    return true;
                }
            }
            return false;

        }

        if (x < 2 && y < 2) {
            // check if this move will not result in the king
            // being attacked if so return true
            if(board.inThreat(end, this.isWhite())){
                return false;
            }

            return true;
        }

        return false;

  
        
    }
}