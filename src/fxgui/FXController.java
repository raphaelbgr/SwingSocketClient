package fxgui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import fxgui.events.individual.ConnectListener;
import fxgui.events.individual.SendListener;

public class FXController {
	
	@FXML
	private Button btn_connect = new Button();
	@FXML
	private Button btn_disconnect = new Button();
	@FXML
	private Button btn_serv_opt = new Button();
	@FXML
	private TextField fld_userName = new TextField();
	@FXML
	private TextField sv_Address = new TextField();
	@FXML
	private PasswordField passwd_field= new PasswordField();
	
	public void handleSendButton() {
		SendListener sl = new SendListener();
		sl.performAction();
	}
	
	public void handleConnectButton() {
		ConnectListener sl = new ConnectListener();
		sl.setBtn_connect(btn_connect);
		sl.setFld_userName(fld_userName);
		sl.setSv_Address(sv_Address);
		sl.setPasswd_field(passwd_field);
		sl.performAction();
	}
	
	public FXController() {
		sv_Address.setId("svAddress");
	}
}
