package gui.fx.events;

import gui.fx.WindowDataFacade;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import sendable.RegistrationMessage;
import serverinteraction.Connect;
import sync.ClientStream;
import threads.FXReceiveFromServerThread;
import clientmain.ClientMain;

public class RequestServerKeys implements EventInterface {

	@Override
	public boolean performAction() {
		if (ClientMain.DATABASE_ADDR == null) {
			WindowDataFacade.getInstance().createConnectingWorker();
			//			WindowDataFacade.getInstance().setBigStatusMsg(getTimestamp() + "LOCAL> " + "Requesting Server Keys...");
			try {
				RegistrationMessage rm = new RegistrationMessage();
				new Connect(WindowDataFacade.getInstance().getAddress(),WindowDataFacade.getInstance().getPort());
				Thread t1 = new Thread(new FXReceiveFromServerThread());
				t1.start();
				rm.setDnsHostName(ClientStream.getInstance().getSock().getInetAddress().getHostName());
				rm.setIp(ClientStream.getInstance().getSock().getInetAddress().getHostAddress());
				rm.setPcname(ClientStream.getInstance().getSock().getInetAddress().getCanonicalHostName());
				rm.setCompilationKey(ClientMain.COMPILATION_KEY);
				rm.setVersion(ClientMain.VERSION);
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