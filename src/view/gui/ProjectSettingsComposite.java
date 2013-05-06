package view.gui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;

public class ProjectSettingsComposite extends Composite {
	private Label lblName;
	private Label lblAuthor;
	private Label lblCompany;
	private Label lblLinesOfCode;
	private Label lblValueAdjustmentFactor;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ProjectSettingsComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(2, false));
		
		lblName = new Label(this, SWT.NONE);
		lblName.setText("Name:");
		
		text = new Text(this, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblAuthor = new Label(this, SWT.NONE);
		lblAuthor.setText("Author:");
		
		text_1 = new Text(this, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblCompany = new Label(this, SWT.NONE);
		lblCompany.setText("Company:");
		
		text_2 = new Text(this, SWT.BORDER | SWT.MULTI);
		GridData gd_text_2 = new GridData(SWT.FILL, SWT.TOP, true, false, 1, 3);
		gd_text_2.heightHint = 56;
		text_2.setLayoutData(gd_text_2);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		
		lblValueAdjustmentFactor = new Label(this, SWT.NONE);
		lblValueAdjustmentFactor.setText("Value adjustment factor:");
		
		text_4 = new Text(this, SWT.BORDER);
		text_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblLinesOfCode = new Label(this, SWT.NONE);
		lblLinesOfCode.setText("Lines of code:");
		
		text_3 = new Text(this, SWT.BORDER);
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
