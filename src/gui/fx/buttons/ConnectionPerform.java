package gui.fx.buttons;

import gui.fx.WindowDataFacade;
import gui.fx.events.EventInterface;

import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import sendable.Client;
import serverinteraction.Connect;
import sync.ClientStream;
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
			wdf.setConnectedLockFields();
			WindowDataFacade.getInstance().createConnectedWorker();
		} catch (ConnectException e){
			WindowDataFacade.getInstance().setBigStatusMsg("LOCAL> " + e.getLocalizedMessage());

			Thread t1 = new Thread() {
				public void run() {
					if (!Status.getInstance().isConnected() && WindowDataFacade.getInstance().getChkbox_autocon().isSelected()) {
						WindowDataFacade.getInstance().createConnectingWorker();
						while(!Status.getInstance().isConnected() && WindowDataFacade.getInstance().getChkbox_autocon().isSelected()) {
							try {
								new Connect(buildClient(wdf));
								WindowDataFacade.getInstance().createConnectedWorker();
								Status.getInstance().setConnected(true);
							} catch (ConnectException e) {
								Status.getInstance().setConnected(false);
								WindowDataFacade.getInstance().createConnectingWorker();
							} catch (UnknownHostException e) {
								Status.getInstance().setConnected(false);
								WindowDataFacade.getInstance().createConnectingWorker();
								e.printStackTrace();
							} catch (IOException e) {
								Status.getInstance().setConnected(false);
								WindowDataFacade.getInstance().createConnectingWorker();
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
						} else {
							WindowDataFacade.getInstance().createCanceledWorker();
						}			
					} else {
						WindowDataFacade.getInstance().createCanceledWorker();
					}
				}
			};
			t1.setDaemon(true);
			t1.start();
		} catch (UnknownHostException e) {
			WindowDataFacade.getInstance().createCanceledWorker();
			e.printStackTrace();
		} catch (IOException e) {
			WindowDataFacade.getInstance().createCanceledWorker();
			e.printStackTrace();
		} catch (Throwable e) {
			WindowDataFacade.getInstance().createCanceledWorker();
			e.printStackTrace();
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
}
