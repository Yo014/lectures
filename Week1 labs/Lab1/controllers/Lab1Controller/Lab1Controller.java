//Aslam Samy
//101361409
//Santo Mukiza
//101324696
import com.cyberbotics.webots.controller.DistanceSensor;
import com.cyberbotics.webots.controller.Motor;
import com.cyberbotics.webots.controller.Robot;

public class Lab1Controller {
    static final byte STRAIGHT = 0;
    static final byte SPIN_LEFT = 1;
    static final byte SPIN_RIGHT = 2;
    static final byte CURVE_LEFT = 3;
    static final byte CURVE_RIGHT = 4;
    static final double MAX_SPEED = 6.28;

    public static void main(String[] args) {
        byte currentState = STRAIGHT;
        boolean randomTurnLeft = false;
        Robot robot = new Robot();
        int timeStep = (int)Math.round(robot.getBasicTimeStep());

        Motor leftMotor = robot.getMotor("left wheel motor");
        Motor rightMotor = robot.getMotor("right wheel motor");
        leftMotor.setPosition(Double.POSITIVE_INFINITY);
        rightMotor.setPosition(Double.POSITIVE_INFINITY);

        DistanceSensor ps7 = robot.getDistanceSensor("ps7");
        DistanceSensor ps6 = robot.getDistanceSensor("ps6");
        DistanceSensor ps0 = robot.getDistanceSensor("ps0");
        DistanceSensor ps1 = robot.getDistanceSensor("ps1");
        DistanceSensor ps5 = robot.getDistanceSensor("ps5");
        DistanceSensor ps2 = robot.getDistanceSensor("ps2");

        ps7.enable(timeStep);
        ps6.enable(timeStep);
        ps0.enable(timeStep);
        ps1.enable(timeStep);
        ps5.enable(timeStep);
        ps2.enable(timeStep);

        double SENSOR_THRESHOLD = 90.0;

        leftMotor.setVelocity(MAX_SPEED);
        rightMotor.setVelocity(MAX_SPEED);

        while(robot.step(timeStep) != -1) {
            double ps7Value = ps7.getValue();
            double ps6Value = ps6.getValue();
            double ps0Value = ps0.getValue();
            double ps1Value = ps1.getValue();
            double ps5Value = ps5.getValue();
            double ps2Value = ps2.getValue();

            boolean lfDetect = ps7Value > SENSOR_THRESHOLD;
            boolean rfDetect = ps0Value > SENSOR_THRESHOLD;
            boolean lsDetect = ps5Value > SENSOR_THRESHOLD;
            boolean rsDetect = ps1Value > SENSOR_THRESHOLD;
            boolean laDetect = ps6Value > SENSOR_THRESHOLD;
            boolean raDetect = ps2Value > SENSOR_THRESHOLD;

            byte nextState = currentState;

            switch(currentState) {
                case STRAIGHT:
                    if (lfDetect || rfDetect) {
                        randomTurnLeft = (Math.random() < 0.5);
                        nextState = randomTurnLeft ? SPIN_LEFT : SPIN_RIGHT;
                    } else if (raDetect) {
                        nextState = SPIN_LEFT;
                    } else if (laDetect) {
                        nextState = SPIN_RIGHT;
                    } else if (rsDetect && !lsDetect) {
                        nextState = CURVE_LEFT;
                    } else if (lsDetect && !rsDetect) {
                        nextState = CURVE_RIGHT;
                    }
                    break;

                case SPIN_LEFT:
                    if (!lfDetect && !rfDetect && !laDetect && !raDetect) {
                        nextState = STRAIGHT;
                    }
                    break;

                case SPIN_RIGHT:
                    if (!lfDetect && !rfDetect && !laDetect && !raDetect) {
                        nextState = STRAIGHT;
                    }
                    break;

                case CURVE_LEFT:
                    if (!rsDetect || lfDetect || rfDetect || laDetect || raDetect) {
                        nextState = STRAIGHT;
                    }
                    break;

                case CURVE_RIGHT:
                    if (!lsDetect || lfDetect || rfDetect || laDetect || raDetect) {
                        nextState = STRAIGHT;
                    }
                    break;
            }

            currentState = nextState;

            switch(currentState) {
                case SPIN_LEFT:
                    leftMotor.setVelocity(-0.5 * MAX_SPEED);
                    rightMotor.setVelocity(0.5 * MAX_SPEED);
                    break;

                case SPIN_RIGHT:
                    leftMotor.setVelocity(0.5 * MAX_SPEED);
                    rightMotor.setVelocity(-0.5 * MAX_SPEED);
                    break;

                case CURVE_LEFT:
                    leftMotor.setVelocity(0.75 * MAX_SPEED);
                    rightMotor.setVelocity(MAX_SPEED);
                    break;

                case CURVE_RIGHT:
                    leftMotor.setVelocity(MAX_SPEED);
                    rightMotor.setVelocity(0.75 * MAX_SPEED);
                    break;

                default:
                    leftMotor.setVelocity(MAX_SPEED);
                    rightMotor.setVelocity(MAX_SPEED);
                    break;
            }
        }
    }
}