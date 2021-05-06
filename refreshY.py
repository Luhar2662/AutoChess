#!/usr/bin/python
# Import required libraries
import sys
import time
import RPi.GPIO as GPIO
 
# Use BCM GPIO references
# instead of physical pin numbers
GPIO.setmode(GPIO.BCM)
 
# Define GPIO signals to use
# Physical pins 29,31,32,33
# GPIO05,GPIO06,GPIO12,GPIO13
StepPins = [5,6,12,13]
 
# Set all pins as output
for pin in StepPins:
  print("Setup pins")
  GPIO.setup(pin,GPIO.OUT)
  GPIO.output(pin, False)
 
# Define advanced sequence
# as shown in manufacturers datasheet
Seq = [[1,0,1,0],
       [0,1,1,0],
       [0,1,0,1],
       [1,0,0,1]]
       
 
StepCount = len(Seq)
if(len(sys.argv) > 2):
  StepDir = int(sys.argv[2])
else:
  StepDir = 1 
            # Set to -1 or -2 for anti-clockwise
 
# Read wait time from command line
WaitTime = 10/float(1000)
 
# Initialise variables
StepCounter = 0
cycles = 0
if len(sys.argv) > 1:
  limit = int(sys.argv[1])
else:
  limit = 50
# Start main loop