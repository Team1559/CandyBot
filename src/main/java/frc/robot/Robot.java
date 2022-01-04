/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.subsystems.Chassis;
import frc.robot.OperatorInterface;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

public static final boolean enableChassis = true;

public static boolean chassisEnabled = false;

public Chassis chassis = new Chassis();
public OperatorInterface oi = new OperatorInterface();

  @Override
  public void robotInit() {

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
    initialize();
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    if(chassisEnabled && enableChassis){
      chassis.main(oi);
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

  public void initialize(){
    if(enableChassis){
      chassis.innit();
      chassisEnabled = true;
    }
  }
}