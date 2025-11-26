// Author: Santo Mukiza  (SN:101324696)
// Author: Charlotte Di Bert (SN:101334204)
import com.cyberbotics.webots.controller.DistanceSensor;
import com.cyberbotics.webots.controller.Motor;
import com.cyberbotics.webots.controller.PositionSensor;
import com.cyberbotics.webots.controller.Field;
import com.cyberbotics.webots.controller.Node;
import com.cyberbotics.webots.controller.Supervisor;
import com.cyberbotics.webots.controller.Compass;

public class Lab9Controller {

  // Various modes that the robot may be in
  static final byte    STRAIGHT = 0;
  static final byte    SPIN_LEFT = 1;
  static final byte    PIVOT_RIGHT = 2;
  static final byte    CURVE_LEFT = 3;
  static final byte    CURVE_RIGHT = 4;
  static final byte    HEAD_TO_GOAL = 5;
  static final byte    ORIENT_TO_GOAL = 6;
  
  static final double  MAX_SPEED       = 1;    // maximum speed of the epuck robot
  static final double  WHEEL_RADIUS    = 2.05; // cm 
  static final double  WHEEL_BASE      = 5.80; // cm

  static PositionSensor  LeftEncoder;
  static PositionSensor  RightEncoder;
  static double          LeftReading, RightReading, PreviousLeft, PreviousRight;
  
  // Store the (x, y) location amd angle (degrees) estimate as well ad radius and theta-delta
  static double X, Y, A, R, TD;
  
  static double[] closestValues;
  static double[] firstContactValues;
  static double[] targetValues = {170, 0}; 


  // Read the compass
  private static double getCompassReadingInDegrees(Compass compass) {
    double compassReadings[] = compass.getValues();
    double rad = Math.atan2(compassReadings[0], compassReadings[1]);
    double bearing = (rad - Math.PI/2) / Math.PI * 180.0;
    if (bearing > 180)
      bearing = 360 - bearing;
    if (bearing < -180)
      bearing = 360 + bearing;
    return bearing;
  }
  
   static double getDistance(double[] initValues, double[] targetValues) {
    double deltaX = targetValues[0] - initValues[0];
    double deltaY = targetValues[1] - initValues[1];
    return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
  }
  
  static boolean isShorterDistance(double distance1, double distance2) {
    if (distance1 < distance2) {
      return true;
    }
    return false;
  }
  
   static void compareDistances(double[] currentPoint) {
    double distanceFromContact  = getDistance(firstContactValues, targetValues);
    double distanceFromCurrentPoint = getDistance(currentPoint, targetValues);
    if (isShorterDistance(distanceFromContact, distanceFromCurrentPoint)) {
      closestValues[0] = currentPoint[0];
      closestValues[1] = currentPoint[1];
    }
  }
      
  
  // Update the estimated position
  static void updateEstimate(byte previousMode, Compass compass) {
    LeftReading = LeftEncoder.getValue() - PreviousLeft;
    RightReading = RightEncoder.getValue() - PreviousRight;
    PreviousLeft = LeftEncoder.getValue();
    PreviousRight = RightEncoder.getValue();
    
    switch(previousMode) {
        case ORIENT_TO_GOAL:
          A =getCompassReadingInDegrees(compass);
          
          break;
        case SPIN_LEFT:
          A = getCompassReadingInDegrees(compass);
          break;
          
        case PIVOT_RIGHT:
        case CURVE_LEFT:
        case CURVE_RIGHT:
          if (RightReading == LeftReading) {
            X = X + (LeftReading * WHEEL_RADIUS) * Math.cos(Math.toRadians(A));
            Y = Y + (LeftReading * WHEEL_RADIUS) * Math.sin(Math.toRadians(A));
          }
          else {
            R = WHEEL_BASE * (LeftReading  / (RightReading - LeftReading)) + WHEEL_BASE/2;
            TD = (RightReading - LeftReading) * WHEEL_RADIUS / WHEEL_BASE / Math.PI * 180;
            X = X + (R*Math.cos(Math.toRadians(TD))*Math.sin(Math.toRadians(A))) +
                    (R*Math.cos(Math.toRadians(A))*Math.sin(Math.toRadians(TD))) - (R*Math.sin(Math.toRadians(A)));
            Y = Y + (R*Math.sin(Math.toRadians(TD))*Math.sin(Math.toRadians(A))) -
                    (R*Math.cos(Math.toRadians(A))*Math.cos(Math.toRadians(TD))) + (R*Math.cos(Math.toRadians(A)));
            A = getCompassReadingInDegrees(compass);
          }
          break;
        case HEAD_TO_GOAL:
          X = X + (LeftReading * WHEEL_RADIUS) * Math.cos(Math.toRadians(A));
          Y = Y + (LeftReading * WHEEL_RADIUS) * Math.sin(Math.toRadians(A));

          
          break;
        case STRAIGHT:
          X = X + (LeftReading * WHEEL_RADIUS) * Math.cos(Math.toRadians(A));
          Y = Y + (LeftReading * WHEEL_RADIUS) * Math.sin(Math.toRadians(A));
          break;
          
    }
  }
  
  
  public static void main(String[] args) {
    Supervisor robot = new Supervisor();
    int timeStep = (int) Math.round(robot.getBasicTimeStep());
    
    // Code required for being able to get the robot's location
    Node robotNode = robot.getSelf();
    Field   translationField = robotNode.getField("translation");

    // Get the motors
    Motor leftMotor = robot.getMotor("left wheel motor");
    Motor rightMotor = robot.getMotor("right wheel motor");
    leftMotor.setPosition(Double.POSITIVE_INFINITY);// indicates continuous rotation servo
    rightMotor.setPosition(Double.POSITIVE_INFINITY);// indicates continuous rotation servo

    // Get and enable the sensors
    DistanceSensor leftAheadSensor = robot.getDistanceSensor("ps7"); 
    DistanceSensor rightAheadSensor = robot.getDistanceSensor("ps0"); 
    DistanceSensor rightAngledSensor = robot.getDistanceSensor("ps1"); 
    DistanceSensor rightSideSensor = robot.getDistanceSensor("ps2"); 
    leftAheadSensor.enable(timeStep);
    rightAheadSensor.enable(timeStep);
    rightAngledSensor.enable(timeStep);
    rightSideSensor.enable(timeStep);

    // Get the encoders
    LeftEncoder = robot.getPositionSensor("left wheel sensor");
    RightEncoder = robot.getPositionSensor("right wheel sensor");
    LeftEncoder.enable(timeStep);
    RightEncoder.enable(timeStep);
    PreviousLeft = 0; PreviousRight = 0;
    
    // Get the Compass sensor
    Compass  compass = robot.getCompass("compass");
    compass.enable(timeStep);

    // Initialize the logic variables for turning
    byte    currentMode = HEAD_TO_GOAL;
    byte    previousMode = STRAIGHT;
    double  leftSpeed=0, rightSpeed=0;
    boolean leftTheLine = false;   // Set to true when we start wall-following and are far enough away from the start-to-goal line
    double tolerance= 5.0;
    double angleTolerance= 5.0;
    
    double sx= -191, sy=0;
    double gx= 170, gy=0;
    
    
    // Set the first estimate to match the current location
    double values[] = translationField.getSFVec3f();
    X = (values[0]*100);
    Y = -(values[2]*100); // Need to negate the Y value
    A = getCompassReadingInDegrees(compass);
    System.out.printf("Robot starts at (x, y) = (%2.1f, %2.1f, %2.0f degrees)\n", (values[0]*100), -(values[2]*100), A);
    boolean PrintState = true;
    while (robot.step(timeStep) != -1) {
      // SENSE: Read the distance sensors
      boolean sideTooClose  = rightSideSensor.getValue() > 300;
      boolean sideTooFar  = rightSideSensor.getValue() < 81;
      boolean frontTooClose  = (rightAheadSensor.getValue() > 81) || (leftAheadSensor.getValue() > 81) || (rightAngledSensor.getValue() > 81);
      boolean lostContact  = rightSideSensor.getValue() < 81;
        
      // THINK: Check for obstacle and decide how we need to turn      
      switch (currentMode) {
        case STRAIGHT:   //if (PrintState) System.out.printf("STRAIGHT\n");
          if (lostContact) { currentMode = PIVOT_RIGHT; break; }
          if (sideTooFar) { currentMode = CURVE_RIGHT; break; }
          if (sideTooClose) { currentMode = CURVE_LEFT; break; }
          if (frontTooClose) { currentMode = SPIN_LEFT; break; }
          if (leftTheLine && Math.abs(Y- 0)< tolerance && (X> sx + tolerance)){
            currentMode= ORIENT_TO_GOAL;
            sx = X; 
            sy=Y;
            
          }
          break;
        case CURVE_RIGHT:   //if (PrintState) System.out.printf("CURVE RIGHT\n");
          if (!sideTooFar) { currentMode = STRAIGHT; break; }
          if (sideTooClose) { currentMode = CURVE_LEFT; break; }
          if (frontTooClose) currentMode = SPIN_LEFT; 
          break;
        case PIVOT_RIGHT:   //if (PrintState) System.out.printf("PIVOT RIGHT\n");
          if (sideTooClose) { currentMode = CURVE_LEFT; break; }
          if (frontTooClose) currentMode = SPIN_LEFT; 
          break;
        case SPIN_LEFT:     //if (PrintState) System.out.printf("SPIN LEFT\n");
          if (!frontTooClose) currentMode = STRAIGHT; 
          break;
        case CURVE_LEFT:    //if (PrintState) System.out.printf("CURVE LEFT\n");
          if (!sideTooClose) currentMode = STRAIGHT; 
          break;
        case HEAD_TO_GOAL:  //if (PrintState) System.out.printf("HEAD_TO_GOAL\n");
        
           if(frontTooClose){
            leftTheLine=false;
            currentMode= SPIN_LEFT;
           
          }
          
          break;
        case ORIENT_TO_GOAL://if (PrintState) System.out.printf("ORIENT TO GOAL\n");
          if (Math.abs(A)< angleTolerance){
            currentMode= HEAD_TO_GOAL;
          }
          
          break;
      }
      updateEstimate(previousMode, compass);
      PrintState = previousMode != currentMode;
      previousMode = currentMode;
       
      if (!leftTheLine && Math.abs(Y) > tolerance){
       leftTheLine= true;
      }
      
      if (Math.abs(X- gx)< tolerance && Math.abs(Y-gy)<tolerance){
          
         leftMotor.setVelocity(0);
         rightMotor.setVelocity(0);
         robot.step(timeStep);
         System.out.printf("Goal reached!");
         break;
            
      }
      
       
      // REACT: Move motors accordingly
      switch(currentMode) {
        case ORIENT_TO_GOAL:
          leftSpeed= -0.5* MAX_SPEED;
          rightSpeed= 0.5* MAX_SPEED;
          
          break;
        case SPIN_LEFT:
          leftSpeed  = -1 * MAX_SPEED;
          rightSpeed = 1 * MAX_SPEED;
          break;
        case PIVOT_RIGHT:
          leftSpeed  = 1 * MAX_SPEED;
          rightSpeed = 0.25 * MAX_SPEED;
          break;
        case CURVE_LEFT:
          leftSpeed  = 0.9 * MAX_SPEED;
          rightSpeed = 1 * MAX_SPEED;
          break;
        case CURVE_RIGHT:
          leftSpeed  = 1 * MAX_SPEED;
          rightSpeed = 0.9 * MAX_SPEED;
          break;
        case HEAD_TO_GOAL:
          leftSpeed= 1* MAX_SPEED;
          rightSpeed= 1* MAX_SPEED; 
       
          
          break;
        case STRAIGHT: 
          leftSpeed  = 1 * MAX_SPEED;
          rightSpeed = 1 * MAX_SPEED;
          break;
      }
      leftMotor.setVelocity(leftSpeed);
      rightMotor.setVelocity(rightSpeed);
    }
  }
}
