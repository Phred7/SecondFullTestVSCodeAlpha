/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class Drive extends Command {

  double sL = 0.0;
  double sR = 0.0;

  public Drive(double speedL, double speedR) {
    requires(Robot.drive);
    sL = speedL;
    sR = speedR;
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.drive.tankDrive(sL, sR);
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