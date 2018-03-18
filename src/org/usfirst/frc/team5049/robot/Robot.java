/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5049.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;


public class Robot extends IterativeRobot {
	
	public static DifferentialDrive m_myRobot;
	public XboxController m_controller;
	
	Timer timer;
	
	private static final int kMotorPortLeft = 0; //Change this to whatever the left motor port is on
	private static final int kMotorPortRight = 1; //Change this to whatever the right motor port is on
	
	//Talon for arm
	TalonSRX m_arm = new TalonSRX(2); //Port # on the RoboRio
	//Talon for intake wheels
	Talon m_Lintake = new Talon(3);
	Talon m_Rintake = new Talon(4);
	
	public static final int kControllerPort = 5; //Change this to whatever the ONE controller port is on
	
	
	Spark m_leftDrive = new Spark(kMotorPortLeft);
	Spark m_rightDrive = new Spark(kMotorPortRight);
	
	public void robotInit() {
		//Spark 0 and 1 are drive motors, 0 is 2 left side 1 is 2 right side.
		m_myRobot = new DifferentialDrive(m_leftDrive, m_rightDrive);

		//m_leftStick = new Joystick(kJoystickPortLeft); //Use this and bottom when using TWO joysticks
		//m_rightStick = new Joystick(kJoystickPortRight);
		m_myRobot.setExpiration(0.1);
		m_controller = new XboxController(kControllerPort); //Use this when using single controller
		
		timer = new Timer();
	}
	

	@Override
	public void autonomousInit() {
		m_myRobot.setSafetyEnabled(false);
		m_leftDrive.setSafetyEnabled(false);
		m_rightDrive.setSafetyEnabled(false);

		timer.reset();
		timer.start();
	
		
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		if (timer.get()<=2) {
			m_myRobot.tankDrive(-0.675, -0.675);
		}
		
		else {
			m_myRobot.tankDrive(0, 0);
		}
		Timer.delay(0.005);
	}

	@Override
	public void teleopInit() {
	}
	@Override
	public void teleopPeriodic() {
		
		m_myRobot.tankDrive(m_controller.getY(Hand.kRight), m_controller.getY(Hand.kLeft));
		if (m_controller.getYButtonPressed()==true)
		{	
			m_Lintake.set(-1);
			m_Rintake.set(1);
		}	
		if (m_controller.getYButtonReleased()==true)
		{
			m_Lintake.set(0);
			m_Rintake.set(0);
		}	
		if (m_controller.getAButtonPressed()==true)
		{
		
			m_Lintake.set(1);
			m_Rintake.set(-1);
		}
		if (m_controller.getAButtonReleased()==true)
		{	
			m_Lintake.set(0);
			m_Rintake.set(0);
		}	
		
		if (m_controller.getPOV()==0)
			m_myRobot.tankDrive(-1,-1);
		if (m_controller.getPOV()==180)
			m_myRobot.tankDrive(1, 1);
		if (m_controller.getPOV()==90)
			m_myRobot.tankDrive(-1, 1);
		if (m_controller.getPOV()==270)
			m_myRobot.tankDrive(1, -1);
		
		if (m_controller.getBumperPressed(Hand.kLeft) == true)
			m_arm.set(ControlMode.PercentOutput, -0.4);
		if (m_controller.getBumperReleased(Hand.kLeft) == true)
			m_arm.set(ControlMode.PercentOutput, 0);
		if (m_controller.getBumperPressed(Hand.kRight) == true)
			m_arm.set(ControlMode.PercentOutput, 1);
		if (m_controller.getBumperReleased(Hand.kRight) == true)
			m_arm.set(ControlMode.PercentOutput, 0);
		
		Timer.delay(0.005); // wait for a motor update time
	}
	@SuppressWarnings("deprecation")
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}