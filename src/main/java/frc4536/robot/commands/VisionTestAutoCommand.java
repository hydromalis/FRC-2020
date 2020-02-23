package frc4536.robot.commands;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc4536.robot.Constants;
import frc4536.robot.subsystems.Conveyor;
import frc4536.robot.subsystems.DriveTrain;
import frc4536.robot.subsystems.Intake;
import frc4536.robot.subsystems.Shooter;

import java.util.ArrayList;

public class VisionTestAutoCommand extends SequentialCommandGroup {

  public VisionTestAutoCommand(Shooter shooter, Conveyor conveyor, DriveTrain driveTrain, Intake intake, Trajectory startToShoot) {
    addRequirements(shooter, conveyor, driveTrain, intake);
    addCommands(
      driveTrain.scurveTo(startToShoot),
      new VisionToTargetCommand(driveTrain).raceWith(shooter.spinUp(() -> Constants.SHOOTER_RPS_TOP, () -> Constants.SHOOTER_RPS_BOTTOM)),
      new ShootCommand(shooter, conveyor)
    );
  }

  @Override
  public String getName() {
    return "Vision Test Auton";
  }
}


