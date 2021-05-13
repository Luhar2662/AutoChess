import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import java.io.*;


public class Controller {
    private static final GpioController gpio = GpioFactory.getInstance();
    final GpioPinDigitalOutput black = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, PinState.LOW);
    final GpioPinDigitalOutput white = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, PinState.HIGH);
    StepperMotorXThread motorX;
    StepperMotorYThread motorY;
    public Controller(){
        black.setShutdownOptions(true, PinState.LOW);
        white.setShutdownOptions(true, PinState.LOW);
        black.low();
        motorX = new StepperMotorXThread();
        motorY = new StepperMotorYThread();
    }


    public void switchTurn(boolean color){
        if(color){
            black.low();
            white.high();
        }
        else{
            black.high();

            white.low();
        }
    }

    public void resetMotors(StepperMotorXThread motorX, StepperMotorYThread motorY){
        
        try{
            File f = new File("/home/pi/Documents/AutoChess/AutoChess/xMotorPos.txt");
            BufferedReader br = new BufferedReader(new FileReader(f));
            String st = br.readLine();
            int xPos = Integer.parseInt(st);

            File f2 = new File("/home/pi/Documents/AutoChess/AutoChess/xMotorPos.txt");
            BufferedReader br1 = new BufferedReader(new FileReader(f2));
            String st1 = br1.readLine();
            int yPos = Integer.parseInt(st1);

            br.close();
            br1.close();
            double waitx = 1000;
            double waity = 1000;
        
            if(xPos>0){
            waitx = motorX.backward(xPos);
            }
            else{
            waitx = motorX.forward(-1 * xPos);
            }

            if(yPos>0){
            waity = motorY.backward(yPos);
            }
            else{
            waity = motorY.forward(-1*yPos);
            }

            try{
            if(waitx>waity){
                if(waitx>waity){
                    Thread.sleep((long)waitx);
                }
                else{Thread.sleep((long)waity);}
    
            Thread.sleep(2000);
    
            System.out.println("should be done?-------------------------------------------------------------------------------");
        }
    }catch(Exception e){}
        }
        catch(Exception e){System.out.println("filenotfound");}
        
    }

    public void movePieces(Move active){
        //holder
        double waitx = 1000;
        double waity = 1000;

        int x = 200 * (active.end().getPos()[0] - active.start().getPos()[0]);
        int y = 200 * (active.end().getPos()[1] - active.start().getPos()[1]);
        System.out.println(x + " " + y);

        if(x>0){
            waitx = motorX.forward(x);
        }
        else{
            waitx = motorX.backward(-1*x);
        }

        if(y>0){
            waity = motorY.forward(y);
        }
        else{
            waity = motorY.backward(-1*y);
        }

        try{
        if(waitx>waity){
            if(waitx>waity){
                Thread.sleep((long)waitx);
            }
            else{Thread.sleep((long)waity);}
    
            Thread.sleep(2000);
    
            System.out.println("should be done?-------------------------------------------------------------------------------");
        }
    }catch(Exception e){}

    }



}
