package gui.fx.buttons;

import java.sql.SQLException;

import gui.fx.events.EventInterface;
import gui.fx.WindowDataFacade;
import dao.DAO;

public class LoginPerform implements EventInterface {

	@Override
	public void performAction() {
		DAO dao = new DAO();
		try {
			dao.connect();
			WindowDataFacade.getInstance().updateLoginCombo(dao.queryLogins());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
