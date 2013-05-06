/**
 * 
 */
package model.data;

/**
 * @author Aaron
 *
 */
public class CostAttribute extends SpecificationAttribute {

	/**
	 * Default-constructor
	 */
	public CostAttribute() {
		super();
	}

	/**
	 * @param name: Name of attribute.
	 * @param description: Description of an attribute.
	 * @param factor: Factor for the function point calculation.
	 */
	public CostAttribute(String name, String description, double factor) {
		super(name, description, factor);
	}

}
