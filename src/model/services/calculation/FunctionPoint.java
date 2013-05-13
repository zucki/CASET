/**
 * 
 */
package model.services.calculation;

import model.data.CalculatedSpecification;
import model.data.DataInterface;
import model.data.ProductData;
import model.data.ProductFunction;
import model.data.SpecificationClassification;

/**
 * @author smgug_000
 *
 */
public class FunctionPoint extends Calculation {

	private DataInterface data;
	
	@Override
	public CalculationResults calculate(String projectName) {
		
		FunctionPointResults results = new FunctionPointResults();
		int unweigted_FP = 0;
		
		for (CalculatedSpecification element:this.data.getCalculatedSpecifications(projectName)) {
			
			if (element instanceof ProductFunction) {
				ProductFunction currentElement = (ProductFunction)element;
				
				switch(currentElement.getCategory()) {
				
				case InputData:
					unweigted_FP = unweigted_FP + this.calcInputOrRequest(currentElement.getClassification());
					break;
				case Request:
					unweigted_FP = unweigted_FP + this.calcInputOrRequest(currentElement.getClassification());
					break;
				case Output:
					unweigted_FP = unweigted_FP + this.calcOutput(currentElement.getClassification());
					break;
				case Database:
					unweigted_FP = unweigted_FP + this.calcDatabase(currentElement.getClassification());
					break;
				case ReferenceData:
					unweigted_FP = unweigted_FP + this.calcReference(currentElement.getClassification());
					break;
				}	
			}
			else if(element instanceof ProductData) {
				ProductData currentElement = (ProductData)element;
				
				switch(currentElement.getCategory()) {
				
				case Database:
					unweigted_FP = unweigted_FP + this.calcDatabase(currentElement.getClassification());
					break;
				case ReferenceData:
					unweigted_FP = unweigted_FP + this.calcReference(currentElement.getClassification());
					break;
				}
			}
		}
		results.setRatedPoints(unweigted_FP);
		return results;
	}

	private int calcInputOrRequest(SpecificationClassification classification) {
		
		switch(classification) {
		
		case Simple:
			return 3;
		case Medium:
			return 4;
		case Complex:
			return 6;
		default:
			return 0;
		}
	}
	
	private int calcOutput(SpecificationClassification classification) {
		
		switch(classification) {
		
		case Simple:
			return 4;
		case Medium:
			return 5;
		case Complex:
			return 7;
		default:
			return 0;
		}
	}
	
	private int calcDatabase(SpecificationClassification classification) {
		
		switch(classification) {
		
		case Simple:
			return 7;
		case Medium:
			return 10;
		case Complex:
			return 15;
		default:
			return 0;
		}
	}
	
private int calcReference(SpecificationClassification classification) {
		
		switch(classification) {
		
		case Simple:
			return 5;
		case Medium:
			return 7;
		case Complex:
			return 10;
		default:
			return 0;
		}
	}

}
