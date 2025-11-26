// Author: Yicheng He (SN:101219652)
// Author: Santo Mukiza (SN:101324696)
 
import com.cyberbotics.webots.controller.DistanceSensor;
import com.cyberbotics.webots.controller.Motor;
import com.cyberbotics.webots.controller.Robot;

public class Lab2Controller {

  // Various modes that the robot may be in
  static final byte    STRAIGHT = 0;
  static final byte    SPIN_LEFT = 1;
  static final byte    PIVOT_RIGHT = 2;
  static final byte    CURVE_LEFT = 3;
  static final byte    CURVE_RIGHT = 4;

  static final double  MAX_SPEED = 5;  // maximum speed of the epuck robot
  
  static final double HALF_SPEED = MAX_SPEED * 0.5;
  
  static final double THREE_QUARTER_SPEED = MAX_SPEED * 0.75 ;
  
  static final double HIGH = 300;
  
  static final double LOW = 80;
  
  

  public static void main(String[] args) {
 
    Robot robot = new Robot();
    int timeStep = (int) Math.round(robot.getBasicTimeStep());

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

    // Initialize the logic variable for turning
    byte    currentMode = STRAIGHT;
    double  leftSpeed, rightSpeed;

    
    while (robot.step(timeStep) != -1) {
      // SENSE: Read the distance sensors
      double leftAheadReading = leftAheadSensor.getValue();
      double rightAheadReading = rightAheadSensor.getValue();
      double rightAngledReading = rightAngledSensor.getValue();
      double rightSideReading = rightSideSensor.getValue();
      
      boolean sideTooClose = (rightSideReading > HIGH);
      boolean sideTooFar = (rightSideReading < LOW);
      boolean lostContact = (rightAngledReading < LOW);
      boolean frontTooClose = (leftAheadReading > LOW || rightAheadReading > LOW || rightAngledReading > LOW);
      
          
      
     
      
      // THINK: Check for obstacle and decide how we need to turn 
      // THINK: Check for obstacle and decide how we need to turn      
      switch (currentMode) {
        case STRAIGHT: //System.out.println("STRAIGHT");
        if (frontTooClose)
        {currentMode = SPIN_LEFT;}
        
        else if (sideTooClose)
        {currentMode = CURVE_LEFT;} 
        
        else if (sideTooFar)
        {currentMode = CURVE_RIGHT;}
        
        else if (lostContact)
        {currentMode = PIVOT_RIGHT;} 
        
        else
        {currentMode = STRAIGHT;}
          break;
          
          
          
          
          
        case CURVE_RIGHT: //System.out.println("CURVE RIGHT");
        if (sideTooClose)
        {currentMode = CURVE_LEFT;}
        
        else if (frontTooClose)
        {currentMode = SPIN_LEFT;}
        
        else if (!sideTooFar)
        {currentMode = STRAIGHT;}
          break;
          
          
          
          
        case PIVOT_RIGHT: //System.out.println("PIVOT RIGHT");

        
        if (sideTooClose)
        {currentMode = CURVE_LEFT;}
        
        else if (frontTooClose)
        {currentMode = SPIN_LEFT;}
          break;
          
          
          
          
        case SPIN_LEFT: //System.out.println("SPIN LEFT");
        if (!frontTooClose)
        {currentMode = STRAIGHT;}
          break;
          
          
          
        case CURVE_LEFT: //System.out.println("CURVE LEFT");
        if (!sideTooClose)
        {currentMode = STRAIGHT;}
          break;
       }
       
       
       
       
       
      // REACT: Move motors accordingly
      switch(currentMode) {
        case SPIN_LEFT:
          leftMotor.setVelocity(-HALF_SPEED);
          rightMotor.setVelocity(HALF_SPEED);
          break;
        case PIVOT_RIGHT:
          leftMotor.setVelocity(MAX_SPEED);
          rightMotor.setVelocity(0);  
          break;
        case CURVE_LEFT:
          leftMotor.setVelocity(THREE_QUARTER_SPEED);
          rightMotor.setVelocity(MAX_SPEED);
          break;
        case CURVE_RIGHT:
          leftMotor.setVelocity(MAX_SPEED);
          rightMotor.setVelocity(THREE_QUARTER_SPEED);
          break;
        default: //This handles the STRAIGHT case
          leftMotor.setVelocity(MAX_SPEED);
          rightMotor.setVelocity(MAX_SPEED);
          break;
      }            
    }
  }
}