package fxgui.events.individual;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import fxgui.events.EventInterface;

public class SendListener implements EventInterface {

	@FXML
	private Button btn_send = new Button();

	@FXML
	public void performAction() {
		System.out.println("Send Clicked");
	}
}
