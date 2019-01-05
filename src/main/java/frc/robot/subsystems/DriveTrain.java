/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.*;

public class DriveTrain extends Subsystem {
  
  private final DifferentialDrive driveWC = RobotMap.drive;

  Talon l = RobotMap.driveL;
  Talon r = RobotMap.driveR;

  Encoder encL = RobotMap.dtEncL;
  Encoder encR = RobotMap.dtEncR;
  ADXRS450_Gyro gyro = RobotMap.gyroSPI;

  public void reset() {
    resetEncs();
    resetGyro();
  }

  public void resetEncR(){
    encR.reset();
  }

  public void resetEncL(){
    encL.reset();
  }

  public void resetGyro(){
    gyro.reset();
  }

  public void calibrateGyro(){
    gyro.calibrate();
  }
  public void resetEncs(){
    resetEncL();
    resetEncR();
  }

  public void rAndCGyro(){
    resetGyro();
    calibrateGyro();
  }

  public int getEncL(){
    return encL.get();
  }

  public int getEncR(){
    return encR.get();
  }

  public double getGyro(){
    return gyro.getAngle();
  }

  public double getGyroRate(){
    return gyro.getRate();
  }

  public void setDriveL(double speed){
    l.set(speed);
  }

  public void setDriveR(double speed){
    r.set(speed);
  }

  public void stopL(){
    l.set(0.0);
  }

  public void stopR(){
    r.set(0.0);
  }

  public void stop(){
    stopL();
    stopR();
  }

  public void arcadeDrive(double xSpeed, double yRotate){
    driveWC.arcadeDrive(xSpeed, yRotate);
  }

  public void tankDrive(double speedL, double speedR){
    driveWC.tankDrive(speedL, speedR);
  }

  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new Drive_Arcade());
    //setDefaultCommand(new Drive_Tank());
  }
}
