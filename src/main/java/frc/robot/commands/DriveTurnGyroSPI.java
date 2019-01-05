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

public class DriveTurnGyroSPI extends Command {

  double motorSpeed = 0.55;
	double direction = 0;
	double currentAngle = 0;
	double error = 0;
	double pAdjustment = 0;
	double iAdjustment = 0;
	double dAdjustment = 0;
	double lastError = 0;
	double PIDAdjustment = 0;
	double speed = 0;
	double way = 1;
	int n = 0;
	int i = 0;
  boolean pid = false;
  
  public DriveTurnGyroSPI(double headingChange) {
    requires(Robot.drive);
    this.direction = headingChange;
  }

  @Override
  protected void initialize() {
    motorSpeed = 0.55;
		currentAngle = 0;
		error = 0;
		pAdjustment = 0;
		iAdjustment = 0;
		dAdjustment = 0;
		lastError = 0;
		PIDAdjustment = 0;
		speed = 0;
		way = 1;
		n = 0;
		i = 0;
    pid = false;
    Robot.drive.stop();
    Robot.drive.reset();
  }

  @Override
  protected void execute() {
    SmartDashboard.putNumber("Angle: ", Robot.drive.getGyro());
    SmartDashboard.putNumber("Target Angle: ", direction);
    error = direction - currentAngle;
    pAdjustment = error * RobotMap.turnP * RobotMap.turnGainMultiplier;
		iAdjustment = iAdjustment + (error * RobotMap.turnI * RobotMap.turnGainMultiplier);
		dAdjustment = (error - lastError) * RobotMap.turnD * RobotMap.turnGainMultiplier;
		lastError = error;
    PIDAdjustment = pAdjustment + iAdjustment + dAdjustment;
    SmartDashboard.putNumber("Heading Error", error);
		if (Robot.drive.getGyro() < 10 + direction && Robot.drive.getGyro() > direction - 10) {
			if (pid == false) {
				iAdjustment = 0;
				pid = true;
			}
		} else {
			pid = false;
		}

		if (pid == true) {
			if (PIDAdjustment > .3) {
				speed = .3;
			} else if (PIDAdjustment < -.3) {
				speed = -.3;
			} else {
				speed = PIDAdjustment;
			}
		} else {
			if (error > 0) {
				speed = motorSpeed;
			} else {
				speed = -motorSpeed;
			}
		}
		Robot.drive.tankDrive(speed, -speed);

		if (Robot.drive.getGyro() < 2 + direction && Robot.drive.getGyro() > direction - 2) {
			n++;
			i++;
		} else {
			n = 0;
		}
  }

  @Override
  protected boolean isFinished() {
    return (Robot.drive.getGyro() < 2 + direction && Robot.drive.getGyro() > direction - 2 && n > 5);
  }

  @Override
  protected void end() {
    pid = false;
    Robot.drive.stop();
  }

  @Override
  protected void interrupted() {
		end();
  }
}