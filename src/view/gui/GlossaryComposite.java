package view.gui;

import java.util.ArrayList;

import model.data.GlossaryEntry;
import model.data.Project;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.List;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;

import controller.Controller;
import controller.ControllerInterface;

public class GlossaryComposite extends Composite {
	private List list;
	private ListViewer listViewer;
	private StyledText styledText;
	private Button buttonAdd;
	private Button buttonRemove;
	private Button buttonEdit;
	private Project project;
	private ControllerInterface controller;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public GlossaryComposite(Composite parent, int style, final Project project, ControllerInterface controller) {
		super(parent, style);
		setLayout(new GridLayout(4, false));
		this.project = project;
		this.controller = controller;

		buttonAdd = new Button(this, SWT.NONE);
		GridData gd_buttonAdd = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_buttonAdd.widthHint = 30;
		gd_buttonAdd.heightHint = 30;
		buttonAdd.setLayoutData(gd_buttonAdd);
		buttonAdd.setText("+");
		
		buttonRemove = new Button(this, SWT.NONE);

		GridData gd_buttonRemove = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_buttonRemove.heightHint = 30;
		gd_buttonRemove.widthHint = 30;
		buttonRemove.setLayoutData(gd_buttonRemove);
		buttonRemove.setText("-");
		
		buttonEdit = new Button(this, SWT.NONE);
		GridData gd_buttonEdit = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_buttonEdit.widthHint = 30;
		gd_buttonEdit.heightHint = 30;
		buttonEdit.setLayoutData(gd_buttonEdit);
		buttonEdit.setText("E");
		
		styledText = new StyledText(this, SWT.BORDER);
		styledText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				IStructuredSelection selection = (IStructuredSelection)listViewer.getSelection();
				((GlossaryEntry)selection.getFirstElement()).setDefinition(styledText.getText());
			}
		});
		styledText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 2));
		
		listViewer = new ListViewer(this, SWT.BORDER | SWT.V_SCROLL);
		list = listViewer.getList();
		GridData gd_list = new GridData(SWT.LEFT, SWT.FILL, false, false, 3, 1);
		gd_list.widthHint = 137;
		list.setLayoutData(gd_list);

		listViewer.setContentProvider(new GlossaryContentProvider());
		listViewer.setLabelProvider(new GlossaryLabelProvider());
		listViewer.setInput(project.getGlossary());
		
		buttonAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				project.getGlossary().add(new GlossaryEntry("Entry", ""));
				listViewer.refresh();
			}
		});
		buttonEdit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(!listViewer.getSelection().isEmpty())
				{
					IStructuredSelection selection = (IStructuredSelection) listViewer.getSelection();
					String txt = ((GlossaryEntry)selection.getFirstElement()).getEntry();
					GlossaryEntryDialog dialog = new GlossaryEntryDialog(getShell(), SWT.NONE, txt);
					String result = dialog.open();
					if (result.length() > 0) {
						((GlossaryEntry)selection.getFirstElement()).setEntry(result);
						listViewer.refresh();
					}
				}
			}
		});
		
		listViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent arg0) {
				if (arg0.getSelection().isEmpty()) {
					styledText.setEditable(false);
				} else {
					IStructuredSelection selection = (IStructuredSelection) arg0.getSelection();
					styledText.setEditable(true);
					styledText.setText(((GlossaryEntry)selection.getFirstElement()).getDefinition());
				}
			}
		});
		
		buttonRemove.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection selection = (IStructuredSelection) listViewer.getSelection();
				if (!selection.isEmpty()) {
					project.getGlossary().remove(selection.getFirstElement());
					listViewer.refresh();
				}
			}
		});
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	public GlossaryEntry getSelectedGlossaryEntry() {
		IStructuredSelection selection = (IStructuredSelection) listViewer.getSelection();
		GlossaryEntry result = null;
		if (!selection.isEmpty()) {
			return (GlossaryEntry)selection.getFirstElement();
		}
		return result;
	}
	
	public void refresh() {
		listViewer.refresh();
	}
}
