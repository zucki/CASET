package view.gui;

import model.data.CocomoMethod;
import model.data.Project;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.events.VerifyEvent;

public class ProjectSettingsComposite extends Composite {
	private Label lblName;
	private Label lblAuthor;
	private Label lblCompany;
	private Label lblLinesOfCode;
	private Label lblValueAdjustmentFactor;
	private Text textName;
	private Text textAuthor;
	private Text textCompany;
	private Text textLOC;
	private Text textVAF;
	private Project project;
	private Label lblCocomoMethod;
	private Combo combo;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ProjectSettingsComposite(Composite parent, int style, Project project) {
		super(parent, style);
		setLayout(new GridLayout(2, false));
		this.project = project;
		
		lblName = new Label(this, SWT.NONE);
		lblName.setText("Name:");
		
		textName = new Text(this, SWT.BORDER);
		textName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblAuthor = new Label(this, SWT.NONE);
		lblAuthor.setText("Author:");
		
		textAuthor = new Text(this, SWT.BORDER);
		textAuthor.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblCompany = new Label(this, SWT.NONE);
		lblCompany.setText("Company:");
		
		textCompany = new Text(this, SWT.BORDER | SWT.MULTI);
		GridData gd_textCompany = new GridData(SWT.FILL, SWT.TOP, true, false, 1, 3);
		gd_textCompany.heightHint = 56;
		textCompany.setLayoutData(gd_textCompany);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		
		lblValueAdjustmentFactor = new Label(this, SWT.NONE);
		lblValueAdjustmentFactor.setText("Value adjustment factor:");
		
		textVAF = new Text(this, SWT.BORDER);
		textVAF.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				e.doit = e.text.matches("[0-9]") 
						|| (e.text.matches("\\.") && !textVAF.getText().contains("."))
						|| (e.text.matches("[0-9]+\\.[0-9]+") && !textVAF.getText().contains("."))
						|| e.character == '\b';
			}
		});
		textVAF.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblLinesOfCode = new Label(this, SWT.NONE);
		lblLinesOfCode.setText("Lines of code:");
		
		textLOC = new Text(this, SWT.BORDER);
		textLOC.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				e.doit = e.text.matches("[0-9]+")
							|| e.character == '\b';
			}
		});
		textLOC.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblCocomoMethod = new Label(this, SWT.NONE);
		lblCocomoMethod.setText("Cocomo Method:");
		
		combo = new Combo(this, SWT.READ_ONLY);
		combo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));

		for (CocomoMethod cm: CocomoMethod.values()) {
			combo.add(cm.toString());
		}
		combo.setText(CocomoMethod.Organic.toString());
		setData();
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public void setData() {
		textAuthor.setText(project.getAuthor());
		textCompany.setText(project.getCompany());
		textName.setText(project.getName());
		textLOC.setText(String.valueOf(project.getLinesOfCode()));
		textVAF.setText(String.valueOf(project.getValueAdjustmentFactor()));
	}
}
