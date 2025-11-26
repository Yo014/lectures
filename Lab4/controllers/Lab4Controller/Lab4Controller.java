𒉭
yo_014
Online



Direct Message

ginger
Search

chat

ginger
.ginger._
This is the beginning of your direct message history with ginger.
No servers in common

Remove Friend

Block
September 17, 2025

ginger

EDM
 — 12:29 PM

𒉭 — 12:30 PM
Move(MAX_SPEED, MAX_SPEED, 5);
    // Calculate new position after straight movement
    double distance = ((LeftReading + RightReading) / 2) * WHEEL_RADIUS;
    x += distance * Math.cos(Math.toRadians(a));
    y += distance * Math.sin(Math.toRadians(a));
    System.out.printf("(x1, y1, a1) = (%.1f, %.1f, %.1f)\n", x, y, a);

    // Spin the robot right for 6 seconds
    Move(MAX_SPEED, -MAX_SPEED, 6);
    // Calculate new position after spin
    double deltaAngle = ((RightReading - LeftReading) * WHEEL_RADIUS / WHEEL_BASE);
    a += Math.toDegrees(deltaAngle);
    a = normalizeAngle(a);
    System.out.printf("(x2, y2, a2) = (%.1f, %.1f, %.1f)\n", x, y, a);

ginger

EDM
 — 12:32 PM


ginger

EDM
 — 12:41 PM
Move(MAX_SPEED, MAX_SPEED, 5);
    // Calculate new position after straight movement
    double leftReading = leftEncoder.getValue();
    double distance = ((LeftReading - previousLeftReading) * WHEEL_RADIUS);
     x += distance * Math.cos(Math.toRadians(a));
     y += distance * Math.sin(Math.toRadians(a));
     System.out.printf("(x1, y1, a1) = (%.1f, %.1f, %.1f)\n", x, y, a);
NEW

𒉭 — 12:43 PM
private static double normalizeAngle(double angle) {
    while (angle > 180) angle -= 360;
    while (angle < -180) angle += 360;
    return angle;
  }
NEW
[12:50 PM]
double vl = MAX_SPEED * WHEEL_RADIUS;
    double vr = (MAX_SPEED-0.2) * WHEEL_RADIUS;
    double R = (WHEEL_BASE/2) * ((vl + vr)/(vr - vl));
    double omega = (vr - vl) / WHEEL_BASE;
    double deltaDistance = (vl + vr) / 2 * 10;
    double deltaTheta = omega * 10;
    double iccX = x - R * Math.sin(Math.toRadians(a));
    double iccY = y + R * Math.cos(Math.toRadians(a));
    x = Math.cos(deltaTheta) * (x - iccX) - Math.sin(deltaTheta) * (y - iccY) + iccX;
    y = Math.sin(deltaTheta) * (x - iccX) + Math.cos(deltaTheta) * (y - iccY) + iccY;
    a += Math.toDegrees(deltaTheta);
    a = normalizeAngle(a);
    System.out.printf("(x3, y3, a3) = (%.1f, %.1f, %.1f)\n", x, y, a);
    System.out.printf("ICC radius = %.1f, θΔ = %.1f\n", Math.abs(R), Math.toDegrees(deltaTheta));
NEW

ginger

EDM
 — 12:51 PM
// Spin the robot right for 6 seconds
     Move(MAX_SPEED, -MAX_SPEED, 6);
    // Calculate new position after spin
    double deltaAngle = ((RightReading - LeftReading) * WHEEL_RADIUS / WHEEL_BASE);
    a += Math.toDegrees(deltaAngle);
    a = normalizeAngle(a);
    System.out.printf("(x2, y2, a2, deltaAngle) = (%.1f, %.1f, %.1f,%.1f)\n", x, y, a, deltaAngle);
NEW
[12:57 PM]

NEW

ginger

EDM
 — 1:01 PM

NEW
[1:06 PM]
double vl = MAX_SPEED * WHEEL_RADIUS;
    double vr = (MAX_SPEED - 0.2) * WHEEL_RADIUS;
    double R = (WHEEL_BASE / 2) * ((vl + vr) / (vr - vl));
    double omega = (vr - vl) / WHEEL_BASE;

    double deltaTheta = omega * 10; // in radians

    // Compute ICC
    double iccX = x - R * Math.sin(Math.toRadians(a));
    double iccY = y + R * Math.cos(Math.toRadians(a));

    // Rotate around ICC
    double cosDt = Math.cos(deltaTheta);
    double sinDt = Math.sin(deltaTheta);

    double x = cosDt * (x - iccX) - sinDt * (y - iccY) + iccX;
    double y = sinDt * (x - iccX) + cosDt * (y - iccY) + iccY;
    a += Math.toDegrees(deltaTheta);
    a = normalizeAngle(a);

    System.out.printf("(x3, y3, a3) = (%.1f, %.1f, %.1f)\n", x, y, a);
    System.out.printf("ICC radius = %.1f, θΔ = %.1f\n", Math.abs(R), Math.toDegrees(deltaTheta));
NEW

𒉭 — 1:21 PM
// Author: NAME (SN: STUDENT NUMBER)
101265942
// Author: NAME (SN: STUDENT NUMBER)

import com.cyberbotics.webots.controller.Motor;
import com.cyberbotics.webots.controller.Robot;
Expand
Lab4Controller.java
7 KB
NEW

ginger

EDM
 — 1:22 PM

NEW
NEW

ginger

EDM
 — 1:30 PM
// Author: Santo Mukiza (SN: 101324696)
// Author: Eric Desrosiers (SN: 101265942)

import com.cyberbotics.webots.controller.Motor;
import com.cyberbotics.webots.controller.Robot;
import com.cyberbotics.webots.controller.PositionSensor;
Expand
message.txt
7 KB
NEW

Message @ginger
﻿

// Author: Santo Mukiza (SN: 101324696)
// Author: Eric Desrosiers (SN: 101265942)

import com.cyberbotics.webots.controller.Motor;
import com.cyberbotics.webots.controller.Robot;
import com.cyberbotics.webots.controller.PositionSensor;

public class Lab4Controller {

  static final double  MAX_SPEED      = 1;  //6.28 is maximum but value of 1 is required for simulator to work properly for kinematics
  static final double  WHEEL_RADIUS   = 2.05; // cm 
  static final double  WHEEL_BASE     = 5.80; // cm
  
  static Robot           Epuck;
  static Motor           LeftMotor;
  static Motor           RightMotor;
  static PositionSensor  LeftEncoder;
  static PositionSensor  RightEncoder;
  static double          LeftReading, RightReading, PreviousLeft, PreviousRight;
  static int             TimeStep;


  // Set each motor to a specific speed and wait for te given amount of seconds
  // Then stop the motors and update the position sensor readings.
  private static void Move(double leftSpeed, double rightSpeed, double seconds) {
    LeftMotor.setVelocity(leftSpeed);
    RightMotor.setVelocity(rightSpeed);
    for (double time = 0.0; time<seconds; time += (TimeStep/1000.0)) {
      Epuck.step(TimeStep);
    }
    LeftMotor.setVelocity(0);
    RightMotor.setVelocity(0);
    Epuck.step(TimeStep);
    LeftReading = LeftEncoder.getValue() - PreviousLeft;
    RightReading = RightEncoder.getValue() - PreviousRight;
    PreviousLeft = LeftEncoder.getValue();
    PreviousRight = RightEncoder.getValue();
    Epuck.step(TimeStep);
  }
  
  // Helper function to normalize angles between -180 and 180 degrees
  private static double normalizeAngle(double angle) {
    while (angle > 180) angle -= 360;
    while (angle < -180) angle += 360;
    return angle;
  }
  
  
  public static void main(String[] args) {
    Epuck = new Robot();
    TimeStep = (int) Math.round(Epuck.getBasicTimeStep());
    
    System.out.println("Time Step = " + TimeStep);

    // Get the motors
    LeftMotor = Epuck.getMotor("left wheel motor");
    RightMotor = Epuck.getMotor("right wheel motor");
    LeftMotor.setPosition(Double.POSITIVE_INFINITY);// indicates continuous rotation servo
    RightMotor.setPosition(Double.POSITIVE_INFINITY);// indicates continuous rotation serv
    LeftMotor.setVelocity(0);
    RightMotor.setVelocity(0);
    
    // Get the encoders
    LeftEncoder = Epuck.getPositionSensor("left wheel sensor");
    RightEncoder = Epuck.getPositionSensor("right wheel sensor");
    LeftEncoder.enable(TimeStep);
    RightEncoder.enable(TimeStep);
    PreviousLeft = 0; PreviousRight = 0;
    
    // Store the (x, y) location amd angle (degrees) estimate
    double x = 0.0, y = 0.0, a = 90.0;
    
    // Print initial position
    System.out.printf("(x0, y0, a0) = (%.1f, %.1f, %.1f)\n", x, y, a);
    
    // Move the robot forward for 5 seconds
    Move(MAX_SPEED, MAX_SPEED, 5);
    // Calculate new position after straight movement
    double distance = ((LeftReading + RightReading) / 2) * WHEEL_RADIUS;
    x += distance * Math.cos(Math.toRadians(a));
    y += distance * Math.sin(Math.toRadians(a));
    System.out.printf("(x1, y1, a1) = (%.1f, %.1f, %.1f)\n", x, y, a);
    
    // Spin the robot right for 6 seconds
    Move(MAX_SPEED, -MAX_SPEED, 6);
    // Calculate new position after spin
    double deltaAngle = ((RightReading - LeftReading) * WHEEL_RADIUS / WHEEL_BASE);
    a += Math.toDegrees(deltaAngle);
    a = normalizeAngle(a);
    System.out.printf("(x2, y2, a2) = (%.1f, %.1f, %.1f)\n", x, y, a);
    
    // Curve the robot right for 10 seconds with right speed 0.2 less than full left speed
    Move(MAX_SPEED, MAX_SPEED-0.2, 10);
    // Calculate new position after curve
    double vl = MAX_SPEED * WHEEL_RADIUS;
    double vr = (MAX_SPEED - 0.2) * WHEEL_RADIUS;
    double R = (WHEEL_BASE / 2) * ((vl + vr) / (vr - vl));
    double omega = (vr - vl) / WHEEL_BASE;

    double deltaTheta = omega * 10; // in radians

    // Compute ICC
    double iccX = x - R * Math.sin(Math.toRadians(a));
    double iccY = y + R * Math.cos(Math.toRadians(a));

    // Rotate around ICC
    double cosDt = Math.cos(deltaTheta);
    double sinDt = Math.sin(deltaTheta);

     x = cosDt * (x - iccX) - sinDt * (y - iccY) + iccX;
     y = sinDt * (x - iccX) + cosDt * (y - iccY) + iccY;
    a += Math.toDegrees(deltaTheta);
    a = normalizeAngle(a);

    System.out.printf("(x3, y3, a3) = (%.1f, %.1f, %.1f)\n", x, y, a);
    System.out.printf("ICC radius = %.1f, θΔ = %.1f\n", Math.abs(R), Math.toDegrees(deltaTheta));
    
    // Curve the robot left for 20 seconds with left speed 1/3 of full right speed
      
    Move(MAX_SPEED/3, MAX_SPEED,20);
    
     vl= (MAX_SPEED/3)* WHEEL_RADIUS;
     vr= MAX_SPEED * WHEEL_RADIUS;
     R=(WHEEL_BASE/2) * ((vl + vr)/(vr - vl));
     omega = (vr - vl) / WHEEL_BASE;
     deltaTheta = omega * 20;
    
     iccX = x - R * Math.sin(Math.toRadians(a));
     iccY = y + R * Math.cos(Math.toRadians(a));
    
    // Rotate around ICC
     cosDt = Math.cos(deltaTheta);
     sinDt = Math.sin(deltaTheta);
    
     x = cosDt * (x - iccX) - sinDt * (y - iccY) + iccX;
     y = sinDt * (x - iccX) + cosDt * (y - iccY) + iccY;
    a += Math.toDegrees(deltaTheta);
    a = normalizeAngle(a);
    
    System.out.printf("(x4, y4, a4) = (%.1f, %.1f, %.1f)\n", x, y, a);
    System.out.printf("ICC radius = %.1f, θΔ = %.1f\n", Math.abs(R), Math.toDegrees(deltaTheta));
    // Move the robot forward for 10 seconds
    Move(MAX_SPEED, MAX_SPEED, 10);
     distance = ((LeftReading + RightReading) / 2) * WHEEL_RADIUS;
    x += distance * Math.cos(Math.toRadians(a));
    y += distance * Math.sin(Math.toRadians(a));
    System.out.printf("(x5, y5, a5) = (%.1f, %.1f, %.1f)\n", x, y, a);
    
    
    // Spin the robot left for 7.5 seconds
    Move(-MAX_SPEED, MAX_SPEED, 7.5);
     deltaAngle = ((LeftReading - RightReading) / 2) * WHEEL_RADIUS;
    a += Math.toDegrees(deltaTheta);
    a = normalizeAngle(a);
    System.out.printf("(x6, y6, a6) = (%.1f, %.1f, %.1f)\n", x, y, a);
    
    // Move the robot forward for 20 seconds
    Move(MAX_SPEED, MAX_SPEED, 20);
     distance = ((LeftReading + RightReading) / 2) * WHEEL_RADIUS;
    x += distance * Math.cos(Math.toRadians(a));
    y += distance * Math.sin(Math.toRadians(a));
    System.out.printf("(x7, y7, a7) = (%.1f, %.1f, %.1f)\n", x, y, a);
    
    
  }
}