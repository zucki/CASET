package view.gui;

import model.data.Project;
import model.data.SpecificationType;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
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

public class CalculatedSpecificationsComposite extends Composite {
	private Tree tree;
	private TreeViewer treeViewer;
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

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public CalculatedSpecificationsComposite(Composite parent, int style, SpecificationType type) {
		super(parent, style);
		createContents();
		self = this;
		this.type = type;
		
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
		//for (Spec)
		currentComposite = null;
	}
	
	private void createContents() {
		setLayout(new GridLayout(3, false));
		
		treeViewer = new TreeViewer(this, SWT.BORDER);
		tree = treeViewer.getTree();
		GridData gd_tree = new GridData(SWT.FILL, SWT.FILL, false, true, 1, 6);
		gd_tree.widthHint = 150;
		tree.setLayoutData(gd_tree);
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
}
