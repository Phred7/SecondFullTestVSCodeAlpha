/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class Drive_Tank extends Command {
  public Drive_Tank() {
    requires(Robot.drive);
  }
  
  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    //Robot.drive.tankDrive(Robot.m_oi.getJoystick1Y(), Robot.m_oi.getJoystick2Y());
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
