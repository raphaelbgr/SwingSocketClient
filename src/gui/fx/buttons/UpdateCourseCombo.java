package gui.fx.buttons;

import java.sql.SQLException;

import dao.DAO;
import gui.fx.WindowDataFacade;
import gui.fx.events.EventInterface;
import gui.fx.events.GetServerKeys;

public class UpdateCourseCombo implements EventInterface {

	@Override
	public boolean performAction() {
		DAO dao = new DAO();
		GetServerKeys gsk = new GetServerKeys();
		if (gsk.performAction()) {
			try {
				dao.connect();
				WindowDataFacade.getInstance().updateCourseCombo(dao.queryCourses());
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
