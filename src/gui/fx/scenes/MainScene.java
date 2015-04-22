package gui.fx.scenes;

import gui.fx.WindowDataFacade;

import java.net.URL;
import java.util.ResourceBundle;

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
	WindowDataFacade wdf = null;
	
	@FXML
	private Button btn_connect = new Button();
	@FXML
	private Button btn_disconnect = new Button();
	@FXML
	private Button btn_serv_opt = new Button();
	@FXML
	private TextField fld_username = new TextField();
	@FXML
	private TextField sv_address = new TextField();
	@FXML
	private PasswordField passwd_field = new PasswordField();
	@FXML
	private TextField sv_port = new TextField();
	@FXML
	private ProgressBar progress = new ProgressBar();
	@FXML
	private ProgressIndicator indicator = new ProgressIndicator();
	
	public MainScene(Parent root) {
		super(root);
		this.root = root;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//Setting and ID for each node.
		btn_connect.setId("btn_connect");
		btn_disconnect.setId("btn_disconnect");
		fld_username.setId("fld_username");
		passwd_field.setId("passwd_field");
		sv_address.setId("sv_address");
		sv_port.setId("sv_port");
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
//		TextField node1 = (TextField) root.getScene().lookup("#fld_username");
//		node1.setText("raphaelbgr");
//		TextField node2 = (TextField)  root.getScene().lookup("#passwd_field");
//		node2.setText("nopass");
//		TextField node3 = (TextField) root.getScene().lookup("#sv_address");
//		node3.setText("localhost");
//		TextField node4 = (TextField) root.getScene().lookup("#sv_port");
//		node4.setText("2000");
		
//		fld_userName.setText("user");
//		passwd_field.setText("nopass");
//		sv_Address.setText("localhost");
//		sv_Port.setText("2000");
	}
	
	public WindowDataFacade getFacade() {
		if (wdf == null) {
			wdf = new WindowDataFacade(root);
		}
		wdf.addNode(btn_connect);
		wdf.addNode(btn_disconnect);
		wdf.addNode(fld_username);
		wdf.addNode(passwd_field);
		wdf.addNode(sv_address);
		wdf.addNode(sv_port);
		wdf.addNode(progress);
		wdf.addNode(indicator);
		return wdf;
	}

}