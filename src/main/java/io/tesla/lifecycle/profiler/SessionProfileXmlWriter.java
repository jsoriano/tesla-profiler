package io.tesla.lifecycle.profiler;

import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class SessionProfileXmlWriter extends SessionProfileFileWriter {
  DocumentBuilder builder;

  public SessionProfileXmlWriter() throws ParserConfigurationException {
    super();
    initDocumentBuilder();
  }

  public SessionProfileXmlWriter(OutputStream output) throws ParserConfigurationException {
    super(output);
    initDocumentBuilder();
  }

  public SessionProfileXmlWriter(String filename) throws ParserConfigurationException {
    super(filename);
    initDocumentBuilder();
  }

  private void initDocumentBuilder() throws ParserConfigurationException {
    DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
    builder = builderFactory.newDocumentBuilder();
  }

  private void render(Document document) {
    try {
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
      DOMSource source = new DOMSource(document);
      StreamResult result = new StreamResult(getOutput());
      transformer.transform(source, result);
    } catch (TransformerException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  private static void addChildToElement(Document document, Element element, String childName, String value) {
    Element child = document.createElement(childName);
    child.appendChild(document.createTextNode(value));
    element.appendChild(child);
  }

  private void fillArtifactChildren(Document document, Element parent, ArtifactProfile artifactProfile) {
    addChildToElement(document, parent, "name", artifactProfile.getName());
    addChildToElement(document, parent, "groupId", artifactProfile.getGroupId());
    addChildToElement(document, parent, "version", artifactProfile.getVersion());
    addChildToElement(document, parent, "time", String.valueOf(((Profile)artifactProfile).getElapsedTime()));
    addChildToElement(document, parent, "id", artifactProfile.getId());
  }

  private Element renderMojo(Document document, MojoProfile mojoProfile) {
    Element mojo = document.createElement("mojo");
    fillArtifactChildren(document, mojo, mojoProfile);
    addChildToElement(document, mojo, "executionId", mojoProfile.getExecutionId());
    return mojo;
  }

  private Element renderPhase(Document document, PhaseProfile phaseProfile) {
    Element phase = document.createElement("phase");
    addChildToElement(document, phase, "name", phaseProfile.getName());
    addChildToElement(document, phase, "time", String.valueOf(phaseProfile.getElapsedTime()));

    Element phases = document.createElement("mojos");
    for (MojoProfile mojoProfile : phaseProfile.getMojoProfiles()) {
        phases.appendChild(renderMojo(document, mojoProfile));
    }
    phase.appendChild(phases);

    return phase;
  }

  private Element renderProject(Document document, ProjectProfile projectProfile) {
    Element project = document.createElement("project");
    fillArtifactChildren(document, project, projectProfile);

    Element phases = document.createElement("phases");
    for (PhaseProfile phaseProfile : projectProfile.getPhaseProfile()) {
        phases.appendChild(renderPhase(document, phaseProfile));
    }
    project.appendChild(phases);

    return project;
  }

  @Override
  public void render(SessionProfile sessionProfile) {
    Document document = builder.newDocument();
    Element root = document.createElement("profile");

    Element session = document.createElement("session");
    addChildToElement(document, session, "time", String.valueOf(sessionProfile.getElapsedTime()));
    root.appendChild(session);

    Element projects = document.createElement("projects");
    for(ProjectProfile projectProfile : sessionProfile.getProjectProfiles()) {
      projects.appendChild(renderProject(document, projectProfile));
    }
    root.appendChild(projects);
    document.appendChild(root);
    render(document);
  }
}
