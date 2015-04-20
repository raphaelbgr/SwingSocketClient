package fxgui.scenes;

import java.net.URL;
import java.util.ResourceBundle;

import fxgui.events.WindowData;
import fxgui.events.buttons.ConnectionPerform;
import fxgui.events.buttons.DisconnectionPerform;
import fxgui.events.buttons.SendPerform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;

public class MainScene extends Scene implements Initializable {

	Parent root = null;
	
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
	private PasswordField passwd_field = new PasswordField();
	@FXML
	private TextField sv_Port = new TextField();
	@FXML
	private ProgressBar progress = new ProgressBar();
	@FXML
	private ProgressIndicator indicator = new ProgressIndicator();
	
	public MainScene(Parent root) {
		super(root);
		this.root = root;
	}
	
	WindowData wd = new WindowData();

	public void handleSendButton() {
		SendPerform sl = new SendPerform();
		sl.performAction(null);
	}

	public void handleDisconnectButton() {
		DisconnectionPerform dp = new DisconnectionPerform();
		dp.performAction(wd);
		
	}

	public void handleConnectButton() {
		ConnectionPerform sl = new ConnectionPerform();
		wd.setUserName(fld_userName.getText());
		wd.setPassword(passwd_field.getText());
		wd.setServer(sv_Address.getText());
		wd.setPort(Integer.valueOf(sv_Port.getText()));

		//Those Nodes will be sent to WindowData Object to be manipulated by the ConnectPerform class.
		wd.addNode(btn_connect);
		wd.addNode(btn_disconnect);
		wd.addNode(fld_userName);
		wd.addNode(passwd_field);
		wd.addNode(sv_Address);
		wd.addNode(sv_Port);
		sl.performAction(wd);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//Setting and ID for each node.
		btn_connect.setId("btn_connect");
		btn_disconnect.setId("btn_disconnect");
		fld_userName.setId("fld_username");
		passwd_field.setId("passwd_field");
		sv_Address.setId("sv_address");
		sv_Port.setId("sv_port");
		progress.setId("progress");
		indicator.setId("indicator");

		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				for (double i = 0.31; i <= 100; i = i + 0.01) {
//					pb.setProgress(i);
//					pi.setProgress(i);
					updateProgress(i, 100);
					try {Thread.sleep(1);} catch (InterruptedException e) {}
				}
				return null;
			}
		};
		progress.progressProperty().bind(task.progressProperty());
		indicator.progressProperty().bind(task.progressProperty());

		Thread th1 = new Thread(task);
		th1.setDaemon(true);
		th1.start();

		//FOR DEBUG
		TextField node1 = (TextField) root.lookup("#fld_username");
		node1.setText("raphaelbgr");
		TextField node2 = (TextField)  root.lookup("#passwd_field");
		node2.setText("nopass");
		TextField node3 = (TextField) root.lookup("#sv_address");
		node3.setText("localhost");
		TextField node4 = (TextField) root.lookup("#sv_port");
		node4.setText("2000");
	}

}