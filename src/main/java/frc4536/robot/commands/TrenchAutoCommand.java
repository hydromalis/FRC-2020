package frc4536.robot.commands;

import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc4536.robot.Constants;
import frc4536.robot.subsystems.Conveyor;
import frc4536.robot.subsystems.DriveTrain;
import frc4536.robot.subsystems.Intake;
import frc4536.robot.subsystems.Shooter;

public class TrenchAutoCommand extends SequentialCommandGroup {
  public TrenchAutoCommand(Shooter shooter, Conveyor conveyor, DriveTrain driveTrain, Intake intake, Trajectory startToShoot, Trajectory shootToEnd, Trajectory endToShoot) {
    addRequirements(shooter, conveyor, driveTrain, intake);
    addCommands(
            driveTrain.scurveTo(startToShoot).raceWith(new IntakeCommands(intake, conveyor)),
            new VisionToTargetCommand(driveTrain).raceWith(shooter.spinUp()),
            new ShootCommand(shooter, conveyor, 0.0).withTimeout(3),
            driveTrain.scurveTo(shootToEnd).raceWith(new IntakeCommands(intake, conveyor)),
            driveTrain.scurveTo(endToShoot),
            new VisionToTargetCommand(driveTrain).raceWith(shooter.spinUp()),
            new ShootCommand(shooter, conveyor, 0.0).withTimeout(3)
    );
  }

  @Override
  public String getName() {
    return "Trench Auton";
  }

}
