import java.io.BufferedReader;
import java.io.InputStreamReader;

public class StepperMotorXThread {
    private boolean busy;
    
    public double stepForward50() {
        ThreadHandler t = new ThreadHandler(true);
        t.start();
        return(.01 * 50*100 * 8);
        
    }

    public double forward(int steps) {
        ThreadHandler t = new ThreadHandler(true,steps);
        t.start();
        return(.01 * steps * 100 * 8);
    }
    public double backward(int steps) {
        ThreadHandler t = new ThreadHandler(true,steps,true);
        t.start();
        return(.01 * steps * 100 * 8);
    }
}
