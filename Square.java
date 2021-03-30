public class Square {
    private Piece piece;
    private int x;
    private int y;
    private String pos;

    public Square(int x, int y, Piece piece){
        this.x = x;
        this.y = y;
        this.piece = piece;
        
    }

    public int[] getPos(){
        int[] posArray = new int[]{x,y};
        return posArray;

    }

    public Piece getPiece(){
        return piece;
    }

    public void setPiece(Piece p){
        piece = p;
    }

    public void setX(int newX){
        x = newX;
    }

    public void setY(int newY){
        y = newY;
    }

}