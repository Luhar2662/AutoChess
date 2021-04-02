public class Pawn extends Piece{
    private boolean hasMoved = false;
    private boolean ePvalid = false;
    
    @Override
    public String token(){
        return "P";
    }
     

    public Pawn(boolean white){
        super(white);
        
    }

    public boolean ePvalid(){
        return ePvalid;
    }

    public void setMoved(boolean moved){
        hasMoved = moved;
    }

    public boolean hasMoved(){
        return hasMoved;
    }

    public void setEP(boolean valid){
        ePvalid = valid;
    }

    public boolean canTake(Board board, Square start, Square end){
        int x1 = start.getPos()[0];
        int y1 = start.getPos()[1];
        int x2 = end.getPos()[0];
        int y2 = end.getPos()[1];
        if( (Math.abs(x2-x1)) == 1 && (this.isWhite() == true && (y2-y1)==1 || this.isWhite() == false && (y2-y1)==-1) && board.getSquare(x2,y2).getPiece() != null){
            return true;
        }

        return false;
    }

    @Override
    public boolean canMove(Board board, Square start, Square end){
        //check movement, check check taking, check en passant!!!
        if(end.getPiece()!=null){
            if(end.getPiece().isWhite() == this.isWhite()){
                return false;
            }
        }

        int x1 = start.getPos()[0];
        int y1 = start.getPos()[1];
        int x2 = end.getPos()[0];
        int y2 = end.getPos()[1];

        //check straight movement
        if((x2-x1)==0 && (this.isWhite() == true && (y2-y1)==-1 || this.isWhite() == false && (y2-y1)==1) && board.getSquare(x2,y2).getPiece() == null){
            return true;
        }

        //check take
        if( (Math.abs(x2-x1)) == 1 && (this.isWhite() == true && (y2-y1)==-1 || this.isWhite() == false && (y2-y1)==1) && board.getSquare(x2,y2).getPiece() != null){
            return true;
        }

        //check jumpmove
        if((x2-x1) == 0 && ((this.isWhite() == true && (y2-y1)==-2) || (this.isWhite() == false && (y2-y1)==2)) && this.hasMoved == false && board.getSquare(x2,y2).getPiece() == null){
            return true;
        }
        
        //somehow check eP?
        if( (Math.abs(x2-x1)) == 1 && (this.isWhite() == true && (y2-y1)==1 || this.isWhite() == false && (y2-y1)==-1) && board.getSquare(x2,y2).getPiece() == null){
            if(this.isWhite()){
                if(board.getSquare(x2,y2-1).getPiece() instanceof Pawn){
                    if(((Pawn)(board.getSquare(x2,y2+1).getPiece())).ePvalid() && board.getSquare(x2,y2+1).getPiece().isWhite() != this.isWhite()){
                        return true;
                    }
                }
            }
            else{
                if(board.getSquare(x2,y2+1).getPiece() instanceof Pawn){
                    if(((Pawn)(board.getSquare(x2,y2-1).getPiece())).ePvalid() && board.getSquare(x2,y2-1).getPiece().isWhite() != this.isWhite()){
                        return true;
                    }
                }
            }
            
        }


        return false;
    }
}