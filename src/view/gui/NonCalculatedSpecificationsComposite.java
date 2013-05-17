package view.gui;

import java.util.ArrayList;

import model.data.ProductPerformance;
import model.data.QualitySpecification;
import model.data.Specification;
import model.data.SpecificationFieldEnum;
import model.data.SpecificationTypeEnum;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;

import controller.ControllerInterface;

public class NonCalculatedSpecificationsComposite extends Composite {
	private Label _lblName;
	private Label _lblDescription;
	private Text _nameText;
	private Text _descriptionText;
	private Label _lblNewLabel;
	private SpecificationTypeEnum _type;
	private List _list;
	private ListViewer _listViewer;
	private Button _btnAdd;
	private Button _btnRemove;
	private ControllerInterface _controller;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public NonCalculatedSpecificationsComposite(Composite parent, int style, SpecificationTypeEnum type, ControllerInterface controller) {
		super(parent, style);
		createContents();
		this._type = type;
		this._controller = controller;
		
		_btnAdd = new Button(this, SWT.NONE);
		_btnAdd.addSelectionListener(controller.createSpecification());
		_btnAdd.setText("Add");
		
		_btnRemove = new Button(this, SWT.NONE);
		_btnRemove.addSelectionListener(controller.removeSpecification());
		_btnRemove.setText("Remove");
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		
		_listViewer = new ListViewer(this, SWT.BORDER | SWT.V_SCROLL);
		
		_list = _listViewer.getList();
		GridData gd_list = new GridData(SWT.LEFT, SWT.FILL, false, false, 2, 6);
		gd_list.widthHint = 125;
		_list.setLayoutData(gd_list);
		
		_listViewer.setContentProvider(new SpecificationContentProvider(type));
		_listViewer.setLabelProvider(new SpecificationLabelProvider());
		
		_lblName = new Label(this, SWT.NONE);
		_lblName.setText("Name:");
		
		_nameText = new Text(this, SWT.BORDER);
		_nameText.addModifyListener(controller.changeSpecification(SpecificationFieldEnum.Name));
		_nameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		_lblDescription = new Label(this, SWT.NONE);
		_lblDescription.setText("Description:");
		
		_descriptionText = new Text(this, SWT.BORDER | SWT.MULTI);
		_descriptionText.addModifyListener(controller.changeSpecification(SpecificationFieldEnum.Description));
		GridData gd_descriptionText = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 5);
		gd_descriptionText.heightHint = 64;
		_descriptionText.setLayoutData(gd_descriptionText);
		
		_lblNewLabel = new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		
		_listViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent arg0) {
				IStructuredSelection selection = (IStructuredSelection) _listViewer.getSelection();
				if (selection.isEmpty()) {
					enable(false);
				} else {
					enable(true);
					Specification s = (Specification)selection.getFirstElement();
					_nameText.setText(s.getName());
					_descriptionText.setText(s.getDescription());
				}
			}
		});
		enable(false);
	}
	
	private void enable(boolean enabled) {
		_nameText.setEnabled(enabled);
		_descriptionText.setEnabled(enabled);
	}
	
	private void createContents() {
		setLayout(new GridLayout(4, false));
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	public void setEnabled(boolean enabled) {
		this._nameText.setEnabled(enabled);
		this._descriptionText.setEnabled(enabled);
	}
	
	public Specification getSpecification() {
		switch (this._type) {
		case Performance:
			return new ProductPerformance(_descriptionText.getText(),
					_nameText.getText());
		case Quality:
			return new QualitySpecification(_descriptionText.getText(),
					_nameText.getText());
			default:
				return null;
		}
	}
	
	public Specification getSelectedSpecification() {
		IStructuredSelection selection = (IStructuredSelection) _listViewer.getSelection();
		if (selection.isEmpty()) {
			return null;
		}
		return (Specification)selection.getFirstElement();
	}
	
	public void refresh() {
		this._listViewer.refresh();
	}
	
	public void setSpecifications(ArrayList<Specification> specifications) {
		this._listViewer.setInput(specifications);
	}
	
	public String getData(SpecificationFieldEnum field) {
		switch (field) {
			case Name:
				return _nameText.getText();
			case Description:
				return _descriptionText.getText();
			default:
				return null;
		}
	}
}
