// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.Indexer;

import com.revrobotics.spark.SparkMax;

import org.littletonrobotics.junction.Logger;

import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Indexer extends SubsystemBase {
  private SparkMax indexerMotor;

  private IndexerState currentState = IndexerState.OFF;
  /** Creates a new Indexer. */
  public Indexer() {
    indexerMotor = new SparkMax(IndexerConstants.kMotorId, MotorType.kBrushless);
  }

  public void setGoal(IndexerState desiredState) {
    currentState = desiredState;
    switch (desiredState) {
      case INDEX:
        indexerMotor.set(IndexerConstants.kMotorSpeed);
        break;
      case OUTDEX:
        indexerMotor.set(-IndexerConstants.kMotorSpeed);
        break;
      case OFF:
        indexerMotor.stopMotor();
        break;
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    logMotorData();
  }

  void logMotorData() {
    Logger.recordOutput("Subsystems/Indexer/IndexerState", currentState.name());

    //Logger.recordOutput("Subsystems/Indexer/IndexerSpeed", indexerMotor.get()); //not sure if this is correct
    //REV does not have an obvious way to get the supply current, stator current, or motor voltage
  }
}
