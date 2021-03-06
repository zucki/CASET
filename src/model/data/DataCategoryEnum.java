/**
 * 
 */
package model.data;

/**
 * This enum defines the different Types of Data specifications that are possible in this tool.
 * You can therefore define Databes-Data or ReferenceData-Data.
 * 
 * @author Aaron
 * 
 *
 */
public enum DataCategoryEnum {
	
	Database,
	ReferenceData;
	
	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	public String toString(){
		switch (this){
		case Database:
			return "Database";
		case ReferenceData:
			return "Reference Data";
		default:
			return null;
		}
		
	}
	
	/**
	 * similar to valueof(String value)
	 * fromString accepts a space in "ReferenceData"
	 * for example "Reference Data"
	 * @param value
	 * @return Enum value SpecificationCategory
	 */
	public static DataCategoryEnum fromString(String value){
		switch(value){
		case "Database":
			return Database;
		case "Reference Data":
			return ReferenceData;
		default:
			return null;
		}
	}
}
