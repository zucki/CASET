package model.services.importexport;

import model.data.DataInterface;

/**
 * @author Markus
 *
 * Factory to create Export
 */
public class ExportFactory {
	
	/**
	 * @param type Filetype of exportfile
	 * @param data DataInterface
	 * @param projectName
	 * @param path Filepath for save
	 * @return
	 */
	public Export createExport(ExportType type, DataInterface data, String projectName, String path) {
		
		switch (type) {
		case XML:
			return new XMLExport(data, projectName, path);
		default:
			return null;
		}
	}
}
