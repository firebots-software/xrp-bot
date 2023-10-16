package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;

public class DriveToDistanceCmd extends Command {
    private Drivetrain drivetrain;
    private double distance;

    private final double kP = 0;
    private final double kI = 0;

    private PIDController pidController;

    public DriveToDistanceCmd(Drivetrain drivetrain, double distance) {
        this.drivetrain = drivetrain;
        this.distance = distance;
        this.pidController = new PIDController(kP, kI, 0);
        
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        pidController.reset();
        pidController.setSetpoint(drivetrain.getLeftDistanceCm() + distance);
    }

    @Override
    public void execute() {
        double current = drivetrain.getLeftDistanceCm();
        double output = pidController.calculate(current);
        drivetrain.arcadeDrive(output, 0);

        SmartDashboard.putNumber("current", current);
        SmartDashboard.putNumber("error", pidController.getPositionError());
        SmartDashboard.putNumber("output", output);
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.arcadeDrive(0, 0);
    }

    @Override
    public boolean isFinished() {
        return pidController.atSetpoint();
    }
}
