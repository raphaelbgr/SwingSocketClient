package app.view.buttons;

import java.sql.SQLException;

import app.control.dao.DAO;
import app.view.WindowDataFacade;
import app.view.events.EventInterface;
import app.view.events.RequestServerKeys;

public class UpdateCourseCombo implements EventInterface {

	@Override
	public boolean performAction() {
		RequestServerKeys gsk = new RequestServerKeys();
		boolean go = gsk.performAction();
		if (go) {
			DAO dao = new DAO();
			try {
				dao.connect();
				WindowDataFacade.getInstance().updateCourseCombo(dao.queryCourses());
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			} finally {
				try {
					dao.disconnect();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else return false;
	}
}
