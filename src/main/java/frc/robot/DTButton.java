package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

public class DTButton {
    private boolean  old;
    private boolean  current;
    private Joystick stick;
    private int      button;

    public DTButton(Joystick stick, int button) {
        this.stick = stick;
        this.button = button;
    }

    public void update() {
        this.update(this.stick.getRawButton(this.button));
    }

    public void update(boolean b) {
        this.old = this.current;
        this.current = b;
    }

    /**
     * @return if button is being held down
     */
    public boolean isDown() {
        return this.current;
    }

    /**
     * @return Falling edge of button press
     */
    public boolean isReleased() {
        return this.old && !this.current;
    }
}
