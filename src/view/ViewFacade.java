/**
 * 
 */
package view;

import controller.Controller;
import controller.ControllerInterface;
import model.ModelFacade;
import model.data.Project;
import view.gui.MainWindow;

/**
 * @author smgug_000
 *
 */
public class ViewFacade implements ViewInterface {
	private MainWindow mainWindow;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ControllerInterface controller = new Controller(new ModelFacade());
			ViewInterface view = new ViewFacade(controller);
			controller.setView(view);
			view.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ViewFacade(ControllerInterface controller) {
		this.mainWindow = new MainWindow(controller);
	}
	
	public void show() {
		this.mainWindow.open();
	}
	
	public void createNewProject() {
		this.mainWindow.createProject();
	}
	
	public Project getSelectedProject() {
		// TODO
		return null;
	}

	@Override
	public void removeSelectedProject() {
		this.mainWindow.removeSelectedProject();
		
	}
}
