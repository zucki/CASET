import model.ModelFacade;
import model.data.Data;
import model.services.calculation.CalculationFacade;
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
			Data data = new Data();
			ControllerInterface controller = new Controller(new ModelFacade(data, new CalculationFacade(data)));
			ViewInterface view = new ViewFacade(controller);
			controller.setView(view);
			view.show();
	}
}
