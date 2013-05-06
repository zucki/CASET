package model.services.calculation;

public class CocomoResults extends CalculationResults {

	private double personMonths;
	private double timeToDev;
	
	public double getPersonMonths() {
		return personMonths;
	}
	
	public void setPersonMonths(double personMonths) {
		this.personMonths = personMonths;
	}
	
	public double getTimeToDev() {
		return timeToDev;
	}
	
	public void setTimeToDev(double timeToDev) {
		this.timeToDev = timeToDev;
	}
}
