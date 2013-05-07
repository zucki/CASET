package view.gui;

import model.data.GlossaryEntry;
import model.data.Project;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.TabItem;

import controller.Controller;
import controller.ControllerInterface;

public class ProjectComposite extends Composite {
	private TabItem tabItem;
	private Project project;
	private GlossaryComposite glossaryComposite;
	private ControllerInterface controller;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ProjectComposite(Composite parent, int style, Project project, ControllerInterface controller) {
		super(parent, style);
		setLayout(new GridLayout(1, false));
		this.project = project;
		this.controller = controller;
		
		TabFolder tabFolder = new TabFolder(this, SWT.BOTTOM);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		TabItem tbtmProject = new TabItem(tabFolder, SWT.NONE);
		tbtmProject.setText("Project Settings");
		tbtmProject.setControl(new ProjectSettingsComposite(tabFolder, SWT.None, project));
		
		TabItem tbtmProjectUse = new TabItem(tabFolder, SWT.NONE);
		tbtmProjectUse.setText("Project Use");
		tbtmProjectUse.setControl(new ProjectUseComposite(tabFolder, SWT.None, project));
		
		TabItem tbtmTarget = new TabItem(tabFolder, SWT.NONE);
		tbtmTarget.setText("Target Specification");
		tbtmTarget.setControl(new TargetSpecificationComposite(tabFolder, SWT.None, project));
		
		TabItem tbtmSpecifications = new TabItem(tabFolder, SWT.NONE);
		tbtmSpecifications.setText("Specifications");
		tbtmSpecifications.setControl(new SpecificationsComposite(tabFolder, SWT.None, project));
		
		TabItem tbtmGlossary = new TabItem(tabFolder, SWT.NONE);
		tbtmGlossary.setText("Glossary");
		this.glossaryComposite = new GlossaryComposite(tabFolder, SWT.None, project, controller);
		tbtmGlossary.setControl(this.glossaryComposite);

	}
	
	public GlossaryEntry getSelectedGlossaryEntry() {
		return this.glossaryComposite.getSelectedGlossaryEntry();
	}
	
	public void showGlossaryChanges() {
		this.glossaryComposite.refresh();
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
