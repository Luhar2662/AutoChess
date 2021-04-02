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

        


    }
}
