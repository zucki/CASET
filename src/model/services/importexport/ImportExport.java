package model.services.importexport;

import model.data.Data;

public class ImportExport {
	private Import imp;
	private Export exp;
	
	public ImportExport(Import imp, Export exp) {
		this.imp = imp;
		this.exp = exp;
	}
	
	public void doExport() {
		exp.doExport(Data.getInstance());
	}
	
	public void doImport() {
		Data daten = imp.doImport();
	}
}
