import java.io.BufferedReader;
import java.io.InputStreamReader;

public class StepperMotorYThread {
    private boolean busy;
    
    public double stepForward50() {
        ThreadHandler t = new ThreadHandler(false);
        t.start();
        return(.01 * 50 * 1000 * 4);
    }

    public double forward(int steps) {
        ThreadHandler t = new ThreadHandler(false,steps);
        t.start();
        return(.01 * steps * 1000 * 4);
    }
    public double backward(int steps) {
        ThreadHandler t = new ThreadHandler(false,steps,true);
        t.start();
        return(.01 * steps * 1000 * 4);
    }
}
