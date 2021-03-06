package model.services.calculation;

import model.data.DataInterface;
import model.data.InfluencingFactor;

/**
 * @author Markus
 * 
 * class to calculate Function Points with influence factors
 *
 */
public class WeightedFunctionPoint extends FunctionPoint {

	/**
	 * @param data: An interface to access the data
	 * @param projectName
	 */
	public WeightedFunctionPoint(DataInterface data, String projectName) {
		super(data, projectName);
	}

	
	/* (non-Javadoc)
	 * @see model.services.calculation.FunctionPoint#calculate()
	 * 
	 * calculation by Jones (1996)
	 */
	@Override
	public CalculationResults calculate() {
		
		double functionPoints = this.calculateUnweightedFP(this._project)*this.calculateInfluenceFactors(this._project);
		
		this._results.setPersons(functionPoints/150.0);
		this._results.setTimeToDevelop(Math.pow(functionPoints, 0.4));
		return _results;
	}
	
	/**
	 * @param projectName
	 * @return the sum of the influence factors
	 */
	public double calculateInfluenceFactors(String projectName) {
		
		int influenceFactorsSum = 0;
		
		for(InfluencingFactor element:this._data.getInfluencingFactors(projectName)) {
			influenceFactorsSum = influenceFactorsSum + element.getValue();
		}
		
		double factor = (double)influenceFactorsSum/100.0+0.7;
		return factor;
	}

}
