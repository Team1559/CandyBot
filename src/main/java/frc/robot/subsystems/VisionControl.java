package frc.robot.subsystems;

import frc.robot.components.*;
import frc.robot.*;
import edu.wpi.first.math.controller.PIDController;

public class VisionControl {
    private PIDController pid;
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
    private final double kpasta = 0.03;
    private final double ki = 0;
    private final double kd = 0.001;
    
    // private Shooter shooter;

    public VisionControl(Vision vision, VisionData visionData, OperatorInterface oi, Chassis chassis) {// , Shooter shooter}) {
        this.oi = oi;
        pid = new PIDController(kpasta, ki, kd);
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
        if (true) {
            update();

            // visionData.Print();
            // System.out.println(balla);
            if (oi.autoCollectButton()) { // go collect the nearest cargo
                if(visionData.isBallValid()){
                    System.out.println("in auto");
                    // shooter.gather();
                    calculateBallChassis();
                    printData();
                    chassis.drive(oi.pilot.getLeftY(), ball_rotation);
                }
                else{
                    System.out.println("Invalid data... remaining in manual control");
                    chassis.drive(oi.pilot.getLeftY(), oi.pilot.getRightX());

                }
            } 
            else {
                chassis.main();
            }
        }
    }

    private void update() {
        visionData = vision.getData();
        balla = visionData.br;

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
        ball_rotation = 0.75 * (balla / 34.0);
    //    ball_rotation = -pid.calculate(balla, 0);
        if(Math.abs(balla) <= ballChassisThreshold){
            ball_rotation = 0;
        }
    }
    private void printData(){
        System.out.println("rotation value is " + ball_rotation);
    }
}
