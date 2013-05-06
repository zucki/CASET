/**
 * 
 */
package model.data;

/**
 * @author Aaron
 *
 */
public abstract class SpecificationAttribute {

	private String name;
	private String description;
	private double factor;
	
	
	/**
	 * @param name: Name of attribute.
	 * @param description: Description of an attribute.
	 * @param factor: Factor for the function point calculation.
	 */
	
	public SpecificationAttribute(String name, String description, double factor) {
		this.setName(name);
		this.setDescription(description);
		this.setFactor(factor);
	}
	
	/**
	 * Default-constructor.
	 */
	public SpecificationAttribute() {
		super();
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the factor
	 */
	public double getFactor() {
		return factor;
	}

	/**
	 * @param factor the factor to set
	 */
	public void setFactor(double factor) {
		this.factor = factor;
	}
}
