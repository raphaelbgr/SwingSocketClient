package gui.fx.buttons;

import gui.fx.WindowDataFacade;
import gui.fx.events.EventInterface;

public class RegisterPerform implements EventInterface {

	@Override
	public void performAction() {
		String name = WindowDataFacade.getInstance().getUserName();
		String pass = WindowDataFacade.getInstance().getPassword();
	}

	
}
