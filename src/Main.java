import model.ModelFacade;
import model.data.Data;
import model.services.calculation.CalculationFunction;
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
			ControllerInterface controller = new Controller(new ModelFacade(new Data(), new CalculationFunction()));
			ViewInterface view = new ViewFacade(controller);
			controller.setView(view);
			view.show();
	}
}
