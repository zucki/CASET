package view.gui;

import model.data.GlossaryEntry;
import model.data.Project;
import model.data.Specification;
import model.data.SpecificationTypeEnum;

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
	protected Shell _shell;
	private TabFolder _projectTabFolder;
	private ControllerInterface _controller;
	private MenuItem _mntmCloseProject;
	private MenuItem _mntmNewProject;
	private MenuItem _mntmOpenProject;
	private MenuItem _mntmSaveProject;
	private MenuItem _mntmSaveAll;
	private MenuItem _mntmExit;

	/**
	 * @wbp.parser.entryPoint
	 */
	public MainWindow(ControllerInterface controller) {
		this._controller = controller;
	}
	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		setListeners();
		_shell.open();
		_shell.layout();
		while (!_shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		_shell = new Shell();
		_shell.setSize(1000, 800);
		_shell.setText("CASET");
		_shell.setMaximized(true);
		_shell.setLayout(new GridLayout(1, false));
		
		Menu menu = new Menu(_shell, SWT.BAR);
		_shell.setMenuBar(menu);
		
		MenuItem mntmFile_1 = new MenuItem(menu, SWT.CASCADE);
		mntmFile_1.setText("File");
		
		Menu menu_1 = new Menu(mntmFile_1);
		mntmFile_1.setMenu(menu_1);
		
		_projectTabFolder = new TabFolder(_shell, SWT.NONE);
		_projectTabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		_mntmNewProject = new MenuItem(menu_1, SWT.NONE);
		_mntmNewProject.setText("New Project");
		
		_mntmOpenProject = new MenuItem(menu_1, SWT.NONE);
		_mntmOpenProject.setText("Open Project");
		
		new MenuItem(menu_1, SWT.SEPARATOR);
		
		_mntmSaveProject = new MenuItem(menu_1, SWT.NONE);
		_mntmSaveProject.setText("Save Project");
		
		_mntmSaveAll = new MenuItem(menu_1, SWT.NONE);
		_mntmSaveAll.setText("Save All");
		
		new MenuItem(menu_1, SWT.SEPARATOR);
		
		_mntmCloseProject = new MenuItem(menu_1, SWT.NONE);
		_mntmCloseProject.setText("Close Project");
		
		_mntmExit = new MenuItem(menu_1, SWT.NONE);
		_mntmExit.setText("Exit");
	}
	
	private void setListeners() {
		_mntmNewProject.addSelectionListener(_controller.createProject());
		_mntmCloseProject.addSelectionListener(_controller.removeSelectedProject());
		_mntmExit.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				_shell.close();
			}
		});
	}
	
	public ProjectComposite createProject(String projectName) {
		TabItem item = new TabItem(this._projectTabFolder, SWT.NONE);
		ProjectComposite c = new ProjectComposite(this._projectTabFolder, SWT.NONE, projectName, _controller);
		item.setText(projectName);
		item.setControl(c);
		this._projectTabFolder.setSelection(item);
		return c;
	}
	
	public void removeSelectedProject() {
		for(int i = 0; i < _projectTabFolder.getSelection().length; ++i) {
			_projectTabFolder.getSelection()[i].dispose();
		}
	}
	
	public ProjectComposite getProjectComposite(String projectName) {
		for (int i = 0; i < _projectTabFolder.getItemCount(); ++i) {
			ProjectComposite pc = (ProjectComposite)_projectTabFolder.getItem(i).getControl();
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
		return (ProjectComposite)(this._projectTabFolder.getSelection()[0].getControl());
	}
	
	public void showGlossaryChanges() {
		getSelectedProjectComposite().showGlossaryChanges();
	}
	
	public void showProjectNameValidity(boolean valid) {
		getSelectedProjectComposite().showProjectNameValidity(valid);
	}
	
	public void changeProjectName(String newName) {
		this._projectTabFolder.getSelection()[0].setText(newName);
		getSelectedProjectComposite().setProjectName(newName);
	}
	
	public Specification getSpecification() {
		return getSelectedProjectComposite().getSpecification();
	}
	
	public Specification getSelectedSpecification() {
		return getSelectedProjectComposite().getSelectedSpecification();
	}
	
	public Shell getShell() {
		return this._shell;
	}
	
	public void showSpecificationChanges() {
		getSelectedProjectComposite().refreshSpecifications();
	}
	
	public SpecificationTypeEnum getSpecificationType() {
		return this.getSelectedProjectComposite().getSelectedSpecificationType();
	}
}
