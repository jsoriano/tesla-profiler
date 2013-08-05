package io.tesla.lifecycle.profiler;

import java.util.ArrayList;
import java.util.List;

import org.apache.maven.project.MavenProject;

public class ProjectProfile extends Profile implements ArtifactProfile {

  private MavenProject project;
  private List<PhaseProfile> phaseProfiles;
  private ArrayList<GoalProfile> goalProfiles;

  public ProjectProfile(MavenProject project) {
    super(new Timer());
    this.project = project;
    this.phaseProfiles = new ArrayList<PhaseProfile>();
    this.goalProfiles = new ArrayList<GoalProfile>();
  }

  public void addPhaseProfile(PhaseProfile phaseProfile) {
    phaseProfiles.add(phaseProfile);
  }

  public void addGoalProfile(GoalProfile goalProfile) {
    goalProfiles.add(goalProfile);
  }

  public String getVersion() {
    return project.getVersion();
  }

  public String getGroupId() {
    return project.getGroupId();
  }

  public String getId() {
    return getGroupId() + ":" + getName() + ":" + getVersion();
  }

  public String getName() {
    return project.getArtifactId();
  }

  public List<PhaseProfile> getPhaseProfiles() {
    return phaseProfiles;
  }

  public List<GoalProfile> getGoalProfiles() {
    return goalProfiles;
  }
}
