package model.services.importexport;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;


/**
 * @author Markus
 * 
 * Superclass for FileExport
 *
 */
public abstract class Export {
	public abstract void doExport() throws TransformerException, IOException, ParserConfigurationException;

}
