package view.gui;

import model.data.Project;
import model.data.ProjectFieldEnum;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;

import controller.ControllerInterface;

public class ProjectUseComposite extends Composite {
	private StyledText _styledText;
	private ControllerInterface _controller;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ProjectUseComposite(Composite parent, int style, ControllerInterface controller) {
		super(parent, style);
		setLayout(new GridLayout(1, false));
		this._controller = controller;
		
		
		_styledText = new StyledText(this, SWT.BORDER);
		_styledText.addModifyListener(controller.changeProjectField(ProjectFieldEnum.ProjectUse));
		_styledText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

	}
	
	public String getProjectUse() {
		return _styledText.getText();
	}

	public void setProjectUse(String use) {
		_styledText.setText(use);
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
