/**
 * 
 */
package model.data;

/**
 * @author Aaron
 *
 */
public class ProductFunction extends CalculatedSpecification {
	
	private FunctionCategoryEnum _category;
	
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
	public ProductFunction(String description, String name, FunctionCategoryEnum category,
			SpecificationClassificationEnum classification){
		super(description, name, classification);
		this._category = category;
	}

	/**
	 * @return the category
	 */
	public FunctionCategoryEnum getCategory() {
		return _category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(FunctionCategoryEnum category) {
		this._category = category;
	}
}
