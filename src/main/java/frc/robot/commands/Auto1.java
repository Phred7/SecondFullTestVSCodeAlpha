/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class Auto1 extends CommandGroup {
  /**
   * Add your docs here.
   */
  public Auto1() {
    addSequential(new DriveStraightGyroSPI(.5, 0, 36));
    //addSequential(new WaitCommand(10));
    //addSequential(new DriveStraightGyroSPI(-.5, 0, -36));
  }
}
