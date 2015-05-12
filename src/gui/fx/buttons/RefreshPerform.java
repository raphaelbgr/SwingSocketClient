package gui.fx.buttons;

import gui.fx.WindowDataFacade;
import gui.fx.events.EventInterface;

public class RefreshPerform implements EventInterface {

	@Override
	public boolean performAction() {
		WindowDataFacade.getInstance().populateHistoryTable();
		return true;
	}

}
