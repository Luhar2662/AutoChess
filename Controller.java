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
    public Controller(){
        black.setShutdownOptions(true, PinState.LOW);
        white.setShutdownOptions(true, PinState.LOW);
        black.low();
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



}