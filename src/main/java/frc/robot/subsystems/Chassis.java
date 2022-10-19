package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.OperatorInterface;
import frc.robot.Wiring;

public class Chassis {
    private final OperatorInterface operatorInterface;
    private final DifferentialDrive differential;

    public Chassis(OperatorInterface oi) {
        operatorInterface = oi;

        WPI_TalonSRX leftMotor = new WPI_TalonSRX(Wiring.LEFT_DRIVE_MOTOR);
        WPI_TalonSRX rightMotor = new WPI_TalonSRX(Wiring.RIGHT_DRIVE_MOTOR);

        rightMotor.setInverted(true);
        differential = new DifferentialDrive(leftMotor, rightMotor);
    }

    public void main() {
        differential.arcadeDrive(operatorInterface.pilot.getLeftY(), operatorInterface.pilot.getRightX());
    }
}
