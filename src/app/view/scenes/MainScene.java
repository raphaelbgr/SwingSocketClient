package app.view.scenes;

import java.net.URL;
import java.util.ResourceBundle;

import app.view.WindowDataFacade;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class MainScene extends Scene implements Initializable {

	Parent root = null;
	WindowDataFacade<?> wdf = null;
	
//	@FXML
//	private Button btn_connect = new Button();
//	@FXML
//	private Button btn_disconnect = new Button();
//	@FXML
//	private Button btn_serv_opt = new Button();
//	@FXML
//	private TextField fld_username = new TextField();
//	@FXML
//	private TextField sv_address = new TextField();
//	@FXML
//	private PasswordField passwd_field = new PasswordField();
//	@FXML
//	private TextField sv_port = new TextField();
//	@FXML
//	private ProgressBar progress = new ProgressBar();
//	@FXML
//	private ProgressIndicator indicator = new ProgressIndicator();
	
//	@FXML
//	private Button btn_connect = null;
//	@FXML
//	private Button btn_disconnect = null;
//	@FXML
//	private Button btn_serv_opt = null;
//	@FXML
//	private TextField fld_username = null;
//	@FXML
//	private TextField sv_address = null;
//	@FXML
//	private PasswordField passwd_field = null;
//	@FXML
//	private TextField sv_port = null;
//	@FXML
//	private ProgressBar progress = null;
//	@FXML
//	private ProgressIndicator indicator = null;
	
	public MainScene(Parent root) {
		super(root);
		this.root = root;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {		
//		WindowDataFacade.getInstance().setRoot(root);
//		System.out.println("MainScene.initialize()");
		
		//Setting and ID for each node.
//		btn_connect.setId("btn_connect");
//		btn_disconnect.setId("btn_disconnect");
//		fld_username.setId("fld_username");
//		passwd_field.setId("passwd_field");
//		sv_address.setId("sv_address");
//		sv_port.setId("sv_port");
//		progress.setId("progress");
//		indicator.setId("indicator");

		

		

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
	
//	public WindowDataFacade getFacade() {
//		wdf.addNode(btn_connect);
//		wdf.addNode(btn_disconnect);
//		wdf.addNode(fld_username);
//		wdf.addNode(passwd_field);
//		wdf.addNode(sv_address);
//		wdf.addNode(sv_port);
//		wdf.addNode(progress);
//		wdf.addNode(indicator);
//		return wdf;
//	}

}