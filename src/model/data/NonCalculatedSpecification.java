/**
 * 
 */
package model.data;

/**
 * Superclass for all specifications, that are not used for the function point calculation.
 * 
 * @author Aaron
 *
 */
public abstract class NonCalculatedSpecification extends Specification {
	
	/**
	 * Default-constructor.
	 */
	public NonCalculatedSpecification() {
		super();
	}
	
	/**
	 * @param description: String of the description.
	 * @param name: String of the specification name.
	 * 
	 */
	public NonCalculatedSpecification(String description, String name) {
		super(description, name);
	}
}
