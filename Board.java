public class Board{
    Square[][] squares;

    public Board(){
        this.resetBoard();
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

        squares[x2][y2].setPiece(squares[x1][y1].getPiece());
        squares[x1][y1].setPiece(null);



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