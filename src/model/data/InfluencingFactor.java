/**
 * 
 */
package model.data;

/**
 * An influencing factor is used to calculate the weighted funcion points. Every project has an influencing factor of each
 * InfluencingFactorType. They are defined in an array of the project.
 * 
 * @author Aaron
 *
 */
public class InfluencingFactor {
	
	private InfluencingFactorTypeEnum _type;
	
	private int _value;
	

	/**
	 * @param type: InfluencingFactorType.
	 */
	public InfluencingFactor(InfluencingFactorTypeEnum type) {
		this._type = type;
		this._value = 0;
	}

	/**
	 * @return the type
	 */
	public InfluencingFactorTypeEnum getType() {
		return _type;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return _value;
	}

	/**
	 * @param value the value to set
	 */
	public boolean setValue(int value) {
		
		switch(this._type){
		case CalculationOprations:
			if(value > 10){
				return false;
			}else{
				this._value = value;
				return true;
			}
		case ExceptionRules:
			if(value > 10){
				return false;
			}else{
				this._value = value;
				return true;
			}
		default:
			if(value > 5){
				return false;
			}else{
				this._value = value;
				return true;
			}
		}
	}

}
