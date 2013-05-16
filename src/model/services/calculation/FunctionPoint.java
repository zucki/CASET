/**
 * 
 */
package model.services.calculation;

import model.data.CalculatedSpecification;
import model.data.DataInterface;
import model.data.InfluencingFactor;
import model.data.ProductData;
import model.data.ProductFunction;
import model.data.SpecificationClassification;

/**
 * @author Markus Zukunft
 * Class for FunctionPoint calculation
 *
 */
public class FunctionPoint extends Calculation {

	private DataInterface data;
	
	@Override
	public CalculationResults calculate(String projectName) {
		
		FunctionPointResults results = new FunctionPointResults();
		
		results.setUnratedPoints(this.calculateUnweightedFP(projectName));
		results.setRatedPoints(results.getUnratedPoints()*this.calculateInfluenceFactors(projectName));
		
		return results;
	}
		
	private int calculateUnweightedFP(String projectName) {
		
		int unweigtedFP = 0;
		
		for (CalculatedSpecification element:this.data.getCalculatedSpecifications(projectName)) {
			
			if (element instanceof ProductFunction) {
				ProductFunction currentElement = (ProductFunction)element;
				
				switch(currentElement.getCategory()) {
				
				case InputData:
					unweigtedFP = unweigtedFP + this.calcInputOrRequest(currentElement.getClassification());
					break;
				case Request:
					unweigtedFP = unweigtedFP + this.calcInputOrRequest(currentElement.getClassification());
					break;
				case Output:
					unweigtedFP = unweigtedFP + this.calcOutput(currentElement.getClassification());
					break;
				case Database:
					unweigtedFP = unweigtedFP + this.calcDatabase(currentElement.getClassification());
					break;
				case ReferenceData:
					unweigtedFP = unweigtedFP + this.calcReference(currentElement.getClassification());
					break;
				}	
			}
			else if(element instanceof ProductData) {
				ProductData currentElement = (ProductData)element;
				
				switch(currentElement.getCategory()) {
				
				case Database:
					unweigtedFP = unweigtedFP + this.calcDatabase(currentElement.getClassification());
					break;
				case ReferenceData:
					unweigtedFP = unweigtedFP + this.calcReference(currentElement.getClassification());
					break;
				}
			}
		}
		return unweigtedFP;
	}
	
	private double calculateInfluenceFactors(String projectName) {
		
		int influenceFactorsSum = 0;
		
		for(InfluencingFactor element:this.data.getInfluencingFactors(projectName)) {
			influenceFactorsSum = influenceFactorsSum + element.getValue();
		}
		
		double factor = influenceFactorsSum/100+0.7;
		return factor;
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
