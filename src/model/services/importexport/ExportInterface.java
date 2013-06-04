package model.services.importexport;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

/**
 * @author Markus
 * Interface for Export a Project
 */
public interface ExportInterface {

	/**
	 * @param type: the type of the exportfile (XML only at the moment)
	 * @param projectName: the name of the project to export
	 * @param path: the location of the output file
	 * @throws TransformerException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public void doExport(ExportType type, String projectName, String path) 
			throws TransformerException, IOException, ParserConfigurationException;
}
