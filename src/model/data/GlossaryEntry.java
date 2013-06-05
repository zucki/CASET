/**
 * 
 */
package model.data;

/**
 * This class defines an entry for the glossary. The entries are located in a list in the project class. Every entry is
 * defined with an entry and a definition of this entry.
 * 
 * @author Aaron
 *
 */
public class GlossaryEntry {

	private String _entry;
	private String _definition;
	
	/**
	 * @param entry: String
	 * @param definition: String
	 */
	public GlossaryEntry(String entry, String definition) {
		this._entry = entry;
		this.setDefinition(definition);
	}
	
	/**
	 * Default-constructor.
	 */
	public GlossaryEntry(){
		this._entry = "";
		this.setDefinition("");
	}

	/**
	 * @return: String-value of entry.
	 */
	public String getEntry() {
		return _entry;
	}

	/**
	 * @param entry: String to set for entry.
	 */
	public void setEntry(String entry) {
		this._entry = entry;
	}

	/**
	 * @return the definition
	 */
	public String getDefinition() {
		return _definition;
	}

	/**
	 * @param definition the definition to set
	 */
	public void setDefinition(String definition) {
		this._definition = definition;
	}
	
	
}
