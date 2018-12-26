/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveGearBoxes extends Command {

  double sgb1 = 0.0;
  double sgb2 = 0.0;

  public DriveGearBoxes(double gb1Speed, double gb2Speed) {
    requires(Robot.gbxs);
    sgb1 = gb1Speed;
    sgb2 = gb2Speed;
  }
  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.gbxs.drive(sgb1, sgb2);
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