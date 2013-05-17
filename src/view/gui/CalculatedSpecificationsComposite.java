package view.gui;

import java.util.ArrayList;

import model.data.CalculatedSpecification;
import model.data.DataCategoryEnum;
import model.data.FunctionCategoryEnum;
import model.data.ProductData;
import model.data.ProductFunction;
import model.data.Project;
import model.data.Specification;
import model.data.SpecificationClassificationEnum;
import model.data.SpecificationFieldEnum;
import model.data.SpecificationTypeEnum;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.List;
import org.eclipse.jface.viewers.ListViewer;

import controller.ControllerInterface;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;

public class CalculatedSpecificationsComposite extends Composite {
	private Composite _self;
	private Composite _currentComposite;
	private Label _lblName;
	private Label _lblDescription;
	private Label _lblClassification;
	private Text _nameText;
	private Text _descriptionText;
	private Combo _classificationCombo;
	private Label _lblNewLabel;
	private Label _lblCategory;
	private Combo _categoryCombo;
	private SpecificationTypeEnum _type;
	private List _list;
	private ListViewer _listViewer;
	private Button _btnAdd;
	private Button _btnRemove;
	private ControllerInterface _controller;
	private SelectionAdapter _addListener;
	private SelectionAdapter _removeListener;
	private ModifyListener _nameListener;
	private ModifyListener _descriptionListener;
	private ModifyListener _classificationListener;
	private ModifyListener _categoryListener;
	private ISelectionChangedListener _selectionListener = new ISelectionChangedListener() {
		public void selectionChanged(SelectionChangedEvent arg0) {
			enableListeners(false);
			IStructuredSelection selection = (IStructuredSelection) _listViewer.getSelection();
			if (selection.isEmpty()) {
				enable(false);
			} else {
				enable(true);
				CalculatedSpecification s = (CalculatedSpecification)selection.getFirstElement();
				_classificationCombo.setText(s.getClassification().toString());
				if (_type == SpecificationTypeEnum.Function)
					_categoryCombo.setText(((ProductFunction)s).getCategory().toString());
				else if (_type == SpecificationTypeEnum.Data)
					_categoryCombo.setText(((ProductData)s).getCategory().toString());
				_nameText.setText(s.getName());
				_descriptionText.setText(s.getDescription());
			}
			enableListeners(true);
		}
	};

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public CalculatedSpecificationsComposite(Composite parent, int style, final SpecificationTypeEnum type, ControllerInterface controller) {
		super(parent, style);
		createContents();
		_self = this;
		this._type = type;
		this._controller = controller;
		
		_btnAdd = new Button(this, SWT.NONE);
		_btnAdd.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		_btnAdd.setText("Add");
		
		_btnRemove = new Button(this, SWT.NONE);
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
		_nameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		_lblDescription = new Label(this, SWT.NONE);
		_lblDescription.setText("Description:");
		
		_descriptionText = new Text(this, SWT.BORDER | SWT.MULTI);
		GridData gd_descriptionText = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 3);
		gd_descriptionText.heightHint = 64;
		_descriptionText.setLayoutData(gd_descriptionText);
		
		_lblNewLabel = new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		
		_lblClassification = new Label(this, SWT.NONE);
		_lblClassification.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		_lblClassification.setText("Classification:");
		
		_classificationCombo = new Combo(this, SWT.READ_ONLY);
		_classificationCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		_lblCategory = new Label(this, SWT.NONE);
		_lblCategory.setText("Category:");
		
		_categoryCombo = new Combo(this, SWT.READ_ONLY);
		_categoryCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		for (SpecificationClassificationEnum sc: SpecificationClassificationEnum.values()) {
			_classificationCombo.add(sc.toString());
		}
		
		if (this._type == SpecificationTypeEnum.Function) {
			for (FunctionCategoryEnum fc: FunctionCategoryEnum.values()) {
				_categoryCombo.add(fc.toString());
			}
		} else {
			for (DataCategoryEnum dc: DataCategoryEnum.values()) {
				_categoryCombo.add(dc.toString());
			}
		}
		enable(false);
		
		_addListener = controller.createSpecification();
		_removeListener = controller.removeSpecification();
		_nameListener = controller.changeSpecification(SpecificationFieldEnum.Name);
		_descriptionListener = controller.changeSpecification(SpecificationFieldEnum.Description);
		_classificationListener = controller.changeSpecification(SpecificationFieldEnum.Classification);
		_categoryListener = controller.changeSpecification(SpecificationFieldEnum.Category);
		enableListeners(true);
	}
	
	private void enableListeners(boolean enabled) {
		if (enabled) {
			_listViewer.addSelectionChangedListener(_selectionListener);
			_btnAdd.addSelectionListener(_addListener);
			_btnRemove.addSelectionListener(_removeListener);
			_nameText.addModifyListener(_nameListener);
			_descriptionText.addModifyListener(_descriptionListener);
			_classificationCombo.addModifyListener(_classificationListener);
			_categoryCombo.addModifyListener(_categoryListener);
		} else {
			_listViewer.removeSelectionChangedListener(_selectionListener);
			_btnAdd.removeSelectionListener(_addListener);
			_btnRemove.removeSelectionListener(_removeListener);
			_nameText.removeModifyListener(_nameListener);
			_descriptionText.removeModifyListener(_descriptionListener);
			_classificationCombo.removeModifyListener(_classificationListener);
			_categoryCombo.removeModifyListener(_categoryListener);
		}
	}
	
	private void enable(boolean enabled) {
		_nameText.setEnabled(enabled);
		_descriptionText.setEnabled(enabled);
		_categoryCombo.setEnabled(enabled);
		_classificationCombo.setEnabled(enabled);
	}
	
	private void createContents() {
		setLayout(new GridLayout(4, false));
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	public void setEnabled(boolean enabled) {
		this._categoryCombo.setEnabled(enabled);
		this._classificationCombo.setEnabled(enabled);
		this._nameText.setEnabled(enabled);
		this._descriptionText.setEnabled(enabled);
	}
	
	public Specification getSpecification() {
		switch (this._type) {
		case Function:
			return new ProductFunction(_descriptionText.getText(),
					_nameText.getText(), 
					FunctionCategoryEnum.fromString(_categoryCombo.getText().length()>0?_categoryCombo.getText():"Database"), 
					SpecificationClassificationEnum.valueOf(
							_classificationCombo.getText().length()>0?_classificationCombo.getText():"Medium"));
		case Data:
			return new ProductData(_descriptionText.getText(),
					_nameText.getText(), 
					DataCategoryEnum.fromString(_categoryCombo.getText().length()>0?_categoryCombo.getText():"Database"), 
					SpecificationClassificationEnum.valueOf(
							_classificationCombo.getText().length()>0?_classificationCombo.getText():"Medium"));
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
			case Category:
				return _categoryCombo.getText();
			case Classification:
				return _classificationCombo.getText();
			default:
				return null;
		}
	}
}
