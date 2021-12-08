/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.Solenoid;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */

public class Robot extends TimedRobot {
  private OperatorInterface oi;
  private TalonSRX rightMotor;
  private TalonSRX leftMotor;
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    oi = new OperatorInterface();
    leftMotor = new TalonSRX(3);
    rightMotor = new TalonSRX(7);
  }


  @Override
  public void robotPeriodic() {

  }

 
  @Override
  public void autonomousInit() {
 
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
  
  }

  /**
   * This function is called once when teleop is enabled.
   */
  @Override
  public void teleopInit() {

  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    /*if(Math.abs(oi.getPilotY()) >= .2)
    leftMotor.set(oi.getPilotY());
    if(Math.abs(oi.getPilotZ()) >= .2)
    rightMotor.set(oi.getPilotZ()); */

    if(oi.pilotButtonIsPressed(7) && Math.abs(oi.getPilotX()) <= 0.1){
      rightMotor.set(ControlMode.PercentOutput,1);
      leftMotor.set(ControlMode.PercentOutput,1);
    }
    else if(oi.pilotButtonIsPressed(7) && oi.getPilotX() >= 0.1){
      rightMotor.set(ControlMode.PercentOutput,1-oi.getPilotX());
      leftMotor.set(ControlMode.PercentOutput,1);
    }
    else if(oi.pilotButtonIsPressed(7) && oi.getPilotX() <= -0.1){
      rightMotor.set(ControlMode.PercentOutput,1);
      leftMotor.set(ControlMode.PercentOutput,1+oi.getPilotX());
    }
    else{
      rightMotor.set(ControlMode.PercentOutput,0);
      leftMotor.set(ControlMode.PercentOutput,0);
    }
  }

  /**
   * This function is called once when the robot is disabled.
   */
  @Override
  public void disabledInit() {

  }

  /**
   * This function is called periodically when disabled.
   */
  @Override
  public void disabledPeriodic() {

  }

  /**
   * This function is called once when test mode is enabled.
   */
  @Override
  public void testInit() {

  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    
  }
}