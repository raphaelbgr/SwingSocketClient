package gui.fx.controllers;

import gui.fx.WindowDataFacade;
import gui.fx.buttons.ConnectionPerform;
import gui.fx.buttons.DisconnectionPerform;
import gui.fx.buttons.SendPerform;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class FXController implements Initializable {

	@FXML
	private Button btn_connect 				= null;
	@FXML
	private Button btn_disconnect 			= null;
	@FXML
	private Button btn_sv_opt 				= null;
	@FXML
	private TextField fld_username 			= null;
	@FXML
	private TextField sv_address 			= null;
	@FXML
	private PasswordField passwd_field 		= null;
	@FXML
	private TextField sv_port 				= null;
	@FXML
	private ProgressBar progress 			= null;
	@FXML
	private ProgressIndicator indicator 	= null;
	@FXML
	private TextField fld_status 			= null;
	@FXML
	private Label lbl_status			 	= null;
	@FXML
	private Label lbl_time 					= null;
	@FXML
	private Button btn_exit 				= null;
	@FXML
	private Button btn_send 				= null;
	@FXML
	private CheckBox chkbox_autocon 		= null;
	@FXML
	private TextArea txt_chatlog	 		= null;
	@FXML
	private ListView<String> list_view		= null;
	@FXML
	private TextField message_box		 	= null;	
	@FXML
	private TextField fld_login_reg		 	= null;
	@FXML
	private TextField fld_name_reg		 	= null;
	@FXML
	private TextField fld_password_reg		= null;
	@FXML
	private TextField fld_password2_reg		= null;
	@FXML
	private ComboBox<String> combo_sex_reg	= null;
	@FXML
	private ComboBox<String> combo_college_reg	 	= null;
	@FXML
	private ComboBox<String> combo_course_reg		= null;
	@FXML
	private ComboBox<String> combo_courseStTr_reg	= null;
	@FXML
	private TextField fld_infnetmail_reg	= null;
	@FXML
	private TextField fld_email_reg			= null;
	@FXML
	private TextField fld_whatsapp_reg		= null;
	@FXML
	private TextField fld_facebook_reg		= null;
	@FXML
	private Tab tab_reg						= null;
	
	@FXML
	private TextField fld_othercol_reg		= null;
	@FXML
	private Label lbl_addcol_reg			= null;
	@FXML
	private TextField fld_addcourse_reg		= null;
	@FXML
	private Label lbl_addcourse_reg			= null;
	@FXML
	private Label lbl_infnetid_reg			= null;
	@FXML
	private Label lbl_addcountry_reg		= null;
	@FXML
	private TextField fld_new_country_reg	= null;
	@FXML
	private Label lbl_addstate_reg			= null;
	@FXML
	private TextField fld_new_state_reg		= null;
	@FXML
	private Label lbl_addcity_reg			= null;
	@FXML
	private TextField fld_new_city_reg		= null;
	@FXML
	private Label lbl_coursestart_reg		= null;
	

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
		btn_send.setDefaultButton(true);
		lbl_time.setText(Calendar.getInstance().getTime().toString());
		loadFacade();
		indicator.setVisible(false);
		fld_status.setText(getTimestamp() + "LOCAL> Offline");
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
		WindowDataFacade.getInstance().addNode(chkbox_autocon);
		WindowDataFacade.getInstance().addNode(lbl_time);
		WindowDataFacade.getInstance().addNode(txt_chatlog);
		WindowDataFacade.getInstance().addNode(list_view);
		WindowDataFacade.getInstance().addNode(message_box);
		WindowDataFacade.getInstance().addNode(fld_login_reg);
		WindowDataFacade.getInstance().addNode(fld_name_reg);
		WindowDataFacade.getInstance().addNode(fld_password_reg);
		WindowDataFacade.getInstance().addNode(fld_password2_reg);
		WindowDataFacade.getInstance().addNode(combo_sex_reg);
		WindowDataFacade.getInstance().addNode(combo_college_reg);
		WindowDataFacade.getInstance().addNode(combo_course_reg);
		WindowDataFacade.getInstance().addNode(combo_courseStTr_reg);
		WindowDataFacade.getInstance().addNode(fld_infnetmail_reg);
		WindowDataFacade.getInstance().addNode(fld_othercol_reg);
		WindowDataFacade.getInstance().addNode(fld_email_reg);
		WindowDataFacade.getInstance().addNode(fld_whatsapp_reg);
		WindowDataFacade.getInstance().addNode(fld_facebook_reg);
		
		WindowDataFacade.getInstance().startClock();
		WindowDataFacade.getInstance().startOnlineUserList();
		
		WindowDataFacade.getInstance().addNode(fld_othercol_reg);
		WindowDataFacade.getInstance().addNode(lbl_addcol_reg);
		WindowDataFacade.getInstance().addNode(fld_addcourse_reg);
		WindowDataFacade.getInstance().addNode(lbl_addcourse_reg);
		WindowDataFacade.getInstance().addNode(lbl_infnetid_reg);
		WindowDataFacade.getInstance().addNode(lbl_addcountry_reg);
		WindowDataFacade.getInstance().addNode(fld_new_country_reg);
		WindowDataFacade.getInstance().addNode(lbl_addstate_reg);
		WindowDataFacade.getInstance().addNode(fld_new_state_reg);
		WindowDataFacade.getInstance().addNode(lbl_addcity_reg);
		WindowDataFacade.getInstance().addNode(fld_new_city_reg);
		WindowDataFacade.getInstance().addNode(lbl_coursestart_reg);
	}

	private String getTimestamp() {
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		String dateFormatted = formatter.format(new Date());
		return "["+dateFormatted+"]" + " ";
	}
	
	public void setDebug(boolean go) {
		if (go) {
			fld_username.setText("raphaelbgr");
			passwd_field.setText("nopass");
			sv_address.setText("surfael.sytes.net");
			sv_port.setText("2000");	
		}
	}
	
	public void setPublicDebug(boolean go) {
		if (go) {
			passwd_field.setText("nopass");
			passwd_field.setDisable(true);
			sv_address.setText("surfael.sytes.net");
			sv_port.setText("2000");	
		}
	}
	
	public void setLockDebugReg() {
		WindowDataFacade.getInstance().lockRegForDebugFields();
	}

}
