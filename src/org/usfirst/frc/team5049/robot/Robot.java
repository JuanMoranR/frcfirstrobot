/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5049.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;



public class Robot extends IterativeRobot {
	
	private DifferentialDrive m_myRobot;
	private Joystick m_leftStick;
	private Joystick m_rightStick;
	private XboxController m_controller;
	private XboxController m_ps4;
	
	private static final int kMotorPortLeft = 0; //Change this to whatever the left motor port is on
	private static final int kMotorPortRight = 1; //Change this to whatever the right motor port is on
	
	//Talon for arm
	Talon m_arm = new Talon(2);
	//Talon for intake wheels
	Talon m_intake = new Talon(3);
	
	private static final int kJoystickPortLeft = 0; //Change this to whatever the left joystick port is on
	private static final int kJoystickPortRight = 1; //Change this to whatever the right joystick port is on
	private static final int kControllerPort = 5; //Change this to whatever the ONE controller port is on
	private static final int kPS4Port = 2;
	
	

	public void robotInit() {
		//Spark 0 and 1 are drive motors, 0 is 2 left side 1 is 2 right side.
		m_myRobot = new DifferentialDrive(new Spark(kMotorPortLeft), new Spark(kMotorPortRight));
		//Old JoyStick Code
		//m_leftStick = new Joystick(kJoystickPortLeft); //Use this and bottom when using TWO joysticks
		//m_rightStick = new Joystick(kJoystickPortRight);
		//Code from SunRise
		//Joystick leftDrive = new Joystick(0);	// set ID 1 in DriverStation
		//Joystick rightDrive = new Joystick(1);	// set ID 2 in DriverStation 
		//m_myRobot.setExpiration(0.1);
		//m_ps4 = new XboxController(kPS4Port);
		m_controller = new XboxController(kControllerPort); //Use this when using single controller
	}

	public void teleopPeriodic() {
			
		//Joystick Controller 
		/*
		if (m_controller.getTriggerAxis(Hand.kLeft)>=0.1)
			m_myRobot.tankDrive(m_controller.getY(Hand.kLeft), m_controller.getY(Hand.kRight));
		m_myRobot.tankDrive(m_controller.getY(Hand.kRight), m_controller.getY(Hand.kLeft)); //Use this when using one controller
		//System.out.println(m_controller.getY(Hand.kRight));
		//m_myRobot.tankDrive(m_leftStick.getY(), m_rightStick.getY()); //Use this when using TWO joysticks
		if (m_controller.getPOV()==0)
			m_myRobot.tankDrive(-1,-1);
		if (m_controller.getPOV()==180)
			m_myRobot.tankDrive(1, 1);
		if (m_controller.getPOV()==90)
			m_myRobot.tankDrive(-1, 1);
		if (m_controller.getPOV()==270)
			m_myRobot.tankDrive(1, -1);
		if (m_controller.getBumperPressed(Hand.kLeft) == true)
			m_arm.set(0.4);
		if (m_controller.getBumperReleased(Hand.kLeft) == true)
			m_arm.set(0);
		if (m_controller.getBumperPressed(Hand.kRight) == true)
			m_arm.set(-0.4);
		if (m_controller.getBumperReleased(Hand.kRight) == true)
			m_arm.set(0);
		m_frontLeft.set(m_controller.getRawAxis(3));
		*/
			
			
		
			
		double fValue = m_controller.getRawAxis(4); //righttrigger
		double bValue = -1* m_controller.getRawAxis(3); //lefttrigger
		
		double jNValue = m_controller.getRawAxis(0);
		double rSpeed;
		double lSpeed;
		double Value = fValue + bValue;
		double drift = 1;
		if(Value > 0) {
			lSpeed = ((Value + (jNValue)/1.6)/1)*drift;
			rSpeed = ((Value - (jNValue)/1.6)/0.9)*drift;
			
			m_myRobot.tankDrive(lSpeed, rSpeed);
		}
		else if(Value < 0) {
			lSpeed = ((Value - (jNValue)/1.6)/1.25)*drift; 
			rSpeed = ((Value + (jNValue/1.6))/1.15)*drift;
			
			m_myRobot.tankDrive(lSpeed, rSpeed);
		} 
		
		m_myRobot.tankDrive(m_controller.getRawAxis(0), m_controller.getRawAxis(2));
		
		
		
		Timer.delay(0.005); // wait for a motor update time
	}
}

		/*
			m_myRobot.tankDrive(m_controller.getY(Hand.kRight), m_controller.getY(Hand.kLeft));
						if (m_controller.getYButtonPressed()==true)
							m_intake.set(-1);
						if (m_controller.getYButtonReleased()==true)
							m_intake.set(0);
						if (m_controller.getAButtonPressed()==true)
							m_intake.set(1);
						if (m_controller.getAButtonReleased()==true)
							m_intake.set(0);
						System.out.println(m_intake);
						if (m_controller.getPOV()==0)
							m_myRobot.tankDrive(-1,-1);
						if (m_controller.getPOV()==180)
							m_myRobot.tankDrive(1, 1);
						if (m_controller.getPOV()==90)
							m_myRobot.tankDrive(-1, 1);
						if (m_controller.getPOV()==270)
							m_myRobot.tankDrive(1, -1);
						if (m_controller.getBumperPressed(Hand.kLeft) == true)
							m_arm.set(-0.4);
						if (m_controller.getBumperReleased(Hand.kLeft) == true)
							m_arm.set(0);
		 				if (m_controller.getBumperPressed(Hand.kRight) == true)
							m_arm.set(100);
						if (m_controller.getBumperReleased(Hand.kRight) == true)
							m_arm.set(0);
			*/	
			/*
			m_myRobot.tankDrive(m_leftStick.getY(), m_rightStick.getY()); //Use this when using TWO joysticks
			if (m_rightStick.getRawButtonPressed(3)==true)
				m_arm.set(-0.4);
			if (m_rightStick.getRawButtonReleased(3)==true)
				m_arm.set(0);
			if (m_rightStick.getRawButtonPressed(4)==true)
				m_arm.set(1);
			if (m_rightStick.getRawButtonReleased(4)==true)
				m_arm.set(0);
				
			m_intake.set(m_rightStick.getZ());
				
			if (m_rightStick.getPOV()==0)
				m_myRobot.tankDrive(-1,-1);
			if (m_rightStick.getPOV()==180)
				m_myRobot.tankDrive(1, 1);
			if (m_rightStick.getPOV()==90)
				m_myRobot.tankDrive(-1, 1);
			if (m_rightStick.getPOV()==270)
			m_myRobot.tankDrive(1, -1);
			
			*/

//	}
//}
