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
import model.data.ProductData;
import model.data.ProductFunction;
import model.data.ProductPerformance;
import model.data.ProjectFieldEnum;
import model.data.QualitySpecification;
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
	 * @param _daten
	 * @param projectName
	 * @return datatree for XML File
	 * @throws ParserConfigurationException
	 */
	private Document getDocFromData(DataInterface _daten, String projectName) throws ParserConfigurationException {
		
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	    docFactory.setNamespaceAware(false); 
	    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	    Document dataDoc = docBuilder.newDocument();
	    
	    // root element
	 	Element root = dataDoc.createElement("Project");
	 	dataDoc.appendChild(root);
	 
	 	root.setAttributeNode(this.createAttr(dataDoc, "name", _daten.getProjectField(projectName, ProjectFieldEnum.Name)));
	 	root.setAttributeNode(this.createAttr(dataDoc, "author", _daten.getProjectField(projectName, ProjectFieldEnum.Author)));
	 	root.setAttributeNode(this.createAttr(dataDoc, "company", _daten.getProjectField(projectName, ProjectFieldEnum.Company)));
	 	
	 	Element loc = dataDoc.createElement("LinesOfCode");
	 	loc.appendChild(dataDoc.createTextNode(_daten.getProjectField(projectName, ProjectFieldEnum.LinesOfCode)));
	 	root.appendChild(loc);
	 	
	 	Element cMethod = dataDoc.createElement("CocomoMethod");
	 	cMethod.appendChild(dataDoc.createTextNode(_daten.getProjectField(projectName, ProjectFieldEnum.Cocomomethod)));
	 	root.appendChild(cMethod);
	 	
	 	Element pUse = dataDoc.createElement("ProjectUse");
	 	pUse.appendChild(dataDoc.createTextNode(_daten.getProjectField(projectName, ProjectFieldEnum.ProjectUse)));
	 	root.appendChild(pUse);
	 	
	 	Element vaf = dataDoc.createElement("ValueAdjustmentFactor");
	 	vaf.appendChild(dataDoc.createTextNode(_daten.getProjectField(projectName, ProjectFieldEnum.ValueAdjustmentFactor)));
	 	root.appendChild(vaf);
	 	
	 	Element targetSpec = dataDoc.createElement("TargetSpecification");
	 	targetSpec.appendChild(dataDoc.createTextNode(_daten.getProjectField(projectName, ProjectFieldEnum.TargetSpecification)));
	 	root.appendChild(targetSpec);
	 	
	 	Element spec = dataDoc.createElement("Specifications");
	 	root.appendChild(spec);
	 	
	 	Element newSpec;
	 	Element newClassification;
	 	Element newCatagory;
	 	Element newDescription;
	 	int specCounter=0;
	 	for (Specification specElement:_daten.getSpecifications(projectName)) {
	 		newSpec = dataDoc.createElement("Specification"+String.valueOf(specCounter));
	 		newSpec.setAttributeNode(this.createAttr(dataDoc, "Type", this.getSpecificationType(specElement)));
	 		newSpec.setAttributeNode(this.createAttr(dataDoc, SpecificationFieldEnum.Name.toString(), 
	 				_daten.getSpecificationField(projectName, specElement, SpecificationFieldEnum.Name)));
	 		
	 		newClassification = dataDoc.createElement(SpecificationFieldEnum.Classification.toString());
	 		newClassification.appendChild(dataDoc.createTextNode(
	 				_daten.getSpecificationField(projectName, specElement, SpecificationFieldEnum.Classification)));
	 		newSpec.appendChild(newClassification);
	 		
	 		newCatagory = dataDoc.createElement(SpecificationFieldEnum.Category.toString());
	 		newCatagory.appendChild(dataDoc.createTextNode(
	 				_daten.getSpecificationField(projectName, specElement, SpecificationFieldEnum.Category)));
	 		newSpec.appendChild(newCatagory);
	 		
	 		newDescription = dataDoc.createElement(SpecificationFieldEnum.Description.toString());
	 		newDescription.appendChild(dataDoc.createTextNode(
	 				_daten.getSpecificationField(projectName, specElement, SpecificationFieldEnum.Description)));
	 		newSpec.appendChild(newDescription);
	 		
	 		spec.appendChild(newSpec);
	 		specCounter++;
	 	}
	 		
		return dataDoc;
	}
	
	/**
	 * @param exportData: XML tree to write in File
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
	
	/**
	 * @param spec
	 * @return: the type of the specification as String
	 */
	private String getSpecificationType(Specification spec) {
		if(spec instanceof ProductData) {
			return "ProductData";
		}
		else if(spec instanceof ProductFunction) {
			return "ProductFunction";
		}
		else if(spec instanceof ProductPerformance) {
			return "ProductPerformance";
		}
		else if(spec instanceof QualitySpecification) {
			return "QualitySpecification";
		}
		else {
			return null;
		}
	}
	
	
	
}
