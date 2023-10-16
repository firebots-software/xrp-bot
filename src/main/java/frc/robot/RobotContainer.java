// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.DriveJoystickCmd;
import frc.robot.commands.DriveToDistanceCmd;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Drivetrain m_xrpDrivetrain = new Drivetrain();
  private final Arm m_arm = new Arm();

  
  // OI
  private Joystick driverPS4;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    driverPS4 = new Joystick(0);
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link edu.wpi.first.wpilibj.GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    m_xrpDrivetrain.setDefaultCommand(new DriveJoystickCmd(m_xrpDrivetrain, () -> -driverPS4.getRawAxis(1), () -> driverPS4.getRawAxis(0)));
    
    final JoystickButton move18 = new JoystickButton(driverPS4, 1);
    move18.whileTrue(new DriveToDistanceCmd(m_xrpDrivetrain, 18));

    final JoystickButton armDown = new JoystickButton(driverPS4, 2);
    armDown
      .onTrue(new InstantCommand(() -> {m_arm.setAngle(10d);}))
      .onFalse(new InstantCommand(() -> {m_arm.setAngle(90d);}));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new WaitCommand(1);
  }
}
