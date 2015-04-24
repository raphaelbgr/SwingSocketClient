package gui.fx.controllers;

import gui.fx.WindowDataFacade;
import gui.fx.buttons.ConnectionPerform;
import gui.fx.buttons.DisconnectionPerform;
import gui.fx.buttons.SendPerform;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;


public class FXController implements Initializable {

	@FXML
	private Button btn_connect = null;
	@FXML
	private Button btn_disconnect = null;
	@FXML
	private Button btn_sv_opt = null;
	@FXML
	private TextField fld_username = null;
	@FXML
	private TextField sv_address = null;
	@FXML
	private PasswordField passwd_field = null;
	@FXML
	private TextField sv_port = null;
	@FXML
	private ProgressBar progress = null;
	@FXML
	private ProgressIndicator indicator = null;
	@FXML
	private TextField fld_status = null;
	@FXML
	private Label lbl_status = null;
	@FXML
	private Label lbl_time;
	@FXML
	private Button btn_exit;
	@FXML
	private Button btn_send;

	List<Node> nodes = new ArrayList<Node>();

	public void handleSendButton() {
		SendPerform sl = new SendPerform();
		sl.performAction();
	}

	public void handleDisconnectButton() {
		DisconnectionPerform dp = new DisconnectionPerform();
		dp.performAction();

	}

	public void handleConnectButton() {
		ConnectionPerform cp = new ConnectionPerform();
		cp.performAction();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		btn_disconnect.setDisable(true);
		btn_send.setDisable(true);

		//		Platform.setImplicitExit(false);
		//		Task<Void> task = new Task<Void>() {
		//			@Override
		//			protected Void call() throws Exception {
		//				while (true) {
		//					lbl_time.setText(Calendar.getInstance().getTime().toString());
		//					try {Thread.sleep(700);} catch (InterruptedException e) {}
		//				}
		//			}
		//		};
		//		Thread th1 = new Thread(task);
		//		th1.start();

//		Task task = new Task<Void>() {
//			@Override
//			public Void call() throws Exception {
//				while (true) {
//					Platform.runLater(new Runnable() {
//						@Override
//						public void run() {
//							lbl_time.setText(Calendar.getInstance().getTime().toString());
//							try {Thread.sleep(7700);} catch (InterruptedException e) {}
//						}
//					});
//				}
//			}
//		};
//		Thread th = new Thread(task);
//		th.setDaemon(true);
//		th.start();

		lbl_time.setText(Calendar.getInstance().getTime().toString());
		
		loadFacade();
		indicator.setVisible(false);

	}

	private void loadFacade() {
		WindowDataFacade.getInstance().addNode(btn_connect);
		WindowDataFacade.getInstance().addNode(btn_disconnect);
		WindowDataFacade.getInstance().addNode(fld_username);
		WindowDataFacade.getInstance().addNode(passwd_field);
		WindowDataFacade.getInstance().addNode(sv_address);
		WindowDataFacade.getInstance().addNode(sv_port);
		WindowDataFacade.getInstance().addNode(progress);
		WindowDataFacade.getInstance().addNode(indicator);
		WindowDataFacade.getInstance().addNode(btn_sv_opt);
		WindowDataFacade.getInstance().addNode(fld_status);
		WindowDataFacade.getInstance().addNode(lbl_status);
		WindowDataFacade.getInstance().addNode(btn_send);

		//		WindowDataFacade.getInstance().setTask(WindowDataFacade.getInstance().startProgressBar());
		//		progress.INDETERMINATE_PROGRESS;
		//		WindowDataFacade.getInstance().getTask().setOnFailed(new EventHandler<T>() {
		//			
		//		});
	}

	public void setDebug(boolean go) {
		if (go) {
			fld_username.setText("raphaelbgr");
			passwd_field.setText("nopass");
			sv_address.setText("localhost");
			sv_port.setText("2000");	
		}
	}

}
