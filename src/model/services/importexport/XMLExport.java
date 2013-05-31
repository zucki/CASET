package model.services.importexport;

import java.io.File;
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

import model.data.DataInterface;
import model.data.ProjectFieldEnum;
import model.data.Specification;
import model.data.SpecificationFieldEnum;

/**
 * @author Markus
 * 
 * Class for XML Export
 *
 */
public class XMLExport extends Export {
	
	private DataInterface _daten;
	private String _projectName;
	private String _path;
	
	/**
	 * Constructor
	 * 
	 * @param data
	 * @param projectName
	 * @param path
	 */
	public XMLExport(DataInterface data, String projectName, String path) {
		this._daten = data;
		this._projectName = projectName;
		this._path = path;
	}
	
	/* (non-Javadoc)
	 * @see model.services.importexport.Export#doExport()
	 */
	@Override
	public void doExport() throws TransformerException, IOException, ParserConfigurationException {
		
		this.writeXMLFile(this.getDocFromData(this._daten, this._projectName), this._path);
	}
	
	/**
	 * @param data
	 * @param projectName
	 * @return datatree for XML File
	 * @throws ParserConfigurationException
	 */
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
	 	
	 	Element cMethod = dataDoc.createElement("CocomoMethod");
	 	cMethod.appendChild(dataDoc.createTextNode(data.getProjectField(projectName, ProjectFieldEnum.Cocomomethod)));
	 	root.appendChild(cMethod);
	 	
	 	Element pUse = dataDoc.createElement("ProjectUse");
	 	pUse.appendChild(dataDoc.createTextNode(data.getProjectField(projectName, ProjectFieldEnum.ProjectUse)));
	 	root.appendChild(pUse);
	 	
	 	Element vaf = dataDoc.createElement("ValueAdjustmentFactor");
	 	vaf.appendChild(dataDoc.createTextNode(data.getProjectField(projectName, ProjectFieldEnum.ValueAdjustmentFactor)));
	 	root.appendChild(vaf);
	 	
	 	Element targetSpec = dataDoc.createElement("TargetSpecification");
	 	targetSpec.appendChild(dataDoc.createTextNode(data.getProjectField(projectName, ProjectFieldEnum.TargetSpecification)));
	 	root.appendChild(targetSpec);
	 	
	 	Element spec = dataDoc.createElement("Specifications");
	 	dataDoc.appendChild(spec);
	 	
	 	Element newSpec;
	 	for (Specification specElement:data.getSpecifications(projectName)) {
	 		newSpec = dataDoc.createElement(data.getSpecificationField(projectName, specElement, SpecificationFieldEnum.Name));
	 		newSpec.setAttributeNode(createAttr(dataDoc, SpecificationFieldEnum.Category.toString(), 
	 				data.getSpecificationField(projectName, specElement, SpecificationFieldEnum.Category)));
	 		newSpec.setAttributeNode(createAttr(dataDoc, SpecificationFieldEnum.Classification.toString(),
	 				data.getSpecificationField(projectName, specElement, SpecificationFieldEnum.Classification)));
	 		newSpec.appendChild(dataDoc.createTextNode(data.getSpecificationField(projectName, specElement, SpecificationFieldEnum.Description)));
	 		spec.appendChild(newSpec);
	 	}
	 		
		return dataDoc;
	}
	
	/**
	 * @param exportData
	 * @param filePath
	 * @throws TransformerException
	 * @throws IOException
	 */
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
	
	/**
	 * @param doc
	 * @param attrName
	 * @param attrValue
	 * @return
	 */
	private Attr createAttr(Document doc, String attrName, String attrValue) {
		Attr newAttr = doc.createAttribute(attrName);
	 	newAttr.setValue(attrValue);
	 	return newAttr;
	}
	
}
