#!/usr/bin/python
# Import required libraries
import sys
import time
import RPi.GPIO as GPIO
 
# Use BCM GPIO references
# instead of physical pin numbers
GPIO.setmode(GPIO.BCM)
 
# Define GPIO signals to use
# Physical pins 11,15,16,18
# GPIO17,GPIO22,GPIO23,GPIO24

Dir = 5
Stp = 6
 
# Set all pins as output
print("Setup pins")
GPIO.setup(Dir,GPIO.OUT)
GPIO.output(Dir, False)
GPIO.setup(Stp,GPIO.OUT)
GPIO.output(Stp,False)

if(len(sys.argv) > 2):
  StepDir = int(sys.argv[2])
else:
  StepDir = 1 # Set to 1 or 2 for clockwise
            # Set to -1 or -2 for anti-clockwise
 
if(StepDir>0):
    GPIO.output(Dir,True)
else:
    GPIO.output(Dir,False)

WaitTime = 10/float(4000)
 
# Initialise variables
StepCounter = 0
cycles = 0
if len(sys.argv) > 1:
  limit = int(sys.argv[1])
else:
  limit = 50
# Start main loop
while cycles<limit:
    GPIO.output(Stp, True)
    time.sleep(WaitTime/2)
    GPIO.output(Stp,False)
    time.sleep(WaitTime/2)
    cycles += 1

    if(cycles%10 == 0 and StepDir > 0):
     f = open("/home/pi/Documents/AutoChess/AutoChess/yMotorPos.txt", "r")
     currentPos =  int(f.read())
     f.close()
     f = open("/home/pi/Documents/AutoChess/AutoChess/yMotorPos.txt", "w")
     f.write(str(currentPos + 10))
     f.close()
     print(cycles, "Y")
    if(cycles%10 == 0 and StepDir<0):
     f = open("/home/pi/Documents/AutoChess/AutoChess/yMotorPos.txt", "r")
     currentPos =  int(f.read())
     f.close()
     f = open("/home/pi/Documents/AutoChess/AutoChess/yMotorPos.txt", "w")
     f.write(str(currentPos - 10))
     f.close()
     print(cycles, "Y")
