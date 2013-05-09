package controller;

import model.ModelInterface;
import model.data.ProjectField;

import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;

import view.ViewInterface;

public class ModifyFieldListener implements ModifyListener {
	private ProjectField field;
	private ModelInterface model;
	private ViewInterface view;

	public ModifyFieldListener(ModelInterface model, ViewInterface view, ProjectField field) {
		this.field = field;
		this.model = model;
		this.view = view;
	}
	
	@Override
	public void modifyText(ModifyEvent arg0) {
		String projectName = view.getSelectedProject();
		String value = view.getData(projectName, field);
		model.changeProjectField(projectName, field, value);
	}

}
