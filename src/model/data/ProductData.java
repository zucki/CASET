/**
 * 
 */
package model.data;

/**
 * @author Aaron
 *
 */
public class ProductData extends CalculatedSpecification {
	
	private DataCategoryEnum _category;
	
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
	public ProductData(String description, String name, DataCategoryEnum category,
			SpecificationClassificationEnum classification){
		super(description, name, classification);
		this._category = category;
	}

	/**
	 * @return the category
	 */
	public DataCategoryEnum getCategory() {
		return _category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(DataCategoryEnum category) {
		this._category = category;
	}
}
