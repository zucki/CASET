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
	private Composite self;
	private Composite currentComposite;
	private Label lblName;
	private Label lblDescription;
	private Label lblClassification;
	private Text nameText;
	private Text descriptionText;
	private Combo classificationCombo;
	private Label lblNewLabel;
	private Label lblCategory;
	private Combo categoryCombo;
	private SpecificationTypeEnum type;
	private List list;
	private ListViewer listViewer;
	private Button btnAdd;
	private Button btnRemove;
	private ControllerInterface controller;
	private SelectionAdapter addListener;
	private SelectionAdapter removeListener;
	private ModifyListener nameListener;
	private ModifyListener descriptionListener;
	private ModifyListener classificationListener;
	private ModifyListener categoryListener;
	private ISelectionChangedListener selectionListener = new ISelectionChangedListener() {
		public void selectionChanged(SelectionChangedEvent arg0) {
			enableListeners(false);
			IStructuredSelection selection = (IStructuredSelection) listViewer.getSelection();
			if (selection.isEmpty()) {
				enable(false);
			} else {
				enable(true);
				CalculatedSpecification s = (CalculatedSpecification)selection.getFirstElement();
				classificationCombo.setText(s.getClassification().toString());
				if (type == SpecificationTypeEnum.Function)
					categoryCombo.setText(((ProductFunction)s).getCategory().toString());
				else if (type == SpecificationTypeEnum.Data)
					categoryCombo.setText(((ProductData)s).getCategory().toString());
				nameText.setText(s.getName());
				descriptionText.setText(s.getDescription());
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
		self = this;
		this.type = type;
		this.controller = controller;
		
		btnAdd = new Button(this, SWT.NONE);
		btnAdd.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnAdd.setText("Add");
		
		btnRemove = new Button(this, SWT.NONE);
		btnRemove.setText("Remove");
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		
		listViewer = new ListViewer(this, SWT.BORDER | SWT.V_SCROLL);
		list = listViewer.getList();
		GridData gd_list = new GridData(SWT.LEFT, SWT.FILL, false, false, 2, 6);
		gd_list.widthHint = 125;
		list.setLayoutData(gd_list);
		listViewer.setContentProvider(new SpecificationContentProvider(type));
		listViewer.setLabelProvider(new SpecificationLabelProvider());
		
		lblName = new Label(this, SWT.NONE);
		lblName.setText("Name:");
		
		nameText = new Text(this, SWT.BORDER);
		nameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblDescription = new Label(this, SWT.NONE);
		lblDescription.setText("Description:");
		
		descriptionText = new Text(this, SWT.BORDER | SWT.MULTI);
		GridData gd_descriptionText = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 3);
		gd_descriptionText.heightHint = 64;
		descriptionText.setLayoutData(gd_descriptionText);
		
		lblNewLabel = new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		
		lblClassification = new Label(this, SWT.NONE);
		lblClassification.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		lblClassification.setText("Classification:");
		
		classificationCombo = new Combo(this, SWT.READ_ONLY);
		classificationCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		lblCategory = new Label(this, SWT.NONE);
		lblCategory.setText("Category:");
		
		categoryCombo = new Combo(this, SWT.READ_ONLY);
		categoryCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		for (SpecificationClassificationEnum sc: SpecificationClassificationEnum.values()) {
			classificationCombo.add(sc.toString());
		}
		
		if (this.type == SpecificationTypeEnum.Function) {
			for (FunctionCategoryEnum fc: FunctionCategoryEnum.values()) {
				categoryCombo.add(fc.toString());
			}
		} else {
			for (DataCategoryEnum dc: DataCategoryEnum.values()) {
				categoryCombo.add(dc.toString());
			}
		}
		enable(false);
		
		addListener = controller.createSpecification();
		removeListener = controller.removeSpecification();
		nameListener = controller.changeSpecification(SpecificationFieldEnum.Name);
		descriptionListener = controller.changeSpecification(SpecificationFieldEnum.Description);
		classificationListener = controller.changeSpecification(SpecificationFieldEnum.Classification);
		categoryListener = controller.changeSpecification(SpecificationFieldEnum.Category);
		enableListeners(true);
	}
	
	private void enableListeners(boolean enabled) {
		if (enabled) {
			listViewer.addSelectionChangedListener(selectionListener);
			btnAdd.addSelectionListener(addListener);
			btnRemove.addSelectionListener(removeListener);
			nameText.addModifyListener(nameListener);
			descriptionText.addModifyListener(descriptionListener);
			classificationCombo.addModifyListener(classificationListener);
			categoryCombo.addModifyListener(categoryListener);
		} else {
			listViewer.removeSelectionChangedListener(selectionListener);
			btnAdd.removeSelectionListener(addListener);
			btnRemove.removeSelectionListener(removeListener);
			nameText.removeModifyListener(nameListener);
			descriptionText.removeModifyListener(descriptionListener);
			classificationCombo.removeModifyListener(classificationListener);
			categoryCombo.removeModifyListener(categoryListener);
		}
	}
	
	private void enable(boolean enabled) {
		nameText.setEnabled(enabled);
		descriptionText.setEnabled(enabled);
		categoryCombo.setEnabled(enabled);
		classificationCombo.setEnabled(enabled);
	}
	
	private void createContents() {
		setLayout(new GridLayout(4, false));
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	public void setEnabled(boolean enabled) {
		this.categoryCombo.setEnabled(enabled);
		this.classificationCombo.setEnabled(enabled);
		this.nameText.setEnabled(enabled);
		this.descriptionText.setEnabled(enabled);
	}
	
	public Specification getSpecification() {
		switch (this.type) {
		case Function:
			return new ProductFunction(descriptionText.getText(),
					nameText.getText(), 
					FunctionCategoryEnum.fromString(categoryCombo.getText().length()>0?categoryCombo.getText():"Database"), 
					SpecificationClassificationEnum.valueOf(
							classificationCombo.getText().length()>0?classificationCombo.getText():"Medium"));
		case Data:
			return new ProductData(descriptionText.getText(),
					nameText.getText(), 
					DataCategoryEnum.fromString(categoryCombo.getText().length()>0?categoryCombo.getText():"Database"), 
					SpecificationClassificationEnum.valueOf(
							classificationCombo.getText().length()>0?classificationCombo.getText():"Medium"));
			default:
				return null;
		}
	}
	
	public Specification getSelectedSpecification() {
		IStructuredSelection selection = (IStructuredSelection) listViewer.getSelection();
		if (selection.isEmpty()) {
			return null;
		}
		return (Specification)selection.getFirstElement();
	}
	
	public void refresh() {
		this.listViewer.refresh();
	}
	
	public void setSpecifications(ArrayList<Specification> specifications) {
		this.listViewer.setInput(specifications);
	}
	
	public String getData(SpecificationFieldEnum field) {
		switch (field) {
			case Name:
				return nameText.getText();
			case Description:
				return descriptionText.getText();
			case Category:
				return categoryCombo.getText();
			case Classification:
				return classificationCombo.getText();
			default:
				return null;
		}
	}
}
