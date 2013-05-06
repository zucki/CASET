package view.gui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.TabItem;

public class ProjectComposite extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ProjectComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(1, false));
		
		TabFolder tabFolder = new TabFolder(this, SWT.BOTTOM);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("Zielbestimmung");
		
		TabItem tbtmNewItem_1 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_1.setText("Produkteinsatz");
		
		TabItem tbtmNewItem_2 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_2.setText("Produktfunktionen");
		
		TabItem tbtmNewItem_3 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_3.setText("Produktdaten");
		
		TabItem tbtmNewItem_4 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_4.setText("Produktleistungen");
		
		TabItem tbtmNewItem_5 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_5.setText("Qualit\u00E4tsanforderungen");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
