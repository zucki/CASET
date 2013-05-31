/**
 * 
 */
package model.services.calculation;
import model.data.DataInterface;

/**
 * @author Markus
 * 
 * 
 * Class to implement the Calculation Interface
 */
public class CalculationFacade implements CalculationInterface {
	
	private static CalculationFacade _calculationFacade;
	/**
	 * the Calculation Methods need a Data Interface to access the Data Fields (f.e. the influence Factors)
	 */
	private DataInterface data;
	
	/**
	 * Default-Constructor.
	 */
	public CalculationFacade(DataInterface dataInt) {
		this.data = dataInt;
	}
	
	/**
	 * Singleton-Function to get the instance.
	 * @return the singleton instance
	 */
	public static CalculationFacade getInstance(){
		return _calculationFacade;
	}
	
	/**
	 * Singleton-Function to create an Instance. 
	 * @param data: Data interface that should be used.
	 */
	public static void makeInstance(DataInterface data){
		if(_calculationFacade == null){
			_calculationFacade = new CalculationFacade(data);
		}
	} 

	@Override
	public CalculationResults calculate(String projectName,
			CalculationMethod method) {

			CalculationFactory calcFac = new CalculationFactory();
			Calculation calc = calcFac.createCalculation(method, this.data, projectName);
			return calc.calculate();
	}
	
}
