package view.gui;

import java.util.ArrayList;

import model.data.GlossaryEntry;
import model.data.GlossaryFieldEnum;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import controller.ControllerInterface;

public class GlossaryComposite extends Composite {
	private List _list;
	private ListViewer _listViewer;
	private StyledText _styledText;
	private Button _buttonAdd;
	private Button _buttonRemove;
	private ControllerInterface _controller;
	private Text _text;
	private Group _grpDescription;
	private Group _grpEntry;

	/**
	 * Create the composite.
	 * @param parent parent composite
	 * @param style SWT-style
	 * @param controller implementation of ControllerInterface
	 */
	public GlossaryComposite(Composite parent, int style, ControllerInterface controller) {
		super(parent, style);
		this._controller = controller;
		createContents();
		setListeners();
	}
	
	
	/**
	 * creates the content of this composite
	 */
	private void createContents() {
		setLayout(new GridLayout(5, false));
				
		_buttonAdd = new Button(this, SWT.NONE);
		_buttonAdd.setImage(SWTResourceManager.getImage(GlossaryComposite.class, "/com/sun/java/swing/plaf/windows/icons/File.gif"));
		GridData gd_buttonAdd = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_buttonAdd.widthHint = 30;
		gd_buttonAdd.heightHint = 30;
		_buttonAdd.setLayoutData(gd_buttonAdd);
		
		_buttonRemove = new Button(this, SWT.NONE);
		_buttonRemove.setImage(SWTResourceManager.getImage(GlossaryComposite.class, "/javax/swing/plaf/metal/icons/ocean/close.gif"));
		
		GridData gd_buttonRemove = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_buttonRemove.heightHint = 30;
		gd_buttonRemove.widthHint = 30;
		_buttonRemove.setLayoutData(gd_buttonRemove);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		
		_listViewer = new ListViewer(this, SWT.BORDER | SWT.V_SCROLL);
		_list = _listViewer.getList();
		GridData gd_list = new GridData(SWT.FILL, SWT.FILL, false, true, 3, 6);
		gd_list.widthHint = 137;
		_list.setLayoutData(gd_list);
		
		_listViewer.setContentProvider(new GlossaryContentProvider());
		_listViewer.setLabelProvider(new GlossaryLabelProvider());
		
		_grpEntry = new Group(this, SWT.NONE);
		_grpEntry.setText("Entry");
		_grpEntry.setLayout(new FillLayout(SWT.HORIZONTAL));
		_grpEntry.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		
		_text = new Text(_grpEntry, SWT.BORDER);
		_text.setEditable(false);
		
		_grpDescription = new Group(this, SWT.NONE);
		_grpDescription.setLayout(new FillLayout(SWT.HORIZONTAL));
		_grpDescription.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 5));
		_grpDescription.setText("Description");
		
		_styledText = new StyledText(_grpDescription, SWT.BORDER);
		_styledText.setEditable(false);
	}
	
	private void setListeners() {
		_buttonRemove.addSelectionListener(_controller.removeGlossaryEntry());
		_styledText.addModifyListener(_controller.changeGlossaryEntry(GlossaryFieldEnum.Description));
		_buttonAdd.addSelectionListener(_controller.createGlossaryEntry());
		_text.addModifyListener(_controller.changeGlossaryEntry(GlossaryFieldEnum.Entry));
		_listViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent arg0) {
				if (arg0.getSelection().isEmpty()) {
					_styledText.setEditable(false);
					_text.setEditable(false);
				} else {
					IStructuredSelection selection = (IStructuredSelection) arg0.getSelection();
					_styledText.setEditable(true);
					_text.setEditable(true);
					_styledText.setText(((GlossaryEntry)selection.getFirstElement()).getDefinition());
					_text.setText(((GlossaryEntry)selection.getFirstElement()).getEntry());
				}
			}
		});
	}
	
	/**
	 * @return entry of the glossaryentry, that is currently selected
	 */
	public String getEntry() {
		return _text.getText();
	}
	
	/**
	 * @return description of the glossaryentry, that is currently selected
	 */
	public String getDescription() {
		return _styledText.getText();
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	/**
	 * @return the currently selected glossaryentry
	 */
	public GlossaryEntry getSelectedGlossaryEntry() {
		IStructuredSelection selection = (IStructuredSelection) _listViewer.getSelection();
		GlossaryEntry result = null;
		if (!selection.isEmpty()) {
			return (GlossaryEntry)selection.getFirstElement();
		}
		return result;
	}
	
	/**
	 * refreshes the glossary, so changes made in the modle are shown
	 */
	public void refresh() {
		_listViewer.refresh();
	}
	
	/**
	 * @param glossary the glossary to show
	 */
	public void setGlossary(ArrayList<GlossaryEntry> glossary) {
		_listViewer.setInput(glossary);
	}
}
