package io.tesla.lifecycle.profiler;

import org.apache.maven.plugin.MojoExecution;

public class MojoProfile extends Profile implements ArtifactProfile {

  private MojoExecution mojoExecution;

  protected MojoProfile(MojoExecution mojoExecution) {
    super(new Timer());
    this.mojoExecution = mojoExecution;
  }

  public String getName() {
    return mojoExecution.getArtifactId();
  }

  public String getGroupId() {
    return mojoExecution.getGroupId();
  }

  public String getVersion() {
    return mojoExecution.getVersion();
  }

  public String getExecutionId() {
    return mojoExecution.getExecutionId();
 }

  public String getId() {
    return getGroupId() + ":" + getName() + ":" + getVersion() + " (" + mojoExecution.getExecutionId() + ")";
  }
}
