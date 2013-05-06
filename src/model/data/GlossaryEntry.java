/**
 * 
 */
package model.data;

/**
 * @author Aaron
 *
 */
public class GlossaryEntry {

	private String entry;
	private String definition;
	
	/**
	 * @param entry: String
	 * @param definition: String
	 */
	public GlossaryEntry(String entry, String definition) {
		this.entry = entry;
		this.setDefinition(definition);
	}
	
	/**
	 * Default-constructor.
	 */
	public GlossaryEntry(){
		this.entry = "";
		this.setDefinition("");
	}

	/**
	 * @return: String-value of entry.
	 */
	public String getEntry() {
		return entry;
	}

	/**
	 * @param entry: String to set for entry.
	 */
	public void setEntry(String entry) {
		this.entry = entry;
	}

	/**
	 * @return the definition
	 */
	public String getDefinition() {
		return definition;
	}

	/**
	 * @param definition the definition to set
	 */
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	
	
}
