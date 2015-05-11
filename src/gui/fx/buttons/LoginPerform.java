package gui.fx.buttons;

import gui.fx.WindowDataFacade;
import gui.fx.events.EventInterface;
import gui.fx.events.GetServerKeys;

import java.sql.SQLException;

import dao.DAO;

public class LoginPerform implements EventInterface {

	@Override
	public boolean performAction() {
		GetServerKeys gsk = new GetServerKeys();
		boolean go = gsk.performAction();
		if (go) {
			DAO dao = new DAO();
			try {
				dao.connect();
				WindowDataFacade.getInstance().updateLoginCombo(dao.queryLogins());
				dao.disconnect();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		} else return false;
	}

}
