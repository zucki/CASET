package model.services.importexport;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

import model.data.Data;

public class XMLExport extends Export {

	@Override
	public void doExport(Data daten) {

	}
	
	private Document getDocFromData(Data daten) throws ParserConfigurationException {
		
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	    docFactory.setNamespaceAware(false); 
	    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	    Document dataDoc = docBuilder.newDocument();
	    
	    // root element
	 	Element rootElement = dataDoc.createElement("Project");
	 	dataDoc.appendChild(rootElement);
	 
	 	Attr attr = dataDoc.createAttribute("name");
	 	// attr.setValue(daten.);
	 	rootElement.setAttributeNode(attr);
	    
		return dataDoc;
		
	}
	
	private void writeXMLFile(Document exportData, String filePath) throws TransformerException, IOException {
		
		FileOutputStream exportFile = new FileOutputStream(new File(filePath));
		
		TransformerFactory transFactory = TransformerFactory.newInstance();
		Transformer trans = transFactory.newTransformer();
		trans.setOutputProperty(OutputKeys.INDENT, "yes");
		trans.setOutputProperty(OutputKeys.METHOD, "xml");
		
		trans.transform(new DOMSource(exportData), new StreamResult(exportFile));
		
		exportFile.flush();
		exportFile.close();
	}
	
}
