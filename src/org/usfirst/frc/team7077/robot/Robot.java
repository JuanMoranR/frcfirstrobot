/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team7077.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * This is a demo program showing the use of the RobotDrive class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 */

public class Robot extends IterativeRobot {
	private DifferentialDrive m_myRobot;
	//private Joystick m_leftStick;
	//private Joystick m_rightStick;
	private XboxController m_controller;
	
	private static final int kMotorPortLeft = 0; //Change this to whatever the left motor port is on
	private static final int kMotorPortRight = 1; //Change this to whatever the right motor port is on
	
	//Talons for intake wheels
	Talon m_frontLeft = new Talon(2);
	Talon m_frontRight = new Talon(3);
	SpeedControllerGroup m_left = new SpeedControllerGroup(m_frontLeft);
	SpeedControllerGroup m_right = new SpeedControllerGroup(m_frontRight);
			
	DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);
	
	private static final int kJoystickPortLeft = 0; //Change this to whatever the left joystick port is on
	private static final int kJoystickPortRight = 1; //Change this to whatever the right joystick port is on
	private static final int kControllerPort = 0; //Change this to whatever the ONE controller port is on
	
	

	public void robotInit() {
		//Spark 0 and 1 are drive motors, 0 is 2 left side 1 is 2 right side.
		m_myRobot = new DifferentialDrive(new Spark(kMotorPortLeft), new Spark(kMotorPortRight));
		//m_leftStick = new Joystick(kJoystickPortLeft); //Use this and bottom when using TWO joysticks
		//m_rightStick = new Joystick(kJoystickPortRight);
		m_controller = new XboxController(kControllerPort); //Use this when using single controller
	}

	public void teleopPeriodic() {
		/** 
		* WIP for DPAD speed toggle (slow, medium, fast, all out)
		* Have two if else statements, one that outputs a number between 0 and 3
		* Depending on which DPAD button is pressed
		* Where 0 is the slowest and 3 is the fastest
		* The next if else checks the number set in the first statement and
		* Sets the max speed to slow or fast
		* What follows is pseudocode
		*/
		int speed = 3;
		if (m_controller.getXButton() == true)
			speed = 0;
		if (m_controller.getYButton() == true)
			speed = 1;
		if (m_controller.getBButton() == true)
			speed = 2;
		if (m_controller.getAButton() == true)
			speed = 3;
			
		switch(speed)
		{
			case 0:
				if (m_controller.getY(Hand.kLeft) == 1)
					m_myRobot.tankDrive(0.25, 0);
				if (m_controller.getY(Hand.kRight) == 1)
					m_myRobot.tankDrive(0, 0.25);
				if (m_controller.getY(Hand.kLeft) == 1 && m_controller.getY(Hand.kRight) == 1)
					m_myRobot.tankDrive(0.25, 0.25);
				
			case 1:
				set robot tank drive to medium;
			case 2:
				set robot tank drive to fast;
			case 3:
				m_myRobot.tankDrive(m_controller.getY(Hand.kRight), m_controller.getY(Hand.kLeft));
			default:
				System.out.println("Somethings wrong, speed variable isnt set");
		}
			
		
		m_myRobot.tankDrive(m_controller.getY(Hand.kRight), m_controller.getY(Hand.kLeft)); //Use this when using one controller
		//System.out.println(m_controller.getY(Hand.kRight));
		//m_myRobot.tankDrive(m_leftStick.getY(), m_rightStick.getY()); //Use this when using TWO joysticks
		if (m_controller.getPOV()==0)
			m_myRobot.tankDrive(-1,-1);
		if (m_controller.getPOV()==180)
			m_myRobot.tankDrive(1, 1);
		m_frontLeft.set(m_controller.getRawAxis(3));
		System.out.println(m_frontLeft);
	}
}
