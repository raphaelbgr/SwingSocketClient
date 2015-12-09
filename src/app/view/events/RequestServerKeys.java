package app.view.events;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import api.control.sync.ClientStream;
import app.ClientMain;
import app.control.serverinteraction.Connect;
import app.model.messages.RegistrationMessage;
import app.view.WindowDataFacade;

public class RequestServerKeys implements EventInterface {

	@Override
	public boolean performAction() {
		if (ClientMain.DATABASE_ADDR == null) {
			WindowDataFacade.getInstance().createConnectingWorker();
			//			WindowDataFacade.getInstance().setBigStatusMsg(getTimestamp() + "LOCAL> " + "Requesting Server Keys...");
			try {
				RegistrationMessage rm = new RegistrationMessage();
				new Connect(WindowDataFacade.getInstance().getAddress(),WindowDataFacade.getInstance().getPort());
//				Thread t1 = new Thread(new FXReceiveFromServerThread());
//				t1.start();
				rm.setDnsHostName(ClientStream.getInstance().getSock().getInetAddress().getHostName());
				rm.setIp(ClientStream.getInstance().getSock().getInetAddress().getHostAddress());
				rm.setPcname(ClientStream.getInstance().getSock().getInetAddress().getCanonicalHostName());
				rm.setCompilationKey(ClientMain.COMPILATION_KEY);
				rm.setVersion(ClientMain.VERSION);
				rm.setOwnerLogin(WindowDataFacade.getInstance().getLogin());
				ClientStream.getInstance().sendObject(rm);
			} catch (Throwable e) {
				WindowDataFacade.getInstance().createCanceledWorker();
				if (e.getLocalizedMessage() != null) {
					WindowDataFacade.getInstance().setBigStatusMsg(getTimestamp() + "LOCAL> " + e.getLocalizedMessage());
				}
				return false;
			}
		} else return true;
		return true;
	}

	private String getTimestamp() {
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		String dateFormatted = formatter.format(new Date());
		return "["+dateFormatted+"]" + " ";
	}
}