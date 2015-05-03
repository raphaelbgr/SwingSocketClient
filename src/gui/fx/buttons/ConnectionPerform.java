package gui.fx.buttons;

import exceptions.ServerException;
import gui.fx.WindowDataFacade;
import gui.fx.events.EventInterface;

import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import sendable.Client;
import serverinteraction.Connect;
import sync.ClientStream;
import threads.FXReceiveFromServerThread;
import clientmain.ClientMain;
import clientmain.Status;

public class ConnectionPerform implements EventInterface {

	ClientStream stream = ClientStream.getInstance();
	WindowDataFacade wdf = WindowDataFacade.getInstance();
	ProgressBar progress = ((ProgressBar)wdf.getNode("progress"));
	ProgressIndicator indicator = ((ProgressIndicator)wdf.getNode("indicator"));
	Task worker = null;

	@FXML
	public void performAction() {
		try {
			WindowDataFacade.getInstance().createConnectingWorker();
			new Connect(buildClient(wdf));
			Status.getInstance().setConnected(true);
			Thread t1 = new Thread(new FXReceiveFromServerThread());
			t1.start();
//			wdf.setConnectedLockFields();
			WindowDataFacade.getInstance().createConnectedWorker();
		} catch (ConnectException | UnknownHostException e) {
			wdf.createCanceledWorker();
			WindowDataFacade.getInstance().setBigStatusMsg(getTimestamp() + "LOCAL> " + e.getLocalizedMessage());
			reconnect();
		} catch (IOException e) {
			wdf.createCanceledWorker();
			WindowDataFacade.getInstance().createCanceledWorker();
			e.printStackTrace();
			reconnect();
		} catch (Throwable e) {
			wdf.createCanceledWorker();
			WindowDataFacade.getInstance().createCanceledWorker();
			e.printStackTrace();
			reconnect();
		}
	}

	private String getTimestamp() {
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		String dateFormatted = formatter.format(new Date());
		return "["+dateFormatted+"]" + " ";
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
							e.printStackTrace();
						} catch (IOException e) {
							Status.getInstance().setConnected(false);
							WindowDataFacade.getInstance().createConnectingWorker();
							WindowDataFacade.getInstance().setConnectingLockFields();
							e.printStackTrace();
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
				} else {
//					WindowDataFacade.getInstance().createCanceledWorker();
//					WindowDataFacade.getInstance().setDisconnectedLockFields();
				}
			}
		};
		t1.setDaemon(true);
		t1.start();
	}
}
