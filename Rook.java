public class Rook extends Piece{
    private boolean canCastle = true;
    public Rook(boolean white){
        super(white);
    }

    @Override
    public boolean canMove(Board board, Square start, Square end){
        
        if(end.getPiece().isWhite() == this.isWhite()){
            return false;
        }
        
        int x = Math.abs(start.getPos()[0] - end.getPos()[0]);
        int y = Math.abs(start.getPos()[1] - end.getPos()[1]);

        //horizontal [2]
        if(x != 0 && y==0){
            return board.checkRange(start.getPos()[0], start.getPos()[1], end.getPos()[0], end.getPos()[1], 2);
        }

        //vertical [1]
        if(x == 0 && y!=0){
            return board.checkRange(start.getPos()[0], start.getPos()[1], end.getPos()[0], end.getPos()[1], 1);
        }

        return false;
    }

    public void setCastle(boolean in){
        canCastle = in;
    }
}