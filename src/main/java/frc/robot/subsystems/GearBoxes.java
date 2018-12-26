/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.*;

public class GearBoxes extends Subsystem {

  VictorSP m1E = RobotMap.gb1;
  VictorSP m2 = RobotMap.gb2;

  Encoder enc1 = RobotMap.gbEnc1;

  public void gb1(){
      resetE();
  }

  public void resetE(){
    enc1.reset();
  }

  public int getE(){
    return enc1.get();
  }

  public void driveGB1(double speed){
    m1E.set(speed);
  }

  public void driveGB2(double speed){
    m2.set(speed);
  }

  public void drive(double speed1, double speed2){
    driveGB1(speed1);
    driveGB2(speed2);
  }

  public void stopGB1(){
    m1E.set(0.0);
  }

  public void stopGB2(){
    m2.set(0.0);
  }

  public void stop(){
    stopGB1();
    stopGB2();
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new DriveGearBoxes(0.0, 0.0));
  }
}
