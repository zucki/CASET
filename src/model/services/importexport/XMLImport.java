package model.services.importexport;

import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import model.data.Data;

public class XMLImport extends Import {

	@Override
	public Data doImport() {
		
		return null;
	}

	public Document readFile(InputStream input) throws ParserConfigurationException, SAXException, IOException {
		
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	    docFactory.setNamespaceAware(false); 
	    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	    
	    return docBuilder.parse(input);
	}
	
}
