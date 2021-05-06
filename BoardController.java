import java.util.Scanner;

public class BoardController {
    private Board board;
    private StepperMotorXThread motorX;
    private StepperMotorYThread motorY;
    private boolean magEngaged = false;
    private double currentx;
    private double currenty;

    

    //this class will move pieces
    
    public BoardController(Board board, StepperMotorXThread motorX, StepperMotorYThread motorY){
        this.board = board;
        this.motorX = motorX;
        this.motorY = motorY;
        magEngaged = false;
    }

    public void vertical(int dist){

    }

    public void horizontal(int dist){

    }
}
