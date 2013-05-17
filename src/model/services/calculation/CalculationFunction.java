/**
 * 
 */
package model.services.calculation;

import model.data.DataInterface;

/**
 * @author smgug_000
 *
 */
public class CalculationFunction implements CalculationInterface {

	/**
	 * Default-Constructor.
	 */
	public CalculationFunction() {
		
	}

	/* (non-Javadoc)
	 * @see model.services.calculation.CalculationInterface#calculate(java.lang.String, model.services.calculation.CalculationMethod)
	 */
	@Override
	public CalculationResults calculate(DataInterface data, String projectName,
			CalculationMethod method) {

			CalculationFactory calcFac = new CalculationFactory();
			Calculation calc = calcFac.createCalculation(method, data, projectName);
			return calc.calculate();
	}
	
}
