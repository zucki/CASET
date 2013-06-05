/**
 * 
 */
package model.data;

import java.util.ArrayList;

/**
 * A project collects all the Data of a single project in its attributes. There are also different product fields
 * which are directly defined in this class and not in other classes. This fields are defined in the ProjectFieldEnum,
 * so that you can access the different fields.
 * @author Aaron
 *
 */
public class Project {


	private ArrayList <Specification> _specifications;
	private InfluencingFactor[] _influencingFactors; 			// There is only one influencing Factor of any type in a project
	private String _author;
	private CocomoMethodEnum _cocomomethod;
	private String _company;
	private ArrayList<GlossaryEntry> _glossary;
	private int _linesOfCode;
	private String _name;
	private String _projectUse;
	private double _valueAdjustmentFactor;
	private String _targetSpecification;
	
	/**
	 * Default-constructor.
	 */
	public Project(){
		this._specifications = new ArrayList<Specification>();
		this._author = "";
		this._cocomomethod = CocomoMethodEnum.Organic;
		this._company = "";
		this._glossary = new ArrayList<GlossaryEntry>();
		this._linesOfCode = 0;
		this._name = "";
		this._projectUse = "";
		this._valueAdjustmentFactor = 0;
		this._targetSpecification = "";
		this._influencingFactors = new InfluencingFactor[InfluencingFactorTypeEnum.values().length];
		for(int i = 0; i < this._influencingFactors.length; i++){
			_influencingFactors[i] = new InfluencingFactor(InfluencingFactorTypeEnum.values()[i]);
		}
	}
	
	/**
	 * @param projectName: Project name of the new project.
	 */
	public Project(String projectName){
		this._specifications = new ArrayList<Specification>();
		this._author = "";
		this._cocomomethod = CocomoMethodEnum.Organic;
		this._company = "";
		this._glossary = new ArrayList<GlossaryEntry>();
		this._linesOfCode = 0;
		this._name = projectName;
		this._projectUse = "";
		this._valueAdjustmentFactor = 0;
		this._targetSpecification = "";
		this._influencingFactors = new InfluencingFactor[InfluencingFactorTypeEnum.values().length];
		for(int i = 0; i < this._influencingFactors.length; i++){
			_influencingFactors[i] = new InfluencingFactor(InfluencingFactorTypeEnum.values()[i]);
		}
	}
	

	/**
	 * @return the specifications
	 */
	public ArrayList <Specification> getSpecifications() {
		return _specifications;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return _author;
	}

	/**
	 * @param autor the author to set
	 */
	public void setAuthor(String author) {
		this._author = author;
	}

	/**
	 * @return the cocomomethodEnum
	 */
	public CocomoMethodEnum getCocomomethod() {
		return _cocomomethod;
	}

	/**
	 * @param cocomomethod the cocomomethod to set
	 */
	public void setCocomomethod(CocomoMethodEnum cocomomethod) {
		this._cocomomethod = cocomomethod;
	}

	/**
	 * @return the company
	 */
	public String getCompany() {
		return _company;
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany(String company) {
		this._company = company;
	}

	/**
	 * @return the glossary
	 */
	public ArrayList<GlossaryEntry> getGlossary() {
		return _glossary;
	}

	/**
	 * @param glossary the glossary to set
	 */
	public void setGlossary(ArrayList<GlossaryEntry> glossary) {
		this._glossary = glossary;
	}

	/**
	 * @return the linesOfCode
	 */
	public int getLinesOfCode() {
		return _linesOfCode;
	}

	/**
	 * @param linesOfCode the linesOfCode to set
	 */
	public void setLinesOfCode(int linesOfCode) {
		this._linesOfCode = linesOfCode;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return _name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this._name = name;
	}

	/**
	 * @return the projectUse
	 */
	public String getProjectUse() {
		return _projectUse;
	}

	/**
	 * @param projectUse the projectUse to set
	 */
	public void setProjectUse(String projectUse) {
		this._projectUse = projectUse;
	}

	/**
	 * @return the valueAdjustmentFactor
	 */
	public double getValueAdjustmentFactor() {
		return _valueAdjustmentFactor;
	}

	/**
	 * @param valueAdjustmentFactor the valueAdjustmentFactor to set
	 */
	public void setValueAdjustmentFactor(double valueAdjustmentFactor) {
		this._valueAdjustmentFactor = valueAdjustmentFactor;
	}

	/**
	 * @return the targetSpecification
	 */
	public String getTargetSpecification() {
		return _targetSpecification;
	}

	/**
	 * @param targetSpecification the targetSpecification to set
	 */
	public void setTargetSpecification(String targetSpecification) {
		this._targetSpecification = targetSpecification;
	}
	
	/**
	 * There is only one InfluencingFactor of any Type
	 * @param type: Searched InfluencingFactorType
	 * @return the influencingFactor with the given type
	 */
	public InfluencingFactor getInfluencingFactor(InfluencingFactorTypeEnum type) {
		for(int i = 0; i < _influencingFactors.length; i++){
			if(_influencingFactors[i].getType() == type){
				return _influencingFactors[i];
			}
		}
		return null;
	}
	
	/**
	 * @return the influencingFactors
	 */
	public InfluencingFactor[] getInfluencingFactors() {
		return _influencingFactors;
	}
}
