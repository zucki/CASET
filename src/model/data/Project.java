/**
 * 
 */
package model.data;

import java.util.ArrayList;

/**
 * @author Aaron
 *
 */
public class Project {


	private ArrayList <Specification> specifications;
	private InfluencingFactor[] influencingFactors; 			// There is only one influencing Factor of any type in a project
	private String author;
	private CocomoMethod cocomomethod;
	private String company;
	private ArrayList<GlossaryEntry> glossary;
	private int linesOfCode;
	private String name;
	private String projectUse;
	private double valueAdjustmentFactor;
	private String targetSpecification;
	
	/**
	 * Default-constructor.
	 */
	public Project(){
		this.specifications = new ArrayList<Specification>();
		this.author = "";
		this.cocomomethod = CocomoMethod.Organic;
		this.company = "";
		this.glossary = new ArrayList<GlossaryEntry>();
		this.linesOfCode = 0;
		this.name = "";
		this.projectUse = "";
		this.valueAdjustmentFactor = 0;
		this.targetSpecification = "";
		this.influencingFactors = new InfluencingFactor[InfluencingFactorType.values().length];
		for(int i = 0; i < this.influencingFactors.length; i++){
			influencingFactors[i] = new InfluencingFactor(InfluencingFactorType.values()[i]);
		}
	}
	
	/**
	 * @param projectName: Project name of the new project.
	 */
	public Project(String projectName){
		this.specifications = new ArrayList<Specification>();
		this.author = "";
		this.cocomomethod = CocomoMethod.Organic;
		this.company = "";
		this.glossary = new ArrayList<GlossaryEntry>();
		this.linesOfCode = 0;
		this.name = projectName;
		this.projectUse = "";
		this.valueAdjustmentFactor = 0;
		this.targetSpecification = "";
		this.influencingFactors = new InfluencingFactor[InfluencingFactorType.values().length];
		for(int i = 0; i < this.influencingFactors.length; i++){
			influencingFactors[i] = new InfluencingFactor(InfluencingFactorType.values()[i]);
		}
	}
	

	/**
	 * @return the specifications
	 */
	public ArrayList <Specification> getSpecifications() {
		return specifications;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param autor the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the cocomomethod
	 */
	public CocomoMethod getCocomomethod() {
		return cocomomethod;
	}

	/**
	 * @param cocomomethod the cocomomethod to set
	 */
	public void setCocomomethod(CocomoMethod cocomomethod) {
		this.cocomomethod = cocomomethod;
	}

	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * @return the glossary
	 */
	public ArrayList<GlossaryEntry> getGlossary() {
		return glossary;
	}

	/**
	 * @param glossary the glossary to set
	 */
	public void setGlossary(ArrayList<GlossaryEntry> glossary) {
		this.glossary = glossary;
	}

	/**
	 * @return the linesOfCode
	 */
	public int getLinesOfCode() {
		return linesOfCode;
	}

	/**
	 * @param linesOfCode the linesOfCode to set
	 */
	public void setLinesOfCode(int linesOfCode) {
		this.linesOfCode = linesOfCode;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the projectUse
	 */
	public String getProjectUse() {
		return projectUse;
	}

	/**
	 * @param projectUse the projectUse to set
	 */
	public void setProjectUse(String projectUse) {
		this.projectUse = projectUse;
	}

	/**
	 * @return the valueAdjustmentFactor
	 */
	public double getValueAdjustmentFactor() {
		return valueAdjustmentFactor;
	}

	/**
	 * @param valueAdjustmentFactor the valueAdjustmentFactor to set
	 */
	public void setValueAdjustmentFactor(double valueAdjustmentFactor) {
		this.valueAdjustmentFactor = valueAdjustmentFactor;
	}

	/**
	 * @return the targetSpecification
	 */
	public String getTargetSpecification() {
		return targetSpecification;
	}

	/**
	 * @param targetSpecification the targetSpecification to set
	 */
	public void setTargetSpecification(String targetSpecification) {
		this.targetSpecification = targetSpecification;
	}
	
	/**
	 * There is only one InfluencingFactor of any Type
	 * @param type: Searched InfluencingFactorType
	 * @return the influencingFactor with the given type
	 */
	public InfluencingFactor getInfluencingFactor(InfluencingFactorType type) {
		for(int i = 0; i < influencingFactors.length; i++){
			if(influencingFactors[i].getType() == type){
				return influencingFactors[i];
			}
		}
		return null;
	}
}
