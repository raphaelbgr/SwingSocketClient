package gui.fx.buttons;

import gui.fx.WindowDataFacade;
import gui.fx.events.EventInterface;
import gui.fx.events.RequestServerKeys;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import clientmain.ClientMain;
import dao.DAO;

public class LoginPerform implements EventInterface {

	@Override
	public boolean performAction() {
		RequestServerKeys gsk = new RequestServerKeys();
		gsk.performAction();
		
		for (int i = 0; i < 40; i++) {
			if (ClientMain.DATABASE_ADDR != null && ClientMain.DATABASE_KEY != null && ClientMain.DATABASE_PASS != null && ClientMain.DATABASE_USER != null) {
				DAO dao = new DAO();
				try {
					dao.connect();
					WindowDataFacade.getInstance().updateLoginCombo(dao.queryLogins());
					dao.disconnect();
					return true;
				} catch (SQLException e) {
					WindowDataFacade.getInstance().createCanceledWorker();
					WindowDataFacade.getInstance().setBigStatusMsg(getTimestamp() + "LOCAL> " + e.getLocalizedMessage());
				}
			} else {
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
				}
			}
		} return false;
	}
	
	private String getTimestamp() {
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		String dateFormatted = formatter.format(new Date());
		return "["+dateFormatted+"]" + " ";
	}

}
