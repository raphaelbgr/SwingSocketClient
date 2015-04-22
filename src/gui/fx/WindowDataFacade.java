package gui.fx;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;

public class WindowDataFacade {

	private Button btn_connect 			= null;
	private Button btn_disconnect 		= null;
	private Button btn_serv_opt 		= null;
	private TextField fld_username		= null;
	private TextField sv_address 		= null;
	private PasswordField passwd_field 	= null;
	private TextField sv_port 			= null;
	private ProgressBar progress 		= null;
	private ProgressIndicator indicator = null;
	
	private List<Node> nodes 			= new ArrayList<Node>();
	
	private Integer port 				= null;
	
	private Parent root = null;

	public WindowDataFacade(Parent root) {
		
		this.root = root;
		
		TabPane tpane =  (TabPane) root.getScene().lookup("#tab_pane");
		tpane.lookup("#fld_userame");
		
//		sv_port = (TextField) root.getScene().lookup("#sv_port");
//		sv_address = (TextField) root.getScene().lookup("#sv_address");
//		fld_username = (TextField) tpane.getScene().lookup("#fld_userame");
//		passwd_field = (PasswordField) root.getScene().lookup("#passwd_field");
//		sv_port = (TextField) root.getScene().lookup("#sv_port");
//		sv_address = (TextField) root.getScene().lookup("#sv_address");
	}
	
	public String getUserName() {
		return fld_username.getText();
	}
	
	public String getPassword() {
		return passwd_field.getText();
	}
	
	public String getAddress() {
		return sv_address.getText();
	}
	
	public Integer getPort() {
		return Integer.valueOf(sv_port.getText());
	}
	
	public void setConnectedLockFields() {
		btn_connect.setDisable(true);
		btn_disconnect.setDisable(false);
		fld_username.setDisable(true);
		passwd_field.setDisable(true);
		sv_port.setDisable(true);
	}
	
	public void setDisconnectedLockFields() {
		btn_connect.setDisable(false);
		btn_disconnect.setDisable(true);
		fld_username.setDisable(false);
		passwd_field.setDisable(false);
		sv_port.setDisable(false);
	}
	
	public void addNode(Node node) {
		nodes.add(node);
	}
	
	public Node getNode(String id) {
		for (Node node : nodes) {
			if (node.getId().equalsIgnoreCase(id)) {
				return node;
			} 
		} return null;
	}
	
	public WindowDataFacade loadNodes() {
		btn_connect = (Button) root.getScene().lookup("#btn_connect");
		btn_disconnect = (Button) root.getScene().lookup("#btn_disconnect");
		fld_username = (TextField) getNode("fld_username");
		passwd_field = (PasswordField) getNode("passwd_field");
		sv_port = (TextField) getNode("sv_port");
		sv_address = (TextField) getNode("sv_address");
		progress = (ProgressBar) root.getScene().lookup("#progress");
		indicator = (ProgressIndicator) root.getScene().lookup("#indicator");
		return this;
	}
	
	public void setDebugMode(boolean go) {
		if (go) {
			fld_username.setText("raphaelbgr");
			passwd_field.setText("nopass");
			sv_address.setText("localhost");
			sv_port.setText("2000");		
		}
	}
}
