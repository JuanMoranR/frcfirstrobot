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

	public void robotInit() {
		m_myRobot = new DifferentialDrive(new Spark(0), new Spark(2));
		//m_leftStick = new Joystick(0); //Use this and bottom when using TWO joysticks
		//m_rightStick = new Joystick(1);
		m_controller = new XboxController(0); //Use this when using single controller
	}

	public void teleopPeriodic() {
		m_myRobot.tankDrive(m_controller.getY(Hand.kRight), m_controller.getY(Hand.kLeft));
		//m_myRobot.tankDrive(m_leftStick.getY(), m_rightStick.getY());
	}
}