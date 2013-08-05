package io.tesla.lifecycle.profiler;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class SessionProfileFileWriter implements SessionProfileRenderer {
  private String filename = "System.out";
  private PrintStream output = null;

  public SessionProfileFileWriter() {
  }

  public SessionProfileFileWriter(OutputStream output) {
    this.output = new PrintStream(output);
  }

  public SessionProfileFileWriter(String filename) {
    this.filename = filename;
  }

  public void render(SessionProfile sessionProfile) {
    StringBuilder sb = new StringBuilder();
    for(ProjectProfile pp : sessionProfile.getProjectProfiles()) {
      sb.append(pp.getId() + "\n");
      for(PhaseProfile phaseProfile : pp.getPhaseProfiles()) {
        sb.append("  " + phaseProfile.getPhase() + " " + Timer.formatTime(phaseProfile.getElapsedTime()) + "\n");
        for(MojoProfile mp : phaseProfile.getMojoProfiles()) {
          sb.append("    " + mp.getId() + Timer.formatTime(mp.getElapsedTime()) + "\n");
        }
      }
      for(GoalProfile goalProfile : pp.getGoalProfiles()) {
        sb.append("  " + goalProfile.getName() + " " + Timer.formatTime(goalProfile.getElapsedTime()) + "\n");
        for(MojoProfile mp : goalProfile.getMojoProfiles()) {
          sb.append("    " + mp.getId() + Timer.formatTime(mp.getElapsedTime()) + "\n");
        }
      }
      render(sb.toString());
    }
  }

  protected PrintStream getOutput() {
    if (output == null) {
      if (filename.equals("") || filename.equals("System.out")) {
        output = System.out;
      } else if (filename.equals("System.err")) {
        output = System.err;
      } else {
        try {
          output = new PrintStream(new FileOutputStream(filename));
        } catch (FileNotFoundException e) {
          e.printStackTrace();
        }
      }
    }
    return output;
  }

  protected void render(String s) {
    getOutput().println(s);
  }
}
