/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team7077.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.*;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

//PLEASE READ!!!! 11.2-12 if We 4 batteries otherwise do 11.6-12
public class Robot extends IterativeRobot {
	
	public static DifferentialDrive m_myRobot;
	public XboxController m_controller;
	public Joystick m_Right;
	public Joystick m_Left;
	//Timers
	Timer autoTime;	//Timer for the Autonomous
	Timer oneEightyTime;	//Timer for testing the 
	
	
	//Ports 
	//Ports are intergers 0 is the right most port whilst 1 is right next to the 0 port.
	private static final int kMotorPortLeft = 0; 
	private static final int kMotorPortRight = 1; 
	
	public static final int kControllerPort = 0; //Change this integer to which ever the port the controller is plugged into (0-9)
	
	//Motors for the Left and Right Drivers.
	Spark m_leftDrive = new Spark(kMotorPortLeft);	//Spark Motors for the Left Wheels
	Spark m_rightDrive = new Spark(kMotorPortRight); //Spark Motors for the Right Wheels
	
	
	//JOYSTICKS

	
	//Doubles for Accelerometers.
	double LDTMotor;
	double RDTMotor;

	
	public void robotInit() {
		//Spark 0 and 1 are drive motors, 0 is the 2 left side 1 is the 2 right side.
		//Spark 0 and 1 are drive motors, 0 is the 2 left side 1 is the 2 right side.
				m_myRobot = new DifferentialDrive(m_leftDrive, m_rightDrive);	//Calls both the "left" and "Right" motors for the myRobot function upon Robot Initiation
				m_controller = new XboxController(kControllerPort); //Call This when using a Controller.
				m_Right = new Joystick(3);	// Right Joystick port goes into the 3rd Port in the Driver Station
				m_Left = new Joystick(4);	// Left Joystick Port goes into the 4th Port in the Driver Station
	}
	

	@Override
	public void autonomousInit() {
		
		autoTime = new Timer();
		
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		if (autoTime.get()<=.9965) {
			m_myRobot.tankDrive(-1, -1);
		}
		else if (autoTime.get()==1.1) {
			m_myRobot.tankDrive(0, 0);
		}
		
		
		
		//NOTES FOR AUTONOMOUS
//		/*if (timer.get()<=1) {
//			m_myRobot.tankDrive(-1, -1);	// 1,1 is backwards so -1,-1 should be forwards
//			m_Lintake.set(-1);
//			m_Rintake.set(1);
//		}
//		else if (timer.get()==1.1) {
//			m_myRobot.tankDrive(0,0);
//		}
//		else if (timer.get()<=1.8) {
//			m_myRobot.tankDrive(-1,1);
//			m_Lintake.set(-1);
//			m_Rintake.set(1);
//		}
//		else if (timer.get()==1.9) {
//			m_myRobot.tankDrive(0,0);
//		
//		//else if (timer.get()<=.5) {
//		//m_myRobot.tankDrive(0, 0);
//		//.9965 is the time it takes for a full 360 turn		
//		//}
//		
//		else if (timer.get()<=2) {
//			m_Lintake.set(-1);
//			m_Rintake.set(1);
//			m_myRobot.tankDrive(-1, -1);
//		}
//		
//		else if (timer.get()<=3) {
//			m_myRobot.tankDrive(-.7, -.7);
//			m_Lintake.set(1);
//			m_Rintake.set(-1);
//			
//			
//		}
//		else if (timer.get()<=4) {
//			m_Lintake.set(-1);
//			m_Rintake.set(1);
//		}
//		
//		else if (timer.get()<=15){
//			//m_Rintake.set(-1);
//			//m_Lintake.set(1);
//			m_myRobot.tankDrive(0, 0);
//		}
//		*/
//		
//		if (timer.get() <=1) {
//			m_myRobot.tankDrive(1,-1);
//		}
//		else {
//			m_myRobot.tankDrive(0,0);
//		}
//		Timer.delay(0.005);
	}
	@Override
	public void teleopInit() {
	}
	@Override
	public void teleopPeriodic() {
		
		LDTMotor = m_controller.getY(Hand.kRight);
		RDTMotor = m_controller.getY(Hand.kLeft);
		
		m_myRobot.tankDrive(RDTMotor/1.3, LDTMotor/1.3);
		
		
//		if (m_controller.getBumperPressed(Hand.kLeft) == true)
//		{	
//			m_Lintake.set(1);
//			m_Rintake.set(-1);
//		}	
//		if (m_controller.getBumperReleased(Hand.kLeft) == true)
//		{
//			m_Lintake.set(0);
//			m_Rintake.set(0);
//		}	
//		if (m_controller.getBumperPressed(Hand.kRight) == true)
//		{
//		
//			m_Lintake.set(-1);
//			m_Rintake.set(1);
//		}
//		if (m_controller.getBumperReleased(Hand.kRight) == true)
//		{	
//			m_Lintake.set(0);
//			m_Rintake.set(0);
//		}	
		
		if (m_controller.getPOV()==0)
			m_myRobot.tankDrive(-0.8,-0.8);
		if (m_controller.getPOV()==180)
			m_myRobot.tankDrive(0.8, 0.8);
		if (m_controller.getPOV()==90)
			m_myRobot.tankDrive(-0.8, 0.8);
		if (m_controller.getPOV()==270)
			m_myRobot.tankDrive(0.8, -0.8);
		/*
		if (m_controller.getYButtonPressed() == true)
			m_arm.set(ControlMode.PercentOutput, -1);
		if (m_controller.getYButtonReleased() == true)
			m_arm.set(ControlMode.PercentOutput, 0);
		if (m_controller.getBButtonPressed() == true)
			m_arm.set(ControlMode.PercentOutput, 1);
		if (m_controller.getBButtonReleased() == true)
			m_arm.set(ControlMode.PercentOutput, 0);
		*/
		//m_arm.set(ControlMode.PercentOutput, m_controller.getX(Hand.kRight));
		//Timer.delay(0.005); // wait for a motor update time
	}
}