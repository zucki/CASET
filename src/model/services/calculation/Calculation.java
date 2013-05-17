package model.services.calculation;

/**
 * @author Markus
 * 
 * 
 * Superclass for cost estimation methods: FunctionPoint and Cocomo
 */
public abstract class Calculation {

	/**
	 * calculate the cost of a project based on the Cocomo or FunctionPoint method 
	 * 
	 * @return cost of the project depending of the selected method
	 * 			for Cocomo: 		Time to Develop and Person Months
	 * 			for FunctionPoint:	Time to Develop, persons and Person Months
	 */
	public abstract CalculationResults calculate();
}
