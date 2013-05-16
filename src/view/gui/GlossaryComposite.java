package view.gui;

import java.util.ArrayList;

import model.data.GlossaryEntry;
import model.data.GlossaryFieldEnum;
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
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.wb.swt.SWTResourceManager;

public class GlossaryComposite extends Composite {
	private List list;
	private ListViewer listViewer;
	private StyledText styledText;
	private Button buttonAdd;
	private Button buttonRemove;
	private ControllerInterface controller;
	private ArrayList<GlossaryEntry> glossary;
	private Text text;
	private Group grpDescription;
	private Group grpEntry;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public GlossaryComposite(Composite parent, int style, ControllerInterface controller) {
		super(parent, style);
		this.controller = controller;
		createContents();
		setListeners();
	}
	
	
	private void createContents() {
		setLayout(new GridLayout(5, false));
				
		buttonAdd = new Button(this, SWT.NONE);
		buttonAdd.setImage(SWTResourceManager.getImage(GlossaryComposite.class, "/com/sun/java/swing/plaf/windows/icons/File.gif"));
		GridData gd_buttonAdd = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_buttonAdd.widthHint = 30;
		gd_buttonAdd.heightHint = 30;
		buttonAdd.setLayoutData(gd_buttonAdd);
		
		buttonRemove = new Button(this, SWT.NONE);
		buttonRemove.setImage(SWTResourceManager.getImage(GlossaryComposite.class, "/javax/swing/plaf/metal/icons/ocean/close.gif"));
		
		GridData gd_buttonRemove = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_buttonRemove.heightHint = 30;
		gd_buttonRemove.widthHint = 30;
		buttonRemove.setLayoutData(gd_buttonRemove);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		
		listViewer = new ListViewer(this, SWT.BORDER | SWT.V_SCROLL);
		list = listViewer.getList();
		GridData gd_list = new GridData(SWT.FILL, SWT.FILL, false, true, 3, 6);
		gd_list.widthHint = 137;
		list.setLayoutData(gd_list);
		
		listViewer.setContentProvider(new GlossaryContentProvider());
		listViewer.setLabelProvider(new GlossaryLabelProvider());
		
		grpEntry = new Group(this, SWT.NONE);
		grpEntry.setText("Entry");
		grpEntry.setLayout(new FillLayout(SWT.HORIZONTAL));
		grpEntry.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		
		text = new Text(grpEntry, SWT.BORDER);
		text.setEditable(false);
		
		grpDescription = new Group(this, SWT.NONE);
		grpDescription.setLayout(new FillLayout(SWT.HORIZONTAL));
		grpDescription.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 5));
		grpDescription.setText("Description");
		
		styledText = new StyledText(grpDescription, SWT.BORDER);
		styledText.setEditable(false);
	}
	
	private void setListeners() {
		buttonRemove.addSelectionListener(controller.removeGlossaryEntry());
		styledText.addModifyListener(controller.changeGlossaryEntry(GlossaryFieldEnum.Description));
		buttonAdd.addSelectionListener(controller.createGlossaryEntry());
		text.addModifyListener(controller.changeGlossaryEntry(GlossaryFieldEnum.Entry));
		listViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent arg0) {
				if (arg0.getSelection().isEmpty()) {
					styledText.setEditable(false);
					text.setEditable(false);
				} else {
					IStructuredSelection selection = (IStructuredSelection) arg0.getSelection();
					styledText.setEditable(true);
					text.setEditable(true);
					styledText.setText(((GlossaryEntry)selection.getFirstElement()).getDefinition());
					text.setText(((GlossaryEntry)selection.getFirstElement()).getEntry());
				}
			}
		});
	}
	
	public String getEntry() {
		return text.getText();
	}
	
	public String getDescription() {
		return styledText.getText();
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
	
	public void setGlossary(ArrayList<GlossaryEntry> glossary) {
		listViewer.setInput(glossary);
	}
}
