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

public class SpecificationContentProvider implements IStructuredContentProvider {

	private SpecificationTypeEnum type;
	private ArrayList<Specification> specificationList;
	
	public SpecificationContentProvider(SpecificationTypeEnum type) {
		this.type = type;
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
			specificationList = (ArrayList<Specification>) newInput;
		}
	}

	@Override
	public Object[] getElements(Object arg0) {
		ArrayList<Specification> result = new ArrayList<Specification>();
		for (Specification spec: specificationList) {
			switch(this.type) {
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
