/**
 * 
 */
package model.data;

/**
 * @author Aaron
 *
 */
public abstract class Specification {

	private String _description;
	private String _name;
	
	
	/**
	 * Default-constructor.
	 */
	public Specification() {
		this._description = "";
		this._name = "";
	}
	
	/**
	 * @param description: String of the description.
	 * @param name: String of the specification name.
	 * 
	 */
	public Specification(String description, String name) {
		this._description = description;
		this._name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return _description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this._description = description;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return _name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this._name = name;
	}
}
