package fxgui.events;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SendListener {

	@FXML
	private Button btn_send = new Button();

	@FXML
	private void handleSendButton() {
		System.out.println("Send Clicked");
	}
	
}
