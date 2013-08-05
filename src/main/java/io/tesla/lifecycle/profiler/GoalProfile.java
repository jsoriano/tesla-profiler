package io.tesla.lifecycle.profiler;

import java.util.ArrayList;
import java.util.List;

public class GoalProfile extends AbstractMojoParent {
  private String goal;
  private List<MojoProfile> mojoProfiles;

  public GoalProfile(String phase) {
    super(new Timer());
    this.goal = phase;
    this.mojoProfiles = new ArrayList<MojoProfile>();
  }

  public void addMojoProfile(MojoProfile mojoProfile) {
    mojoProfiles.add(mojoProfile);
  }

  public String getGoal() {
    return goal;
  }

  public List<MojoProfile> getMojoProfiles() {
    return mojoProfiles;
  }

  public String getName() {
    return goal;
  }

  public void addToProjectProfile(ProjectProfile projectProfile) {
    projectProfile.addGoalProfile(this);
  }
}
