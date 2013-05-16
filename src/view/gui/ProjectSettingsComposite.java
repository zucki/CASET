package view.gui;

import model.data.CocomoMethodEnum;
import model.data.Project;
import model.data.ProjectFieldEnum;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;

import controller.ControllerInterface;

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
	private Label lblCocomoMethod;
	private Combo combo;
	private Label label;
	private ControllerInterface controller;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ProjectSettingsComposite(Composite parent, int style, ControllerInterface controller) {
		super(parent, style);
		this.controller = controller;
		createContents();
	}
	
	private void createContents() {
		setLayout(new GridLayout(3, false));
		
		lblName = new Label(this, SWT.NONE);
		lblName.setText("Name:");
		
		textName = new Text(this, SWT.BORDER);
		textName.addModifyListener(controller.changeProjectName());
		textName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		label = new Label(this, SWT.NONE);
		GridData gd_label = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_label.heightHint = 20;
		gd_label.widthHint = 20;
		label.setLayoutData(gd_label);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
		label.setText("     ");
		
		lblAuthor = new Label(this, SWT.NONE);
		lblAuthor.setText("Author:");
		
		textAuthor = new Text(this, SWT.BORDER);
		textAuthor.addModifyListener(controller.changeProjectField(ProjectFieldEnum.Author));
		textAuthor.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		lblCompany = new Label(this, SWT.NONE);
		lblCompany.setText("Company:");
		
		textCompany = new Text(this, SWT.BORDER | SWT.MULTI);
		textCompany.addModifyListener(controller.changeProjectField(ProjectFieldEnum.Company));
		GridData gd_textCompany = new GridData(SWT.FILL, SWT.TOP, true, false, 2, 3);
		gd_textCompany.heightHint = 56;
		textCompany.setLayoutData(gd_textCompany);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		
		lblValueAdjustmentFactor = new Label(this, SWT.NONE);
		lblValueAdjustmentFactor.setText("Value adjustment factor:");
		
		textVAF = new Text(this, SWT.BORDER);
		textVAF.addModifyListener(controller.changeProjectField(ProjectFieldEnum.ValueAdjustmentFactor));
		textVAF.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				e.doit = (e.text.matches("[0-9]") 
						|| (e.text.matches("\\.") && !textVAF.getText().contains("."))
						|| (e.text.matches("[0-9]+\\.[0-9]+") && !textVAF.getText().contains("."))
						|| e.character == '\b')
						&& (textVAF.getText().length() + e.text.length() < 20);
			}
		});
		textVAF.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		lblLinesOfCode = new Label(this, SWT.NONE);
		lblLinesOfCode.setText("Lines of code:");
		
		textLOC = new Text(this, SWT.BORDER);
		textLOC.addModifyListener(controller.changeProjectField(ProjectFieldEnum.LinesOfCode));
		textLOC.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				e.doit = (e.text.matches("[0-9]+")
							|| e.character == '\b')
							&& (textLOC.getText().length() + e.text.length() < 10);
			}
		});
		textLOC.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		lblCocomoMethod = new Label(this, SWT.NONE);
		lblCocomoMethod.setText("Cocomo Method:");
		
		combo = new Combo(this, SWT.READ_ONLY);
		combo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 2, 1));
		
				for (CocomoMethodEnum cm: CocomoMethodEnum.values()) {
					combo.add(cm.toString());
				}
		combo.setText(CocomoMethodEnum.Organic.toString());
		combo.addModifyListener(controller.changeProjectField(ProjectFieldEnum.Cocomomethod));
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	public String getAuthor() {
		return textAuthor.getText();
	}
	
	public void setAuthor(String txt) {
		textAuthor.setText(txt);
	}
	
	public String getCompany() {
		return textCompany.getText();
	}
	
	public void setCompany(String txt) {
		textCompany.setText(txt);
	}
	
	public String getName() {
		return textName.getText();
	}
	
	public void setName(String txt) {
		textName.setText(txt);
	}
	
	public String getLOC() {
		return textLOC.getText().length()>0?textLOC.getText():"0";
	}
	
	public void setLOC(String txt) {
		textLOC.setText(txt);
	}
	
	public String getVAF() {
		return textVAF.getText().length()>0?textVAF.getText():"0";
	}
	
	public void setVAF(String txt) {
		textVAF.setText(txt);
	}
	
	public String getCocomoMethod() {
		return combo.getText();
	}
	
	public void setCocomoMethod(String method) {
		this.combo.setText(method);
	}
	
	public void showProjectNameValidity(boolean valid) {
		if (valid) {
			this.label.setBackground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
		} else {
			this.label.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
		}
	}
}
