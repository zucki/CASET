package model.services.calculation;

import model.data.DataInterface;

public class UnweightedFunctionPoint extends FunctionPoint {
	
	UnweightedFunctionPoint(DataInterface data, String projectName) {
		super(data, projectName);
	}

	@Override
	public CalculationResults calculate() {
		
		double functionPoints = this.calculateUnweightedFP(this.project);
		
		this.results.setPersons(functionPoints/150);
		this.results.setTimeToDevelop(Math.pow(functionPoints, 0.4));
		return results;
	}

}
