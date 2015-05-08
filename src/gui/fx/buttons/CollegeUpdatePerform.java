package gui.fx.buttons;

import java.sql.SQLException;

import dao.DAO;
import gui.fx.WindowDataFacade;
import gui.fx.events.EventInterface;

public class CollegeUpdatePerform implements EventInterface {

	@Override
	public void performAction() {
		DAO dao = new DAO();
		try {
			dao.connect();
			WindowDataFacade.getInstance().updateCollegeCombo(dao.queryColleges());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
