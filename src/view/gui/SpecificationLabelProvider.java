package view.gui;

import model.data.Specification;

import org.eclipse.jface.viewers.LabelProvider;

/**
 * A labelprovider decides how the content of a jface control is displayed
 * @author smgug_000
 *
 */
public class SpecificationLabelProvider extends LabelProvider {
	@Override
	public String getText(Object element) {
		if (element instanceof Specification) {
			Specification s = (Specification) element;
			return s.getName();
		}
		return "INVALID";
	}
}
