package io.tesla.lifecycle.profiler;

import org.apache.maven.plugin.MojoExecution;

public class MojoParentFactory {
  public static AbstractMojoParent getMojoParent(MojoExecution mojoExecution) {
    String phase = mojoExecution.getLifecyclePhase();
    if (phase != null) {
      return new PhaseProfile(phase);
    }

    String goal = mojoExecution.getGoal();
    if (goal != null) {
      return new GoalProfile(goal);
    }

    throw new IllegalArgumentException("Mojo execution out of goals and phases?");
  }
}
