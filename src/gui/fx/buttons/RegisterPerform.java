package gui.fx.buttons;

import gui.fx.WindowDataFacade;
import gui.fx.events.EventInterface;
import dao.DAO;

public class RegisterPerform implements EventInterface {

	@Override
	public void performAction() {
		String name = WindowDataFacade.getInstance().getUserName();
		String pass = WindowDataFacade.getInstance().getPassword();
		
		DAO dao = new DAO();
		dao.connect();
		
	}

	
}
