package app.view.buttons;

import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import app.ClientMain;
import app.control.serverinteraction.Connect;
import app.control.serverinteraction.Send;
import app.control.services.FXReceiveFromServerThread;
import app.view.WindowDataFacade;
import app.view.events.EventInterface;
import net.sytes.surfael.api.control.sync.Status;
import net.sytes.surfael.api.model.clients.Client;
import net.sytes.surfael.api.model.exceptions.LocalException;

public class SendPerform implements EventInterface {

	@Override
	public boolean performAction() {
		if (WindowDataFacade.getInstance().validadeMessage()) {
			try {
				WindowDataFacade.getInstance().lockConnectButton(true);
				WindowDataFacade.getInstance().createConnectingWorker();
				new Send(WindowDataFacade.getInstance().getMessage());
				WindowDataFacade.getInstance().clearMessageBox();
				WindowDataFacade.getInstance().createSendWorker();
				return true;
			} catch (IOException e) {
				WindowDataFacade.getInstance().lockConnectButton(false);
				WindowDataFacade.getInstance().setBigStatusMsg(getTimestamp() +"LOCAL> " + e.getLocalizedMessage());
				WindowDataFacade.getInstance().createCanceledWorker();
				e.printStackTrace();
				reconenct();
				return false;
			} catch (LocalException e) {
				WindowDataFacade.getInstance().lockConnectButton(false);
				WindowDataFacade.getInstance().setBigStatusMsg(getTimestamp() +"LOCAL> " + e.getLocalizedMessage());
				WindowDataFacade.getInstance().createCanceledWorker();
				e.printStackTrace();
				reconenct();
				return false;
			} finally {
				reconenct();
			}
		} else {
			WindowDataFacade.getInstance().setBigStatusMsg(getTimestamp() +"LOCAL> " + "Blank messages not allowed.");
			return false;
		}
	}

	private Client buildClient(WindowDataFacade wdf) {
		Client c = new Client();
		c.setLogin(wdf.getLogin());
		c.setTargetPort(wdf.getPort().intValue());
		c.setVersion(ClientMain.VERSION);
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
			} if (Status.getInstance().isConnected()) {
				WindowDataFacade.getInstance().createConnectedWorker();
				WindowDataFacade.getInstance().setConnectedLockFields();
				WindowDataFacade.getInstance().createConnectedWorker();
			} else {
				WindowDataFacade.getInstance().createCanceledWorker();
				WindowDataFacade.getInstance().setDisconnectedLockFields();
			}			
		}
	}
}
