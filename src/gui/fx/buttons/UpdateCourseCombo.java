package gui.fx.buttons;

import java.sql.SQLException;

import dao.DAO;
import gui.fx.WindowDataFacade;
import gui.fx.events.EventInterface;
import gui.fx.events.RequestServerKeys;

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
