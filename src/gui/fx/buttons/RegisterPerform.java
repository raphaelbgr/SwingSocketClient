package gui.fx.buttons;

import gui.fx.WindowDataFacade;
import gui.fx.events.EventInterface;
import gui.fx.events.GetServerKeys;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import dao.DAO;

public class RegisterPerform implements EventInterface {

	@Override
	public boolean performAction() {
		
		GetServerKeys gsk = new GetServerKeys();
		boolean go = gsk.performAction();
		if (go) {
			DAO dao = new DAO();
			try {
				dao.connect();
				dao.registerUser();
				dao.disconnect();
				WindowDataFacade.getInstance().createConnectedWorker();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
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
