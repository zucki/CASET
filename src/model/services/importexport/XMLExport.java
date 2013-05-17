package model.services.importexport;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

import model.data.Data;
import model.data.DataInterface;
import model.data.ProjectFieldEnum;

public class XMLExport extends Export {
	
	@Override
	public void doExport(Data daten) {

	}
	
	private Document getDocFromData(DataInterface data, String projectName) throws ParserConfigurationException {
		
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	    docFactory.setNamespaceAware(false); 
	    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	    Document dataDoc = docBuilder.newDocument();
	    
	    // root element
	 	Element root = dataDoc.createElement("Project");
	 	dataDoc.appendChild(root);
	 
	 	root.setAttributeNode(createAttr(dataDoc, "name", data.getProjectField(projectName, ProjectFieldEnum.Name)));
	 	root.setAttributeNode(createAttr(dataDoc, "author", data.getProjectField(projectName, ProjectFieldEnum.Author)));
	 	root.setAttributeNode(createAttr(dataDoc, "company", data.getProjectField(projectName, ProjectFieldEnum.Company)));
	 	
	 	Element loc = dataDoc.createElement("Lines Of Code");
	 	loc.appendChild(dataDoc.createTextNode(data.getProjectField(projectName, ProjectFieldEnum.LinesOfCode)));
	 	root.appendChild(loc);
	 	
	 	Element cMethod = dataDoc.
	 	
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
	
	private Attr createAttr(Document doc, String attrName, String attrValue) {
		Attr newAttr = doc.createAttribute(attrName);
	 	newAttr.setValue(attrValue);
	 	return newAttr;
	}
	
}
