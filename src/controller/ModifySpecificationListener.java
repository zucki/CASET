package controller;

import model.ModelInterface;
import model.data.SpecificationFieldEnum;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;

import view.ViewInterface;

/**
 * A flexible common listener for all specifications, that changes the field,
 * when the content of the control it is given to gets modified.
 * It is a part of the controller.
 * @author smgug_000
 *
 */
public class ModifySpecificationListener implements ModifyListener {

	private SpecificationFieldEnum _field;
	private ModelInterface _model;
	private ViewInterface _view;
	
	/**
	 * Constructor that takes a specificationtype, the model and the view. this
	 * listener is a part of the controller
	 * @param model implementation of ModelInterface
	 * @param view implementation of ViewInterface
	 * @param field the field the control, that gets this listener, belongs to
	 */
	public ModifySpecificationListener(ModelInterface model, ViewInterface view, SpecificationFieldEnum field) {
		this._field = field;
		this._model = model;
		this._view = view;
	}

	@Override
	public void modifyText(ModifyEvent arg0) {
		String projectName = _view.getSelectedProject();
		_model.changeSpecificationField(projectName, _view.getSelectedSpecification(), 
				_field, _view.getData(projectName, _field));
		_view.showSpecificationChanges();
	}
}
