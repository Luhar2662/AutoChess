public class King extends Piece{
    private boolean canCastle;
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
    public boolean canMove(Board board, Square start, Square end)
    {
        
        if (end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }
  
        int x = Math.abs(start.getPos()[1] - end.getPos()[1]);
        int y = Math.abs(start.getPos()[2] - end.getPos()[2]);
        if (x < 2 || y < 2) {
            // check if this move will not result in the king
            // being attacked if so return true
            return true;
        }


  
        return this.isValidCastling(board, start, end);
    }
}