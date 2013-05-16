/**
 * 
 */
package model.services.calculation;

/**
 * @author smgug_000
 *
 */
public class Calculation implements CalculationInterface {

	/**
	 * Default-Constructor.
	 */
	public Calculation() {
		
	}

	/* (non-Javadoc)
	 * @see model.services.calculation.CalculationInterface#calculate(java.lang.String, model.services.calculation.CalculationMethod)
	 */
	@Override
	public CalculationResults calculate(String projectName,
			CalculationMethod method) {
		// TODO Auto-generated method stub

		if (method == CalculationMethod.Cocomo) {
			Cocomo cocomoCalcFunction = new Cocomo();
			return cocomoCalcFunction.calculate(projectName);
		}
		else if (method == CalculationMethod.FunctionPointUnweighted) {
			FunctionPoint fpCalc = new FunctionPoint();
			return fpCalc.calculate(projectName, false);
		}
		else if (method ==CalculationMethod.FunctionPointWeighted){
			FunctionPoint fpCalc = new FunctionPoint();
			return fpCalc.calculate(projectName, true);
		}
		else {
			return null;
		}
	}
	
}
