package view.gui;

import model.data.Project;

import org.eclipse.swt.widgets.Composite;

public class SpecificationsComposite extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public SpecificationsComposite(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
