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