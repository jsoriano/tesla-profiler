package io.tesla.lifecycle.profiler;

public abstract class AbstractMojoParent extends Profile implements MojoParent {
  protected AbstractMojoParent(Timer timer) {
    super(timer);
  }
}
