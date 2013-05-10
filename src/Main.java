import model.ModelFacade;
import model.data.Data;
import view.ViewFacade;
import view.ViewInterface;
import controller.Controller;
import controller.ControllerInterface;


public class Main {
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
			ControllerInterface controller = new Controller(new ModelFacade(new Data()));
			ViewInterface view = new ViewFacade(controller);
			controller.setView(view);
			view.show();
	}
}
