public class Move {
    private Player player;
    private Square start;
    private Square end;
    private Piece moving;
    private Piece taken;
    private boolean castling = false;
    private boolean jumping = false;
    private int moveNum;
    private boolean eP = false;


    public Move(Player player, Square start, Square end, Piece moving, Piece taken, boolean castling, boolean jumping, boolean eP, int num){
        this.player = player;
        this.start = start;
        this.end = end;
        this.moving = moving;
        this.taken = taken;
        this.castling = castling;
        this.jumping = jumping;
        this.moveNum = num;
        this.eP = eP;

    }

    public Piece moving(){
        return moving;
    }

    public Piece taken(){
        return taken;
    }

    public Square start(){
        return start;
    }

    public Square end(){
        return end;
    }

    public Player player(){
        return player;
    }

    public boolean eP(){
        return eP;
    }
    public boolean castling(){
        return castling;
    }
    public boolean jumping(){
        return jumping;
    }

    public int moveNum(){
        return moveNum;
    }



   
}
