package model.services.importexport;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import model.data.DataInterface;

public class ExportFacade implements ExportInterface {

	private DataInterface _data;
	
	private static ExportFacade _exportFacade;
	
	/**
	 * default Constructor
	 */
	public ExportFacade(DataInterface dataInt) {
		this._data = dataInt;
	}
	
	/**
	 * Singleton-Function to get the instance.
	 * @return the singleton instance
	 */
	public static ExportFacade getInstance(){
		return _exportFacade;
	}
	
	/**
	 * Singleton-Function to create an Instance. 
	 * @param data: Data interface that should be used.
	 */
	public static void makeInstance(DataInterface data){
		if(_exportFacade == null){
			_exportFacade = new ExportFacade(data);
		}
	} 
	
	@Override
	public void doExport(ExportType type, String projectName, String path) 
			throws TransformerException, IOException, ParserConfigurationException {
		
		ExportFactory exportFac = new ExportFactory();
		Export exp = exportFac.createExport(type, this._data, projectName, path);
		exp.doExport();
	}

}
