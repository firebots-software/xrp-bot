package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.xrp.XRPServo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {
    private final XRPServo m_armServo;

    public Arm() {
        m_armServo = new XRPServo(4);
    }

    public void setAngle(double deg) {
        m_armServo.setAngle(deg);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("arm degree", m_armServo.getPosition());
    }
}
