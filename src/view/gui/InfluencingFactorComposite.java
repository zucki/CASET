package view.gui;

import model.data.InfluencingFactorTypeEnum;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;

import controller.ControllerInterface;

public class InfluencingFactorComposite extends Composite {
	private Label lblNewLabel;
	private Label lblNewLabel_1;
	private Label lblNewLabel_2;
	private Label lblNewLabel_4;
	private Label lblNewLabel_5;
	private Label lblNewLabel_6;
	private Group grpVerarbeitungslogik;
	private Text interlockingText;
	private Text decentralDataText;
	private Text transactionRateText;
	private Text reusabilityText;
	private Text databaseConversionText;
	private Text adaptabilityText;
	private Label lblRechenoperationen;
	private Label lblKontrollverfahren;
	private Label lblAusnahmeregelungen;
	private Label lblLogik;
	private Text calculationOperationsText;
	private Text controlProceduresText;
	private Text exceptionRulesText;
	private Text logicText;
	private ControllerInterface controller;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public InfluencingFactorComposite(Composite parent, int style, ControllerInterface controller) {
		super(parent, style);
		this.controller = controller;
		createContents();
	}
	private void createContents() {
		setLayout(new GridLayout(2, false));
		
		lblNewLabel = new Label(this, SWT.NONE);
		GridData gd_lblNewLabel = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel.widthHint = 200;
		lblNewLabel.setLayoutData(gd_lblNewLabel);
		lblNewLabel.setText("Interlocking:");
		
		interlockingText = new Text(this, SWT.BORDER);
		interlockingText.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		
		lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setText("Decentral Data:");
		
		decentralDataText = new Text(this, SWT.BORDER);
		decentralDataText.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		
		lblNewLabel_2 = new Label(this, SWT.NONE);
		lblNewLabel_2.setText("Transaction Rate:");
		
		transactionRateText = new Text(this, SWT.BORDER);
		transactionRateText.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		
		grpVerarbeitungslogik = new Group(this, SWT.NONE);
		grpVerarbeitungslogik.setLayout(new GridLayout(2, false));
		grpVerarbeitungslogik.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		grpVerarbeitungslogik.setText("Processing Logic");
		
		lblRechenoperationen = new Label(grpVerarbeitungslogik, SWT.NONE);
		GridData gd_lblRechenoperationen = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblRechenoperationen.widthHint = 193;
		lblRechenoperationen.setLayoutData(gd_lblRechenoperationen);
		lblRechenoperationen.setText("Calculation Operations:");
		
		calculationOperationsText = new Text(grpVerarbeitungslogik, SWT.BORDER);
		calculationOperationsText.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		
		lblKontrollverfahren = new Label(grpVerarbeitungslogik, SWT.NONE);
		lblKontrollverfahren.setText("Control Procedures:");
		
		controlProceduresText = new Text(grpVerarbeitungslogik, SWT.BORDER);
		controlProceduresText.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		
		lblAusnahmeregelungen = new Label(grpVerarbeitungslogik, SWT.NONE);
		lblAusnahmeregelungen.setText("Exception Rules:");
		
		exceptionRulesText = new Text(grpVerarbeitungslogik, SWT.BORDER);
		exceptionRulesText.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		
		lblLogik = new Label(grpVerarbeitungslogik, SWT.NONE);
		lblLogik.setText("Logic:");
		
		logicText = new Text(grpVerarbeitungslogik, SWT.BORDER);
		logicText.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		
		lblNewLabel_4 = new Label(this, SWT.NONE);
		lblNewLabel_4.setText("Reusability:");
		
		reusabilityText = new Text(this, SWT.BORDER);
		reusabilityText.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		
		lblNewLabel_5 = new Label(this, SWT.NONE);
		lblNewLabel_5.setText("Database-Conversion:");
		
		databaseConversionText = new Text(this, SWT.BORDER);
		databaseConversionText.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		
		lblNewLabel_6 = new Label(this, SWT.NONE);
		lblNewLabel_6.setText("Adaptability:");
		
		adaptabilityText = new Text(this, SWT.BORDER);
		adaptabilityText.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		
		interlockingText.addModifyListener(controller.changeInfluencingFactor(InfluencingFactorTypeEnum.Interlocking));
		interlockingText.addVerifyListener(makeVerifyListener(5));
		adaptabilityText.addModifyListener(controller.changeInfluencingFactor(InfluencingFactorTypeEnum.Adaptability));
		adaptabilityText.addVerifyListener(makeVerifyListener(5));
		calculationOperationsText.addModifyListener(controller.changeInfluencingFactor(InfluencingFactorTypeEnum.CalculationOprations));
		calculationOperationsText.addVerifyListener(makeVerifyListener(10));
		controlProceduresText.addModifyListener(controller.changeInfluencingFactor(InfluencingFactorTypeEnum.ControlProcedures));
		controlProceduresText.addVerifyListener(makeVerifyListener(5));
		reusabilityText.addModifyListener(controller.changeInfluencingFactor(InfluencingFactorTypeEnum.Reusability));
		reusabilityText.addVerifyListener(makeVerifyListener(5));
		databaseConversionText.addModifyListener(controller.changeInfluencingFactor(InfluencingFactorTypeEnum.DatabaseConversion));
		databaseConversionText.addVerifyListener(makeVerifyListener(5));
		exceptionRulesText.addModifyListener(controller.changeInfluencingFactor(InfluencingFactorTypeEnum.ExceptionRules));
		exceptionRulesText.addVerifyListener(makeVerifyListener(10));
		logicText.addModifyListener(controller.changeInfluencingFactor(InfluencingFactorTypeEnum.Logic));
		logicText.addVerifyListener(makeVerifyListener(5));
		transactionRateText.addModifyListener(controller.changeInfluencingFactor(InfluencingFactorTypeEnum.TransactionRate));
		transactionRateText.addVerifyListener(makeVerifyListener(5));
		decentralDataText.addModifyListener(controller.changeInfluencingFactor(InfluencingFactorTypeEnum.DecentralData));
		decentralDataText.addVerifyListener(makeVerifyListener(5));
	}
	
	private VerifyListener makeVerifyListener(final int max) {
		return new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				Text text = (Text)e.getSource();
				System.out.println(text.getText()+e.text);
				e.doit = (e.text.matches("[0-9]+")
							&& (Integer.parseInt(text.getText()+e.text) <= max))
							|| e.character == '\b';
			}
		};
	}
	
	public String getInfluencingFactor(InfluencingFactorTypeEnum type) {
		switch (type) {
			case Interlocking:
				return interlockingText.getText();
			case Adaptability:
				return adaptabilityText.getText();
			case CalculationOprations:
				return calculationOperationsText.getText();
			case ControlProcedures:
				return controlProceduresText.getText();
			case DatabaseConversion:
				return databaseConversionText.getText();
			case DecentralData:
				return decentralDataText.getText();
			case ExceptionRules:
				return exceptionRulesText.getText();
			case Logic:
				return logicText.getText();
			case Reusability:
				return reusabilityText.getText();
			case TransactionRate:
				return transactionRateText.getText();
			default:
				return null;
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
