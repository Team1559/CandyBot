/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

public class OperatorInterface {
    public Joystick pilot;
    public Joystick copilot;
    public DTButton[] pilotButtons;
    public DTButton[] copilotButtons;
    public DTButton[] cocopilotButtons;

    public OperatorInterface() {
        pilot = new Joystick(Constants.PILOT_JOYSTICK);
        copilot = new Joystick(Constants.COPILOT_JOYSTICK);

        copilotButtons = new DTButton[20];
        for (int i = 0; i < copilotButtons.length; i++)
            copilotButtons[i] = new DTButton(copilot, i + 1);

        pilotButtons = new DTButton[20];
        for (int i = 0; i < pilotButtons.length; i++)
            pilotButtons[i] = new DTButton(pilot, i + 1);
    }

    /**
     * Gets the left joystick x-value on the pilot controller
     */
    public double pilotLeftStickX() {
        return pilot.getRawAxis(Buttons.LEFT_JOYSTICK_X);
    }

    /**
     * Gets the left joystick y-value on the pilot controller
     */
    public double pilotLeftStickY() {
        return pilot.getRawAxis(Buttons.LEFT_JOYSTICK_Y);
    }

    /**
     * Gets the right joystick x-value on the pilot controller
     */
    public double pilotRightStickX() {
        return pilot.getRawAxis(Buttons.RIGHT_JOYSTICK_X);
    }

    /**
     * Gets the right joystick y-value on the pilot controller
     */
    public double pilotRightStickY() {
        return pilot.getRawAxis(Buttons.RIGHT_JOYSTICK_Y);
    }

    /**
     * Gets the value of the left trigger on the pilot controller
     */
    public double pilotLeftTrigger() {
        return pilot.getRawAxis(Buttons.LEFT_TRIGGER);
    }

    /**
     * Gets the value of the right trigger on the pilot controller
     */
    public double pilotRightTrigger() {
        return pilot.getRawAxis(Buttons.RIGHT_TRIGGER);
    }

    /**
     * Gets a copilot button
     *
     * @param id
     *            - the id of the button to get
     */
    public DTButton getCopilotButton(int id) {
        return copilotButtons[id];
    }

    /**
     * Gets a cocopilot button
     *
     * @param id
     *            - the id of the button to get
     */
    public DTButton getCocopilotButton(int id) {
        return cocopilotButtons[id];
    }

    /**
     * Gets the state of the given button on the copilot controller
     *
     * @return {@code true} if pressed, otherwise {@code false}
     */
    public boolean copilotButtonIsPressed(int button) {
        return copilot.getRawButton(button);
    }

    public double getPilotAxis(int num) {
        return pilot.getRawAxis(num);
    }

    public double getCopilotAxis(int num) {
        // gets the axis on the copilot box
        return copilot.getRawAxis(num);
    }

    public int dpadPilot() {
        return pilot.getPOV(0);
    }

    public int dpadCopilot() {
        return copilot.getPOV(0);
    }
}
