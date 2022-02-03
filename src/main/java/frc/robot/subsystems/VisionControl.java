package frc.robot.subsystems;

import frc.robot.components.*;
import frc.robot.*;

public class VisionControl {
    private OperatorInterface oi;
    private Vision vision;
    private VisionData visionData;
    private double balla = 0;
    // thresholds
    private double ballChassisThreshold = 2;
    private double hoopChassisThreshold = 10;
    private double shooterThreshold = 10;

    // shooter variables
    private double desiredAngle;// angle of the shooter in degrees
    private double desiredPower;// desired shooter power in RPMS
    // chassis variables
    private double hoop_forward_speed = 0; // speed front to back between 0-1
    private double hoop_sidespeed = 0; // straife speed between 0-1
    private double hoop_rotation = 0; // rotation speed between 0-1
    private double ball_forward_speed = 0; // speed front to back between 0-1
    private double ball_sidespeed = 0; // straife speed between 0-1
    private double ball_rotation = 0; // rotation speed between 0-1
    private Chassis chassis;
    // private Shooter shooter;

    public VisionControl(Vision vision, VisionData visionData, OperatorInterface oi, Chassis chassis) {// , Shooter shooter}) {
        this.oi = oi;
        this.vision = vision;
        this.visionData = visionData;
        this.chassis = chassis;
        // this.shooter = shooter;
    }

    public void auto() { // pathfind to cargo, collect it, and score it
        update();
        if(visionData.isBallValid() && visionData.isHoopValid()){
            // auto code will go here
        }
        else{
            System.out.println("Invalid data... aborting");
        }
    }

    public void main() {
        while (true) {
            update();

            visionData.Print();
            if (oi.autoCollectButton()) { // go collect the nearest cargo
                if(visionData.isBallValid()){
                    System.out.println("in auto");
                    // shooter.gather();
                    calculateBallChassis();
                    printData();
                    chassis.drive(oi.pilot.getLeftX(), ball_rotation);
                }
                else{
                    System.out.println("Invalid data... aborting");
                }
            } 
            else {
                break;
            }
        }
    }

    private void update() {
        visionData = vision.getData();
        balla = visionData.bx;

    }

    private void calculateShooter() {
        // desiredAngle = __calculated_angle__;
        // desiredPower = __calculated_RPMS__;

    }

    private void calculateHoopChassis() {
        // hoop_forward_speed = __calculated_forward_speed__;
        // hoop_sidespeed = __calculated_side_speed__;
        // hoop_rotation = __calculated_rotation__;
    }
    private void calculateBallChassis() {
        // ball_forward_speed = __calculated_forward_speed__;
        // ball_sidespeed = __calculated_side_speed__;
        System.out.println(balla);
        ball_rotation = 0.5 * (balla / 34);
        if(Math.abs(ball_rotation) <= ballChassisThreshold){
            ball_rotation = 0;
        }
    }
    private void printData(){
        System.out.println("rotation value is" + ball_rotation);
    }
}
