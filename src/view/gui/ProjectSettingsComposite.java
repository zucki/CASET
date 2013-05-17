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
	private Label _lblName;
	private Label _lblAuthor;
	private Label _lblCompany;
	private Label _lblLinesOfCode;
	private Label _lblValueAdjustmentFactor;
	private Text _textName;
	private Text _textAuthor;
	private Text _textCompany;
	private Text _textLOC;
	private Text _textVAF;
	private Label _lblCocomoMethod;
	private Combo _combo;
	private Label _label;
	private ControllerInterface _controller;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ProjectSettingsComposite(Composite parent, int style, ControllerInterface controller) {
		super(parent, style);
		this._controller = controller;
		createContents();
	}
	
	private void createContents() {
		setLayout(new GridLayout(3, false));
		
		_lblName = new Label(this, SWT.NONE);
		_lblName.setText("Name:");
		
		_textName = new Text(this, SWT.BORDER);
		_textName.addModifyListener(_controller.changeProjectName());
		_textName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		_label = new Label(this, SWT.NONE);
		GridData gd_label = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_label.heightHint = 20;
		gd_label.widthHint = 20;
		_label.setLayoutData(gd_label);
		_label.setBackground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
		_label.setText("     ");
		
		_lblAuthor = new Label(this, SWT.NONE);
		_lblAuthor.setText("Author:");
		
		_textAuthor = new Text(this, SWT.BORDER);
		_textAuthor.addModifyListener(_controller.changeProjectField(ProjectFieldEnum.Author));
		_textAuthor.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		_lblCompany = new Label(this, SWT.NONE);
		_lblCompany.setText("Company:");
		
		_textCompany = new Text(this, SWT.BORDER | SWT.MULTI);
		_textCompany.addModifyListener(_controller.changeProjectField(ProjectFieldEnum.Company));
		GridData gd_textCompany = new GridData(SWT.FILL, SWT.TOP, true, false, 2, 3);
		gd_textCompany.heightHint = 56;
		_textCompany.setLayoutData(gd_textCompany);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		
		_lblValueAdjustmentFactor = new Label(this, SWT.NONE);
		_lblValueAdjustmentFactor.setText("Value adjustment factor:");
		
		_textVAF = new Text(this, SWT.BORDER);
		_textVAF.addModifyListener(_controller.changeProjectField(ProjectFieldEnum.ValueAdjustmentFactor));
		_textVAF.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				e.doit = (e.text.matches("[0-9]") 
						|| (e.text.matches("\\.") && !_textVAF.getText().contains("."))
						|| (e.text.matches("[0-9]+\\.[0-9]+") && !_textVAF.getText().contains("."))
						|| e.character == '\b')
						&& (_textVAF.getText().length() + e.text.length() < 20);
			}
		});
		_textVAF.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		_lblLinesOfCode = new Label(this, SWT.NONE);
		_lblLinesOfCode.setText("Lines of code:");
		
		_textLOC = new Text(this, SWT.BORDER);
		_textLOC.addModifyListener(_controller.changeProjectField(ProjectFieldEnum.LinesOfCode));
		_textLOC.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				e.doit = (e.text.matches("[0-9]+")
							|| e.character == '\b')
							&& (_textLOC.getText().length() + e.text.length() < 10);
			}
		});
		_textLOC.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		_lblCocomoMethod = new Label(this, SWT.NONE);
		_lblCocomoMethod.setText("Cocomo Method:");
		
		_combo = new Combo(this, SWT.READ_ONLY);
		_combo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 2, 1));
		
				for (CocomoMethodEnum cm: CocomoMethodEnum.values()) {
					_combo.add(cm.toString());
				}
		_combo.setText(CocomoMethodEnum.Organic.toString());
		_combo.addModifyListener(_controller.changeProjectField(ProjectFieldEnum.Cocomomethod));
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	public String getAuthor() {
		return _textAuthor.getText();
	}
	
	public void setAuthor(String txt) {
		_textAuthor.setText(txt);
	}
	
	public String getCompany() {
		return _textCompany.getText();
	}
	
	public void setCompany(String txt) {
		_textCompany.setText(txt);
	}
	
	public String getName() {
		return _textName.getText();
	}
	
	public void setName(String txt) {
		_textName.setText(txt);
	}
	
	public String getLOC() {
		return _textLOC.getText().length()>0?_textLOC.getText():"0";
	}
	
	public void setLOC(String txt) {
		_textLOC.setText(txt);
	}
	
	public String getVAF() {
		return _textVAF.getText().length()>0?_textVAF.getText():"0";
	}
	
	public void setVAF(String txt) {
		_textVAF.setText(txt);
	}
	
	public String getCocomoMethod() {
		return _combo.getText();
	}
	
	public void setCocomoMethod(String method) {
		this._combo.setText(method);
	}
	
	public void showProjectNameValidity(boolean valid) {
		if (valid) {
			this._label.setBackground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
		} else {
			this._label.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
		}
	}
}
