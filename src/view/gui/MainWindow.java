package view.gui;

import model.data.GlossaryEntry;
import model.data.Specification;
import model.data.SpecificationTypeEnum;
import model.services.calculation.CalculationMethod;
import model.services.calculation.CalculationResults;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

import controller.ControllerInterface;

/**
 * The application window
 * @author smgug_000
 *
 */
public class MainWindow {
	protected Shell _shell;
	private TabFolder _projectTabFolder;
	private ControllerInterface _controller;
	private MenuItem _mntmCloseProject;
	private MenuItem _mntmNewProject;
	private MenuItem _mntmOpenProject;
	private MenuItem _mntmExportProject;
	private MenuItem _mntmExit;
	private CalculationDialog _calcDialog;

	/**
	 * creates a new mainwindow
	 */
	public MainWindow(ControllerInterface controller) {
		this._controller = controller;
	}
	/**
	 * Open the window
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
	 * @wbp.parser.entryPoint
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
		
		_mntmExportProject = new MenuItem(menu_1, SWT.NONE);
		_mntmExportProject.addSelectionListener(_controller.exportProjectAsXml());
		_mntmExportProject.setText("Export Project");
		
		new MenuItem(menu_1, SWT.SEPARATOR);
		
		_mntmCloseProject = new MenuItem(menu_1, SWT.NONE);
		_mntmCloseProject.setText("Close Project");
		
		_mntmExit = new MenuItem(menu_1, SWT.NONE);
		_mntmExit.setText("Exit");
		
		MenuItem mntmCalculation = new MenuItem(menu, SWT.CASCADE);
		mntmCalculation.setText("Calculation");
		
		Menu menu_2 = new Menu(mntmCalculation);
		mntmCalculation.setMenu(menu_2);
		
		MenuItem mntmCalculate = new MenuItem(menu_2, SWT.NONE);
		mntmCalculate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				_calcDialog = new CalculationDialog(_shell, SWT.NONE, _controller);
				_calcDialog.open();
				_calcDialog = null;
			}
		});
		mntmCalculate.setText("Calculate");
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
	
	/**
	 * Creates a new project in the mainwindow
	 * @param projectName name of the new project
	 * @return the projectcomposite that was created
	 */
	public ProjectComposite createProject(String projectName) {
		TabItem item = new TabItem(this._projectTabFolder, SWT.NONE);
		ProjectComposite c = new ProjectComposite(this._projectTabFolder, SWT.NONE, projectName, _controller);
		item.setText(projectName);
		item.setControl(c);
		this._projectTabFolder.setSelection(item);
		return c;
	}
	
	/**
	 * removes all selected projects from the maindwindow
	 */
	public void removeSelectedProject() {
		for(int i = 0; i < _projectTabFolder.getSelection().length; ++i) {
			_projectTabFolder.getSelection()[i].dispose();
		}
	}
	
	/**
	 * @param projectName name of the project
	 * @return projectcomposite of a project
	 */
	public ProjectComposite getProjectComposite(String projectName) {
		for (int i = 0; i < _projectTabFolder.getItemCount(); ++i) {
			ProjectComposite pc = (ProjectComposite)_projectTabFolder.getItem(i).getControl();
			if (pc.getProjectName().equals(projectName)) {
				return pc;
			}
		}
		return null;
	}
	
	/**
	 * @return the currently selected glossaryentry in the selected project
	 */
	public GlossaryEntry getSelectedGlossaryEntry() {
		return getSelectedProjectComposite().getSelectedGlossaryEntry();
	}
	
	/**
	 * @return the currently selected projectcomposite
	 */
	public ProjectComposite getSelectedProjectComposite() {
		TabItem[] selection = _projectTabFolder.getSelection();
		if (selection.length == 0) {
			return null;
		}
		return (ProjectComposite)(selection[0].getControl());
	}
	
	/**
	 * refreshes the glossary
	 */
	public void showGlossaryChanges() {
		getSelectedProjectComposite().showGlossaryChanges();
	}
	
	/**
	 * Show the validity of a projectname in the current projectomposite
	 * @param valid is the name valid?
	 */
	public void showProjectNameValidity(boolean valid) {
		getSelectedProjectComposite().showProjectNameValidity(valid);
	}
	
	/**
	 * changes a project name in the mainwindow
	 * @param newName the new name of the project
	 */
	public void changeProjectName(String newName) {
		this._projectTabFolder.getSelection()[0].setText(newName);
		getSelectedProjectComposite().setProjectName(newName);
	}
	
	/**
	 * @return the currently selected specification of the
	 * currently selcted project
	 */
	public Specification getSelectedSpecification() {
		return getSelectedProjectComposite().getSelectedSpecification();
	}
	
	/**
	 * @return the shell of the mainwindow
	 */
	public Shell getShell() {
		return this._shell;
	}
	
	/**
	 * refreshes the specification so changes are shown
	 */
	public void showSpecificationChanges() {
		getSelectedProjectComposite().refreshSpecifications();
	}
	
	/**
	 * @return the type of the selected specification of the selected
	 * project
	 */
	public SpecificationTypeEnum getSpecificationType() {
		return this.getSelectedProjectComposite().getSelectedSpecificationType();
	}
	
	/**
	 * @return the currently chosen calculationmethod or null, if no calculationdialog is open
	 */
	public CalculationMethod getCalculationMethod() {
		if (_calcDialog == null) {
			return null;
		}
		return _calcDialog.getCalculationMethod();
	}
	
	/**
	 * Shows calculationresults in the currently opened calculationdialog
	 * @param results the results to show
	 */
	public void setCalculationResult(CalculationResults results) {
		if (_calcDialog != null) { 
			_calcDialog.setCalculationResult(results);
		}
	}
	
	public String saveFileDialog() {
		FileDialog dialog = new FileDialog(_shell);
		return dialog.open();
	}
}
