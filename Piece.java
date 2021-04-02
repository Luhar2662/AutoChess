public abstract class Piece{
    private boolean taken = false;
    private boolean white = false;

    public Piece(boolean white){
        this.white = white;
    }

    public boolean isWhite(){
        return white;
    }

    public boolean isTaken(){
        return taken;
    }

    public void taken(){
        taken = true;
    }

    public void setColor(boolean newCol){
        white = newCol;
    }

    public void setTaken(boolean newS){
        taken = newS;
    }

    public abstract boolean canMove (Board board, Square start, Square end);

    public abstract String token ();
}