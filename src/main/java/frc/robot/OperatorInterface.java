/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

public class OperatorInterface {
    public Joystick   pilot;
    public Joystick   copilot;
    public DTButton[] pilotButtons;
    public DTButton[] copilotButtons;
    public DTButton[] cocopilotButtons;

    public OperatorInterface() {
        this.pilot = new Joystick(Constants.PILOT_JOYSTICK);
        this.copilot = new Joystick(Constants.COPILOT_JOYSTICK);

        this.copilotButtons = new DTButton[20];
        for (int i = 0; i < this.copilotButtons.length; i++)
            this.copilotButtons[i] = new DTButton(this.copilot, i + 1);

        this.pilotButtons = new DTButton[20];
        for (int i = 0; i < this.pilotButtons.length; i++)
            this.pilotButtons[i] = new DTButton(this.pilot, i + 1);
    }

    /**
     * Gets the left joystick x-value on the pilot controller
     */
    public double pilotLeftStickX() {
        return this.pilot.getRawAxis(Buttons.LEFT_JOYSTICK_X);
    }

    /**
     * Gets the left joystick y-value on the pilot controller
     */
    public double pilotLeftStickY() {
        return this.pilot.getRawAxis(Buttons.LEFT_JOYSTICK_Y);
    }

    /**
     * Gets the right joystick x-value on the pilot controller
     */
    public double pilotRightStickX() {
        return this.pilot.getRawAxis(Buttons.RIGHT_JOYSTICK_X);
    }

    /**
     * Gets the right joystick y-value on the pilot controller
     */
    public double pilotRightStickY() {
        return this.pilot.getRawAxis(Buttons.RIGHT_JOYSTICK_Y);
    }

    /**
     * Gets the value of the left trigger on the pilot controller
     */
    public double pilotLeftTrigger() {
        return this.pilot.getRawAxis(Buttons.LEFT_TRIGGER);
    }

    /**
     * Gets the value of the right trigger on the pilot controller
     */
    public double pilotRightTrigger() {
        return this.pilot.getRawAxis(Buttons.RIGHT_TRIGGER);
    }

    /**
     * Gets a copilot button
     *
     * @param id
     *            - the id of the button to get
     */
    public DTButton getCopilotButton(int id) {
        return this.copilotButtons[id];
    }

    /**
     * Gets a cocopilot button
     *
     * @param id
     *            - the id of the button to get
     */
    public DTButton getCocopilotButton(int id) {
        return this.cocopilotButtons[id];
    }

    /**
     * Gets the state of the given button on the copilot controller
     *
     * @return {@code true} if pressed, otherwise {@code false}
     */
    public boolean copilotButtonIsPressed(int button) {
        return this.copilot.getRawButton(button);
    }

    public double getPilotAxis(int num) {
        return this.pilot.getRawAxis(num);
    }

    public double getCopilotAxis(int num) {
        // gets the axis on the copilot box
        return this.copilot.getRawAxis(num);
    }

    public int dpadPilot() {
        return this.pilot.getPOV(0);
    }

    public int dpadCopilot() {
        return this.copilot.getPOV(0);
    }
}
