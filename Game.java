import java.util.ArrayList;

public class Game {
    private Board board;
    private Board prevBoard = new Board();
    private Player p1;
    private Player p2;
    private Player active;
    private ArrayList<Move> moves = new ArrayList<Move>();
    private int moveNum = 0;

    public Game(Player p1, Player p2){
        board = new Board();
        board.resetBoard();
        this.p1 = p1;
        this.p2 = p2;
        active = this.p1;
    }

    public Player active(){
        return active;
    }

    public Move createMove(int x1, int x2, int y1, int y2, Player player, String endName){
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
        
        if(jumping){
            System.out.println("jumping");
        }
        if(castling){
            System.out.println("castling");
        }
        if(eP){
            System.out.println("eP");
        }

        
        Move move = new Move(player,  start,  end,  moving,  taken,  castling,  jumping, eP,  num, endName);
        return(move);
    }


    public void undoMove(){
        board = prevBoard;
        if(active == p1){
            active = p2;
        }
        else{
            active = p1;
        }
        moves.remove(moves.size()-1);
    }

    public boolean isValid(Move move){
        System.out.println("checking valid");
        boolean isValid = false;
        if((active.playingWhite() == move.player().playingWhite()) &&  (active.playingWhite() == move.moving().isWhite())){
            if(move.moving().canMove(board, move.start(),move.end())){
                isValid = true;
                System.out.println("can move");
            }
        }

        //STILL HAVE TO CHECK FOR KINGCHECK!!! use a copied board. if a check results for that color, not valid
        Board copied = new Board(board);
        copied.updateBoard(move);
        Square target = copied.getKingPos(move.player().playingWhite());
        if(copied.inThreat(target, target.getPiece().isWhite())){
            isValid = false;
        }

        //Square kingSq = board.getKingPos(move.player().playingWhite());
        //if(board.inThreat(kingSq, move.player().playingWhite())){
        //    System.out.println("Check!");
        //}

        return isValid;

        


    }


    public Board getBoard(){
        return board;
    }

    public void runMove(Move move){
        System.out.println("running move");
        prevBoard = new Board(board);

        board.updateBoard(move);
        if(active == p1){
            active = p2;
        }
        else{
            active = p1;
        }
        System.out.println("updated board");


        moves.add(move);
        moveNum++;
        System.out.println(moves.get(moves.size()-1));
    }

    public void printBoard(){
        System.out.println("current board:");
        System.out.println("  a b c d e f g h");
        System.out.println("  ________________");
        
        for(int i=0; i<8; i++){
            System.out.print("" + (8-i) + "|");
            for(int j=0;j<8;j++){
                Square current = board.getSquare(j,i);
                if(current.getPiece() == null){
                    System.out.print("~ ");
                }
                else{
                    System.out.print(current.getPiece().token());
                    if(current.getPiece().isWhite()){
                        System.out.print(" ");
                    }
                    else{
                        System.out.print(".");
                    }
                }
            }
            System.out.println("|");
        }
        System.out.println("  _________________");

        //print taken list
        
        System.out.println("Taken Pieces:");
        System.out.println(" Black:         | White:          ");
        System.out.println("__________________________________");
        for(int i =0; i<2; i++){
            System.out.print("|");
            for(int j =0; j < 16; j++){
                Square take = board.getTakenSq(i,j);
                if(take.getPiece() == null){
                    System.out.print("~ ");
                }
                else{
                    System.out.print(take.getPiece().token());
                    if(take.getPiece().isWhite()){
                        System.out.print(" ");
                    }
                    else{
                        System.out.print(".");
                    }
                }

            }
            System.out.println("|");
        }
        System.out.println("__________________________________");

    }

    public void printMoveList(){
        for(Move m : moves){
            System.out.println(m);
        }
    }

}
