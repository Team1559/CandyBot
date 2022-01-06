package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.Buttons;
import frc.robot.OperatorInterface;

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

    public Shooter(OperatorInterface operatorInterface) {
        this.operatorInterface = operatorInterface;
        this.dPadPressed = false;

        this.shooterMoter = new WPI_TalonSRX(3);
        this.shooterEnabled = false;
        this.shooterSpeed = 0D;

        this.hopperMotor = new WPI_TalonSRX(1);

        this.isRunning = true;
        this.lastKillTime = 0L;
    }

    public void main() {
        this.readButtons();

        this.checkValues();

        this.setMotors();
    }

    private void readButtons() {
        // enable toggles
        if (this.operatorInterface.pilot.getRawButtonPressed(Buttons.B)) {
            this.shooterEnabled = !this.shooterEnabled;
        }

        if (this.operatorInterface.pilot.getRawButtonPressed(Buttons.A)) {
            this.hopperEnabled = !this.hopperEnabled;
        }

        // change shooter speed
        if (this.shooterEnabled) {
            int dPad = this.operatorInterface.dpadPilot();
            if (dPad == -1) {
                this.dPadPressed = false;
            } else if (!this.dPadPressed) {
                if (dPad == Buttons.DPAD_UP) {
                    this.shooterSpeed += 0.1D;
                } else if (dPad == Buttons.DPAD_DOWN) {
                    this.shooterSpeed -= 0.1D;
                }
                this.dPadPressed = true;
            }
        }
    }
    private void checkValues() {
        // ensure shooter speed in range
        if (this.shooterSpeed > 1D) {
            this.shooterSpeed = 1D;
        } else if (this.shooterSpeed < 0.1D) {
            this.shooterSpeed = 0.1D;
        }

        // ensure hopper and shooter are running in sync
        if (!this.shooterEnabled) {
            this.hopperEnabled = false;
        } else if (this.hopperEnabled) {
            this.shooterEnabled = true;
        }
    }

    private void setMotors() {
        if (this.hopperEnabled) {
            this.hopperMotor.set(ControlMode.PercentOutput, HOPPER_SPEED);
            this.hopperEnabled = true;
        } else {
            this.hopperMotor.set(ControlMode.PercentOutput, 0D);
        }

        if (this.shooterEnabled) {
            this.shooterMoter.set(ControlMode.PercentOutput, this.shooterSpeed);
        } else {
            this.shooterMoter.set(ControlMode.PercentOutput, 0D);
        }

        System.out.println("Shooter speed:" + this.shooterSpeed);
        System.out.println("Auger speed:  " + this.hopperEnabled);
    }

    private void setKill(boolean kill) {
        if (kill && this.isRunning) {
            this.isRunning = false;
            this.lastKillTime = System.currentTimeMillis();
        } else {
            this.isRunning = true;
            this.lastKillTime = 0L;
        }
    }
}
