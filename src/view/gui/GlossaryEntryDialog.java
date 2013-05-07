package view.gui;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class GlossaryEntryDialog extends Dialog {

	protected String result = "";
	protected Shell shell;
	private Label lblEnterAName;
	private Text text;
	private Button buttonCancel;
	private Button buttonOkay;
	private String txt;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public GlossaryEntryDialog(Shell parent, int style, String txt) {
		super(parent, style);
		setText("Glossary Entry");
		this.txt = txt;
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public String open() {
		createContents();
		text.setText(txt);
		text.setSelection(0, txt.length());
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
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
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shell.setSize(312, 109);
		shell.setText(getText());
		shell.setLayout(new GridLayout(2, false));
		
		lblEnterAName = new Label(shell, SWT.NONE);
		lblEnterAName.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		lblEnterAName.setText("Enter a name for the glossary entry:");
		new Label(shell, SWT.NONE);
		
		text = new Text(shell, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		buttonCancel = new Button(shell, SWT.NONE);
		buttonCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		GridData gd_buttonCancel = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
		gd_buttonCancel.widthHint = 60;
		buttonCancel.setLayoutData(gd_buttonCancel);
		buttonCancel.setText("Cancel");
		
		buttonOkay = new Button(shell, SWT.NONE);
		buttonOkay.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				result = text.getText();
				shell.close();
			}
		});
		GridData gd_buttonOkay = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_buttonOkay.widthHint = 60;
		buttonOkay.setLayoutData(gd_buttonOkay);
		buttonOkay.setText("Okay");

	}

}
