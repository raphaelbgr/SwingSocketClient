package gui.fx.buttons;

import gui.fx.WindowDataFacade;
import gui.fx.events.EventInterface;
import gui.fx.events.GetServerKeys;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import dao.DAO;

public class RegisterPerform implements EventInterface {

	@Override
	public boolean performAction() {
		DAO dao = new DAO();
		WindowDataFacade.getInstance().createConnectingWorker();
		GetServerKeys gsk = new GetServerKeys();
		if (gsk.performAction()) {
			try {
				dao.connect();
				dao.registerUser();
				dao.disconnect();
				WindowDataFacade.getInstance().createConnectedWorker();
				return true;
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				WindowDataFacade.getInstance().createCanceledWorker();
				WindowDataFacade.getInstance().setBigStatusMsg(getTimestamp() + "LOCAL> " + e.getLocalizedMessage());
				return false;
			}
		} else return false;
	}

	private String getTimestamp() {
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		String dateFormatted = formatter.format(new Date());
		return "["+dateFormatted+"]" + " ";
	}


}
