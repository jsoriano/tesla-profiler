package io.tesla.lifecycle.profiler;

import java.util.List;

public interface MojoParent {
  public String getName();
  public void addMojoProfile(MojoProfile mojoProfile);
  public List<MojoProfile> getMojoProfiles();
  public void addToProjectProfile(ProjectProfile projectProfile);
}
