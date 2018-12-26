/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class DriveGearBox1Encoder extends Command {

  double rotations = 0;
  double motorSpeed = 0.3;
  double currentLocation = 0;
  double error = 0;
  double pA = 0;
  double iA = 0;
  double dA = 0;
  double pidA = 0;
  double lastError = 0;

  public DriveGearBox1Encoder(double rotationChange) {
    requires(Robot.gbxs);
    this.rotations = rotationChange * RobotMap.encCountsToRot;
  }

  @Override
  protected void initialize() {
    Robot.gbxs.stopGB1();
    Robot.gbxs.getE();
  }

  @Override
  protected void execute() {
    SmartDashboard.putNumber("GB1 Error", error);
    SmartDashboard.putNumber("GB1 Rotations", rotations);
    SmartDashboard.putNumber("GB1 Encoder Value", Robot.gbxs.getE());
    currentLocation = Robot.gbxs.getE();
    error = rotations - currentLocation;
    pA = (error) * RobotMap.driveStraightP;
    iA = iA + (error * RobotMap.driveStraightI);
    dA = (error - lastError) * RobotMap.driveStraightD;
    lastError = error;
    pidA = pA + iA + dA;
    Robot.gbxs.driveGB1(motorSpeed - pidA);
  }

  @Override
  protected boolean isFinished() {
    return (Math.abs(Robot.gbxs.getE()) > ((Math.abs(rotations))));
  }

  @Override
  protected void end() {
    Robot.gbxs.stopGB1();
  }

  @Override
  protected void interrupted() {
  }
}