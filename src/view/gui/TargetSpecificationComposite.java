package view.gui;

import model.data.Project;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;

public class TargetSpecificationComposite extends Composite {
	private StyledText styledText;
	private Project project;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public TargetSpecificationComposite(Composite parent, int style, Project project) {
		super(parent, style);
		setLayout(new GridLayout(1, false));
		this.project = project;
		
		styledText = new StyledText(this, SWT.BORDER);
		styledText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
