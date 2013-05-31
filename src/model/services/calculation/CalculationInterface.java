/**
 * 
 */
package model.services.calculation;

/**
 * @author Markus
 * Interface for the Calculations
 */
public interface CalculationInterface {

	/**
	 * @param projectname: The name of the Project for which the calculation is to be performed
	 * @param method: the type of the Calculation (Cocomo, UnweightedFunctionPoint, WeightedFunctionPoint)
	 * @return the calculation results: person months and time to develop
	 */
	public CalculationResults calculate(String projectname, CalculationMethod method);
}
