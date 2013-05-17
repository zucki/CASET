package model.services.calculation;

import model.data.DataInterface;



/**
 * @author Markus
 *
 */
public class CalculationFactory {

	/**
	 * creates a new Calculation Method depending on the user choice
	 * 
	 * @param type: the Calculation Method (Cocomo, unweighted FunctionPoint and weighted FunctionPoint)
	 * @param data
	 * @param projectName
	 * @return an Instance of the chosen Calculation Method
	 */
	public  Calculation createCalculation(CalculationMethod type, DataInterface data, String projectName) {
		
		switch(type) {
		
		case Cocomo:
			return new Cocomo(data, projectName);
		case FunctionPointUnweighted:
			return new UnweightedFunctionPoint(data, projectName);
		case FunctionPointWeighted:
			return new WeightedFunctionPoint(data, projectName);
		default:
			return null;
		}
	}
}
