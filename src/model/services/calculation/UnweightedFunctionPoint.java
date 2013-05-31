package model.services.calculation;

import model.data.DataInterface;

/**
 * @author Markus
 * 
 *  class to calculate Function Points without influence factors
 *
 */
public class UnweightedFunctionPoint extends FunctionPoint {
	
	/**
	 * @param data: An interface to access the data
	 * @param projectName
	 */
	public UnweightedFunctionPoint(DataInterface data, String projectName) {
		super(data, projectName);
	}

	
	/* (non-Javadoc)
	 * @see model.services.calculation.FunctionPoint#calculate()
	 * 
	 * calculation by Jones (1996) 
	 */
	@Override
	public CalculationResults calculate() {
		
		double functionPoints = this.calculateUnweightedFP(this._project);
		
		this._results.setPersons(functionPoints/150);
		this._results.setTimeToDevelop(Math.pow(functionPoints, 0.4));
		return _results;
	}

}
