package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.Buttons;
import frc.robot.OperatorInterface;

public class Shooter {
    private OperatorInterface operatorInterface;
    private boolean           dPadPressed;

    // private TalonSRX shooterMoter;
    private double   shooterSpeed;
    private boolean  shooterEnabled;

    // private TalonSRX hopperMotor;
    private boolean  hopperEnabled;

    private boolean isKilled;
    private long    killTime;

    public void init(OperatorInterface operatorInterface) {
        this.operatorInterface = operatorInterface;
        this.dPadPressed = false;

        // this.shooterMoter = new WPI_TalonSRX(3);
        this.shooterEnabled = false;
        this.shooterSpeed = 0D;

        // this.hopperMotor = new WPI_TalonSRX(1);

        this.isKilled = false;
        this.killTime = 0L;
    }

    public void main() {
        this.readButtons();

        this.checkValues();

        this.setMotors();
    }

    public void readButtons() {
        // enable toggles
        if (this.operatorInterface.pilot.getRawButtonPressed(Buttons.B)) {
            this.shooterEnabled = !this.shooterEnabled;
        }

        // if (this.operatorInterface.pilot.getRawButtonPressed(Buttons.A)) {
        // this.hopperEnabled = !this.hopperEnabled;
        // }

        // change shooter speed
        if (this.shooterEnabled) {
            int dPad = this.operatorInterface.dpadCopilot();
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

    public void checkValues() {
        // ensure shooter speed in range
        if (this.shooterSpeed > 1D) {
            this.shooterSpeed = 1D;
        } else if (this.shooterSpeed < 0D) {
            this.shooterSpeed = 0D;
        }
    }

    public void setMotors() {
        // if (this.hopperEnabled) {
        //     this.hopperMotor.set(ControlMode.PercentOutput, 0.25D);
        //     this.hopperEnabled = true;
        // } else {
        //     this.hopperMotor.set(ControlMode.PercentOutput, 0D);
        // }

        // if (this.shooterEnabled) {
        //     this.shooterMoter.set(ControlMode.PercentOutput, this.shooterSpeed);
        // } else {
        //     this.shooterMoter.set(ControlMode.PercentOutput, 0D);
        // }

        System.out.println("Shooter speed:" + this.shooterSpeed);
    }
}
