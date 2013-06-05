/**
 * 
 */
package model.data;

/**
 * Fields of a Specification, that are able to be edited.
 * Description and Name can be used for every specification.
 * Classification can only be used for CalculatedSpecifications
 * Category are different enums for the ProjectFunctions and the ProductData.
 * 
 * @author Aaron
 *
 */
public enum SpecificationFieldEnum {
	
	Description,
	Name,
	Category,
	Classification;
}
