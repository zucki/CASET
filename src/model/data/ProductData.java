/**
 * 
 */
package model.data;

/**
 * @author Aaron
 *
 */
public class ProductData extends CalculatedSpecification {
	
	private DataCategory category;
	
	/**
	 * Default-constructor.
	 */
	public ProductData() {
		super();
	}
	
	/**
	 * @param description: String of the description.
	 * @param name: String of the specification name.
	 * @param classification: Classification for the Function Point calculation.
	 * @param category: Category of the Function for the Function Point Calculation.
	 */
	public ProductData(String description, String name, DataCategory category,
			SpecificationClassification classification){
		super(description, name, classification);
		this.setCategory(category);
	}

	/**
	 * @return the category
	 */
	public DataCategory getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(DataCategory category) {
		this.category = category;
	}
}
