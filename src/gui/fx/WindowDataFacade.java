package gui.fx;

import java.util.ArrayList;
import java.util.List;

import javafx.concurrent.Task;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
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
	private TextField fld_status;
	private Label lbl_status;
	private Label lbl_time;
	private Button btn_exit;
	private Button btn_send;
	
	private List<Node> nodes 			= new ArrayList<Node>();
	private Parent root = null;
	protected double toCurrentProgress;
	protected double fromCurrentProgress;
	
	private Task<Void> task = null;
	private CheckBox chkbox_autocon;
	

	public static WindowDataFacade wdf;
	public static WindowDataFacade getInstance() {
		if (wdf == null) {
			wdf =  new WindowDataFacade();
		}
		return wdf;
	}
	
	public void setRoot(Parent root) {
		this.root = root;
	}
	
	private WindowDataFacade() {
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
	
	public void setSmallStatusMsg(String s) {
		lbl_status.setText(s);
	}
	
	public void setBigStatusMsg(String s) {
		fld_status.setText(s);
	}
	
	public void setTimeLabel(String s) {
		lbl_time.setText(s);
	}
	
	public void setConnectedLockFields() {
		btn_send.setDisable(false);
		btn_connect.setDisable(true);
		btn_disconnect.setDisable(false);
		fld_username.setDisable(true);
		passwd_field.setDisable(true);
		sv_address.setDisable(true);
		sv_port.setDisable(true);
	}
	
	public void setDisconnectedLockFields() {
		btn_send.setDisable(true);
		btn_connect.setDisable(false);
		btn_disconnect.setDisable(true);
		fld_username.setDisable(false);
		passwd_field.setDisable(false);
		sv_address.setDisable(false);
		sv_port.setDisable(false);
	}
	
	public void addNode(Node node) {
		if (node.getId().equalsIgnoreCase("btn_connect")) {
			btn_connect = (Button) node;
		} else if (node.getId().equalsIgnoreCase("btn_disconnect")) {
			btn_disconnect = (Button) node;
		} else if (node.getId().equalsIgnoreCase("btn_serv_opt")) {
			btn_serv_opt = (Button) node;
		} else if (node.getId().equalsIgnoreCase("fld_username")) {
			fld_username = (TextField) node;
		} else if (node.getId().equalsIgnoreCase("sv_address")) {
			sv_address = (TextField) node;
		} else if (node.getId().equalsIgnoreCase("passwd_field")) {
			passwd_field = (PasswordField) node;
		} else if (node.getId().equalsIgnoreCase("sv_port")) {
			sv_port = (TextField) node;
		} else if (node.getId().equalsIgnoreCase("progress")) {
			progress = (ProgressBar) node;
		} else if (node.getId().equalsIgnoreCase("indicator")) {
			indicator = (ProgressIndicator) node;
		} else if (node.getId().equalsIgnoreCase("fld_status")) {
			fld_status = (TextField) node;
		} else if (node.getId().equalsIgnoreCase("lbl_status")) {
			lbl_status = (Label) node;
		} else if (node.getId().equalsIgnoreCase("lbl_time")) {
			lbl_time = (Label) node;
		} else if (node.getId().equalsIgnoreCase("btn_exit")) {
			btn_exit = (Button) node;
		} else if (node.getId().equalsIgnoreCase("btn_send")) {
			btn_send = (Button) node;
		} else if (node.getId().equalsIgnoreCase("chkbox_autocon")) {
			chkbox_autocon = (CheckBox) node;
		}
		nodes.add(node);
	}
	
	public Node getNode(String id) {
		for (Node node : nodes) {
			if (node != null && node.getId().equalsIgnoreCase(id)) {
				return node;
			} 
		} return null;
	}
	
	public double getToCurrentProgress() {
		return toCurrentProgress;
	}

	public double getFromCurrentProgress() {
		return fromCurrentProgress;
	}

	public void setToCurrentProgress(double toCurrentProgress) {
		this.toCurrentProgress = toCurrentProgress;
	}
	
	public Task<Void> getTask() {
		return task;
	}

	public void setTask(Task<Void> task) {
		this.task = task;
	}

	public TextField getFld_status() {
		return fld_status;
	}

	public Label getLbl_status() {
		return lbl_status;
	}

	public ProgressBar getProgress() {
		return progress;
	}

	public boolean isAutoReconnChecked() {
		return chkbox_autocon.isSelected();
	}
	
	public CheckBox getChkbox_autocon() {
		return chkbox_autocon;
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
