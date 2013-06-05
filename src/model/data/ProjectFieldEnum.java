/**
 * 
 */
package model.data;

/**
 * Enum for the different Fields of an project, except of specifications and glossary.
 * Often used to access the different fields of an project. For example to change them without knowing
 * the structure of an project.
 * @author Aaron
 *
 */
public enum ProjectFieldEnum {

	Author,
	Cocomomethod,
	Company,
	LinesOfCode,
	Name,
	ProjectUse,
	ValueAdjustmentFactor,
	TargetSpecification,
	Glossary,
	GlossaryEntry,
	GlossaryDescription,
	Specifications;
}
