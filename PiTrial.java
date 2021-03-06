// START SNIPPET: blink-gpio-snippet


/*
 * #%L
 * **********************************************************************
 * ORGANIZATION  :  Pi4J
 * PROJECT       :  Pi4J :: Java Examples
 * FILENAME      :  BlinkGpioExample.java
 *
 * This file is part of the Pi4J project. More information about
 * this project can be found here:  https://pi4j.com/
 * **********************************************************************
 * %%
 * Copyright (C) 2012 - 2021 Pi4J
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

 

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

/**
 * This example code demonstrates how to perform simple
 * blinking LED logic of a GPIO pin on the Raspberry Pi
 * using the Pi4J library.
 *
 * @author Robert Savage
 */
public class PiTrial {
 
    public static void main(String[] args) throws InterruptedException {

        System.out.println("<--Pi4J--> GPIO Blink Example ... started.");
        StepperMotorX motorX = new StepperMotorX();
        StepperMotorY motorY = new StepperMotorY();
        //motorX.backward(75);
        //motorY.backward(75);
        //motorX.forward(100);
        //motorY.forward(100);

        StepperMotorXThread motorXT = new StepperMotorXThread();
        StepperMotorYThread motorYT = new StepperMotorYThread();
        Controller c = new Controller();
        //c.resetMotors(motorXT, motorYT);
        while(true){
        double waitx = motorXT.forward(400);
        double waity = motorYT.forward(400);
       
        System.out.println(waitx);

        if(waitx>waity){
            Thread.sleep((long)waitx);
        }
        else{Thread.sleep((long)waity);}

        Thread.sleep(1000);

        System.out.println("should be done?-------------------------------------------------------------------------------");

        
        //c.resetMotors(motorXT, motorYT);
        waitx = motorXT.backward(400);
        waity = motorYT.backward(400);
       
        System.out.println(waitx);

        if(waitx>waity){
            Thread.sleep((long)waitx);
        }
        else{Thread.sleep((long)waity);}

        Thread.sleep(1000);

        System.out.println("should be done?-------------------------------------------------------------------------------");

        
        }
    }
}

       /* File dir = new File("/Documents/AutoChess/AutoChess/");
        try{
            System.out.println("calling p");
        Process p = Runtime.getRuntime().exec("python StepCont.py", null, dir);
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line = "";
        while((line=reader.readLine()) != null){
            System.out.println(line+="\n");
        }
        }
        catch(IOException e){
            System.out.println("yo errors");
        }
        */
       
        // create gpio controller
        /*final GpioController gpio = GpioFactory.getInstance();

        // provision gpio pin #01 & #03 as an output pins and blink
        final GpioPinDigitalOutput led1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01);
        final GpioPinDigitalOutput led2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03);

        // provision gpio pin #02 as an input pin with its internal pull down resistor enabled
        final GpioPinDigitalInput myButton = gpio.provisionDigitalInputPin(RaspiPin.GPIO_02, PinPullResistance.PULL_DOWN);

        // create and register gpio pin listener
        myButton.addListener(new GpioPinListenerDigital() {
                @Override
                public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                    // when button is pressed, speed up the blink rate on LED #2
                    if(event.getState().isHigh()){
                      led2.blink(200);
                    }
                    else{
                      led2.blink(1000);
                    }
                }
            });

        // continuously blink the led every 1/2 second for 15 seconds
        led1.blink(500);

        // continuously blink the led every 1 second
        led2.blink(1000);

        System.out.println(" ... the LED will continue blinking until the program is terminated.");
        System.out.println(" ... PRESS <CTRL-C> TO STOP THE PROGRAM.");

        // keep program running until user aborts (CTRL-C)
        
        // stop all GPIO activity/threads
        // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
        // gpio.shutdown();   <--- implement this method call if you wish to terminate the Pi4J GPIO controller
    }
}
*/

//END SNIPPET: blink-gpio-snippet
