import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ThreadHandler extends Thread{

    private int steps;
    private boolean forward;
    private boolean xMotor;


    public ThreadHandler(boolean motor){
         this.xMotor = motor;
         steps = 50;
         forward = true;
    }

    public ThreadHandler(boolean motor, int steps){
        this.xMotor = motor;
        this.steps = steps;
        forward = true;
    }
    public ThreadHandler(boolean motor, int steps,boolean backward){
        this.xMotor = motor;
        this.steps = steps;
        forward = !(backward);
    }

    public void run(){
        if(xMotor){
            if(forward){
                try{
                    String dir = "/home/pi/Documents/AutoChess/AutoChess/";
                    ProcessBuilder pb = new ProcessBuilder("sudo","python", dir + "StepperXF.py",(""+steps));
                    pb.redirectErrorStream(true);
                    Process p = pb.start();
            
                    BufferedReader bfr = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    String line ="";
                    //System.out.println("running py script" + line);
                    line = bfr.readLine();
                    System.out.println(line);
                    while(((line = bfr.readLine()) != null)){
                        //System.out.println(line);
                    }
                }catch(Exception e){System.out.println(e);}
            }
            else{
                try{
                    String dir = "/home/pi/Documents/AutoChess/AutoChess/";
                    ProcessBuilder pb = new ProcessBuilder("sudo","python", dir + "StepperXF.py",(""+steps), (""+(-1)));
                    pb.redirectErrorStream(true);
                    Process p = pb.start();
            
                    BufferedReader bfr = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    String line ="";
                    //System.out.println("running py script" + line);
                    line = bfr.readLine();
                    System.out.println(line);
                    while(((line = bfr.readLine()) != null)){
                       //System.out.println(line);
                    }
                }catch(Exception e){System.out.println(e);}
            }

        }

        else{
            if(forward){
                try{
                    String dir = "/home/pi/Documents/AutoChess/AutoChess/";
                    ProcessBuilder pb = new ProcessBuilder("sudo","python", dir + "StepperYF.py",(""+steps));
                    pb.redirectErrorStream(true);
                    Process p = pb.start();
            
                    BufferedReader bfr = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    String line ="";
                    //System.out.println("running py script" + line);
                    line = bfr.readLine();
                    System.out.println(line);
                    while(((line = bfr.readLine()) != null)){
                        //System.out.println(line);
                    }
                }catch(Exception e){System.out.println(e);}
            }
            else{
                try{
                    String dir = "/home/pi/Documents/AutoChess/AutoChess/";
                    ProcessBuilder pb = new ProcessBuilder("sudo","python", dir + "StepperYF.py",(""+steps), (""+(-1)));
                    pb.redirectErrorStream(true);
                    Process p = pb.start();
            
                    BufferedReader bfr = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    String line ="";
                    //System.out.println("running py script" + line);
                    line = bfr.readLine();
                    System.out.println(line);
                    while(((line = bfr.readLine()) != null)){
                        //System.out.println(line);
                    }
                }catch(Exception e){System.out.println(e);}
            }

        }

        }
    }
    

