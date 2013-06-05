/**
 * 
 */
package model.data;

/**
 * @author Aaron
 * 
 * abstract superclass for the specifications that are used for the Function-Point calculation
 *
 */
public abstract class CalculatedSpecification extends Specification {

	private SpecificationClassificationEnum _classification;
	
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
			SpecificationClassificationEnum classification){
		super(description, name);
		this._classification = classification;
	}

	/**
	 * @return the classification
	 */
	public SpecificationClassificationEnum getClassification() {
		return _classification;
	}

	/**
	 * @param classification the classification to set
	 */
	public void setClassification(SpecificationClassificationEnum classification) {
		this._classification = classification;
	}
}
