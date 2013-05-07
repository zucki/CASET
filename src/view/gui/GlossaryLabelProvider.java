package view.gui;

import model.data.GlossaryEntry;

import org.eclipse.jface.viewers.LabelProvider;

public class GlossaryLabelProvider extends LabelProvider {
	@Override
	public String getText(Object element) {
		if (element instanceof GlossaryEntry) {
			GlossaryEntry e = (GlossaryEntry) element;
			return e.getEntry();
		}
		return "INVALID";
	}
}
