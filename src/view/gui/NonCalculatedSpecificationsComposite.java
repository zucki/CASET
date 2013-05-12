package view.gui;

import java.util.ArrayList;

import model.data.DataCategory;
import model.data.FunctionCategory;
import model.data.ProductData;
import model.data.ProductFunction;
import model.data.ProductPerformance;
import model.data.Project;
import model.data.QualitySpecification;
import model.data.Specification;
import model.data.SpecificationClassification;
import model.data.SpecificationType;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.IStructuredSelection;
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
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;

import controller.ControllerInterface;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;

public class NonCalculatedSpecificationsComposite extends Composite {
	private Composite self;
	private Composite currentComposite;
	private Label lblName;
	private Label lblDescription;
	private Text nameText;
	private Text descriptionText;
	private Label lblNewLabel;
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
	public NonCalculatedSpecificationsComposite(Composite parent, int style, SpecificationType type, ControllerInterface controller) {
		super(parent, style);
		createContents();
		self = this;
		this.type = type;
		this.controller = controller;
		
		btnAdd = new Button(this, SWT.NONE);
		btnAdd.addSelectionListener(controller.createSpecification());
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
		GridData gd_descriptionText = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 5);
		gd_descriptionText.heightHint = 64;
		descriptionText.setLayoutData(gd_descriptionText);
		
		lblNewLabel = new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		currentComposite = null;
		
		listViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent arg0) {
				IStructuredSelection selection = (IStructuredSelection) listViewer.getSelection();
				if (selection.isEmpty()) {
					enable(false);
				} else {
					enable(true);
					Specification s = (Specification)selection.getFirstElement();
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
	}
	
	private void createContents() {
		setLayout(new GridLayout(4, false));
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	public void setEnabled(boolean enabled) {
		this.nameText.setEnabled(enabled);
		this.descriptionText.setEnabled(enabled);
	}
	
	public Specification getSpecification() {
		switch (this.type) {
		case Performance:
			return new ProductPerformance(descriptionText.getText(),
					nameText.getText());
		case Quality:
			return new QualitySpecification(descriptionText.getText(),
					nameText.getText());
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
