// Santo Mukiza : 101324696

//Joshua Wang:101307312


import com.cyberbotics.webots.controller.Camera;
import com.cyberbotics.webots.controller.Accelerometer;
import com.cyberbotics.webots.controller.DistanceSensor;
import com.cyberbotics.webots.controller.Motor;
import com.cyberbotics.webots.controller.Robot;

public class Lab3Controller {

  static final byte CAMERA_WIDTH  = 52;
  static final byte CAMERA_HEIGHT = 39;
  
  static final byte WANDER      = 0;
  static final byte HOME_IN     = 1;
  static final byte PUSH_BALL   = 2;
  static final byte TURN_AROUND = 3;
  static final byte GO_FORWARD  = 4;
  static final byte AVOID       = 5;
  
  static final byte LEFT        = 0;
  static final byte RIGHT       = 1;
  static final byte NONE        = 2;
  static final byte FULL_SPEED  = 6; // reasonable wheel velocity for this assignment
  
  public static void main(String[] args) {
    Robot robot = new Robot();
    int timeStep = (int) Math.round(robot.getBasicTimeStep());

    // Get the motors
    Motor leftMotor = robot.getMotor("left wheel motor");
    Motor rightMotor = robot.getMotor("right wheel motor");
    leftMotor.setPosition(Double.POSITIVE_INFINITY);
    rightMotor.setPosition(Double.POSITIVE_INFINITY);

    // Get and enable the distance sensors
    DistanceSensor leftAheadSensor = robot.getDistanceSensor("ps7"); 
    DistanceSensor rightAheadSensor = robot.getDistanceSensor("ps0"); 
    leftAheadSensor.enable(timeStep);
    rightAheadSensor.enable(timeStep);

    // Get and enable the accelerometer
    Accelerometer accelerometer = new Accelerometer("accelerometer");
    accelerometer.enable(timeStep);
    
    // Set up the camera
    Camera camera = new Camera("camera");
    camera.enable(timeStep);
    
    // --- Accelerometer circular buffer ---
    byte   accelIndex = 0; 
    double accelValues[][] = new double[10][3]; 
    double accelTemp[] = new double[3];

    int leftSpeed, rightSpeed;
    leftSpeed = rightSpeed = 0;
    
    byte currentMode = WANDER;
    byte turnCount = 0;
    byte turnDirection = NONE;

    // Booleans for camera detection
    boolean detectedLeft = false, detectedRight = false, detectedStraight = false, notDetected = true;

    while (robot.step(timeStep) != -1) {
      // SENSE: Read the sensors

      // read accelerometer and store into circular buffer
      accelValues[accelIndex] = accelerometer.getValues();
      accelIndex = (byte)((accelIndex + 1) % 10);

      // compute running average into accelTemp
      for (int j=0; j<3; j++)
        accelTemp[j] = 0;
      for (int i=0; i<10; i++)
        for (int j=0; j<3; j++)
          accelTemp[j] += accelValues[i][j];
      for (int j=0; j<3; j++)
        accelTemp[j] = accelTemp[j]/10.0;

      // Determine tipping using thresholds (these can be tuned)
      // accelTemp indices: [0]=x (forward/back), [1]=y (left/right), [2]=z (vertical)
      boolean tippedForward  = accelTemp[0] < -1.0; // leaning forward onto ramp
      boolean tippedBackward = accelTemp[0] > 1.0;  // leaning backward
      boolean tippedSideways = Math.abs(accelTemp[1]) > 2.0; // tipped left or right
      boolean flat = !(tippedForward || tippedBackward || tippedSideways);

      // CAMERA: grab center row and examine red pixels
      int[] image = camera.getImage();
      int midRow = CAMERA_HEIGHT/2;
      int leftCount = 0, centerCount = 0, rightCount = 0;
      int epsilon = 3; // small noise tolerance
      for (int x = 0; x < CAMERA_WIDTH; x++) {
        int r = Camera.imageGetRed(image, CAMERA_WIDTH, x, midRow);
        int g = Camera.imageGetGreen(image, CAMERA_WIDTH, x, midRow);
        int b = Camera.imageGetBlue(image, CAMERA_WIDTH, x, midRow);
        // simple "isRed" test (tunable)
        if ((r > 60) && (g < 100) && (b < 100)) {
          if (x < CAMERA_WIDTH/3)
            leftCount++;
          else if (x < 2*CAMERA_WIDTH/3)
            centerCount++;
          else
            rightCount++;
        }
      }
      detectedLeft = (leftCount > (rightCount + epsilon));
      detectedRight = (rightCount > (leftCount + epsilon));
      detectedStraight = (centerCount > epsilon);
      notDetected = !(detectedLeft || detectedRight || detectedStraight);

      // DEBUG prints (uncomment while testing)
      // System.out.printf("accelAvg=(%4.2f,%4.2f,%4.2f) tippedF=%b tippedB=%b tippedS=%b flat=%b\n", accelTemp[0], accelTemp[1], accelTemp[2], tippedForward, tippedBackward, tippedSideways, flat);
      // System.out.printf("cam L=%d C=%d R=%d => L=%b C=%b R=%b\n", leftCount, centerCount, rightCount, detectedLeft, detectedStraight, detectedRight);

      // THINK: Make a decision as to what MODE to be in
      switch(currentMode) {
        case WANDER:
          // If a ball is detected, begin homing-in
          if (!notDetected) {
            currentMode = HOME_IN;
            turnCount = 0;
            turnDirection = NONE;
            break;
          }
          // If tipping forward onto ramp -> need to TURN_AROUND
          if (tippedForward) {
            currentMode = TURN_AROUND;
            turnDirection = (byte)(Math.random() * 2); // random left or right
            break;
          }
          // If close to a pillar (front proximity sensors) -> AVOID
          double leftProx = leftAheadSensor.getValue();
          double rightProx = rightAheadSensor.getValue();
          // only avoid if no ball detected
          if (notDetected && (leftProx > 800 || rightProx > 800)) {
            currentMode = AVOID;
            turnDirection = (byte)(Math.random() * 2);
            break;
          }
          break;

        case HOME_IN:
          // If proximity indicates contact with something, assume it's a ball -> PUSH_BALL
          if (leftAheadSensor.getValue() > 800 || rightAheadSensor.getValue() > 800) {
            currentMode = PUSH_BALL;
            break;
          }
          // If we lose the ball, wander again
          if (notDetected) {
            currentMode = WANDER;
            break;
          }
          // If tipping forward while homing, avoid falling
          if (tippedForward) {
            currentMode = TURN_AROUND;
            turnDirection = (byte)(Math.random() * 2);
            break;
          }
          break;

        case PUSH_BALL:
          // If we lose the ball while pushing, resume wandering
          if (notDetected) {
            currentMode = WANDER;
            break;
          }
          // If tipping forward while pushing, turn around immediately
          if (tippedForward) {
            currentMode = TURN_AROUND;
            turnDirection = (byte)(Math.random() * 2);
            break;
          }
          break;

        case TURN_AROUND:
          // Keep turning until robot is flat again (i.e., no longer tipping forward/back)
          if (flat) {
            currentMode = GO_FORWARD; // once turned, drive forward up the ramp
            break;
          }
          break;

        case GO_FORWARD:
          // Drive forward until off the ramp (flat) then resume wandering
          if (flat) {
            currentMode = WANDER;
            break;
          }
          break;

        case AVOID:
          // Once proximity is clear, resume wandering
          if (leftAheadSensor.getValue() < 600 && rightAheadSensor.getValue() < 600) {
            currentMode = WANDER;
            break;
          }
          // also if a ball appears while avoiding, go home-in
          if (!notDetected) {
            currentMode = HOME_IN;
            break;
          }
          break;
      }
      
      // REACT: Move motors according to the MODE
      leftSpeed = FULL_SPEED;
      rightSpeed = FULL_SPEED;
      switch(currentMode) {
        case WANDER: 
          if (turnCount > 0) {
            if (turnDirection == LEFT)
              leftSpeed -= 2; // curve left
            else
              rightSpeed -= 2; // curve right
            turnCount--;
            if (turnCount == 0) 
              turnDirection = NONE;
          }
          else {
            if ((byte)(Math.random()*5) == 0) {// turn 20% of the time
              turnDirection = (byte)(Math.random()*2);
              turnCount = (byte)(Math.random()*51+25);
            }
          }
          break;

        case HOME_IN:
          // Aim toward the ball using the camera booleans
          turnCount = 0;
          if (detectedLeft) {
            // ball is left; turn left slightly
            leftSpeed = FULL_SPEED - 3;
            rightSpeed = FULL_SPEED;
          }
          else if (detectedRight) {
            // ball is right; turn right slightly
            leftSpeed = FULL_SPEED;
            rightSpeed = FULL_SPEED - 3;
          }
          else if (detectedStraight) {
            // ball centered; go straight
            leftSpeed = FULL_SPEED;
            rightSpeed = FULL_SPEED;
          }
          else {
            // no detection - be conservative
            leftSpeed = FULL_SPEED - 1;
            rightSpeed = FULL_SPEED;
          }
          break;

        case PUSH_BALL: 
          // go straight to push the ball
          turnCount = 0;
          leftSpeed = FULL_SPEED;
          rightSpeed = FULL_SPEED;
          break;

        case TURN_AROUND:
          turnCount = 0;
          // spin 180-ish in chosen direction until accelerometer says flat
          if (turnDirection == LEFT) {
            leftSpeed = -FULL_SPEED;
            rightSpeed = FULL_SPEED;
          } else if (turnDirection == RIGHT) {
            leftSpeed = FULL_SPEED;
            rightSpeed = -FULL_SPEED;
          } else {
            // fallback
            leftSpeed = FULL_SPEED;
            rightSpeed = -FULL_SPEED;
          }
          break;

        case GO_FORWARD: 
          // move forward until off the ramp
          turnCount = 0;
          leftSpeed = FULL_SPEED;
          rightSpeed = FULL_SPEED;
          break;

        case AVOID: 
          turnCount = 0;
          // turn away from obstacle
          if (turnDirection == LEFT) {
            leftSpeed = FULL_SPEED - 3;
            rightSpeed = FULL_SPEED;
          } else {
            leftSpeed = FULL_SPEED;
            rightSpeed = FULL_SPEED - 3;
          }
          break;
      }

      leftMotor.setVelocity(leftSpeed);
      rightMotor.setVelocity(rightSpeed);
    }
  }
}

