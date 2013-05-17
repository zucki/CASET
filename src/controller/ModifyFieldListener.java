package controller;

import model.ModelInterface;
import model.data.ProjectFieldEnum;

import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;

import view.ViewInterface;

/**
 * A flexible common listener for all project fields, that changes the field,
 * when the content of the control it is given to gets modified.
 * It is a part of the controller.
 * @author smgug_000
 *
 */
public class ModifyFieldListener implements ModifyListener {
	private ProjectFieldEnum _field;
	private ModelInterface _model;
	private ViewInterface _view;

	/**
	 * Constructor that takes a projectfield, the model and the view. this
	 * listener is a part of the controller
	 * @param model implementation of ModelInterface
	 * @param view implementation of ViewInterface
	 * @param field the field the control, that gets this listener, belongs to
	 */
	public ModifyFieldListener(ModelInterface model, ViewInterface view, ProjectFieldEnum field) {
		this._field = field;
		this._model = model;
		this._view = view;
	}
	
	@Override
	public void modifyText(ModifyEvent arg0) {
		String projectName = _view.getSelectedProject();
		String value = _view.getData(projectName, _field);
		_model.changeProjectField(projectName, _field, value);
	}

}
