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
	private Label _lblNewLabel;
	private Label _lblNewLabel_1;
	private Label _lblNewLabel_2;
	private Label _lblNewLabel_4;
	private Label _lblNewLabel_5;
	private Label _lblNewLabel_6;
	private Group _grpVerarbeitungslogik;
	private Text _interlockingText;
	private Text _decentralDataText;
	private Text _transactionRateText;
	private Text _reusabilityText;
	private Text _databaseConversionText;
	private Text _adaptabilityText;
	private Label _lblRechenoperationen;
	private Label _lblKontrollverfahren;
	private Label _lblAusnahmeregelungen;
	private Label _lblLogik;
	private Text _calculationOperationsText;
	private Text _controlProceduresText;
	private Text _exceptionRulesText;
	private Text _logicText;
	private ControllerInterface _controller;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public InfluencingFactorComposite(Composite parent, int style, ControllerInterface controller) {
		super(parent, style);
		this._controller = controller;
		createContents();
	}
	private void createContents() {
		setLayout(new GridLayout(2, false));
		
		_lblNewLabel = new Label(this, SWT.NONE);
		GridData gd_lblNewLabel = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel.widthHint = 200;
		_lblNewLabel.setLayoutData(gd_lblNewLabel);
		_lblNewLabel.setText("Interlocking:");
		
		_interlockingText = new Text(this, SWT.BORDER);
		_interlockingText.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		
		_lblNewLabel_1 = new Label(this, SWT.NONE);
		_lblNewLabel_1.setText("Decentral Data:");
		
		_decentralDataText = new Text(this, SWT.BORDER);
		_decentralDataText.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		
		_lblNewLabel_2 = new Label(this, SWT.NONE);
		_lblNewLabel_2.setText("Transaction Rate:");
		
		_transactionRateText = new Text(this, SWT.BORDER);
		_transactionRateText.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		
		_grpVerarbeitungslogik = new Group(this, SWT.NONE);
		_grpVerarbeitungslogik.setLayout(new GridLayout(2, false));
		_grpVerarbeitungslogik.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		_grpVerarbeitungslogik.setText("Processing Logic");
		
		_lblRechenoperationen = new Label(_grpVerarbeitungslogik, SWT.NONE);
		GridData gd_lblRechenoperationen = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblRechenoperationen.widthHint = 193;
		_lblRechenoperationen.setLayoutData(gd_lblRechenoperationen);
		_lblRechenoperationen.setText("Calculation Operations:");
		
		_calculationOperationsText = new Text(_grpVerarbeitungslogik, SWT.BORDER);
		_calculationOperationsText.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		
		_lblKontrollverfahren = new Label(_grpVerarbeitungslogik, SWT.NONE);
		_lblKontrollverfahren.setText("Control Procedures:");
		
		_controlProceduresText = new Text(_grpVerarbeitungslogik, SWT.BORDER);
		_controlProceduresText.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		
		_lblAusnahmeregelungen = new Label(_grpVerarbeitungslogik, SWT.NONE);
		_lblAusnahmeregelungen.setText("Exception Rules:");
		
		_exceptionRulesText = new Text(_grpVerarbeitungslogik, SWT.BORDER);
		_exceptionRulesText.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		
		_lblLogik = new Label(_grpVerarbeitungslogik, SWT.NONE);
		_lblLogik.setText("Logic:");
		
		_logicText = new Text(_grpVerarbeitungslogik, SWT.BORDER);
		_logicText.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		
		_lblNewLabel_4 = new Label(this, SWT.NONE);
		_lblNewLabel_4.setText("Reusability:");
		
		_reusabilityText = new Text(this, SWT.BORDER);
		_reusabilityText.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		
		_lblNewLabel_5 = new Label(this, SWT.NONE);
		_lblNewLabel_5.setText("Database-Conversion:");
		
		_databaseConversionText = new Text(this, SWT.BORDER);
		_databaseConversionText.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		
		_lblNewLabel_6 = new Label(this, SWT.NONE);
		_lblNewLabel_6.setText("Adaptability:");
		
		_adaptabilityText = new Text(this, SWT.BORDER);
		_adaptabilityText.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		
		_interlockingText.addModifyListener(_controller.changeInfluencingFactor(InfluencingFactorTypeEnum.Interlocking));
		_interlockingText.addVerifyListener(makeVerifyListener(5));
		_adaptabilityText.addModifyListener(_controller.changeInfluencingFactor(InfluencingFactorTypeEnum.Adaptability));
		_adaptabilityText.addVerifyListener(makeVerifyListener(5));
		_calculationOperationsText.addModifyListener(_controller.changeInfluencingFactor(InfluencingFactorTypeEnum.CalculationOprations));
		_calculationOperationsText.addVerifyListener(makeVerifyListener(10));
		_controlProceduresText.addModifyListener(_controller.changeInfluencingFactor(InfluencingFactorTypeEnum.ControlProcedures));
		_controlProceduresText.addVerifyListener(makeVerifyListener(5));
		_reusabilityText.addModifyListener(_controller.changeInfluencingFactor(InfluencingFactorTypeEnum.Reusability));
		_reusabilityText.addVerifyListener(makeVerifyListener(5));
		_databaseConversionText.addModifyListener(_controller.changeInfluencingFactor(InfluencingFactorTypeEnum.DatabaseConversion));
		_databaseConversionText.addVerifyListener(makeVerifyListener(5));
		_exceptionRulesText.addModifyListener(_controller.changeInfluencingFactor(InfluencingFactorTypeEnum.ExceptionRules));
		_exceptionRulesText.addVerifyListener(makeVerifyListener(10));
		_logicText.addModifyListener(_controller.changeInfluencingFactor(InfluencingFactorTypeEnum.Logic));
		_logicText.addVerifyListener(makeVerifyListener(5));
		_transactionRateText.addModifyListener(_controller.changeInfluencingFactor(InfluencingFactorTypeEnum.TransactionRate));
		_transactionRateText.addVerifyListener(makeVerifyListener(5));
		_decentralDataText.addModifyListener(_controller.changeInfluencingFactor(InfluencingFactorTypeEnum.DecentralData));
		_decentralDataText.addVerifyListener(makeVerifyListener(5));
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
				return _interlockingText.getText();
			case Adaptability:
				return _adaptabilityText.getText();
			case CalculationOprations:
				return _calculationOperationsText.getText();
			case ControlProcedures:
				return _controlProceduresText.getText();
			case DatabaseConversion:
				return _databaseConversionText.getText();
			case DecentralData:
				return _decentralDataText.getText();
			case ExceptionRules:
				return _exceptionRulesText.getText();
			case Logic:
				return _logicText.getText();
			case Reusability:
				return _reusabilityText.getText();
			case TransactionRate:
				return _transactionRateText.getText();
			default:
				return null;
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
