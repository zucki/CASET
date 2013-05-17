/**
 * 
 */
package model.services.calculation;

/**
 * @author Markus
 *
 */
public interface CalculationInterface {

	/**
	 * @param projectname
	 * @param method
	 * @return
	 */
	public CalculationResults calculate(String projectname, CalculationMethod method);
}
