/**
 * 
 */
package model.data;

/**
 * @author Aaron
 *
 */
public abstract class CalculatedSpecification extends Specification {

	private SpecificationClassification classification;
	
	/**
	 * Default-constructor.
	 */
	public CalculatedSpecification() {
		super();
	}
	
	/**
	 * @param description: String of the description.
	 * @param name: String of the specification name.
	 * @param classification: Classification for the Function Point calculation.
	 * 
	 */
	public CalculatedSpecification(String description, String name,
			SpecificationClassification classification){
		super(description, name);
		this.classification = classification;
	}

	/**
	 * @return the classification
	 */
	public SpecificationClassification getClassification() {
		return classification;
	}

	/**
	 * @param classification the classification to set
	 */
	public void setClassification(SpecificationClassification classification) {
		this.classification = classification;
	}
}
