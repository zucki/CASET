package view.gui;

import java.util.ArrayList;

import model.data.GlossaryEntry;
import model.data.ProductData;
import model.data.ProductFunction;
import model.data.ProductPerformance;
import model.data.QualitySpecification;
import model.data.Specification;
import model.data.SpecificationTypeEnum;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * A contentprovider decides what is shown in a jface control
 * @author smgug_000
 *
 */
public class SpecificationContentProvider implements IStructuredContentProvider {

	private SpecificationTypeEnum _type;
	private ArrayList<Specification> _specificationList;
	
	/**
	 * This is a special contentprovider: it only returns specifications
	 * of a specific type
	 * @param type the type to return
	 */
	public SpecificationContentProvider(SpecificationTypeEnum type) {
		this._type = type;
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	@Override
	public void inputChanged(Viewer arg0, Object oldInput, Object newInput) {
		if (newInput instanceof ArrayList<?>) {
			ArrayList<Object> oList = (ArrayList<Object>) newInput;
			_specificationList = (ArrayList<Specification>) newInput;
		}
	}

	@Override
	public Object[] getElements(Object arg0) {
		ArrayList<Specification> result = new ArrayList<Specification>();
		for (Specification spec: _specificationList) {
			switch(this._type) {
			case Data:
				if (spec instanceof ProductData)
					result.add(spec);
				break;
			case Function:
				if (spec instanceof ProductFunction)
					result.add(spec);
				break;
			case Performance:
				if (spec instanceof ProductPerformance)
					result.add(spec);
				break;
			case Quality:
				if (spec instanceof QualitySpecification)
					result.add(spec);
				break;
			}
		}
		return result.toArray();
	}

}
