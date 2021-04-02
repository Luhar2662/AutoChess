public class Knight extends Piece{
    public Knight(boolean white){
        super(white);
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

        return x * y == 2;
    }
}