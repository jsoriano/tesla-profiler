package io.tesla.lifecycle.profiler;

import java.util.ArrayList;
import java.util.List;

import org.apache.maven.project.MavenProject;

public class ProjectProfile extends Profile implements ArtifactProfile {

  private MavenProject project;
  private List<PhaseProfile> phaseProfiles;

  public ProjectProfile(MavenProject project) {
    super(new Timer());
    this.project = project;
    this.phaseProfiles = new ArrayList<PhaseProfile>();
  }

  public void addPhaseProfile(PhaseProfile phaseProfile) {
    phaseProfiles.add(phaseProfile);
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

  public List<PhaseProfile> getPhaseProfile() {
    return phaseProfiles;
  }
}
