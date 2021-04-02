import java.util.ArrayList;

public class Game {
    private Board board;
    private Player p1;
    private Player p2;
    private Player active;
    private ArrayList<Move> moves;
    private int moveNum = 0;

    public Game(Player p1, Player p2){
        board = new Board();
        this.p1 = p1;
        this.p2 = p2;
        if(p1.playingWhite()){
            active = p1;
        }
        else{
            active = p2;
        }
    }

    public Move createMove(int x1, int x2, int y1, int y2, Player player){
        if(x1 < 0 || x1 > 7 || y1 < 0 || y1 > 7 || x2 < 0 || x2 > 7 || y2 < 0 || y2 > 7){
            return null;
        }
        
        if(board.getSquare(x1,y1).getPiece() ==  null){
            return null;
        }

        Square start = board.getSquare(x1,y1);
        Square end = board.getSquare(x2,y2);
        Piece taken = end.getPiece();
        Piece moving = start.getPiece();
        boolean castling = false;
        boolean jumping = false;
        int num = moveNum;
        boolean eP = false;

        if(!(moving instanceof Pawn) && !(moving instanceof King)){
            castling = false;
            jumping = false; 
            eP = false;
        }
        else if(moving instanceof Pawn){
            if(start.getPos()[0] == end.getPos()[0] && (Math.abs(start.getPos()[1]-end.getPos()[1]) == 2)){
                jumping = true;
                castling = false;
                eP = false;
            }

            if(Math.abs(start.getPos()[0] - end.getPos()[0]) == 1 && (Math.abs(start.getPos()[1]-end.getPos()[1]) == 1) && end.getPiece() == null){
                eP = true;
                castling = false;
                jumping = false;
            }
        }
        else{
            if(start.getPos()[1] == end.getPos()[1] && (Math.abs(start.getPos()[0]-end.getPos()[0]) == 2)){
                castling = true;
                jumping = false;
                eP = false;
            }
        }
        


        
        Move move = new Move(player,  start,  end,  moving,  taken,  castling,  jumping, eP,  num);
        return(move);
    }

    public boolean isValid(Move move){
        boolean isValid = false;
        if(active == move.player()){
            if(move.moving().canMove(board, move.start(),move.end())){
                isValid = true;
            }
        }

        //STILL HAVE TO CHECK FOR KINGCHECK!!! use a copied board. if a check results for that color, not valid
        Board copied = new Board(board);
        copied.updateBoard(move);
        Square target = copied.getKingPos(move.player().playingWhite());
        if(copied.inThreat(target, target.getPiece().isWhite())){
            isValid = false;
        }


        return isValid;


    }

    public void runMove(Move move){
        board.updateBoard(move);
        if(active == p1){
            active = p2;
        }
        else{
            active = p1;
        }
        moves.add(move);
        moveNum++;
    }

}
