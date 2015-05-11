package gui.fx.events;

import gui.fx.WindowDataFacade;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import sendable.RegistrationMessage;
import serverinteraction.Connect;
import serverinteraction.Disconnect;
import sync.ClientStream;
import threads.FXReceiveFromServerThread;
import clientmain.ClientMain;

public class GetServerKeys implements EventInterface {

	@Override
	public boolean performAction() {
		if (ClientMain.DATABASE_ADDR == null) {
			WindowDataFacade.getInstance().createConnectingWorker();
			WindowDataFacade.getInstance().setBigStatusMsg(getTimestamp() + "LOCAL> " + "Requesting Server Keys...");
			try {
				RegistrationMessage rm = new RegistrationMessage();
				new Connect(WindowDataFacade.getInstance().getAddress(),WindowDataFacade.getInstance().getPort());
				Thread t1 = new Thread(new FXReceiveFromServerThread());
				t1.start();
				rm.setPcname(ClientStream.getInstance().getSock().getInetAddress().getHostName());
				rm.setIp(ClientStream.getInstance().getSock().getInetAddress().getHostAddress());
				ClientStream.getInstance().sendObject(rm);
				WindowDataFacade.getInstance().setBigStatusMsg(getTimestamp() + "LOCAL> " + "Waiting for server keys request.");
				for (int i = 0; i < 3; i++) {
					if (ClientMain.DATABASE_ADDR != null && ClientMain.DATABASE_KEY != null && ClientMain.DATABASE_PASS != null && ClientMain.DATABASE_USER != null) {
						WindowDataFacade.getInstance().createConnectedWorker();
						WindowDataFacade.getInstance().setBigStatusMsg(getTimestamp() + "LOCAL> " + "Got server database keys.");
						return true;
					} else {
						Thread.sleep(2000);
						WindowDataFacade.getInstance().setBigStatusMsg(getTimestamp() + "LOCAL> " + "Waiting for server keys request. Try "+i+"...");

					}
				}
				if (ClientMain.DATABASE_ADDR == null) {
					WindowDataFacade.getInstance().setBigStatusMsg(getTimestamp() + "LOCAL> " + "Unable to get Database keys from server...");
					WindowDataFacade.getInstance().createCanceledWorker();
					return false;
				}

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