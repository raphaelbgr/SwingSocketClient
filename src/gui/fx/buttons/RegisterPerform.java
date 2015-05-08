package gui.fx.buttons;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import gui.fx.WindowDataFacade;
import gui.fx.events.EventInterface;
import dao.DAO;

public class RegisterPerform implements EventInterface {

	@Override
	public void performAction() {
		
		DAO dao = new DAO();
		try {
			dao.connect();
			dao.registerUser();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			WindowDataFacade.getInstance().setBigStatusMsg(getTimestamp() + "LOCAL> " + e.getLocalizedMessage());
		}
		
	}
	
	private String getTimestamp() {
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		String dateFormatted = formatter.format(new Date());
		return "["+dateFormatted+"]" + " ";
	}

	
}
