/**
 * 
 */
package model.data;

/**
 * The factory generates an object of a specification. You have to use the SpecificationTypeEnum to define which
 * specification has to be generated.
 * Factory is realized with the Singleton Pattern.
 * @author Aaron
 *
 */
public class SpecificationFactory {
	
	private static SpecificationFactory _instance = null;
	
	private SpecificationFactory(){}
	
	public static SpecificationFactory getInstance(){
		if(_instance == null){
			_instance = new SpecificationFactory();
		}
		return _instance;
	}
	
	public Specification createSpecification(SpecificationTypeEnum type){
		switch(type){
		case Data:
			return createDataClassification();
		case Function:
			return createFunctionClassification();
		case Performance:
			return createPerformenceClassification();
		case Quality:
			return createQualityClassification();
		default:
			return null;
		}
	}
	
	
	/**
	 * Factory-Method for ProductData.
	 * @return builded ProductData Object 
	 */
	private ProductData createDataClassification(){
		return new ProductData();
	}
	
	/**
	 * Factory-Method for ProductFunction.
	 * @return builded ProductFunction Object 
	 */
	private ProductFunction createFunctionClassification(){
		return new ProductFunction();
	}
	
	/**
	 * Factory-Method for ProductPerformence.
	 * @return builded ProductPerformence Object 
	 */
	private ProductPerformance createPerformenceClassification(){
		return new ProductPerformance();
	}
	
	/**
	 * Factory-Method for QualitySpecification.
	 * @return builded QualitySpecification Object 
	 */
	private QualitySpecification createQualityClassification(){
		return new QualitySpecification();
	}
	

}
