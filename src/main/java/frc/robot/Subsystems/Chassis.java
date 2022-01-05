package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.OperatorInterface;

public class Chassis {
    DifferentialDrive differential;

    public void init() {
        WPI_TalonSRX leftMotor = new WPI_TalonSRX(1);
        WPI_TalonSRX rightMotor = new WPI_TalonSRX(3);
        differential = new DifferentialDrive(leftMotor, rightMotor);

    }

    public void main(OperatorInterface oi) {
        differential.arcadeDrive(oi.pilotLeftStickY(), oi.pilotRightStickX());
    }
}
