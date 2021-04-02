public class Bishop extends Piece{
    
    public Bishop(boolean white){
        super(white);
    }

    @Override
    public String token(){
        return "B";
    }
    @Override
    public boolean canMove(Board board, Square start, Square end){
        
        if(end.getPiece()!=null){
            if(end.getPiece().isWhite() == this.isWhite()){
                return false;
            }
        }
        
        int x = Math.abs(start.getPos()[0] - end.getPos()[0]);
        int y = Math.abs(start.getPos()[1] - end.getPos()[1]);

        if(x == y && x!=0){
            return board.checkRange(start.getPos()[0], start.getPos()[1], end.getPos()[0], end.getPos()[1], 3);
        }

        return false;
    }
}