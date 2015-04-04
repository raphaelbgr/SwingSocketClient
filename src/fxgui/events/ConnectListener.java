package fxgui.events;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ConnectListener {

	@FXML
	private Button btn_connect = new Button();

	@FXML
	public void performAction() {
		System.out.println("Connect Button clicked");
	}
	
}
