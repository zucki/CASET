package view.gui;

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

import controller.Controller;
import controller.ControllerInterface;

public class MainWindow {
	
	int projectCounter = 0;

	protected Shell shell;
	private TabFolder projectTabFolder;
	private ControllerInterface controller;

	public MainWindow(ControllerInterface controller) {
		this.controller = controller;
	}
	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
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
		
		MenuItem mntmNewProject = new MenuItem(menu_1, SWT.NONE);
		mntmNewProject.addSelectionListener(controller.createProjectListener());
		mntmNewProject.setText("New Project");
		
		MenuItem mntmOpenProject = new MenuItem(menu_1, SWT.NONE);
		mntmOpenProject.setText("Open Project");
		
		new MenuItem(menu_1, SWT.SEPARATOR);
		
		MenuItem mntmSaveProject = new MenuItem(menu_1, SWT.NONE);
		mntmSaveProject.setText("Save Project");
		
		MenuItem mntmSaveAll = new MenuItem(menu_1, SWT.NONE);
		mntmSaveAll.setText("Save All");
		
		new MenuItem(menu_1, SWT.SEPARATOR);
		
		MenuItem mntmCloseProject = new MenuItem(menu_1, SWT.NONE);
		mntmCloseProject.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TabItem item = projectTabFolder.getSelection()[0];
				item.dispose();
			}
		});
		mntmCloseProject.setText("Close Project");
		
		MenuItem mntmExit = new MenuItem(menu_1, SWT.NONE);
		mntmExit.setText("Exit");
	}
	
	public void createProject() {
		TabItem item = new TabItem(this.projectTabFolder, SWT.NONE);
		item.setText(String.format("Project %d", projectCounter));
		++projectCounter;
		ProjectComposite c = new ProjectComposite(this.projectTabFolder, SWT.NONE);
		item.setControl(c);
		this.projectTabFolder.setSelection(item);
	}
	
	public void removeSelectedProject() {
		for(int i = 0; i < projectTabFolder.getSelection().length; ++i) {
			projectTabFolder.getSelection()[i].dispose();
		}
	}
}
