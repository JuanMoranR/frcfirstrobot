
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/


package org.usfirst.frc.team7077.robot;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {
	Joystick driverstick = new Joystick (0);
	
	Victor LeftFrontDrive = new Victor (0);
	Victor LeftRearDrive = new Victor (1);
	Victor RightFrontDrive = new Victor (2);
	Victor RightRearDrive = new Victor (3);
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
	
	}
	/**
	
	 * <p>You can add additional auto modes by adding additional comparisons to
	 * the switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		
	}
	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		
		}
	}
	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		double LeftStickValue = driverstick.getRawAxis(1);
		double RightStickValue = driverstick.getRawAxis(4);
		
		LeftFrontDrive.set(LeftStickValue);
		LeftRearDrive.set(LeftStickValue);
		RightFrontDrive.set(RightStickValue); 
		RightRearDrive.set(RightStickValue);
	}
	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}

/*
public class Robot extends IterativeRobot {
	private DifferentialDrive m_myRobot;
	private Joystick m_leftStick;
	private Joystick m_rightStick;

	public void robotInit() {
		m_myRobot = new DifferentialDrive(new Spark(0), new Spark(1));
		m_leftStick = new Joystick(0); //If tank drive with 2 joysticks, this is LEFT hand
		m_rightStick = new Joystick(1); //If tank drive with 2 joysticks, this is RIGHT hand
	}

	public void teleopPeriodic() {
		//Left stick controls left side, right stick controls right side. Only Y-axis moves robot, left and right does nothing.
		m_myRobot.tankDrive(m_leftStick.getY(), m_rightStick.getY());
	}
}
*/



