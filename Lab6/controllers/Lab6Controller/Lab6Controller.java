// Author: Santo Mukiza (SN:101324696)
// Author : Fahim Kazi (SN: 101263543)
//room: 32

import com.cyberbotics.webots.controller.Camera;
import com.cyberbotics.webots.controller.PositionSensor;
import com.cyberbotics.webots.controller.Motor;
import com.cyberbotics.webots.controller.Robot;

public class Lab6Controller {

  //*******************
  // Camera dimensions
  //*******************
  static final byte CAMERA_WIDTH = 52;
  static final byte CAMERA_HEIGHT = 39;
  
  //****************************
  // The FIXED beacon locations
  //****************************
  static final int  x1 = -60, y1 =  30;  // RED Beacon location
  static final int  x2 =   0, y2 = -70;  // GREEN Beacon location
  static final int  x3 =  60, y3 =   0;  // BLUE Beacon location
  
  //*****************************************************************************************************
  // The locations to visit in sequence.  These numbers do not match the numbers in the LAB instructions.
  // They have been tweaked slightly due to the innacurate movements of the robot.  DO NOT change any
  // of them because they have been adjusted so that your estimations will be as accurate as possible. 
  //*****************************************************************************************************
  static final int     x[] = {0, -34, 10, 39, 70, 72, 94, 83,  87,  56,  16,   3, -29, -49, -71, -74};
  static final int     y[] = {0,  40, 41, 62, 67, 15, 17,  0, -45, -38, -62, -32, -26, -27,  -9, -72};
    
  static final double  MAX_SPEED      = 1;    // Need to go this slow for accurate position estimation
  static final double  WHEEL_RADIUS   = 2.05; // cm 
  static final double  WHEEL_BASE     = 5.80; // cm
  
  
  
  
  
  //*******************************************
  // Variables needed by the various functions
  //*******************************************
  static Robot            Epuck;
  static Motor            LeftMotor, RightMotor;
  static PositionSensor   LeftEncoder, RightEncoder;
  static Camera           Cam;
  static int              TimeStep;
  static double           PreviousReading = 0;
  static double startAngle = 90;  // Angle that that robot starts at (i.e., 90 degrees)

  
  


  
  //*************************************************************************************
  // Set each motor to a specific speed and wait until the left sensor reaches 
  // the specified number of radians.  DO NOT CHANGE IT.
  //*************************************************************************************
  private static void move(double leftSpeed, double rightSpeed, double thisManyRadians) {
    LeftMotor.setVelocity(leftSpeed);
    RightMotor.setVelocity(rightSpeed);
    while(true) {
      double reading = RightEncoder.getValue() - PreviousReading;
      if ((thisManyRadians > 0) && (reading > thisManyRadians))
        break;
      if ((thisManyRadians < 0) && (reading < thisManyRadians)) 
        break;
      Epuck.step(TimeStep);
    }
    LeftMotor.setVelocity(0);
    RightMotor.setVelocity(0);
    Epuck.step(TimeStep);
    PreviousReading = RightEncoder.getValue();
    Epuck.step(TimeStep);
  }
  
  
  
  
  //*************************************************************************************
  // This method moves forward from point (x1,y1) to point (x2, y2).  DO NOT CHANGE IT.
  //*************************************************************************************
  private static void moveAhead(double x1, double y1, double x2, double y2) {
    double xDiff = x2 - x1;
    double yDiff = y2 - y1;
    double radiansToTurn = (Math.sqrt(xDiff*xDiff + yDiff*yDiff) / WHEEL_RADIUS);

    move(MAX_SPEED, MAX_SPEED, radiansToTurn);
  }
	
  //*************************************************************************************
  // This method spins from point (x1,y1) to point (x2, y2).  DO NOT CHANGE IT.
  //*************************************************************************************
  private static void makeTurn(double x1, double y1, double x2, double y2) {
    double xDiff = x2 - x1;
    double yDiff = y2 - y1;
    double turn = Math.toDegrees(Math.atan2(yDiff, xDiff));
    turn = (turn - startAngle) % 360;
    if (turn < -180)
      turn += 360;
    else if (turn > 180)
      turn -= 360;

    // Determine how many radians to spin for
    double radiansToTurn = Math.toRadians(turn) * (1.0/(WHEEL_RADIUS/WHEEL_BASE*2.0));
    if (turn > 0) 
      move(-MAX_SPEED, MAX_SPEED, radiansToTurn);
    else
      move(MAX_SPEED, -MAX_SPEED, radiansToTurn);
    startAngle += turn;
  }


  //*************************************************************************************
  // This method spins the robot clockwise 360 degrees looking for the three beacons.   
  // It then calculates its position based on triangulation.  The angle
  // passed in is the angle that the robot started at before spinning. 
  // The point number can be used for printing to indicate which point is
  // being calculated at this time. 
  //*************************************************************************************
  private static void calculatePosition(double angle, byte pointNumber) {
    // SPIN for 360 degrees DO NOT CHANGE THIS CODE
    double spinRadians = (Math.PI*WHEEL_BASE) / WHEEL_RADIUS;
    LeftMotor.setVelocity(-MAX_SPEED);
    RightMotor.setVelocity(MAX_SPEED);

    //*************************************************************************************
    // ADD ANY INTIALIZATION CODE YOU WANT HERE
    //*************************************************************************************
     double[] beaconAngles = {-999, -999, -999}; 
    boolean[] found = {false, false, false};      // track if each beacon has been recorded
    
    // Do NOT alter the looping code!!! ... just insert inside it at the location shown
    double   reading = 0;
    while ((Epuck.step(TimeStep) != -1) && (reading < spinRadians)) {
      reading = RightEncoder.getValue() - PreviousReading;
      

      
      
    //*************************************************************************************
    // INSERT YOUR BEACON-FINDING CODE HERE
     int[] image = Cam.getImage();
     int centerRow = CAMERA_HEIGHT / 2;

      // --- RED ---
      int firstR=-1, lastR=-1;
      boolean centerR=false;
      for (int i=0;i<CAMERA_WIDTH;i++){
        int r=Camera.imageGetRed(image,CAMERA_WIDTH,i,centerRow);
        int g=Camera.imageGetGreen(image,CAMERA_WIDTH,i,centerRow);
        int b=Camera.imageGetBlue(image,CAMERA_WIDTH,i,centerRow);
        if(r>60 && g<50 && b<50){
          if(firstR==-1) firstR=i;
          lastR=i;
          if(i==CAMERA_WIDTH/2) centerR=true;
        }
      }
      if(centerR && !found[0]){
        int leftGap=(CAMERA_WIDTH/2)-firstR;
        int rightGap=lastR-(CAMERA_WIDTH/2);
        if(Math.abs(leftGap-rightGap)<=2){
          double ang = angle + (reading/spinRadians*360.0);
          beaconAngles[0]=ang;
          found[0]=true;
        }
      }

      // --- GREEN ---
      int firstG=-1,lastG=-1; boolean centerG=false;
      for (int i=0;i<CAMERA_WIDTH;i++){
        int r=Camera.imageGetRed(image,CAMERA_WIDTH,i,centerRow);
        int g=Camera.imageGetGreen(image,CAMERA_WIDTH,i,centerRow);
        int b=Camera.imageGetBlue(image,CAMERA_WIDTH,i,centerRow);
        if(r<50 && g>60 && b<50){
          if(firstG==-1) firstG=i;
          lastG=i;
          if(i==CAMERA_WIDTH/2) centerG=true;
        }
      }
      if(centerG && !found[1]){
        int leftGap=(CAMERA_WIDTH/2)-firstG;
        int rightGap=lastG-(CAMERA_WIDTH/2);
        if(Math.abs(leftGap-rightGap)<=2){
          double ang = angle + (reading/spinRadians*360.0);
          beaconAngles[1]=ang;
          found[1]=true;
        }
      }

      // --- BLUE ---
      int firstB=-1,lastB=-1; boolean centerB=false;
      for (int i=0;i<CAMERA_WIDTH;i++){
        int r=Camera.imageGetRed(image,CAMERA_WIDTH,i,centerRow);
        int g=Camera.imageGetGreen(image,CAMERA_WIDTH,i,centerRow);
        int b=Camera.imageGetBlue(image,CAMERA_WIDTH,i,centerRow);
        if(r<50 && g<50 && b>60){
          if(firstB==-1) firstB=i;
          lastB=i;
          if(i==CAMERA_WIDTH/2) centerB=true;
        }
      }
      if(centerB && !found[2]){
        int leftGap=(CAMERA_WIDTH/2)-firstB;
        int rightGap=lastB-(CAMERA_WIDTH/2);
        if(Math.abs(leftGap-rightGap)<=2){
          double ang = angle + (reading/spinRadians*360.0);
          beaconAngles[2]=ang;
          found[2]=true;
        }
      }

    //*************************************************************************************
      
    // DO NOT CHANGE THE NEXT SIX LINES OF CODE!!
    }
    LeftMotor.setVelocity(0);
    RightMotor.setVelocity(0);
    Epuck.step(TimeStep);
    PreviousReading = RightEncoder.getValue();
    Epuck.step(TimeStep);
    
    
    
    //*************************************************************************************
    // WRITE CODE HERE TO CALCULATE AND DISPLAY THE ROBOT LOCATION
    if(beaconAngles[0]==-999 || beaconAngles[1]==-999 || beaconAngles[2]==-999){
      System.out.println("(x"+pointNumber+", y"+pointNumber+") = CANNOT COMPUTE LOCATION");
      return;
    }
    //*************************************************************************************    
     // ToTal algorithm implementation
    double xr=x1, yr=y1; // red
    double xg=x2, yg=y2; // green
    double xb=x3, yb=y3; // blue

    // Step 1
    double x1p=xr-xg, y1p=yr-yg;
    double x3p=xb-xg, y3p=yb-yg;

    // Step 2
    double T12=1.0/Math.tan(Math.toRadians(beaconAngles[1]-beaconAngles[0]));
    double T23=1.0/Math.tan(Math.toRadians(beaconAngles[2]-beaconAngles[1]));
    double T31=(1.0-T12*T23)/(T12+T23);

    // Step 3
    double x12p=x1p+T12*y1p, y12p=y1p-T12*x1p;
    double x23p=x3p-T23*y3p, y23p=y3p+T23*x3p;
    double x31p=(x3p+x1p)+T31*(y3p-y1p);
    double y31p=(y3p+y1p)-T31*(x3p-x1p);

    // Step 4
    double k31p=x1p*x3p+y1p*y3p+T31*(x1p*y3p-x3p*y1p);

    // Step 5
    double d=(x12p-x23p)*(y23p-y31p)-(y12p-y23p)*(x23p-x31p);

    if(Math.abs(d)<100){
      System.out.println("(x"+pointNumber+", y"+pointNumber+") = INACCURATE");
      return;
    }

    // Step 6
    double X=xg+k31p*(y12p-y23p)/d;
    double Y=yg+k31p*(x23p-x12p)/d;

    System.out.println("(x"+pointNumber+", y"+pointNumber+") = ("+Math.round(X)+", "+Math.round(Y)+")");
    //*************************************************************************************    
  }


  //*******************************************************************************************
  // DO NOT CHANGE THE MAIN FUNCTION ... EXCEPT FOR COMMENTING THINGS OUT WHILE YOU ARE TESTING
  //*******************************************************************************************
  public static void main(String[] args) {
    Epuck = new Robot();
    TimeStep = (int) Math.round(Epuck.getBasicTimeStep());

    // Get the motors
    LeftMotor = Epuck.getMotor("left wheel motor");
    RightMotor = Epuck.getMotor("right wheel motor");
    LeftMotor.setPosition(Double.POSITIVE_INFINITY);// indicates continuous rotation servo
    RightMotor.setPosition(Double.POSITIVE_INFINITY);// indicates continuous rotation servo
    LeftMotor.setVelocity(0);
    RightMotor.setVelocity(0);
    
    // Get the encoders
    LeftEncoder = Epuck.getPositionSensor("left wheel sensor");
    RightEncoder = Epuck.getPositionSensor("right wheel sensor");
    LeftEncoder.enable(TimeStep);
    RightEncoder.enable(TimeStep);
    
    // Set up the camera
    Cam = new Camera("camera");
    Cam.enable(TimeStep);
    
    // Travel through the points in the array one at a time in sequence and determine the location at each point by using triangulation
    for (byte i=0; i<x.length - 1; i++) {
      calculatePosition(startAngle, i);
      makeTurn(x[i], y[i], x[i+1], y[i+1]);
      moveAhead(x[i], y[i], x[i+1], y[i+1]);
    }
    calculatePosition(startAngle, (byte)(x.length - 1));  // Get the last one  
  }
}
