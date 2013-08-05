package io.tesla.lifecycle.profiler;

import java.util.ArrayList;
import java.util.List;

import org.apache.maven.project.MavenProject;

public class ProjectProfile extends Profile implements ArtifactProfile {

  private MavenProject project;
  private List<PhaseProfile> phaseProfiles;
  private ArrayList<MojoProfile> mojoProfiles;

  public ProjectProfile(MavenProject project) {
    super(new Timer());
    this.project = project;
    this.phaseProfiles = new ArrayList<PhaseProfile>();
    this.mojoProfiles = new ArrayList<MojoProfile>();
  }

  public void addPhaseProfile(PhaseProfile phaseProfile) {
    phaseProfiles.add(phaseProfile);
  }

  public void addMojoProfile(MojoProfile mojoProfile) {
    mojoProfiles.add(mojoProfile);
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

  public List<MojoProfile> getMojoProfiles() {
    return mojoProfiles;
  }
}
