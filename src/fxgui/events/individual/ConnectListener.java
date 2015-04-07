package fxgui.events.individual;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import fxgui.events.EventInterface;

public class ConnectListener implements EventInterface {

	@FXML
	private Button btn_connect = new Button();

	@FXML
	public void performAction() {
		TextField txt_field = (TextField) btn_connect.getScene().lookup("#fld_userName");
		System.out.println(txt_field.getText());
	}
	
}
