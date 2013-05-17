package view.gui;

import model.data.GlossaryEntry;

import org.eclipse.jface.viewers.LabelProvider;

/**
 * A labelprovider decides how the content of a jface control is displayed
 * @author smgug_000
 *
 */
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
