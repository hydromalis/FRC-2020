package frc4536.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc4536.robot.Constants;
import frc4536.robot.subsystems.Conveyor;
import frc4536.robot.subsystems.Intake;

import java.util.function.BooleanSupplier;

public class IntakeCommands extends CommandBase {
    private final Intake m_intake;
    private final Conveyor m_conveyor;
    private final BooleanSupplier m_isReversed;

  public IntakeCommands(Intake intake, Conveyor conveyor, BooleanSupplier reversed) {
    m_intake = intake;
    m_conveyor = conveyor;
    m_isReversed = reversed;
    addRequirements(intake,conveyor);
  }

  public IntakeCommands(Intake intake, Conveyor conveyor){
      this(intake, conveyor, () -> false);
  }

  @Override
  public void execute() {
      m_intake.intake((m_isReversed.getAsBoolean() ? -1 : 1) * Constants.INTAKE_SPINSPEED);
      m_intake.extendIntake();
      m_conveyor.moveConveyor(Constants.CONVEYOR_INTAKE_SPEED, false);
      m_conveyor.raiseTop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
    public void end(boolean a){
      System.out.println("Intake Command Finished");
  }

  @Override
    public String getName(){
      return "Intake Command";
  }
}