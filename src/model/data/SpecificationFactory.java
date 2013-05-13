/**
 * 
 */
package model.data;

/**
 * Factory is realized with the Singleton Pattern.
 * @author Aaron
 *
 */
public class SpecificationFactory {
	
	private static SpecificationFactory instance = null;
	
	private SpecificationFactory(){}
	
	public static SpecificationFactory getInstance(){
		if(instance == null){
			instance = new SpecificationFactory();
		}
		return instance;
	}
	
	public Specification createSpecification(SpecificationType type){
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
