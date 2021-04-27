public class trial {
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

        while(true){
        double waitx = motorXT.forward(40);
        double waity = motorYT.forward(40);
       
        System.out.println(waitx);

        if(waitx>waity){
            Thread.sleep((long)waitx);
        }
        else{Thread.sleep((long)waity);}

        Thread.sleep(2000);

        System.out.println("should be done?-------------------------------------------------------------------------------");

        
        }
    }
}
