package fxgui.scenes;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MainScene extends Scene implements Initializable {

	public MainScene(Parent root) {
		super(root);
	}

	@FXML
	private Button btn_connect;
	@FXML
	private Button btn_disconnect;
	@FXML
	private Button btn_sel_serv;
	@FXML
	private TextField fld_userName;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		fld_userName.setId("fld_userName");
	}

}
