package fxgui.events.individual;

import java.io.IOException;
import java.net.UnknownHostException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import serverinteraction.Connect;
import fxgui.events.EventInterface;

public class ConnectListener implements EventInterface {

	private Button btn_connect = null;
	private TextField fld_userName = null;
	private TextField sv_Address = null;
	private PasswordField passwd_field = null;

	
	@FXML
	public void performAction() {
		try {
			new Connect(sv_Address.getText(),2000);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setBtn_connect(Button btn_connect) {
		this.btn_connect = btn_connect;
	}

	public void setFld_userName(TextField fld_userName) {
		this.fld_userName = fld_userName;
	}

	public void setSv_Address(TextField sv_Address) {
		this.sv_Address = sv_Address;
	}
	
	public void setPasswd_field(PasswordField passwd_field) {
		this.passwd_field = passwd_field;
	}
	
}
