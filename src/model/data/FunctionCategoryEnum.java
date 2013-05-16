/**
 * 
 */
package model.data;

/**
 * @author Aaron
 *
 */
public enum FunctionCategoryEnum {
	InputData,
	Request,
	Output,
	Database,
	ReferenceData;
	
	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	public String toString(){
		switch (this){
		case InputData:
			return "Input Data";
		case Request:
			return "Request";
		case Output:
			return "Output";
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
	 * fromString accepts a spaces in "InputData" and "ReferenceData"
	 * for example "Input Data" or "Reference Data"
	 * @param value
	 * @return Enum value SpecificationCategory
	 */
	public static FunctionCategoryEnum fromString(String value){
		switch(value){
		case "Input Data":
			return InputData;
		case "Request":
			return Request;
		case "Output":
			return Output;
		case "Database":
			return Database;
		case "Reference Data":
			return ReferenceData;
		default:
			return null;
		}
	}

}
