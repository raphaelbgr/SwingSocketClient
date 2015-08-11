package app.view.buttons;

import app.view.WindowDataFacade;
import app.view.events.EventInterface;

public class RefreshPerform implements EventInterface {

	@Override
	public boolean performAction() {
		WindowDataFacade.getInstance().populateHistoryTable();
		return true;
	}

}
