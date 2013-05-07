package controller;

import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;

import view.ViewInterface;

public interface ControllerInterface {
	public void setView(ViewInterface view);
	public SelectionAdapter createProject();
	public SelectionAdapter removeSelectedProject();
	public SelectionAdapter changeGlossaryEntry();
	public SelectionAdapter createGlossaryEntry();
	public SelectionAdapter removeGlossaryEntry();
	public ModifyListener changeProjectName();
}
