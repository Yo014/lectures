// Room: 2
// Author: Denis Koeterev (SN: 101348890)
// Collab: Santo Mukiza   (SN: 101324696)
import com.cyberbotics.webots.controller.Motor;
import com.cyberbotics.webots.controller.Robot;
import com.cyberbotics.webots.controller.PositionSensor;

public class Lab5Controller {

  static final double  MAX_SPEED      = 0.25;  //6.28 is maximum but value of 1 is required for simulator to work properly for kinematics
  static final double  WHEEL_RADIUS   = 2.05; // cm 
  static final double  WHEEL_BASE     = 5.80; // cm
  
  static Robot           Epuck;
  static Motor           LeftMotor;
  static Motor           RightMotor;
  static PositionSensor  rightEncoder;
  static double          PreviousReading = 0;
  static int             TimeStep;


  // The locations to visit in sequence, and starting angle and position
  static int           x[] = {0, 30, 30, -10, 10, -10, -60, -50, -40, -30, -20, 0};
  static int           y[] = {0, 30, 60, 70, 50, 50, 40, 30, 30, 20, 20, 0};
  static double startAngle = 90;


  // Set each motor to a specific speed and wait until the left sensor reaches 
  // the specified number of radians.
  private static void move(double leftSpeed, double rightSpeed, double thisManyRadians) {
    LeftMotor.setVelocity(leftSpeed);
    RightMotor.setVelocity(rightSpeed);
    while(true) {
      double reading = rightEncoder.getValue() - PreviousReading;
      if ((thisManyRadians > 0) && (reading >= thisManyRadians)) 
        break;
      if ((thisManyRadians < 0) && (reading <= thisManyRadians)) 
        break;
      Epuck.step(TimeStep);
    }
    LeftMotor.setVelocity(0);
    RightMotor.setVelocity(0);
    Epuck.step(TimeStep);
    PreviousReading = rightEncoder.getValue();
    Epuck.step(TimeStep);
  }
  
	
  public static void main(String[] args) {
    Epuck = new Robot();
    TimeStep = (int) Math.round(Epuck.getBasicTimeStep());

    // Get the motors
    LeftMotor = Epuck.getMotor("left wheel motor");
    RightMotor = Epuck.getMotor("right wheel motor");
    LeftMotor.setPosition(Double.POSITIVE_INFINITY);// indicates continuous rotation servo
    RightMotor.setPosition(Double.POSITIVE_INFINITY);// indicates continuous rotation serv
    LeftMotor.setVelocity(0);
    RightMotor.setVelocity(0);
    
    // Get the encoders
    rightEncoder = Epuck.getPositionSensor("right wheel sensor");
    rightEncoder.enable(TimeStep);
       
    // Travel through the points in the array one at a time in sequence
    // WRITE YOUR CODE HERE
    //
    double a_deg = startAngle;

    
    for (int i = 0; i<x.length-1; i+=1) {
      double dx = x[i+1]-x[i];
      double dy = y[i+1]-y[i];
      
      double theta = get_delta_theta(dy, dx, a_deg);
      a_deg += theta;
 
      if (theta < 0.0) {
          move(MAX_SPEED, -MAX_SPEED, Math.toRadians(theta)*(WHEEL_BASE/WHEEL_RADIUS/2));
      } else {
          move(-MAX_SPEED, MAX_SPEED, Math.toRadians(theta)*(WHEEL_BASE/WHEEL_RADIUS/2));
      }
      
      double distance = Math.sqrt((Math.pow((dx), 2)+Math.pow((dy), 2)))/WHEEL_RADIUS;
      move(MAX_SPEED, MAX_SPEED, distance);
    }

  }
  
  static double get_delta_theta(double dy, double dx, double angle) {
      double theta = (Math.atan2(dy, dx)*(180.0/Math.PI));
      theta = (theta-angle) % 360.0;
      if (theta < -180.0) {
        theta += 360.0;
      } else if (theta > 180.0) {
        theta -= 360.0;
      }
      return theta;
  }
}
