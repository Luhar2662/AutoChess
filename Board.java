import java.util.Scanner;
import java.util.ArrayList;


public class Board{
    Square[][] squares;

    public Board(){
        squares = new Square[8][8];
        this.resetBoard();
    }

    public Board(Board cBoard){
        squares = new Square[8][8];
        for(int i = 0; i<8;i++){
            for(int j = 0; j<8; j++){
                
                Piece piece = cBoard.getSquare(i,j).getPiece();

                boolean white = false;
                //check null
                if(piece != null){
                    white = piece.isWhite();
                }
                else{
                    squares[i][j] = new Square(i,j,null);
                    
                }

                //check instances for piece typing
                if(piece instanceof King){
                    boolean castle = ((King)piece).canCastle();
                    King copyKing = new King(white);
                    copyKing.setCastling(castle);
                    squares[i][j] = new Square(i, j, copyKing);

                }

                if(piece instanceof Bishop){
                    Bishop copyBish = new Bishop(white);
                    squares[i][j] = new Square(i, j, copyBish);

                }

                if(piece instanceof Knight){
                    Knight copyKn = new Knight(white);
                    squares[i][j] = new Square(i, j, copyKn);

                }

                if(piece instanceof Rook){
                    Rook copyRook = new Rook(white);
                    squares[i][j] = new Square(i, j, copyRook);

                }

                if(piece instanceof Queen){
                    Queen copyQueen = new Queen(white);
                    squares[i][j] = new Square(i, j, copyQueen);

                }

                if(piece instanceof Pawn){
                    boolean hasMoved = ((Pawn)piece).hasMoved();
                    boolean eP = ((Pawn)piece).ePvalid();
                    Pawn copyPawn = new Pawn(white);
                    copyPawn.setMoved(hasMoved);
                    copyPawn.setEP(eP);
                    squares[i][j] = new Square(i, j, copyPawn);

                }

            }
        }
    }

    public Square getSquare(int x, int y){
        if (x < 0 || x > 7 || y < 0 || y > 7) {
           return null;
        }

        return squares[x][y];
    }

    //type 1: vertical, type 2: horizontal, type 3: diag
    public boolean checkRange(int x1, int y1, int x2, int y2, int type){
        if(type == 1){
            if(y1 > y2){
                for(int i = y2 + 1; i < y1; i++){
                    if(squares[x1][i].getPiece() != null){
                        return false;
                    }
                }
            }

            if(y2 > y1){
                for(int i = y1 + 1; i < y2; i++){
                    if(squares[x1][i].getPiece() != null){
                        return false;
                    }
                }
            }

            return true;
        }
        //horizontal
        if(type == 2){
            if(x1 > x2){
                for(int i = x2 + 1; i < x2; i++){
                    if(squares[i][y1].getPiece() != null){
                        return false;
                    }
                }
            }

            if(x2 > x1){
                for(int i = x1 + 1; i < x2; i++){
                    if(squares[i][y1].getPiece() != null){
                        return false;
                    }
                }
            }

            return true;
        }

        //check diagonal - casework
        if(type == 3){
            int diff = Math.abs(x1 - x2);
            // Up + Right
            if(x2>x1 && y2>y1){
                for(int i = 1; i < diff; i++){
                    if(squares[x1+i][y1+i].getPiece() != null){
                        return false;
                    }
                }
                
            }

            // Up + Left
            if(x2<x1 && y2>y1){
                for(int i = 1; i < diff; i++){
                    if(squares[x1-i][y1+i].getPiece() != null){
                        return false;
                    }
                }
                
            }

            // Down + Left
            if(x2<x1 && y2<y1){
                for(int i = 1; i < diff; i++){
                    if(squares[x1-i][y1-i].getPiece() != null){
                        return false;
                    }
                }
                
            }

            // Down + Right
            if(x2>x1 && y2<y1){
                for(int i = 1; i < diff; i++){
                    if(squares[x1+i][y1-i].getPiece() != null){
                        return false;
                    }
                }
                
            }
            return true;
        }
        

        return false;
    }


    public boolean inThreat(Square target, boolean white){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                Square current = this.getSquare(i,j);
                if(current.getPiece() != null){
                    if(current.getPiece().isWhite() != white){
                        if(!(current.getPiece() instanceof Pawn)){
                            if(current.getPiece().canMove(this, current, target)){
                                return true;
                            }
                        }
                        else{
                            if(((Pawn)(current.getPiece())).canTake(this, current, target)){
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean canBlock(Square target, boolean white){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                Square current = this.getSquare(i,j);
                if(current.getPiece() != null){
                    if(current.getPiece().isWhite() != white && !(current.getPiece() instanceof King)){
                        if(!(current.getPiece() instanceof Pawn)){
                            if(current.getPiece().canMove(this, current, target)){
                                return true;
                            }
                        }
                        else{
                            if(((Pawn)(current.getPiece())).canMove(this, current, target)){
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    

    public boolean checkmated(boolean white){
        boolean isBoxed = true;
        Square kingpos = this.getKingPos(white);
        int x1 = kingpos.getPos()[0];
        int y1 = kingpos.getPos()[1];
        boolean kingCanMove = false;
        System.out.println("checking for checkmate");
        //canKingMove?
        if(this.inThreat(kingpos,white)){
            for(int i = -1; i<2; i++){
                for(int j = -1; j<2; j++){
                    if(i>= 0 && i < 8 && j>=0 && j<8){
                    if(kingpos.getPiece().canMove(this, kingpos, this.getSquare(x1+i,y1+j))){
                        kingCanMove = true;
                    }
                }
                }
            }
        }

        if(kingCanMove){
            isBoxed = false;
            return isBoxed;
        }
        else{
            System.out.println("King cant move");
        }

        //can aggressor be taken?
        //get all aggressor squares. 
        ArrayList<Square> aggSquares = new ArrayList<Square>();
    
        for(int i = 0; i<8; i++){
            for(int j = 0; j<8; j++){
                Square current = this.getSquare(i,j);
                if(current.getPiece() != null){
                if(current.getPiece().canMove(this, current, kingpos)){
                    if(current.getPiece().isWhite() == white){
                        aggSquares.add(current);
                    }
                }
            }
        }
        }

        for(Square s : aggSquares){
            if(this.inThreat(s, !(white))){
                isBoxed = false;
            }
        }

        //define move coordinates.
        



        //get aggressing piece
        for(Square s : aggSquares){
            Piece aPiece = s.getPiece();
            int x2 = s.getPos()[0];
            int y2 = s.getPos()[1];
            
            if(!(aPiece instanceof Knight)){
                //test for diff types of motion
                //vertical
                if(x2 == x1){
                    for(int i = y1+1; i<y2; i ++){
                        if(this.canBlock(this.getSquare(x1,i), ! white)){
                            isBoxed = false;
                        }
                    }
                }
                //horizontal
                else if(y2 == y1){
                    for(int i = x1+1; i<x2; i ++){
                        if(this.canBlock(this.getSquare(x1,i), ! white)){
                            isBoxed = false;
                        }
                    }

                }

                //diag
                else{
                    int diff = Math.abs(x1 - x2);
            // Up + Right
            if(x2>x1 && y2>y1){
                for(int i = 1; i < diff; i++){
                    if(this.canBlock(this.getSquare(x1+i,y1+i), ! white)){
                            isBoxed = false;
                        }
                }
                
            }

            // Up + Left
            if(x2<x1 && y2>y1){
                for(int i = 1; i < diff; i++){
                    if(this.canBlock(this.getSquare(x1-i,y1+i), ! white)){
                        isBoxed = false;
                    }
                }
                
            }

            // Down + Left
            if(x2<x1 && y2<y1){
                for(int i = 1; i < diff; i++){
                    if(this.canBlock(this.getSquare(x1-i,y1-i), ! white)){
                        isBoxed = false;
                    }
                }
                
            }

            // Down + Right
            if(x2>x1 && y2<y1){
                for(int i = 1; i < diff; i++){
                    if(this.canBlock(this.getSquare(x1+i,y1-i), ! white)){
                        isBoxed = false;
                    }
                }
                
            }

                    }

                }


            }
            return isBoxed;
        }

        
    

    public Square getKingPos(boolean color){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                Square current = this.getSquare(i,j);
                if(current.getPiece() instanceof King && current.getPiece().isWhite()==color){
                    return current;
                }
            }
        }
        return null;

    }

    public boolean check(boolean white){
        for(int i = 0;i<8; i++){
            for(int j = 0; j<8; j++){
                Square current = this.getSquare(i,j);
                if(current.getPiece() instanceof King){
                    if(current.getPiece().isWhite() == white){
                        return this.inThreat(current, white);
                    }
                }
            }
        }
        return false;
    }

    

    public boolean updateBoard(Move move){
        Square start = move.start();
        Square end = move.end();
        int x1 = start.getPos()[0];
        int x2 = end.getPos()[0];
        int y1 = start.getPos()[1];
        int y2 = end.getPos()[1];

        if(move.taken() != null){

            squares[x2][y2].getPiece().setTaken(true);
            squares[x2][y2].setPiece(null);
        }

        //no special circumstance
        if(!move.castling() && !move.eP() && !move.jumping()){
            squares[x2][y2].setPiece(squares[x1][y1].getPiece());
            squares[x1][y1].setPiece(null);

            if(move.moving() instanceof Pawn){
                ((Pawn)(move.moving())).setMoved(true);
                if(((Pawn)(move.moving())).ePvalid()){
                    ((Pawn)(move.moving())).setEP(false);
                }

                //handle promotion
                if(end.getPos()[1] == 0 || end.getPos()[1]==7){
                    
                    boolean valInput = false;
                    int choice = 1;
                    while(!valInput){
                        System.out.println("Promotion: choose new piece. 1:Q, 2:N, 3:B, 4:R");
                        
                        if(choice == 1 || choice == 2 || choice == 3 || choice == 4){
                            valInput = true;
                        }
                        else{
                            System.out.println("invalid input, try again");
                        }
                    }

                    if(choice == 1){
                        squares[x2][y2].setPiece(new Queen(squares[x2][y2].getPiece().isWhite()));
                    }
                    if(choice == 2){
                        squares[x2][y2].setPiece(new Knight(squares[x2][y2].getPiece().isWhite()));
                    }
                    if(choice == 3){
                        squares[x2][y2].setPiece(new Bishop(squares[x2][y2].getPiece().isWhite()));
                    }
                    if(choice == 4){
                        squares[x2][y2].setPiece(new Rook(squares[x2][y2].getPiece().isWhite()));
                    }

                    
                }
            }

            for(int i = 0; i<8;i++){
                for(int j = 0; j<8; j++){
                    Square current = squares[i][j];
                    if(current.getPiece()!=null){
                        if(current.getPiece() instanceof Pawn){
                            if(((Pawn)(current.getPiece())).ePvalid()){
                                ((Pawn)(current.getPiece())).setEP(false);
                            }

                        }
                    }
                }
            }

            if(move.moving() instanceof King){
                ((King)(move.moving())).setCastling(false);
            }
        }

        //jumpmove
        if(!move.castling() && !move.eP() && move.jumping()){
            squares[x2][y2].setPiece(squares[x1][y1].getPiece());
            squares[x1][y1].setPiece(null);

            ((Pawn)(move.moving())).setEP(true);
            ((Pawn)(move.moving())).setMoved(true);

        }

        //castling
        if(move.castling() && !move.eP() && !move.jumping()){
            squares[x2][y2].setPiece(squares[x1][y1].getPiece());
            squares[x1][y1].setPiece(null);

            //queenside
            if(x2<x1){
                squares[3][y2].setPiece(squares[0][y1].getPiece());
                squares[0][y1].setPiece(null);
            }
            //kingside
            if(x1<x2){
                squares[5][y2].setPiece(squares[7][y1].getPiece());
                squares[7][y1].setPiece(null);
            }

            ((King)(move.moving())).setCastling(false);

        }

        //En Passant
        if(!move.castling() && move.eP() && !move.jumping()){
            squares[x2][y2].setPiece(squares[x1][y1].getPiece());
            squares[x1][y1].setPiece(null);

            if(move.moving().isWhite()){
                squares[x2][y2+1].setPiece(null);
            }
            else{
                squares[x2][y2-1].setPiece(null);
            }
        }

        Square kingSq = this.getKingPos(move.player().playingWhite());
        if(this.inThreat(kingSq, move.player().playingWhite())){
            System.out.println("Check!");
        }


        return true;
    }


    public void resetBoard(){
        // initialize black pieces
        squares[0][0] = new Square(0, 0, new Rook(false));
        squares[1][0] = new Square(1,0, new Knight(false));
        squares[2][0] = new Square(2,0, new Bishop(false));
        squares[3][0] = new Square(3,0, new Queen(false));
        squares[4][0] = new Square(4,0, new King(false));
        squares[7][0] = new Square(7,0, new Rook(false));
        squares[6][0] = new Square(6,0, new Knight(false));
        squares[5][0] = new Square(5,0, new Bishop(false));

        for(int i=0; i<8; i++){
            squares[i][1] = new Square(i,1, new Pawn(false));
        }

        
        // initialize white pieces
        squares[0][7] = new Square(0,7, new Rook(true));
        squares[1][7] = new Square(1,7, new Knight(true));
        squares[2][7] = new Square(2,7, new Bishop(true));
        squares[3][7] = new Square(3,7, new Queen(true));
        squares[4][7] = new Square(4,7, new King(true));
        squares[7][7] = new Square(7, 7, new Rook(true));
        squares[6][7] = new Square(6,7, new Knight(true));
        squares[5][7] = new Square(5,7, new Bishop(true));

        for(int i=0; i<8; i++){
            squares[i][6] = new Square(i,6, new Pawn(true));
        }

        // initialize remaining boxes without any piece
        for (int i = 0; i < 8; i++) {
            for (int j = 2; j < 6; j++) {
                squares[i][j] = new Square(i, j, null);
            }
        }
    }

}   