/**
 * 
 */
package model.data;

/**
 * Product Performances are specifications, that are not used for the function point calculation.
 * 
 * @author Aaron
 *
 */
public class ProductPerformance extends NonCalculatedSpecification {
	
	/**
	 * Default-constructor.
	 */
	public ProductPerformance() {
		super();
	}
	
	/**
	 * @param description: String of the description.
	 * @param name: String of the specification name.
	 * 
	 */
	public ProductPerformance(String description, String name) {
		super(description, name);
	}
}
