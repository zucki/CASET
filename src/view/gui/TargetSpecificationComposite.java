package view.gui;

import model.data.Project;
import model.data.ProjectFieldEnum;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;

import controller.ControllerInterface;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;

public class TargetSpecificationComposite extends Composite {
	private StyledText styledText;
	private ControllerInterface controller;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public TargetSpecificationComposite(Composite parent, int style, ControllerInterface controller) {
		super(parent, style);
		setLayout(new GridLayout(1, false));
		this.controller = controller;
		
		styledText = new StyledText(this, SWT.BORDER);
		styledText.addModifyListener(controller.changeProjectField(ProjectFieldEnum.TargetSpecification));
		styledText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	public String getTargetSpecification() {
		return styledText.getText();
	}
	
	public void setTargetSpecification(String spec) {
		styledText.setText(spec);
	}
}
