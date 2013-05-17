package model.services.calculation;

import model.data.DataInterface;
import model.data.InfluencingFactor;

/**
 * @author Markus
 *
 */
public class WeightedFunctionPoint extends FunctionPoint {

	/**
	 * @param data
	 * @param projectName
	 */
	public WeightedFunctionPoint(DataInterface data, String projectName) {
		super(data, projectName);
	}

	/* (non-Javadoc)
	 * @see model.services.calculation.FunctionPoint#calculate()
	 */
	@Override
	public CalculationResults calculate() {
		
		double functionPoints = this.calculateUnweightedFP(this.project)*this.calculateInfluenceFactors(this.project);
		
		this.results.setPersons(functionPoints/150);
		this.results.setTimeToDevelop(Math.pow(functionPoints, 0.4));
		return results;
	}
	
	/**
	 * @param projectName
	 * @return
	 */
	public double calculateInfluenceFactors(String projectName) {
		
		int influenceFactorsSum = 0;
		
		for(InfluencingFactor element:this.data.getInfluencingFactors(projectName)) {
			influenceFactorsSum = influenceFactorsSum + element.getValue();
		}
		
		double factor = influenceFactorsSum/100+0.7;
		return factor;
	}

}
