package app.view.buttons;

import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import app.ClientMain;
import app.control.serverinteraction.Connect;
import app.control.services.FXReceiveFromServerThread;
import app.view.WindowDataFacade;
import app.view.events.EventInterface;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import net.sytes.surfael.api.control.sync.ClientStream;
import net.sytes.surfael.api.control.sync.Status;
import net.sytes.surfael.api.model.clients.Client;

public class ConnectionPerform implements EventInterface {

	ClientStream stream = ClientStream.getInstance();
	WindowDataFacade wdf = WindowDataFacade.getInstance();
	ProgressBar progress = ((ProgressBar) wdf.getNode("progress"));

	@FXML
	public boolean performAction() {
		if (wdf.validateName()) {
			if (wdf.validadePassword()) {
				if (wdf.validadeIP()) {
					if (wdf.validadePort()) {
						try {
							WindowDataFacade.getInstance().lockConnectButton(true);
							WindowDataFacade.getInstance().createConnectingWorker();
							new Connect(buildClient(wdf));
							Status.getInstance().setConnected(true);
							Thread t1 = new Thread(new FXReceiveFromServerThread());
							t1.start();
							WindowDataFacade.getInstance().createConnectedWorker();
						} catch (Throwable e) {
							WindowDataFacade.getInstance().lockConnectButton(false);
							wdf.createCanceledWorker();
							WindowDataFacade.getInstance().createCanceledWorker();
							e.printStackTrace();
							reconnect();
						}
					} else {
						wdf.setBigStatusMsg(getTimestamp() + "LOCAL> " + "Invalid port range.");
						return false;
					}
				} else {
					wdf.setBigStatusMsg(getTimestamp() + "LOCAL> " + "Invalid IP lenght.");
					return false;
				}
			} else {
				wdf.setBigStatusMsg(getTimestamp() + "LOCAL> " + "Invalid password lenght.");
				return false;
			}
		} else {
			wdf.setBigStatusMsg(getTimestamp() + "LOCAL> " + "Please enter a valid login.");
			return false;
		}
		return true;
	}

	private String getTimestamp() {
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		String dateFormatted = formatter.format(new Date());
		return "["+dateFormatted+"]" + " ";
	}
	
	private Client buildClient(WindowDataFacade wdf) {
		Client c = new Client();
		c.setLogin(wdf.getLogin());
		c.setTargetPort(wdf.getPort().intValue());
		c.setVersion(ClientMain.VERSION);
//		c.setPassword(wdf.getPassword());
		c.setMD5Password(wdf.getPassword());
		c.setTargetIp(WindowDataFacade.getInstance().getAddress());
		c.setConnect(true);
		c.setPlatform(0);
		return c;
	}
	
	private void reconnect() {
		Thread t1 = new Thread() {
			public void run() {
				if (!Status.getInstance().isConnected() && WindowDataFacade.getInstance().getChkbox_autocon().isSelected()) {
					WindowDataFacade.getInstance().createConnectingWorker();
					while(!Status.getInstance().isConnected() && WindowDataFacade.getInstance().getChkbox_autocon().isSelected()) {
						try {
							new Connect(buildClient(wdf));
							Thread t1 = new Thread(new FXReceiveFromServerThread());
							t1.start();
							WindowDataFacade.getInstance().createConnectedWorker();
							Status.getInstance().setConnected(true);
							WindowDataFacade.getInstance().setConnectedLockFields();
						} catch (ConnectException e) {
							Status.getInstance().setConnected(false);
							WindowDataFacade.getInstance().createConnectingWorker();
							WindowDataFacade.getInstance().setConnectingLockFields();
						} catch (UnknownHostException e) {
							Status.getInstance().setConnected(false);
							WindowDataFacade.getInstance().createConnectingWorker();
							WindowDataFacade.getInstance().setConnectingLockFields();
//							e.printStackTrace();
						} catch (IOException e) {
							Status.getInstance().setConnected(false);
							WindowDataFacade.getInstance().createConnectingWorker();
							WindowDataFacade.getInstance().setConnectingLockFields();
//							e.printStackTrace();
						} finally {
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
					if (Status.getInstance().isConnected()) {
						WindowDataFacade.getInstance().createConnectedWorker();
						wdf.setConnectedLockFields();
						WindowDataFacade.getInstance().createConnectedWorker();
					} else {
						WindowDataFacade.getInstance().createCanceledWorker();
						WindowDataFacade.getInstance().setDisconnectedLockFields();
					}			
				}
			}
		};
		t1.setDaemon(true);
		t1.start();
	}
}
