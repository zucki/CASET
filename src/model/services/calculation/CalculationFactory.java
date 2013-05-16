package model.services.calculation;



public class CalculationFactory {

	public  Calculation createCocomo() {
		return new Cocomo();
	}
	
	public  Calculation createFunctionPoint() {
		return null;
	}
}
