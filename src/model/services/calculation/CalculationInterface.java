/**
 * 
 */
package model.services.calculation;

import model.data.DataInterface;

/**
 * @author Aaron
 *
 */
public interface CalculationInterface {

	public CalculationResults calculate(DataInterface data, String projectname, CalculationMethod method);
}
