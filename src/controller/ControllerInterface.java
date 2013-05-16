package controller;

import model.data.GlossaryFieldEnum;
import model.data.InfluencingFactorTypeEnum;
import model.data.ProjectFieldEnum;
import model.data.SpecificationFieldEnum;

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
	public ModifyListener changeProjectField(ProjectFieldEnum field);
	ModifyListener changeGlossaryEntry(GlossaryFieldEnum field);
	public ModifyListener changeSpecification(SpecificationFieldEnum field);
	public ModifyListener changeInfluencingFactor(InfluencingFactorTypeEnum field);
}
