package model.services.calculation;

/**
 * @author Markus
 *
 */
public class CocomoResults extends CalculationResults {

	private double _personMonths;
	private double _timeToDev;
	
	
	/**
	 * @return Person Months
	 */
	public double getPersonMonths() {
		return _personMonths;
	}
	
	/**
	 * @param personMonths
	 */
	public void setPersonMonths(double personMonths) {
		this._personMonths = personMonths;
	}
	
	/**
	 * @return time to develop
	 */
	public double getTimeToDev() {
		return _timeToDev;
	}
	
	/**
	 * @param timeToDev
	 */
	public void setTimeToDev(double timeToDev) {
		this._timeToDev = timeToDev;
	}
}
