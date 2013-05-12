package view.gui;

import java.util.ArrayList;

import model.data.CalculatedSpecification;
import model.data.DataCategory;
import model.data.FunctionCategory;
import model.data.ProductData;
import model.data.ProductFunction;
import model.data.Project;
import model.data.Specification;
import model.data.SpecificationClassification;
import model.data.SpecificationType;

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
	private SpecificationType type;
	private List list;
	private ListViewer listViewer;
	private Button btnAdd;
	private Button btnRemove;
	private ControllerInterface controller;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public CalculatedSpecificationsComposite(Composite parent, int style, final SpecificationType type, ControllerInterface controller) {
		super(parent, style);
		createContents();
		self = this;
		this.type = type;
		this.controller = controller;
		
		btnAdd = new Button(this, SWT.NONE);
		btnAdd.addSelectionListener(controller.createSpecification());
		btnAdd.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnAdd.setText("Add");
		
		btnRemove = new Button(this, SWT.NONE);
		btnRemove.addSelectionListener(controller.removeSpecification());
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
		nameText.addModifyListener(controller.changeSpecification());
		nameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblDescription = new Label(this, SWT.NONE);
		lblDescription.setText("Description:");
		
		descriptionText = new Text(this, SWT.BORDER | SWT.MULTI);
		descriptionText.addModifyListener(controller.changeSpecification());
		GridData gd_descriptionText = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 3);
		gd_descriptionText.heightHint = 64;
		descriptionText.setLayoutData(gd_descriptionText);
		
		lblNewLabel = new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		
		lblClassification = new Label(this, SWT.NONE);
		lblClassification.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		lblClassification.setText("Classification:");
		
		classificationCombo = new Combo(this, SWT.READ_ONLY);
		classificationCombo.addModifyListener(controller.changeSpecification());
		classificationCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		lblCategory = new Label(this, SWT.NONE);
		lblCategory.setText("Category:");
		
		categoryCombo = new Combo(this, SWT.READ_ONLY);
		categoryCombo.addModifyListener(controller.changeSpecification());
		categoryCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		for (SpecificationClassification sc: SpecificationClassification.values()) {
			classificationCombo.add(sc.toString());
		}
		
		if (this.type == SpecificationType.Function) {
			for (FunctionCategory fc: FunctionCategory.values()) {
				categoryCombo.add(fc.toString());
			}
		} else {
			for (DataCategory dc: DataCategory.values()) {
				categoryCombo.add(dc.toString());
			}
		}
		
		listViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent arg0) {
				IStructuredSelection selection = (IStructuredSelection) listViewer.getSelection();
				if (selection.isEmpty()) {
					enable(false);
				} else {
					enable(true);
					CalculatedSpecification s = (CalculatedSpecification)selection.getFirstElement();
					classificationCombo.setText(s.getClassification().toString());
					if (type == SpecificationType.Function)
						categoryCombo.setText(((ProductFunction)s).getCategory().toString());
					else if (type == SpecificationType.Data)
						categoryCombo.setText(((ProductData)s).getCategory().toString());
					nameText.setText(s.getName());
					descriptionText.setText(s.getDescription());
				}
			}
		});
		enable(false);
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
					FunctionCategory.fromString(categoryCombo.getText().length()>0?categoryCombo.getText():"Database"), 
					SpecificationClassification.valueOf(
							classificationCombo.getText().length()>0?classificationCombo.getText():"Medium"));
		case Data:
			return new ProductData(descriptionText.getText(),
					nameText.getText(), 
					DataCategory.fromString(categoryCombo.getText().length()>0?categoryCombo.getText():"Database"), 
					SpecificationClassification.valueOf(
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
}
