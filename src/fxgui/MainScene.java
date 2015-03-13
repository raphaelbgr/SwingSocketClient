package fxgui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class MainScene implements Initializable {

	@FXML
	private Button btn_send;
	@FXML
	private Button btn_connect;
	@FXML
	private Button btn_disconnect;
	@FXML
	private Button btn_sel_serv;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}

}
