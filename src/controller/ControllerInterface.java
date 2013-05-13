package controller;

import model.data.GlossaryField;
import model.data.ProjectField;
import model.data.SpecificationField;

import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;

import view.ViewInterface;

public interface ControllerInterface {
	public void setView(ViewInterface view);
	public SelectionAdapter createProject();
	public SelectionAdapter removeSelectedProject();
	public SelectionAdapter createGlossaryEntry();
	public SelectionAdapter removeGlossaryEntry();
	public SelectionAdapter createSpecification();
	public SelectionAdapter removeSpecification();
	public ModifyListener changeProjectName();
	public ModifyListener changeProjectField(ProjectField field);
	ModifyListener changeGlossaryEntry(GlossaryField field);
	public ModifyListener changeSpecification(SpecificationField field);
}
