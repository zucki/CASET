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

public class MainWindow {
	
	int projectCounter = 0;

	protected Shell shlCaset;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainWindow window = new MainWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlCaset.open();
		shlCaset.layout();
		while (!shlCaset.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlCaset = new Shell();
		shlCaset.setSize(623, 458);
		shlCaset.setText("CASET");
		shlCaset.setLayout(new GridLayout(1, false));
		
		Menu menu = new Menu(shlCaset, SWT.BAR);
		shlCaset.setMenuBar(menu);
		
		MenuItem mntmFile_1 = new MenuItem(menu, SWT.CASCADE);
		mntmFile_1.setText("File");
		
		Menu menu_1 = new Menu(mntmFile_1);
		mntmFile_1.setMenu(menu_1);
		
		final TabFolder tabFolder_1 = new TabFolder(shlCaset, SWT.NONE);
		tabFolder_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		MenuItem mntmNewProject = new MenuItem(menu_1, SWT.NONE);
		mntmNewProject.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TabItem item = new TabItem(tabFolder_1, SWT.NONE);
				item.setText(String.format("Project %d", projectCounter));
				++projectCounter;
				ProjectComposite c = new ProjectComposite(tabFolder_1, SWT.NONE);
				item.setControl(c);
				tabFolder_1.setSelection(item);
			}
		});
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
				TabItem item = tabFolder_1.getSelection()[0];
				item.dispose();
			}
		});
		mntmCloseProject.setText("Close Project");
		
		MenuItem mntmExit = new MenuItem(menu_1, SWT.NONE);
		mntmExit.setText("Exit");
	}
}
