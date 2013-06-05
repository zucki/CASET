package view.gui;

import model.services.calculation.CalculationMethod;
import model.services.calculation.CalculationResults;
import model.services.calculation.CocomoResults;
import model.services.calculation.FunctionPointResults;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import controller.ControllerInterface;

public class CalculationDialog extends Dialog {

	protected Object result;
	protected Shell shlCalculation;
	private Text text;
	private ControllerInterface controller;
	private Combo combo;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public CalculationDialog(Shell parent, int style, ControllerInterface controller) {
		super(parent, style);
		this.controller = controller;
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlCalculation.open();
		shlCalculation.layout();
		Display display = getParent().getDisplay();
		while (!shlCalculation.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shlCalculation = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shlCalculation.setSize(450, 300);
		shlCalculation.setText("Calculation");
		shlCalculation.setLayout(new GridLayout(2, false));
		
		Label lblCalculationMethod = new Label(shlCalculation, SWT.NONE);
		lblCalculationMethod.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCalculationMethod.setText("Calculation Method:");
		
		combo = new Combo(shlCalculation, SWT.READ_ONLY);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		text = new Text(shlCalculation, SWT.BORDER | SWT.READ_ONLY | SWT.MULTI);
		text.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));

		for (CalculationMethod method: CalculationMethod.values()) {
			combo.add(method.toString());
		}
		combo.select(0);
		new Label(shlCalculation, SWT.NONE);
		
		Button calculationButton = new Button(shlCalculation, SWT.NONE);
		calculationButton.addSelectionListener(controller.startCalculation());
		GridData gd_calculationButton = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_calculationButton.widthHint = 80;
		calculationButton.setLayoutData(gd_calculationButton);
		calculationButton.setText("Calculate");
	}
	
	/**
	 * @return the currently chosen calculationmethod
	 */
	public CalculationMethod getCalculationMethod() {
		return CalculationMethod.values()[combo.getSelectionIndex()];
	}
	
	/**
	 * Shows the results of a calculation in the textbox
	 * @param results the results to show
	 */
	public void setCalculationResult(CalculationResults results) {
		if (results instanceof CocomoResults) {
			showCocomoReport((CocomoResults) results);
		} else if (results instanceof FunctionPointResults) {
			showFunctionPointReport((FunctionPointResults)results);
		}
	}
	
	private void showCocomoReport(CocomoResults results) {
		text.setText("");
		text.append(String.format("Time to develop: %s\n", String.valueOf(results.getTimeToDev())));
		text.append(String.format("Person Months:   %s\n", String.valueOf(results.getPersonMonths())));
	}
	
	private void showFunctionPointReport(FunctionPointResults results) {
		text.setText("");
		text.append(String.format("Time to develop: %s\n", String.valueOf(results.getTimeToDevelop())));
		text.append(String.format("Persons:         %s\n", String.valueOf(results.getPersons())));
		text.append(String.format("Person Months:   %s\n", String.valueOf(results.getPersonMonths())));
	}
}
