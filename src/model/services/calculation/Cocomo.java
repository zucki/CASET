/**
 * 
 */
package model.services.calculation;


import model.data.DataInterface;
import model.data.ProjectFieldEnum;
import java.lang.Math;
import model.data.CocomoMethodEnum;

/**
 * @author Markus
 *
 */
public class Cocomo extends Calculation {

	private DataInterface _data;
	private String _project;
	
	/**
	 * @param data: An interface to access the data
	 * @param projectName
	 */
	public Cocomo(DataInterface data, String projectName) {
		this._data = data;
		this._project = projectName;
	}
	
	/* (non-Javadoc)
	 * @see model.services.calculation.Calculation#calculate()
	 */
	public  CocomoResults calculate() {

		int linesOfCode = Integer.parseInt(this._data.getProjectField(this._project, ProjectFieldEnum.LinesOfCode));
		CocomoResults result = new CocomoResults();
		
		String methodString = this._data.getProjectField(this._project, ProjectFieldEnum.Cocomomethod);
		CocomoMethodEnum method = CocomoMethodEnum.valueOf(methodString);
		
		switch (method) {
		
		case Embedded:
			result.setPersonMonths(3.6 * Math.pow(linesOfCode,1.2));
			result.setTimeToDev(2.5 * Math.pow(linesOfCode, 0.32));
			break;
		case Organic:
			result.setPersonMonths(2.4 * Math.pow(linesOfCode,1.05));
			result.setTimeToDev(2.5 * Math.pow(linesOfCode, 0.38));
			break;
		case Semidetached:
			result.setPersonMonths(3.0 * Math.pow(linesOfCode,1.12));
			result.setTimeToDev(2.5 * Math.pow(linesOfCode, 0.35));
			break;	
		default:
			result.setPersonMonths(0);
			result.setTimeToDev(0);
			break;
		}

		return result;
	}
}
