package gui.fx.buttons;

import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import clientmain.ClientMain;
import clientmain.Status;
import exceptions.LocalException;
import gui.fx.WindowDataFacade;
import gui.fx.events.EventInterface;
import sendable.Client;
import serverinteraction.Connect;
import serverinteraction.Send;
import threads.FXReceiveFromServerThread;

public class SendPerform implements EventInterface {


	@Override
	public void performAction() {
		try {
			WindowDataFacade.getInstance().createConnectingWorker();
			new Send(WindowDataFacade.getInstance().getMessage());
			WindowDataFacade.getInstance().clearMessageBox();
			WindowDataFacade.getInstance().createSendWorker();
		} catch (IOException e) {
			WindowDataFacade.getInstance().setBigStatusMsg(getTimestamp() +"LOCAL> " + e.getLocalizedMessage());
			WindowDataFacade.getInstance().createCanceledWorker();
			e.printStackTrace();
			reconenct();
		} catch (LocalException e) {
			WindowDataFacade.getInstance().setBigStatusMsg(getTimestamp() +"LOCAL> " + e.getLocalizedMessage());
			WindowDataFacade.getInstance().createCanceledWorker();
			e.printStackTrace();
			reconenct();
		} finally {
			reconenct();
		}
	}
	
	private Client buildClient(WindowDataFacade wdf) {
		Client c = new Client();
		c.setName(wdf.getUserName());
		c.setTargetPort(wdf.getPort().intValue());
		c.setVersion(ClientMain.version);
		c.setPassword(wdf.getPassword());
		c.setTargetIp(WindowDataFacade.getInstance().getAddress());
		return c;
	}
	
	private String getTimestamp() {
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		String dateFormatted = formatter.format(new Date());
		return "["+dateFormatted+"]" + " ";
	}
	
	private void reconenct() {
		if (!Status.getInstance().isConnected() && WindowDataFacade.getInstance().getChkbox_autocon().isSelected()) {
			WindowDataFacade.getInstance().createConnectingWorker();
			while(!Status.getInstance().isConnected() && WindowDataFacade.getInstance().getChkbox_autocon().isSelected()) {
				try {
					new Connect(buildClient(WindowDataFacade.getInstance()));
					Thread t1 = new Thread(new FXReceiveFromServerThread());
					t1.start();
					WindowDataFacade.getInstance().createConnectedWorker();
					Status.getInstance().setConnected(true);
					WindowDataFacade.getInstance().setConnectedLockFields();
				} catch (ConnectException e2) {
					Status.getInstance().setConnected(false);
					WindowDataFacade.getInstance().createConnectingWorker();
					WindowDataFacade.getInstance().setConnectingLockFields();
				} catch (UnknownHostException e2) {
					Status.getInstance().setConnected(false);
					WindowDataFacade.getInstance().createConnectingWorker();
					WindowDataFacade.getInstance().setConnectingLockFields();
//					e.printStackTrace();
				} catch (IOException e2) {
					Status.getInstance().setConnected(false);
					WindowDataFacade.getInstance().createConnectingWorker();
					WindowDataFacade.getInstance().setConnectingLockFields();
					e2.printStackTrace();
				} finally {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e2) {
						e2.printStackTrace();
					}
				}
			}
			if (Status.getInstance().isConnected()) {
				WindowDataFacade.getInstance().createConnectedWorker();
				WindowDataFacade.getInstance().setConnectedLockFields();
				WindowDataFacade.getInstance().createConnectedWorker();
			} else {
				WindowDataFacade.getInstance().createCanceledWorker();
				WindowDataFacade.getInstance().setDisconnectedLockFields();
			}			
		} else {
		}
	}
}
