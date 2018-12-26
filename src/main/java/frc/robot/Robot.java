/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.*;
import frc.robot.commands.*;

public class Robot extends TimedRobot {
  public static DriveTrain drive;
  public static GearBoxes gbxs;
  public static OI m_oi;

  Command m_autonomousCommand;
  Command NoAuto;
  Command Auto1;
  Command Auto2;

  SendableChooser<Command> autoChooser = new SendableChooser<>();

  @Override
  public void robotInit() {
    RobotMap.init();

    drive = new DriveTrain();
    gbxs = new GearBoxes();
    m_oi = new OI();

    autoChooser.addDefault("Default: No Auto", new Auto1());//new NoAuto());
    autoChooser.addObject("Auto 1", new Auto1());
    autoChooser.addObject("Auto 2", new Auto2());
    SmartDashboard.putData("Auto mode", autoChooser);
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    m_autonomousCommand = autoChooser.getSelected();
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    SmartDashboard.putNumber("SPI Angle", Robot.drive.getGyro());
    SmartDashboard.putNumber("Left Drive Encoder", Robot.drive.getEncL());
    SmartDashboard.putNumber("Right Drive Encoder", Robot.drive.getEncR());

  }

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    SmartDashboard.putNumber("SPI Angle", Robot.drive.getGyro());
    SmartDashboard.putNumber("Left Drive Encoder", Robot.drive.getEncL());
    SmartDashboard.putNumber("Right Drive Encoder", Robot.drive.getEncR());

  }

  @Override
  public void testPeriodic() {
  }
}
