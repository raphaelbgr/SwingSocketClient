package gui.fx.buttons;

import gui.fx.WindowDataFacade;
import gui.fx.events.EventInterface;

import java.io.IOException;
import java.net.UnknownHostException;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import sendable.Client;
import serverinteraction.Connect;
import sync.ClientStream;
import clientmain.ClientMain;

public class ConnectionPerform implements EventInterface {

	ClientStream stream = ClientStream.getInstance();
	WindowDataFacade wdf = WindowDataFacade.getInstance();
	ProgressBar progress = ((ProgressBar)wdf.getNode("progress"));
	ProgressIndicator indicator = ((ProgressIndicator)wdf.getNode("indicator"));
	Task worker = null;
	
	@FXML
	public void performAction() {
		
		try {
			createWorker();
			Client c = buildClient(wdf);
			new Connect(c);
			wdf.setConnectedLockFields();
			worker.cancel(true);
			progress.progressProperty().unbind();
			createSlowWorker();
		} catch (UnknownHostException e) {
//			cancelProgress();
			e.printStackTrace();
		} catch (IOException e) {
//			cancelProgress();
			e.printStackTrace();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void createWorker() {
		worker = createConnectWorkerTask();
		progress.progressProperty().unbind();
		indicator.progressProperty().unbind();
		progress.progressProperty().bind(worker.progressProperty());
		indicator.progressProperty().bind(worker.progressProperty());
		new Thread(worker).start();
	}
	
	private void createSlowWorker() {
		worker = createSlowtWorkerTask();
		progress.progressProperty().unbind();
		indicator.progressProperty().unbind();
		progress.progressProperty().bind(worker.progressProperty());
		indicator.progressProperty().bind(worker.progressProperty());
		new Thread(worker).start();
	}
	
	private Task createSlowtWorkerTask() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
            	for (int i = 0; i <= 100; i++) {
            		updateProgress(i, 100);
            		Thread.sleep(1);
            	}
                return true;
            }
        };
	}
	
	private Task createConnectWorkerTask() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                updateProgress(0, -1);
                return true;
            }
        };
	}

	private Client buildClient(WindowDataFacade wdf) {
		Client c = new Client();
		c.setName(wdf.getUserName());
		c.setPort(wdf.getPort().intValue());
		c.setVersion(ClientMain.version);
		c.setPassword(wdf.getPassword());
		return c;
	}
}
