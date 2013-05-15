/**
 * 
 */
package model.data;

/**
 * @author Aaron
 *
 */
public class InfluencingFactor {
	
	private InfluencingFactorType type;
	
	private int value;
	

	/**
	 * @param type: InfluencingFactorType.
	 */
	public InfluencingFactor(InfluencingFactorType type) {
		this.type = type;
		this.value = 0;
	}

	/**
	 * @return the type
	 */
	public InfluencingFactorType getType() {
		return type;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public boolean setValue(int value) {
		
		switch(this.type){
		case CalculationOprations:
			if(value > 10){
				return false;
			}else{
				this.value = value;
				return true;
			}
		case ExceptionRules:
			if(value > 10){
				return false;
			}else{
				this.value = value;
				return true;
			}
		default:
			if(value > 5){
				return false;
			}else{
				this.value = value;
				return true;
			}
		}
	}

}
