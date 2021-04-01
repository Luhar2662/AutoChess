import java.util.ArrayList;

public class Player {
    
    private boolean whiteP;
    private String name;
    private ArrayList<Piece> pieces = new ArrayList<Piece>();
    
    public Player(boolean isWhite, String name){
        whiteP = isWhite;
        this.name = name;
    }

    public boolean playingWhite(){
        return whiteP;
    }

    public String getName(){
        return name;
    }




}

