/**
 * 
 */
package model.services.calculation;

import static org.junit.Assert.*;

import model.data.CocomoMethodEnum;
import model.data.Data;
import model.data.DataInterface;
import model.data.ProjectFieldEnum;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * JUnit Test to test the calculation of a project.
 * Every kind of supported calculation will be tested. If one of the calculation tests fail, the calculations after that one
 * won't be calculated and tested.
 * 
 * @author Aaron
 *
 */
public class JUnitCalculationFacadeTest {

	@Test
	public void testCalculate() {
		
		// create a Calculation Interface to calculate the generated data structure
		DataInterface data = Data.getInstance();
		CalculationInterface calc = CalculationFacade.getInstance();
		if(calc == null){
			CalculationFacade.makeInstance(data);
			calc = CalculationFacade.getInstance();
		}
		
		//generate data structure for the calculation
		String projectName = "testProject";
		data.createNewProject(projectName);
		data.changeProjectField(projectName, ProjectFieldEnum.LinesOfCode, "3500");
		
		//calculation for COCOMO embedded. Expected result: about 16.19PM
		// test fail if difference is bigger than 1%
		data.changeProjectField(projectName, ProjectFieldEnum.Cocomomethod, CocomoMethodEnum.Embedded.toString());
		double expected_COCOMO = 16.19;
		double result = ((CocomoResults)calc.calculate(projectName, CalculationMethod.Cocomo)).getPersonMonths();
		double difference = Math.abs(result-expected_COCOMO);
		assertFalse("COCMO (embedded): Expected (" + expected_COCOMO + "), Calculated (" + 
		result + ")",difference/expected_COCOMO > 0.01);
		
		//calculation for COCOMO organic. Expected result: about 8,94PM
		// test fail if difference is bigger than 1%
		data.changeProjectField(projectName, ProjectFieldEnum.Cocomomethod, CocomoMethodEnum.Organic.toString());
		expected_COCOMO = 8.94;
		result = ((CocomoResults)calc.calculate(projectName, CalculationMethod.Cocomo)).getPersonMonths();
		difference = Math.abs(result-expected_COCOMO);
		assertFalse("COCMO (organic): Expected (" + expected_COCOMO + "), Calculated (" + 
		result + ")",difference/expected_COCOMO > 0.01);
		
		//calculation for COCOMO semidetached. Expected result: about 12.203PM
		// test fail if difference is bigger than 1%
		data.changeProjectField(projectName, ProjectFieldEnum.Cocomomethod, CocomoMethodEnum.Semidetached.toString());
		expected_COCOMO = 12.203;
		result = ((CocomoResults)calc.calculate(projectName, CalculationMethod.Cocomo)).getPersonMonths();
		difference = Math.abs(result-expected_COCOMO);
		assertFalse("COCMO (semidetached): Expected (" + expected_COCOMO + "), Calculated (" + 
		result + ")",difference/expected_COCOMO > 0.01);
		
		
		// Create Specifications for Function-Point calculation
	}

}
