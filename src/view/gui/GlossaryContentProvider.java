package view.gui;

import java.util.ArrayList;

import model.data.GlossaryEntry;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class GlossaryContentProvider implements IStructuredContentProvider {
	private ArrayList<GlossaryEntry> entries;
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void inputChanged(Viewer arg0, Object oldInput, Object newInput) {
		if (newInput instanceof ArrayList<?>) {
			ArrayList<Object> oList = (ArrayList<Object>) newInput;
			entries = (ArrayList<GlossaryEntry>) newInput;
		}
	}
	
	public Object[] getElements(Object arg0) {
		return entries.toArray();
	}

}
