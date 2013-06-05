/**
 * 
 */
package model.data;

/**
 * A QualitySpecification is a specification which is calculated in the function point method.
 * 
 * @author Aaron
 *
 */
public class QualitySpecification extends NonCalculatedSpecification {
	
	/**
	 * Default-constructor.
	 */
	public QualitySpecification() {
		super();
	}
	
	/**
	 * @param description: String of the description.
	 * @param name: String of the specification name.
	 * 
	 */
	public QualitySpecification(String description, String name) {
		super(description, name);
	}
}
