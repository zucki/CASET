package model.services.calculation;

import model.data.DataInterface;
import model.data.InfluencingFactor;

public class WeightedFunctionPoint extends FunctionPoint {

	public WeightedFunctionPoint(DataInterface data, String projectName) {
		super(data, projectName);
	}

	@Override
	public CalculationResults calculate() {
		
		double functionPoints = this.calculateUnweightedFP(this.project)*this.calculateInfluenceFactors(this.project);
		
		this.results.setPersons(functionPoints/150);
		this.results.setTimeToDevelop(Math.pow(functionPoints, 0.4));
		return results;
	}
	
	public double calculateInfluenceFactors(String projectName) {
		
		int influenceFactorsSum = 0;
		
		for(InfluencingFactor element:this.data.getInfluencingFactors(projectName)) {
			influenceFactorsSum = influenceFactorsSum + element.getValue();
		}
		
		double factor = influenceFactorsSum/100+0.7;
		return factor;
	}

}
