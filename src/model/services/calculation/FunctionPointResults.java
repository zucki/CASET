package model.services.calculation;

/**
 * @author Markus
 * 
 * container class for the results of a FunctionPoint Calculation
 *
 */
public class FunctionPointResults extends CalculationResults {

	private double timeToDevelop;
	private double persons;
	
	/**
	 * 
	 */
	public FunctionPointResults() {
		this.timeToDevelop = 0;
		this.persons = 0;
	}
	
	/**
	 * @return time to develop
	 */
	public double getTimeToDevelop() {
		return timeToDevelop;
	}
	
	/**
	 * @param timeToDevelop
	 */
	public void setTimeToDevelop(double timeToDevelop) {
		this.timeToDevelop = timeToDevelop;
	}
	
	/**
	 * @return persons
	 */
	public double getPersons() {
		return persons;
	}
	
	/**
	 * @param persons
	 */
	public void setPersons(double persons) {
		this.persons = persons;
	}
	
	/**
	 * @return the person months
	 */
	public double getPersonMonths() {
		return this.timeToDevelop*this.persons;
	}
	
}
