/**
 * 
 */
package model.services.calculation;

import model.data.DataInterface;

/**
 * @author smgug_000
 *
 */
public class CalculationFacade implements CalculationInterface {

	private DataInterface data;
	/**
	 * Default-Constructor.
	 */
	public CalculationFacade(DataInterface dataInt) {
		this.data = dataInt;
	}

	/* (non-Javadoc)
	 * @see model.services.calculation.CalculationInterface#calculate(java.lang.String, model.services.calculation.CalculationMethod)
	 */
	@Override
	public CalculationResults calculate(String projectName,
			CalculationMethod method) {

			CalculationFactory calcFac = new CalculationFactory();
			Calculation calc = calcFac.createCalculation(method, this.data, projectName);
			return calc.calculate();
	}
	
}
