// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.Intake;

import org.littletonrobotics.junction.Logger;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  private SparkMax intakeMotor;

  private IntakeState currentState = IntakeState.OFF;
  /** Creates a new Intake. */
  public Intake() {
    intakeMotor = new SparkMax(IntakeConstants.kMotorId, MotorType.kBrushless);
  }

  public void setGoal(IntakeState desiredState) {
    currentState = desiredState;
    switch (desiredState) {
      case OFF:
        intakeMotor.stopMotor();
        break;
      case INTAKE:
        intakeMotor.set(IntakeConstants.kMotorSpeed);
        break;
      case OUTTAKE:
        intakeMotor.set(-IntakeConstants.kMotorSpeed);
        break;
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    logMotorData();
  }

  void logMotorData() {
    Logger.recordOutput("Subsystems/Intake/IntakeState", currentState.name());

    //Logger.recordOutput("Subsystems/Intake/IntakeSpeed", intakeMotor.get()); //not sure if this is correct
    //REV does not have an obvious way to get the supply current, stator current, or motor voltage
  }
}
