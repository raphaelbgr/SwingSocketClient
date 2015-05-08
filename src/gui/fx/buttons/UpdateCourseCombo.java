package gui.fx.buttons;

import java.sql.SQLException;

import dao.DAO;
import gui.fx.WindowDataFacade;
import gui.fx.events.EventInterface;

public class UpdateCourseCombo implements EventInterface {

	@Override
	public void performAction() {
		DAO dao = new DAO();
		try {
			dao.connect();
			WindowDataFacade.getInstance().updateCourseCombo(dao.queryCourses());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
