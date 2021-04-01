public class Move {
    private Player player;
    private Square start;
    private Square end;
    private Piece moving;
    private Piece taken;
    private boolean castleMove = false;
    private boolean jumpMove = false;
    private int moveNum;


    public Move(Player player, Square start, Square end, Piece moving, Piece taken, boolean castling, boolean jumping, int num){
        this.player = player;
        this.start = start;
        this.end = end;
        this.moving = moving;
        this.taken = taken;
        this.castleMove = castling;
        this.jumpMove = jumping;
        this.moveNum = num;

    }

    

   
}
