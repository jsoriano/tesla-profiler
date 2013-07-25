package io.tesla.lifecycle.profiler;

import java.io.OutputStream;
import java.io.PrintStream;

public class SessionProfileRenderer {
  private PrintStream output;

  public SessionProfileRenderer() {
    this.output = System.out;
  }

  public SessionProfileRenderer(OutputStream output) {
    this.output = new PrintStream(output);
  }

  public void render(SessionProfile sessionProfile) {
    StringBuilder sb = new StringBuilder();
    for(ProjectProfile pp : sessionProfile.getProjectProfiles()) {
      sb.append(pp.getProjectName() + "\n");
      for(PhaseProfile phaseProfile : pp.getPhaseProfile()) {
        sb.append("  " + phaseProfile.getPhase() + " " + Timer.formatTime(phaseProfile.getElapsedTime()) + "\n");
        for(MojoProfile mp : phaseProfile.getMojoProfiles()) {
          sb.append("    " + mp.getId() + Timer.formatTime(mp.getElapsedTime()) + "\n");
        }
      }
      render(sb.toString());
    }
  }

  private void render(String s) {
    output.println(s);
  }
}
