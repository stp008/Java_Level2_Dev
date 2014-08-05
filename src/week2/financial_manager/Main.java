/**
 * @author clack008@gmail.com
 */


package week2.financial_manager;

import week1.financial_manager.controller.Controller;
import week1.financial_manager.model.Model;
import week1.financial_manager.model.ModelImpl;
import week1.financial_manager.view.ViewImpl;

public class Main {

	public static void main(String[] args) {
		Model model = new ModelImpl();
		ViewImpl view = new ViewImpl();
		Controller controller = new Controller(model, view);
		controller.init();
		controller.process();		
	}

}
