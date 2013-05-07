/**
 * 
 */
package model.services.calculation;

import model.data.Project;
import java.lang.Math;

/**
 * @author Markus Zukunft
 *
 */
public class Cocomo extends Calculation {
	
	@Override
	public CalculationResults calculate(Project currentProject) {
		
		CocomoResults result = new CocomoResults();
		
		switch (currentProject.getCocomomethod()) {
		
		case Embedded:
			result.setPersonMonths(3.6 * Math.pow(currentProject.getLinesOfCode(),1.2));
			result.setTimeToDev(2.5 * Math.pow(currentProject.getLinesOfCode(), 0.32));
			break;
		case Organic:
			result.setPersonMonths(2.4 * Math.pow(currentProject.getLinesOfCode(),1.05));
			result.setTimeToDev(2.5 * Math.pow(currentProject.getLinesOfCode(), 0.38));
			break;
		case Semidetached:
			result.setPersonMonths(3.0 * Math.pow(currentProject.getLinesOfCode(),1.12));
			result.setTimeToDev(2.5 * Math.pow(currentProject.getLinesOfCode(), 0.35));
			break;	
		default:
			result.setPersonMonths(0);
			result.setTimeToDev(0);
			break;
		}

		return result;
	}

}
