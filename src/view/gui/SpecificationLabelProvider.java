package view.gui;

import model.data.Specification;

import org.eclipse.jface.viewers.LabelProvider;

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
