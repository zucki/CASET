/**
 * 
 */
package model.data;

/**
 * @author Aaron
 *
 */
public abstract class Specification {

	private String description;
	private String name;
	
	
	/**
	 * Default-constructor.
	 */
	public Specification() {
		this.description = "";
		this.name = "";
	}
	
	/**
	 * @param description: String of the description.
	 * @param name: String of the specification name.
	 * 
	 */
	public Specification(String description, String name) {
		this.description = description;
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
}
