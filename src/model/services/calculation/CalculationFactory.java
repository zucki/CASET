package model.services.calculation;

import model.data.DataInterface;



public class CalculationFactory {

	public  Calculation createCalculation(CalculationMethod type, DataInterface data, String projectName) {
		
		switch(type) {
		
		case Cocomo:
			return new Cocomo(data, projectName);
		case FunctionPointUnweighted:
			return new UnweightedFunctionPoint(data, projectName);
		case FunctionPointWeighted:
			return new WeightedFunctionPoint(data, projectName);
		default:
			return null;
		}
	}
}
