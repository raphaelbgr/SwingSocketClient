package fxgui.scenes;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class MainScene extends Scene implements Initializable {

	public MainScene(Parent arg0) {
		super(arg0);
		btn_send.onKeyPressedProperty();
	}

	@FXML
	private Button btn_send = new Button();
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
