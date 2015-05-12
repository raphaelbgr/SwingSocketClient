package gui.fx.buttons;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import clientmain.ClientMain;
import dao.DAO;
import gui.fx.WindowDataFacade;
import gui.fx.events.EventInterface;
import gui.fx.events.RequestServerKeys;

public class PerformHistoryComboUpdate implements EventInterface {

	@Override
	public boolean performAction() {
		RequestServerKeys gsk = new RequestServerKeys();
		gsk.performAction();

		for (int i = 0; i < 100; i++) {
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
					try {
						dao.disconnect();
					} catch (SQLException e1) {
//							e.printStackTrace();
					}
				}
			} else {
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
