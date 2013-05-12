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
	
	/**
	 * Factory-Method for ProductData.
	 * @return builded ProductData Object 
	 */
	public ProductData createDataClassification(){
		return new ProductData();
	}
	
	/**
	 * Factory-Method for ProductFunction.
	 * @return builded ProductFunction Object 
	 */
	public ProductFunction createFunctionClassification(){
		return new ProductFunction();
	}
	
	/**
	 * Factory-Method for ProductPerformence.
	 * @return builded ProductPerformence Object 
	 */
	public ProductPerformance createPerformenceClassification(){
		return new ProductPerformance();
	}
	
	/**
	 * Factory-Method for QualitySpecification.
	 * @return builded QualitySpecification Object 
	 */
	public QualitySpecification createQualityClassification(){
		return new QualitySpecification();
	}
	

}
