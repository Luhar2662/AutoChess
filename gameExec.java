import java.util.Scanner;
import java.util.ArrayList;

public class gameExec {
    public static void main(String[] args){
        //initialize game
        Scanner input = new Scanner(System.in);
        System.out.println("Enter White Player's name:");
        String whiteName = input.nextLine();
        Player p1 = new Player(true, whiteName);
        ArrayList<String> moveStList = new ArrayList();

        System.out.println("Enter Black Player's name:");
        String blackName = input.nextLine();
        Player p2 = new Player(false, blackName);

        Game game = new Game(p1,p2);

        System.out.println("starting game... " + p1.getName() + "'s turn:");

        //start game
        boolean running = true;
        game.printBoard();
        while(running == true){
            //check checkmate
            
            //take input - run until validated
            System.out.println(game.active().getName() + "'s turn:");
            boolean valid = false;
            Player active = game.active();
            Move activeMove = new Move(new Player(true, "filler"), new Square(0,0,null), new Square(0,0,null), null, null, false, false, false, 0, "none");
            while(!valid){
                //input
                System.out.println("input move: start square, end square");
                String sq1 = input.nextLine();
                String sq2 = input.nextLine();
                if(sq1.length() != 2 || sq2.length() != 2 ){
                    System.out.println("invalid input");
                }

                //ASCII conversion
                char[] ch1 = sq1.toCharArray();
                int x1 = (ch1[0] - 97);
                int y1 = 8-((ch1[1] - 48));

                char[] ch2 = sq2.toCharArray();
                int x2 = (ch2[0] - 97);
                int y2 = 8-((ch2[1] - 48));

                System.out.println("(" + x1 + "" + y1 + "),(" + x2 + "" + y2 +")");


                activeMove = game.createMove(x1, x2, y1, y2, active, sq2);
                //check move

                if(activeMove != null){
                valid = game.isValid(activeMove);
            }
                //if not valid, re prompt
                if(!valid){
                    System.out.println("invalid move try again");
                }
                else{
                    moveStList.add(sq1);
                    moveStList.add(sq2);
                }
            }

            //once valid, execute move, switch active player, restart loop
            game.runMove(activeMove);
            game.printBoard();
            if(game.getBoard().inThreat(game.getBoard().getKingPos(game.active().playingWhite()), game.active().playingWhite())){
                System.out.println("Check!");
                boolean currentColor = game.active().playingWhite();
            if(game.getBoard().checkmated(currentColor)){
                System.out.println("Checkmated!");
                if(currentColor){
                    System.out.println("Black Wins!");
                }
                else{
                    System.out.println("White Wins!");
                }

                running = false;
            }
            }

        }

        System.out.println("game finished. Print movelist?");
        String inputln = input.nextLine();
        if(inputln.equals("")){
            
        }
        else{
            game.printMoveList();
        }

        System.out.println("Print game string list?");
         inputln = input.nextLine();
        if(inputln.equals("")){
            
        }
        else{
            for(String s : moveStList){
                System.out.println(s);
            }
        }



        


    }
}
