package model.services.calculation;

public class FunctionPointResults extends CalculationResults {

	private double timeToDevelop;
	private double persons;
	
	public FunctionPointResults() {
		this.timeToDevelop = 0;
		this.persons = 0;
	}
	
	public double getTimeToDevelop() {
		return timeToDevelop;
	}
	
	public void setTimeToDevelop(double timeToDevelop) {
		this.timeToDevelop = timeToDevelop;
	}
	
	public double getPersons() {
		return persons;
	}
	
	public void setPersons(double persons) {
		this.persons = persons;
	}
	
	public double getPersonMonths() {
		return this.timeToDevelop*this.persons;
	}
	
}
