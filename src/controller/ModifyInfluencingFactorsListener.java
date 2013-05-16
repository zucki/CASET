package controller;

import model.ModelInterface;
import model.data.InfluencingFactorType;
import model.data.SpecificationField;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;

import view.ViewInterface;

public class ModifyInfluencingFactorsListener implements ModifyListener {

	private InfluencingFactorType field;
	private ModelInterface model;
	private ViewInterface view;
	
	public ModifyInfluencingFactorsListener(ModelInterface model, ViewInterface view, InfluencingFactorType field) {
		this.field = field;
		this.model = model;
		this.view = view;
	}
	
	@Override
	public void modifyText(ModifyEvent arg0) {
		String projectName = view.getSelectedProject();
		String value = view.getdata(projectName, field);
		model.changeInflencingFactorField(projectName, field, value.length()==0?"0":value);
	}

}
