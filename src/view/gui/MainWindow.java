package view.gui;

import model.data.GlossaryEntry;
import model.data.Project;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import controller.Controller;
import controller.ControllerInterface;

public class MainWindow {
	protected Shell shell;
	private TabFolder projectTabFolder;
	private ControllerInterface controller;
	private MenuItem mntmCloseProject;
	private MenuItem mntmNewProject;
	private MenuItem mntmOpenProject;
	private MenuItem mntmSaveProject;
	private MenuItem mntmSaveAll;
	private MenuItem mntmExit;

	/**
	 * @wbp.parser.entryPoint
	 */
	public MainWindow(ControllerInterface controller) {
		this.controller = controller;
	}
	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		setListeners();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(623, 458);
		shell.setText("CASET");
		shell.setLayout(new GridLayout(1, false));
		
		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		
		MenuItem mntmFile_1 = new MenuItem(menu, SWT.CASCADE);
		mntmFile_1.setText("File");
		
		Menu menu_1 = new Menu(mntmFile_1);
		mntmFile_1.setMenu(menu_1);
		
		projectTabFolder = new TabFolder(shell, SWT.NONE);
		projectTabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		mntmNewProject = new MenuItem(menu_1, SWT.NONE);
		mntmNewProject.setText("New Project");
		
		mntmOpenProject = new MenuItem(menu_1, SWT.NONE);
		mntmOpenProject.setText("Open Project");
		
		new MenuItem(menu_1, SWT.SEPARATOR);
		
		mntmSaveProject = new MenuItem(menu_1, SWT.NONE);
		mntmSaveProject.setText("Save Project");
		
		mntmSaveAll = new MenuItem(menu_1, SWT.NONE);
		mntmSaveAll.setText("Save All");
		
		new MenuItem(menu_1, SWT.SEPARATOR);
		
		mntmCloseProject = new MenuItem(menu_1, SWT.NONE);
		mntmCloseProject.setText("Close Project");
		
		mntmExit = new MenuItem(menu_1, SWT.NONE);
		mntmExit.setText("Exit");
	}
	
	private void setListeners() {
		mntmNewProject.addSelectionListener(controller.createProject());
		mntmCloseProject.addSelectionListener(controller.removeSelectedProject());
		mntmExit.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shell.close();
			}
		});
	}
	
	public ProjectComposite createProject(String projectName) {
		TabItem item = new TabItem(this.projectTabFolder, SWT.NONE);
		ProjectComposite c = new ProjectComposite(this.projectTabFolder, SWT.NONE, projectName, controller);
		item.setText(projectName);
		item.setControl(c);
		this.projectTabFolder.setSelection(item);
		return c;
	}
	
	public void removeSelectedProject() {
		for(int i = 0; i < projectTabFolder.getSelection().length; ++i) {
			projectTabFolder.getSelection()[i].dispose();
		}
	}
	
	public ProjectComposite getProjectComposite(String projectName) {
		for (int i = 0; i < projectTabFolder.getItemCount(); ++i) {
			ProjectComposite pc = (ProjectComposite)projectTabFolder.getItem(i).getControl();
			if (pc.getProjectName().equals(projectName)) {
				return pc;
			}
		}
		return null;
	}
	
	public GlossaryEntry getSelectedGlossaryEntry() {
		return getSelectedProjectComposite().getSelectedGlossaryEntry();
	}
	
	public ProjectComposite getSelectedProjectComposite() {
		return (ProjectComposite)(this.projectTabFolder.getSelection()[0].getControl());
	}
	
	public void showGlossaryChanges() {
		getSelectedProjectComposite().showGlossaryChanges();
	}
	
	public void showProjectNameValidity(boolean valid) {
		getSelectedProjectComposite().showProjectNameValidity(valid);
	}
	
	public void changeProjectName(String newName) {
		this.projectTabFolder.getSelection()[0].setText(newName);
		getSelectedProjectComposite().setProjectName(newName);
	}
	
	public Shell getShell() {
		return this.shell;
	}
}
