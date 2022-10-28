package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.OperatorInterface;
import frc.robot.Wiring;

public class Shooter {
    private static final double HOPPER_SPEED = 0.15D;

    private final OperatorInterface operatorInterface;
    private boolean                 dPadPressed;

    private WPI_TalonSRX shooterMoter;
    private double       shooterSpeed;
    private boolean      shooterEnabled;

    private WPI_TalonSRX hopperMotor;
    private boolean      hopperEnabled;

    private boolean isRunning;
    private long    lastKillTime;

    public Shooter(OperatorInterface oi) {
        operatorInterface = oi;
        dPadPressed = false;

        shooterMoter = new WPI_TalonSRX(Wiring.SHOOTER_MOTOR);
        shooterEnabled = false;
        shooterSpeed = 0D;

        shooterMoter.setInverted(true);

        hopperMotor = new WPI_TalonSRX(Wiring.HOPPER_MOTOR);

        isRunning = true;
        lastKillTime = 0L;
    }

    public void main() {
        readButtons();

        checkValues();

        setMotors();
    }

    private void readButtons() {
        // enable toggles
        if (operatorInterface.pilot.getBButtonPressed()) {
            shooterEnabled = !shooterEnabled;
            System.out.println("Motor is" + shooterEnabled);
        }

        if (operatorInterface.pilot.getAButtonPressed()) {
            hopperEnabled = !hopperEnabled;
        }

        // change shooter speed
        if (shooterEnabled) {
            int dPad = operatorInterface.pilot.getRawDPad();
            if (dPad == -1) {
                dPadPressed = false;
            } else if (!dPadPressed) {
                if (dPad == 0) {
                    shooterSpeed += 0.1D;
                } else if (dPad == 180) {
                    shooterSpeed -= 0.1D;
                }
                dPadPressed = true;
            }
            System.out.println("Motor speed is " + shooterSpeed);
        }
    }

    private void checkValues() {
        // ensure shooter speed in range
        if (shooterSpeed > 1D) {
            shooterSpeed = 1D;
        } else if (shooterSpeed < 0.1D) {
            shooterSpeed = 0.1D;
        }

        // ensure hopper and shooter are running in sync
        if (!shooterEnabled) {
            hopperEnabled = false;
        } else if (hopperEnabled) {
            shooterEnabled = true;
        }
    }

    private void setMotors() {
        if (hopperEnabled) {
            hopperMotor.set(ControlMode.PercentOutput, HOPPER_SPEED);
            hopperEnabled = true;
        } else {
            hopperMotor.set(ControlMode.PercentOutput, 0D);
        }

        if (shooterEnabled) {
            shooterMoter.set(ControlMode.PercentOutput, shooterSpeed);
        } else {
            shooterMoter.set(ControlMode.PercentOutput, 0D);
        }
    }

    private void setKill(boolean kill) {
        if (kill && isRunning) {
            isRunning = false;
            lastKillTime = System.currentTimeMillis();
        } else {
            isRunning = true;
            lastKillTime = 0L;
        }
    }
}
