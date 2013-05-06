package view.gui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.TabItem;

public class ProjectComposite extends Composite {
	private TabItem tabItem;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ProjectComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(1, false));
		
		TabFolder tabFolder = new TabFolder(this, SWT.BOTTOM);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		TabItem tbtmProject = new TabItem(tabFolder, SWT.NONE);
		tbtmProject.setText("Project Settings");
		tbtmProject.setControl(new ProjectSettingsComposite(tabFolder, SWT.None));
		
		TabItem tbtmProjectUse = new TabItem(tabFolder, SWT.NONE);
		tbtmProjectUse.setText("Project Use");
		tbtmProjectUse.setControl(new ProjectUseComposite(tabFolder, SWT.None));
		
		TabItem tbtmTarget = new TabItem(tabFolder, SWT.NONE);
		tbtmTarget.setText("Target Specification");
		tbtmTarget.setControl(new TargetSpecificationComposite(tabFolder, SWT.None));
		
		TabItem tbtmSpecifications = new TabItem(tabFolder, SWT.NONE);
		tbtmSpecifications.setText("Specifications");
		tbtmSpecifications.setControl(new SpecificationsComposite(tabFolder, SWT.None));
		
		TabItem tbtmGlossary = new TabItem(tabFolder, SWT.NONE);
		tbtmGlossary.setText("Glossary");
		tbtmGlossary.setControl(new GlossaryComposite(tabFolder, SWT.None));

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
