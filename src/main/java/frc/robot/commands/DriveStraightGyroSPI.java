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

public class DriveStraightGyroSPI extends Command {

  double motorSpeed = .15;
	double direction = 0;
	double currentAngle = 0;
	double distance = 0;
	double error = 0;
	double pAdjustment = 0;
	double iAdjustment = 0;
	double dAdjustment = 0;
	double lastError = 0;
  double pidAdjustment = 0;
  double distanceTraveled = 0;
  double distanceToTarget = 0;
  
  public DriveStraightGyroSPI(double motorSpeed, double headingChange, double distance) {
    requires(Robot.drive);
    this.motorSpeed = 1*motorSpeed;
    this.direction = headingChange;
    this.distance = distance * RobotMap.encCountsToIn;
    this.distance = (int) this.distance;
    System.out.println(this.distance);
    SmartDashboard.putNumber("distance: ", this.distance);
    this.distanceToTarget = this.distance;
  }

  @Override
  protected void initialize() {
    Robot.drive.stop();
    Robot.drive.reset();
    RobotMap.drive.setSafetyEnabled(false);
  }

  @Override
  protected void execute() {
    currentAngle = Robot.drive.getGyro();
    error = direction - currentAngle;
    pAdjustment = (direction - currentAngle) * RobotMap.driveStraightP * RobotMap.driveStraightGainMultiplier;
    iAdjustment = iAdjustment + (error * RobotMap.driveStraightI * RobotMap.driveStraightGainMultiplier);
    dAdjustment = (error - lastError) * RobotMap.driveStraightD * RobotMap.driveStraightGainMultiplier;
    lastError = error;
    pidAdjustment = pAdjustment + iAdjustment + dAdjustment;
    distanceTraveled = Robot.drive.getEncL();
    distanceToTarget = distanceToTarget - distanceTraveled;
    Robot.drive.tankDrive((motorSpeed - pidAdjustment), ((-motorSpeed) - pidAdjustment));
    SmartDashboard.putNumber("Error", error);
    SmartDashboard.putNumber("Adjustment", pidAdjustment);
    SmartDashboard.putNumber("Distance", distanceToTarget);
    System.out.println(Robot.drive.getEncL());
  }

  @Override
  protected boolean isFinished() {
    return (Math.abs(Robot.drive.getEncL()) > ((Math.abs(this.distance))));
  }

  @Override
  protected void end() {
    Robot.drive.stop();
    //RobotMap.drive.setSafetyEnabled(true);
  }

  @Override
  protected void interrupted() {
    end();
  }
}