package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.OperatorInterface;


public class Chassis{
    public DifferentialDrive dd;
    //TalonSRX lm;
        public void innit(){
            WPI_TalonSRX leftMotor = new WPI_TalonSRX(1);
            WPI_TalonSRX rightMotor = new WPI_TalonSRX(7);
            //lm = new TalonSRX(1);
            dd = new DifferentialDrive(leftMotor, rightMotor);

        }
        public void main(OperatorInterface oi){
            // lm.set(ControlMode.PercentOutput, -1);
            dd.arcadeDrive(oi.pilot.getRawAxis(1),oi.pilot.getRawAxis(4));
        }
}