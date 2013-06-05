/**
 * 
 */
package model.services.calculation;

import static org.junit.Assert.*;

import java.util.ArrayList;

import model.data.CocomoMethodEnum;
import model.data.Data;
import model.data.DataCategoryEnum;
import model.data.DataInterface;
import model.data.FunctionCategoryEnum;
import model.data.InfluencingFactorTypeEnum;
import model.data.ProductFunction;
import model.data.ProjectFieldEnum;
import model.data.Specification;
import model.data.SpecificationClassificationEnum;
import model.data.SpecificationFieldEnum;
import model.data.SpecificationTypeEnum;

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
		data.createNewSpecification(projectName, SpecificationTypeEnum.Function);
		data.createNewSpecification(projectName, SpecificationTypeEnum.Function);
		data.createNewSpecification(projectName, SpecificationTypeEnum.Function);
		data.createNewSpecification(projectName, SpecificationTypeEnum.Function);
		data.createNewSpecification(projectName, SpecificationTypeEnum.Function);
		data.createNewSpecification(projectName, SpecificationTypeEnum.Function);
		data.createNewSpecification(projectName, SpecificationTypeEnum.Data);
		data.createNewSpecification(projectName, SpecificationTypeEnum.Data);
		data.createNewSpecification(projectName, SpecificationTypeEnum.Data);
		data.createNewSpecification(projectName, SpecificationTypeEnum.Data);
		data.createNewSpecification(projectName, SpecificationTypeEnum.Data);
		ArrayList <Specification> specifications = data.getSpecifications(projectName);
		for (int i = 0; i<specifications.size(); i++){
			Specification spec = specifications.get(i);
			data.changeSpecificationField(projectName, spec, SpecificationFieldEnum.Classification, 
					SpecificationClassificationEnum.Complex.toString());
			if(spec instanceof ProductFunction){
				data.changeSpecificationField(projectName, spec, SpecificationFieldEnum.Category,
						FunctionCategoryEnum.InputData.toString());
			}
			else{
				data.changeSpecificationField(projectName, spec, SpecificationFieldEnum.Category,
						DataCategoryEnum.Database.toString());
			}
			
		}
		
		//calculation for function point unweighted. Expected result: about 4.868PM
		// test fail if difference is bigger than 1%
		double expected_functionpoint_result = 4.868;
		CalculationResults res = calc.calculate(projectName, CalculationMethod.FunctionPointUnweighted);
		result = ((FunctionPointResults)(res)).getPersonMonths();
		difference = Math.abs(result-expected_functionpoint_result);
		assertFalse("Function-Point (unweighted): Expected (" + expected_functionpoint_result + "), Calculated (" + 
		result + ")",difference/expected_functionpoint_result > 0.01);
		
		
		//Last but not least weighted function point calculation
		
		//define influencing factors
		data.changeInflencingFactorField(projectName, InfluencingFactorTypeEnum.Adaptability,"5");
		data.changeInflencingFactorField(projectName, InfluencingFactorTypeEnum.CalculationOprations,"5");
		data.changeInflencingFactorField(projectName, InfluencingFactorTypeEnum.ControlProcedures,"5");
		data.changeInflencingFactorField(projectName, InfluencingFactorTypeEnum.DatabaseConversion,"5");
		data.changeInflencingFactorField(projectName, InfluencingFactorTypeEnum.DecentralData,"5");
		data.changeInflencingFactorField(projectName, InfluencingFactorTypeEnum.ExceptionRules,"5");
		data.changeInflencingFactorField(projectName, InfluencingFactorTypeEnum.Interlocking,"5");
		data.changeInflencingFactorField(projectName, InfluencingFactorTypeEnum.Logic,"5");
		data.changeInflencingFactorField(projectName, InfluencingFactorTypeEnum.Reusability,"5");
		data.changeInflencingFactorField(projectName, InfluencingFactorTypeEnum.TransactionRate,"5");
		
		//calculation for function point weighted. Expected result: about 6.284PM
		// test fail if difference is bigger than 1%
		expected_functionpoint_result = 6.284;
		res = calc.calculate(projectName, CalculationMethod.FunctionPointWeighted);
		result = ((FunctionPointResults)(res)).getPersonMonths();
		difference = Math.abs(result-expected_functionpoint_result);
		assertFalse("Function-Point (weighted): Expected (" + expected_functionpoint_result + "), Calculated (" + 
		result + ")",difference/expected_functionpoint_result > 0.01);
		
	}
}



