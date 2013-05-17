package view.gui;

import java.util.ArrayList;

import model.data.GlossaryEntry;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * A contentprovider decides what is shown in a jface control
 * @author smgug_000
 *
 */
public class GlossaryContentProvider implements IStructuredContentProvider {
	private ArrayList<GlossaryEntry> _entries;
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void inputChanged(Viewer arg0, Object oldInput, Object newInput) {
		if (newInput instanceof ArrayList<?>) {
			ArrayList<Object> oList = (ArrayList<Object>) newInput;
			_entries = (ArrayList<GlossaryEntry>) newInput;
		}
	}
	
	public Object[] getElements(Object arg0) {
		return _entries.toArray();
	}

}
