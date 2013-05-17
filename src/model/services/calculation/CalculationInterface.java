/**
 * 
 */
package model.services.calculation;

/**
 * @author Aaron
 *
 */
public interface CalculationInterface {

	public CalculationResults calculate(String projectname, CalculationMethod method);
}
