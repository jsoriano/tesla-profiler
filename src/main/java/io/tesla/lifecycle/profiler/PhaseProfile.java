package io.tesla.lifecycle.profiler;

import java.util.ArrayList;
import java.util.List;

public class PhaseProfile extends AbstractMojoParent {

  private String phase;
  private List<MojoProfile> mojoProfiles;

  public PhaseProfile(String phase) {
    super(new Timer());
    this.phase = phase;
    this.mojoProfiles = new ArrayList<MojoProfile>();
  }

  public void addMojoProfile(MojoProfile mojoProfile) {
    mojoProfiles.add(mojoProfile);
  }

  public String getPhase() {
    return phase;
  }

  public List<MojoProfile> getMojoProfiles() {
    return mojoProfiles;
  }

  public String getName() {
    return phase;
  }

  public void addToProjectProfile(ProjectProfile projectProfile) {
    projectProfile.addPhaseProfile(this);
  }
}
