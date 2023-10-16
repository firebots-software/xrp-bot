package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;

public class DriveJoystickCmd extends Command {
    private Supplier<Double> forwardSupplier, rotationSupplier;
    private Drivetrain drivetrain;

    public DriveJoystickCmd(Drivetrain drivetrain, Supplier<Double> forwardSpeed, Supplier<Double> rotationSpeed) {
        this.drivetrain = drivetrain;
        this.forwardSupplier = forwardSpeed;
        this.rotationSupplier = rotationSpeed;

        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        drivetrain.arcadeDrive(forwardSupplier.get(), -rotationSupplier.get());
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return false;
    }
}
