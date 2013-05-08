/**
 * 
 */
package model.data;

/**
 * @author Aaron
 *
 */
public class ProductFunction extends CalculatedSpecification {
	
	private FunctionCategory category;
	
	/**
	 * Default-constructor.
	 */
	public ProductFunction() {
		super();
	}
	
	/**
	 * @param description: String of the description.
	 * @param name: String of the specification name.
	 * @param classification: Classification for the Function Point calculation.
	 * @param category: Category of the Function for the Function Point Calculation.
	 */
	public ProductFunction(String description, String name, FunctionCategory category,
			SpecificationClassification classification){
		super(description, name, classification);
		this.setCategory(category);
	}

	/**
	 * @return the category
	 */
	public FunctionCategory getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(FunctionCategory category) {
		this.category = category;
	}
}
