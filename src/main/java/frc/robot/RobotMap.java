/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class RobotMap {

  public static DifferentialDrive drive;
  public static Talon driveL;
  public static Talon driveR;
  public static VictorSP gb1;
  public static VictorSP gb2;

  public static ADXRS450_Gyro gyroSPI;
  public static Encoder dtEncL;
  public static Encoder dtEncR;
  public static Encoder gbEnc1;

  public static final double sensitivity = 0.075;
  public static final double encCountsToIn = 27.851497;
  public static final double encCountsToRot = 520;

  public static final double turnGainMultiplier = 0.1;
	public static final double turnP = 0.5;
	public static final double turnI = 0.03;
	public static final double turnD = 0.5;
	
	public static final double driveStraightGainMultiplier = 0.03; //.03
	public static final double driveStraightP = 0.45; //.45 //1.0 (12/23)
	public static final double driveStraightI = 0.015; //.015 //1.0 (12/23)
  public static final double driveStraightD = 0.011; //.011 //3.0 (12/23) //8.0(12/23-2)

  public static void init(){

    driveL = new Talon(1);
    driveR = new Talon(0);
    gb1 = new VictorSP(2);
    gb2 = new VictorSP(3);

    dtEncL = new Encoder(0, 1, false, EncodingType.k4X);
    dtEncR = new Encoder(2, 3, true, EncodingType.k4X);
    gbEnc1 = new Encoder(4, 5, false, EncodingType.k4X);

    gyroSPI = new ADXRS450_Gyro();

    driveR.setInverted(true);

    SpeedControllerGroup m_right = new SpeedControllerGroup(driveR);
    SpeedControllerGroup m_left = new SpeedControllerGroup(driveL);

    drive = new DifferentialDrive(m_left, m_right);
    
  }
  
}
