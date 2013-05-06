/**
 * 
 */
package model.data;

import java.util.ArrayList;

/**
 * @author Aaron
 *
 */
public class Specification {

	private ArrayList<SpecificationAttribute> attributes;
	private String description;
	private String name;
	private SpecificationType type;
	
	
	/**
	 * Default-constructor.
	 */
	public Specification() {
		this.setAttributes(new ArrayList<SpecificationAttribute>());
		this.setDescription("");
		this.setName("");
		this.setType(SpecificationType.Function);
	}
	
	/**
	 * @param attributes: ArrayList of SpecificationAttribute.
	 * @param description: String of the description.
	 * @param name: String of the specification name.
	 * @param type: Enum SpecificationType.
	 */
	public Specification(ArrayList<SpecificationAttribute> attributes,
			String description, String name, SpecificationType type) {
		super();
		this.setAttributes(attributes);
		this.setDescription(description);
		this.setName(name);
		this.setType(type);
	}

	/**
	 * @return the attributes
	 */
	public ArrayList<SpecificationAttribute> getAttributes() {
		return attributes;
	}

	/**
	 * @param attributes the attributes to set
	 */
	public void setAttributes(ArrayList<SpecificationAttribute> attributes) {
		this.attributes = attributes;
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

	/**
	 * @return the type
	 */
	public SpecificationType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(SpecificationType type) {
		this.type = type;
	}
	
	
	
	
	
	
}
