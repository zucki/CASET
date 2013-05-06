package model.services.importexport;

import model.data.Daten;

public class ImportExport {
	private Import imp;
	private Export exp;
	
	public ImportExport(Import imp, Export exp) {
		this.imp = imp;
		this.exp = exp;
	}
	
	public void doExport() {
		exp.doExport(Daten.getInstance());
	}
	
	public void doImport() {
		Daten daten = imp.doImport();
	}
}
