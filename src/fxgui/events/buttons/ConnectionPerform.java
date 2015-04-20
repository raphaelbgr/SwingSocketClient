package fxgui.events.buttons;

import java.io.IOException;
import java.net.UnknownHostException;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import sendable.Client;
import serverinteraction.Connect;
import sync.ClientStream;
import clientmain.ClientMain;
import fxgui.events.EventInterface;
import fxgui.events.WindowData;

public class ConnectionPerform implements EventInterface {

	ClientStream stream = ClientStream.getInstance();

	@FXML
	public void performAction(WindowData wd) {
		final ProgressBar pb = (ProgressBar) wd.getNodes().get(0).getScene().lookup("#progress");
		final ProgressIndicator pi = (ProgressIndicator) wd.getNodes().get(0).getScene().lookup("#indicator");
		try {

			Client c = buildClient(wd);

			new Connect(c);

			lockConnectionFields(wd);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Client buildClient(WindowData wd) {
		Client c = new Client();
		c.setName(wd.getUserName());
		c.setPort(wd.getPort());
		c.setVersion(ClientMain.version);
		c.setPassword(wd.getPassword());
		c.setLogin(wd.getUserName());
		return c;
	}

	private void lockConnectionFields(WindowData wd) {
		for (Node node : wd.getNodes()) {
			if (!node.getId().equalsIgnoreCase("btn_disconnect")) {
				node.setDisable(true);
			} else if (node.getId().equalsIgnoreCase("btn_disconnect")) {
				node.setDisable(false);
			}
		}

	}

}
