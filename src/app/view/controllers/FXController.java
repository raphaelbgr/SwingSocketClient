package app.view.controllers;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import app.ClientMain;
import app.view.WindowDataFacade;
import app.view.buttons.CollegeUpdatePerform;
import app.view.buttons.ConnectionPerform;
import app.view.buttons.DisconnectionPerform;
import app.view.buttons.ExitPerform;
import app.view.buttons.LoginPerform;
import app.view.buttons.PerformHistoryComboUpdate;
import app.view.buttons.RefreshPerform;
import app.view.buttons.RegisterPerform;
import app.view.buttons.SendPerform;
import app.view.buttons.UpdateCourseCombo;
import app.view.events.EventInterface;
import app.view.scenes.MainScene;


public class FXController implements Initializable {

	@FXML
	private Button btn_connect 				= null;
	@FXML
	private Button btn_disconnect 			= null;
	@FXML
	private Button btn_sv_opt 				= null;
	@FXML
	private ComboBox<String> combo_login	= null;
	//	private TextField fld_username 			= null;
	@FXML
	private TextField sv_address 			= null;
	@FXML
	private PasswordField passwd_field 		= null;
	@FXML
	private TextField sv_port 				= null;
	@FXML
	private ProgressBar progress 			= null;
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
	@FXML
	private ComboBox<String> combo_country_reg	= null;
	@FXML
	private ComboBox<String> combo_state_reg	= null;
	@FXML
	private ComboBox<String> combo_city_reg		= null;
	@FXML
	private ComboBox<String> combo_hist_rows	= null;
	@FXML
	private TableView table_chathistory			= null;
	@FXML
	private Button btn_refresh					= null;
	@FXML
	private CheckBox chkbox_mute				= null;
	@FXML
	private MenuItem menu_creator				= null;
	
	private FXMLLoader aboutMeLoader 			= null;
	@FXML
	private Label lbl_version					= null;


	List<Node> nodes = new ArrayList<Node>();

	public void handleMenuCreator() {
		aboutMeLoader = new FXMLLoader(getClass().getResource("/aboutme.fxml"));
		Parent root;
		try {
			root = (Parent) aboutMeLoader.load();
			Scene scene2 = new MainScene(root);
			scene2.setRoot(root);
			final Stage aboutMeStage = new Stage();
			aboutMeStage.setScene(scene2);
			aboutMeStage.setTitle("About Me");
			aboutMeStage.setResizable(false);
			aboutMeStage.toFront();
			aboutMeStage.setOnCloseRequest(new EventHandler() {
				@Override
				public void handle(Event arg0) {
					aboutMeStage.close();
				}	
			});
			aboutMeStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void handleRefreshtButton() {
		EventInterface ei = new RefreshPerform();
		if (ei.performAction()) {
			WindowDataFacade.getInstance().populateHistoryTable();
		}
	}

	public void handleExitButton() {
		EventInterface ei = new ExitPerform();
		ei.performAction();
	}

	public void handleHistoryCombo() {
		EventInterface ei = new PerformHistoryComboUpdate();
		if (ei.performAction()) {
			WindowDataFacade.getInstance().populateHistoryTable();
		}
	}

	public void handleDetectSelection() {
		WindowDataFacade.getInstance().dynamicFieldsEnable();
	}

	public void handleCourseRegComboClick() {
		EventInterface ei = new UpdateCourseCombo();
		ei.performAction();
	}

	public void handleCollegeRegComboClick() {
		EventInterface ei = new CollegeUpdatePerform();
		ei.performAction();
	}

	public void handleLoginComboClick() {
		EventInterface ei = new LoginPerform();
		ei.performAction();
	}

	public void handleSendButton() {
		EventInterface ei = new SendPerform();
		ei.performAction();
	}

	public void handleDisconnectButton() {
		EventInterface ei = new DisconnectionPerform();
		ei.performAction();

	}

	public void handleConnectButton() {
		EventInterface ei = new ConnectionPerform();
		ei.performAction();
	}

	public void handleRegisterButton() {
		EventInterface ei = new RegisterPerform();
		ei.performAction();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btn_disconnect.setDisable(true);
		btn_send.setDisable(true);
		btn_send.setDefaultButton(true);
		lbl_time.setText(Calendar.getInstance().getTime().toString());
		loadFacade();
		fld_status.setText(getTimestamp() + "LOCAL> Offline");
	}

	private void loadFacade() {
		WindowDataFacade.getInstance().addNode(btn_connect);
		WindowDataFacade.getInstance().addNode(btn_disconnect);
//		WindowDataFacade.getInstance().addNode(fld_username);
		WindowDataFacade.getInstance().addNode(passwd_field);
		WindowDataFacade.getInstance().addNode(sv_address);
		WindowDataFacade.getInstance().addNode(sv_port);
		WindowDataFacade.getInstance().addNode(progress);
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

		WindowDataFacade.getInstance().addNode(fld_othercol_reg);
		//		WindowDataFacade.getInstance().addNode(lbl_addcol_reg);
		//		WindowDataFacade.getInstance().addNode(fld_addcourse_reg);
		//		WindowDataFacade.getInstance().addNode(lbl_addcourse_reg);
		WindowDataFacade.getInstance().addNode(lbl_infnetid_reg);
		//		WindowDataFacade.getInstance().addNode(lbl_addcountry_reg);
		//		WindowDataFacade.getInstance().addNode(fld_new_country_reg);
		//		WindowDataFacade.getInstance().addNode(lbl_addstate_reg);
		//		WindowDataFacade.getInstance().addNode(fld_new_state_reg);
		//		WindowDataFacade.getInstance().addNode(lbl_addcity_reg);
		//		WindowDataFacade.getInstance().addNode(fld_new_city_reg);
		WindowDataFacade.getInstance().addNode(lbl_coursestart_reg);
		//		WindowDataFacade.getInstance().addTab(tab_reg);


		WindowDataFacade.getInstance().addNode(combo_country_reg);
		WindowDataFacade.getInstance().addNode(combo_state_reg);
		WindowDataFacade.getInstance().addNode(combo_city_reg);
		WindowDataFacade.getInstance().addNode(combo_login);

		WindowDataFacade.getInstance().addNode(table_chathistory);
		WindowDataFacade.getInstance().addNode(combo_hist_rows);
		WindowDataFacade.getInstance().addNode(btn_refresh);
		WindowDataFacade.getInstance().addNode(chkbox_mute);
		WindowDataFacade.getInstance().addNode(lbl_version);
		
//		WindowDataFacade.getInstance().addNode(menu_creator);
		
//		WindowDataFacade.getInstance().addNode(about_linkedin_link);
//		WindowDataFacade.getInstance().addNode(about_my_cv_link);
//		WindowDataFacade.getInstance().addNode(about_whatsapp);
//		WindowDataFacade.getInstance().addNode(about_email);

		WindowDataFacade.getInstance().startClock();
		WindowDataFacade.getInstance().startOnlineUserList();
		WindowDataFacade.getInstance().initialize();
	}

	private String getTimestamp() {
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		String dateFormatted = formatter.format(new Date());
		return "["+dateFormatted+"]" + " ";
	}

	public void setLockedServer(boolean go) {
			sv_address.setDisable(go);
			sv_port.setDisable(go);	
	}

	public void setPublicDefaultValues(boolean go) {
		if (go) {
			sv_address.setText(ClientMain.defaultServer);
			sv_port.setText("2000");	
		}
	}

}
