package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.Constants;
import frc.robot.OperatorInterface;

public class Chassis {
    private final OperatorInterface operatorInterface;
    private final DifferentialDrive differential;

    public Chassis(OperatorInterface oi) {
        operatorInterface = oi;

        WPI_TalonSRX leftMotor = new WPI_TalonSRX(Constants.LEFT_DRIVE_MOTOR);
        WPI_TalonSRX rightMotor = new WPI_TalonSRX(Constants.RIGHT_DRIVE_MOTOR);
        rightMotor.setInverted(true);
        differential = new DifferentialDrive(leftMotor, rightMotor);
    }

    public void main() {
        differential.arcadeDrive(operatorInterface.pilot.getLeftX(),
            operatorInterface.pilot.getRightX());
    }
}
