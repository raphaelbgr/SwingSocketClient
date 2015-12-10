package app.view.buttons;

import java.io.IOException;

import app.view.WindowDataFacade;
import app.view.events.EventInterface;
import net.sytes.surfael.api.control.sync.ClientStream;
import net.sytes.surfael.api.model.messages.ServerMessage;

public class PerformHistoryComboUpdate implements EventInterface {
	@Override
	public boolean performAction() {
		
		// CREATES A TASK TO SEND A HISTORY REQUEST ON BACKGROUND
		Thread th = new Thread(new Runnable() {
			@Override
			public void run() {
				ServerMessage sm = new ServerMessage();
				sm.setRequest("history");
				sm.setRowLimit(WindowDataFacade.getInstance().queryLimitSelected());
				try {
					ClientStream.getInstance().sendObject(sm);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		th.start();
		return true;
	}

}
