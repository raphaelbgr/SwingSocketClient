package gui.fx.buttons;

import java.sql.SQLException;

import clientmain.ClientMain;
import dao.DAO;
import gui.fx.WindowDataFacade;
import gui.fx.events.EventInterface;

public class CollegeUpdatePerform implements EventInterface {

	@Override
	public boolean performAction() {
		if (ClientMain.DATABASE_ADDR != null) {
			DAO dao = new DAO();
			try {
				dao.connect();
				WindowDataFacade.getInstance().updateCollegeCombo(dao.queryColleges());
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
