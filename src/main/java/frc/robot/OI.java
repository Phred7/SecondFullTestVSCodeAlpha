/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
  public Joystick joystick1;
  //public Joystick joystick2;

  public JoystickButton trigr1;
  public JoystickButton btn2;

  public OI(){
    joystick1 = new Joystick(0);
    //joystick2 = new Joystick(1);

    trigr1 = new JoystickButton(joystick1, 1);
    //trigr1.whenPressed(new Command());
    //trigr1.whenReleased(new Command());
  }

  public Joystick getJoystick1(){
      return joystick1;
  }

  public double getJoystick1X(){
    if(Math.abs(joystick1.getX())>RobotMap.sensitivity){
      System.out.println(joystick1.getX());
      return -1*joystick1.getX();
    } else {
      return 0.0;
    }
  }

  public double getJoystick1Y(){
    if(Math.abs(joystick1.getY())>RobotMap.sensitivity){
      System.out.println(joystick1.getY());
      return -1*joystick1.getY();
    } else {
      return 0.0;
    }
  }

 /* public Joystick getJoystick2(){
    return joystick2;
  }

  public double getJoystick2X(){
    if(Math.abs(joystick2.getX())>RobotMap.sensitivity){
      return -1*joystick2.getX();
    }else{
      return 0.0;
    }
  }

  public double getJoystick2Y(){
    if(Math.abs(joystick2.getX())>RobotMap.sensitivity){
      return -1*joystick2.getX();
    } else{
      return 0.0;
    }
  }*/
}
