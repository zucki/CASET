package controller;

import org.eclipse.swt.events.SelectionAdapter;

import view.ViewInterface;

public interface ControllerInterface {
	public void setView(ViewInterface view);
	public SelectionAdapter createProjectListener();
	public SelectionAdapter removeSelectedProject();
	public SelectionAdapter changeGlossaryEntry();
}
