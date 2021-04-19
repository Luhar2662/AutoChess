import java.io.BufferedReader;
import java.io.InputStreamReader;

public class StepperMotorX {
    
    public void stepForward50() {
        try{
            String dir = "/home/pi/Documents/AutoChess/AutoChess/";
            ProcessBuilder pb = new ProcessBuilder("sudo","python", dir + "StepperXF.py");
            pb.redirectErrorStream(true);
            Process p = pb.start();
    
            BufferedReader bfr = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line ="";
            System.out.println("running py script" + line);
            line = bfr.readLine();
            System.out.println(line);
            while(((line = bfr.readLine()) != null)){
                System.out.println(line);
            }
        }catch(Exception e){System.out.println(e);}
    }

    public void forward(int steps) {
        try{
            String dir = "/home/pi/Documents/AutoChess/AutoChess/";
            ProcessBuilder pb = new ProcessBuilder("sudo","python", dir + "StepperXF.py",(""+steps));
            pb.redirectErrorStream(true);
            Process p = pb.start();
    
            BufferedReader bfr = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line ="";
            System.out.println("running py script" + line);
            line = bfr.readLine();
            System.out.println(line);
            while(((line = bfr.readLine()) != null)){
                System.out.println(line);
            }
        }catch(Exception e){System.out.println(e);}
    }

    public void backward(int steps) {
        try{
            String dir = "/home/pi/Documents/AutoChess/AutoChess/";
            ProcessBuilder pb = new ProcessBuilder("sudo","python", dir + "StepperXF.py",(""+steps), (""+(-1)));
            pb.redirectErrorStream(true);
            Process p = pb.start();
    
            BufferedReader bfr = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line ="";
            System.out.println("running py script" + line);
            line = bfr.readLine();
            System.out.println(line);
            while(((line = bfr.readLine()) != null)){
                System.out.println(line);
            }
        }catch(Exception e){System.out.println(e);}
    }
}
