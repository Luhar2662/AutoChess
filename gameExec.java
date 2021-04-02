import java.util.Scanner;

public class gameExec {
    public static void main(String[] args){
        //initialize game
        Scanner input = new Scanner(System.in);
        System.out.println("Enter White Player's name:");
        String whiteName = input.nextLine();
        Player p1 = new Player(true, whiteName);

        System.out.println("Enter Black Player's name:");
        String blackName = input.nextLine();
        Player p2 = new Player(false, blackName);

        Game game = new Game(p1,p2);

        System.out.println("starting game... " + p1.getName() + "'s turn:");

        //start game
        boolean running = true;
        game.printBoard();
        while(running = true){
            //take input - run until validated
            System.out.println(game.active().getName() + "'s turn:");
            boolean valid = false;
            Player active = game.active();
            Move activeMove = new Move(new Player(true, "filler"), new Square(0,0,null), new Square(0,0,null), null, null, false, false, false, 0);
            while(!valid){
                //input
                System.out.println("input move: x1, y1, x2, y2");
                int x1 = input.nextInt();
                int y1 = input.nextInt();
                int x2 = input.nextInt();
                int y2 = input.nextInt();

                activeMove = game.createMove(x1, x2, y1, y2, active);
                //check move

                valid = game.isValid(activeMove);

                //if not valid, re prompt
                if(!valid){
                    System.out.println("invalid move try again");
                }
            }

            //once valid, execute move, switch active player, restart loop
            game.runMove(activeMove);
            game.printBoard();

        }

        


    }
}
