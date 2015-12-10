package app.view.buttons;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import app.control.serverinteraction.Connect;
import app.control.serverinteraction.Register;
import app.view.WindowDataFacade;
import app.view.events.EventInterface;
import net.sytes.surfael.api.control.sync.ClientStream;
import net.sytes.surfael.api.control.sync.Status;

public class RegisterPerform implements EventInterface {

	@Override
	public boolean performAction() {
		try {
			Status.getInstance().setConnected(true);
			WindowDataFacade.getInstance().createConnectingWorker();
			new Connect(WindowDataFacade.getInstance().getAddress(), WindowDataFacade.getInstance().getPort());
			new Register(WindowDataFacade.getInstance().buildNewClientFromForm());
			ClientStream.getInstance().getSock().close();
			WindowDataFacade.getInstance().createConnectedWorker();
			WindowDataFacade.getInstance().setBigStatusMsg(getTimestamp() + "LOCAL> " + "Sent register user request to the server.");
		} catch (Throwable e) {
			e.printStackTrace();
			WindowDataFacade.getInstance().createCanceledWorker();
			WindowDataFacade.getInstance().setBigStatusMsg(getTimestamp() + "LOCAL> " + e.getLocalizedMessage());
		} finally {
			Status.getInstance().setConnected(false);
		}
		return true;
	}
	
	private String getTimestamp() {
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		String dateFormatted = formatter.format(new Date());
		return "["+dateFormatted+"]" + " ";
	}


}
