import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;


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
