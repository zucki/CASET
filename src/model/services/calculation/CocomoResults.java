package model.services.calculation;

/**
 * @author Markus
 *
 */
public class CocomoResults extends CalculationResults {

	private double personMonths;
	private double timeToDev;
	
	
	/**
	 * @return Person Months
	 */
	public double getPersonMonths() {
		return personMonths;
	}
	
	/**
	 * @param personMonths
	 */
	public void setPersonMonths(double personMonths) {
		this.personMonths = personMonths;
	}
	
	/**
	 * @return time to develop
	 */
	public double getTimeToDev() {
		return timeToDev;
	}
	
	/**
	 * @param timeToDev
	 */
	public void setTimeToDev(double timeToDev) {
		this.timeToDev = timeToDev;
	}
}
