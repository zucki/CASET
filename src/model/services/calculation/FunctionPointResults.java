package model.services.calculation;

/**
 * @author Markus
 * 
 * container class for the results of a FunctionPoint Calculation
 *
 */
public class FunctionPointResults extends CalculationResults {

	private double _timeToDevelop;
	private double _persons;
	
	/**
	 * 
	 */
	public FunctionPointResults() {
		this._timeToDevelop = 0;
		this._persons = 0;
	}
	
	/**
	 * @return time to develop
	 */
	public double getTimeToDevelop() {
		return _timeToDevelop;
	}
	
	/**
	 * @param timeToDevelop
	 */
	public void setTimeToDevelop(double timeToDevelop) {
		this._timeToDevelop = timeToDevelop;
	}
	
	/**
	 * @return persons
	 */
	public double getPersons() {
		return _persons;
	}
	
	/**
	 * @param persons
	 */
	public void setPersons(double persons) {
		this._persons = persons;
	}
	
	/**
	 * @return the person months
	 */
	public double getPersonMonths() {
		return this._timeToDevelop*this._persons;
	}
	
}
