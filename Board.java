public class Board{
    Square[][] squares;

    public Board(){
        this.resetBoard();
    }

    public Board(Board cBoard){
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
                squares[x2][y2-1].setPiece(null);
            }
            else{
                squares[x2][y2+1].setPiece(null);
            }
        }



        return true;
    }


    public void resetBoard(){
        // initialize black pieces
        squares[0][0] = new Square(0, 0, new Rook(false));
        squares[0][1] = new Square(0, 1, new Knight(false));
        squares[0][2] = new Square(0, 2, new Bishop(false));
        squares[0][3] = new Square(0, 3, new Queen(false));
        squares[0][4] = new Square(0, 4, new King(false));
        squares[0][7] = new Square(0, 7, new Rook(false));
        squares[0][6] = new Square(0, 6, new Knight(false));
        squares[0][5] = new Square(0, 5, new Bishop(false));

        for(int i=0; i<8; i++){
            squares[1][i] = new Square(1,i, new Pawn(false));
        }

        
        // initialize white pieces
        squares[7][0] = new Square(7, 0, new Rook(true));
        squares[7][1] = new Square(7, 1, new Knight(true));
        squares[7][2] = new Square(7, 2, new Bishop(true));
        squares[7][3] = new Square(7, 3, new Queen(true));
        squares[7][4] = new Square(7, 4, new King(true));
        squares[7][7] = new Square(7, 7, new Rook(true));
        squares[7][6] = new Square(7, 6, new Knight(true));
        squares[7][5] = new Square(7, 5, new Bishop(true));

        for(int i=0; i<8; i++){
            squares[7][i] = new Square(7,i, new Pawn(true));
        }

        // initialize remaining boxes without any piece
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                squares[i][j] = new Square(i, j, null);
            }
        }
    }

}   