package app.view.buttons;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import app.ClientMain;
import app.control.dao.DAO;
import app.view.WindowDataFacade;
import app.view.events.EventInterface;
import app.view.events.RequestServerKeys;

public class PerformHistoryComboUpdate2 implements EventInterface {
	@Override
	public boolean performAction() {
		RequestServerKeys gsk = new RequestServerKeys();
		gsk.performAction();

		for (int i = 0; i < 100; i++) {
			if (ClientMain.DATABASE_ADDR != null && ClientMain.DATABASE_KEY != null && ClientMain.DATABASE_PASS != null && ClientMain.DATABASE_USER != null) {
				DAO dao = new DAO();
				try {
					dao.connect();
//					WindowDataFacade.getInstance().populateHistoryTable();
					dao.disconnect();
					return true;
				} catch (SQLException e) {
					WindowDataFacade.getInstance().createCanceledWorker();
					WindowDataFacade.getInstance().setBigStatusMsg(getTimestamp() + "LOCAL> " + e.getLocalizedMessage());
//					try {
						if (ClientMain.DATABASE_ADDR != null && ClientMain.DATABASE_KEY != null && ClientMain.DATABASE_PASS != null && ClientMain.DATABASE_USER != null) {
//							dao.disconnect();
						}
//					}
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
