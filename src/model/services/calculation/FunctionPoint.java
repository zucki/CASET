/**
 * 
 */
package model.services.calculation;

import model.data.CalculatedSpecification;
import model.data.DataInterface;
import model.data.ProductData;
import model.data.ProductFunction;
import model.data.SpecificationClassificationEnum;

/**
 * @author Markus Zukunft
 * Class for FunctionPoint calculation
 *
 */
public class FunctionPoint extends Calculation{

	protected DataInterface data;
	protected String project;
	protected FunctionPointResults results;
	
	public FunctionPoint(DataInterface data, String projectName) {
		this.data = data;
		this.project = projectName;
	}
	
	/* (non-Javadoc)
	 * @see model.services.calculation.Calculation#calculate()
	 */
	@Override
	public CalculationResults calculate() {
		// TODO Auto-generated method stub
		return null;
	}
		
	/**
	 * calculate the number of unweighted FunctionPoints
	 * @param projectName
	 * @return 
	 */
	protected double calculateUnweightedFP(String projectName) {
		
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

	/**
	 * 
	 * @param classification
	 * @return
	 */
	private int calcInputOrRequest(SpecificationClassificationEnum classification) {
		
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
	
	/**
	 * @param classification
	 * @return
	 */
	private int calcOutput(SpecificationClassificationEnum classification) {
		
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
	
	/**
	 * @param classification
	 * @return
	 */
	private int calcDatabase(SpecificationClassificationEnum classification) {
		
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
	
	/**
	 * @param classification
	 * @return
	 */
	private int calcReference(SpecificationClassificationEnum classification) {
		
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
