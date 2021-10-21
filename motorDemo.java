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
import java.util.Scanner;

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
public class motorDemo {
 
    public static void main(String[] args) throws InterruptedException {

        
        StepperMotorXThread motorXT = new StepperMotorXThread();
        StepperMotorYThread motorYT = new StepperMotorYThread();
        Controller c = new Controller();
        c.resetMotors(motorXT, motorYT);
        Scanner input = new Scanner(System.in);

        while(true){
            


            System.out.println("Motor X steps?");
            int xSteps = input.nextInt();

            System.out.println("Motor Y steps?");
            int ySteps = input.nextInt();

        double waitx = 0;
        
        if(xSteps != 0){
            if(xSteps > 0){
            waitx = motorXT.forward(xSteps);
            }
            else{
            waitx = motorXT.backward(-1 * xSteps);
            }
        }

        double waity = 0;
        if(ySteps != 0){
            if(ySteps > 0){
            waity = motorYT.forward(ySteps);
            }
            else{
            waity = motorYT.backward(-1 * ySteps);
            }
        }

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

     