package model.services.calculation;

import model.data.DataInterface;

/**
 * @author Markus
 *
 */
public class UnweightedFunctionPoint extends FunctionPoint {
	
	/**
	 * @param data
	 * @param projectName
	 */
	public UnweightedFunctionPoint(DataInterface data, String projectName) {
		super(data, projectName);
	}

	/* (non-Javadoc)
	 * @see model.services.calculation.FunctionPoint#calculate()
	 */
	@Override
	public CalculationResults calculate() {
		
		double functionPoints = this.calculateUnweightedFP(this.project);
		
		this.results.setPersons(functionPoints/150);
		this.results.setTimeToDevelop(Math.pow(functionPoints, 0.4));
		return results;
	}

}
