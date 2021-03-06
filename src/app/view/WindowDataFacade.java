package app.view;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import app.ClientMain;
import app.view.models.MessageDataTableModel;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import net.sytes.surfael.api.control.classes.MD5;
import net.sytes.surfael.api.model.clients.Client;
import net.sytes.surfael.api.model.clients.NewClient;
import net.sytes.surfael.api.model.messages.History;
import net.sytes.surfael.api.model.messages.Message;
import net.sytes.surfael.api.model.messages.NormalMessage;

@SuppressWarnings("unused")
public class WindowDataFacade {

	private Button btn_connect 						= null;
	private Button btn_disconnect 					= null;
	private Button btn_sv_opt 						= null;
	private TextField fld_username					= null;
	private TextField fld_sv_address 					= null;
	private PasswordField fld_password 				= null;
	private TextField fld_sv_port 						= null;
	private ProgressBar progress 					= null;
	private ProgressIndicator indicator				= null;
	private TextField fld_status  					= null;
	private Label lbl_status  						= null;
	private Label lbl_time 							= null;
	private Button btn_exit 						= null;
	private Button btn_send 						= null;
	private CheckBox chkbox_autocon 				= null;
	private ListView<String> list_view 				= null;
	private TextArea txt_chatlog 					= null;

	private List<Node> nodes 						= new ArrayList<Node>();
	private Parent root 							= null;
	private Task<Void> task 						= null;
	private TextField message_box					= null;

	ObservableList<String> items 					= null;
	private TextField fld_login_reg					= null;
	private TextField fld_name_reg					= null;
	private TextField fld_password_reg				= null;
	private TextField fld_password2_reg				= null;
	private ComboBox<String> combo_sex_reg			= null;
	private ComboBox<String> combo_college_reg		= null;
	private ComboBox<String> combo_course_reg		= null;
	private ComboBox<String> combo_courseStTr_reg	= null;
	private TextField fld_infnetmail_reg			= null;
	private TextField fld_email_reg					= null;
	private TextField fld_whatsapp_reg				= null;
	private TextField fld_facebook_reg				= null;

	private TextField fld_othercol_reg				= null;
	private Label lbl_addcol_reg					= null;
	private TextField fld_addcourse_reg				= null;
	private Label lbl_addcourse_reg					= null;
	private Label lbl_infnetid_reg					= null;
	private Label lbl_addcountry_reg				= null;
	private TextField fld_new_country_reg			= null;
	private Label lbl_addstate_reg					= null;
	private TextField fld_new_state_reg				= null;
	private Label lbl_addcity_reg					= null;
	private TextField fld_new_city_reg				= null;
	private Label lbl_coursestart_reg				= null;

	private Tab tab_reg								= null;
	private ComboBox<String> combo_state_reg		= null;
	private ComboBox<String> combo_country_reg		= null;
	private ComboBox<String> combo_city_reg			= null;
	private ComboBox<String> combo_login			= null;
	private TableView<MessageDataTableModel> table_chathistory		= null;
	private ComboBox<String> combo_hist_rows;
	private Button btn_refresh;

	private Stage mainStage;
	private CheckBox chkbox_mute;
	private Hyperlink about_linkedin_link;
	private Hyperlink about_my_cv_link;
	private Hyperlink about_whatsapp;
	private Hyperlink about_email;
	private Label lbl_version;

	public static WindowDataFacade wdf;
	public static WindowDataFacade getInstance() {
		if (wdf == null) {
			wdf =  new WindowDataFacade();
		}
		return wdf;
	}

	public String getCityReg() {
		if (combo_city_reg.getSelectionModel().getSelectedItem() != null) {
			return combo_city_reg.getSelectionModel().getSelectedItem().toString();
		}
		else return null;
	}

	public String getStateReg() {
		if (combo_state_reg.getSelectionModel().getSelectedItem() != null) {
			return combo_state_reg.getSelectionModel().getSelectedItem().toString();
		}
		else return null;
	}

	public String getCountryReg() {
		if (combo_country_reg.getSelectionModel().getSelectedItem() != null) {
			return combo_country_reg.getSelectionModel().getSelectedItem().toString();
		}
		else return null;
	}

	public String getCourse() {
		if (combo_course_reg.isDisable()) {
			return "Not infnet";
		} else return combo_course_reg.getSelectionModel().getSelectedItem().toString();
	}

	public void setRoot(Parent root) {
		this.root = root;
	}

	private WindowDataFacade() {
	}

	public String getLogin() {
		return fld_username.getText();
//		if (combo_login.getSelectionModel().getSelectedItem() != null) {
//			return combo_login.getSelectionModel().getSelectedItem().toString();
//		}
//		else return null;
	}

	public String getPassword() {
		if (fld_password.getText() != null || !fld_password.getText().equalsIgnoreCase("")) {
			return MD5.getMD5(fld_password.getText());
		} else return "";
	}

	public String getAddress() {
		return fld_sv_address.getText();
	}

	public Integer getPort() {
		return Integer.valueOf(fld_sv_port.getText());
	}

	public void setSmallStatusMsg(String s) {
		lbl_status.setText(s);
	}

	public synchronized void setBigStatusMsg(final String s) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				fld_status.setText(s);
			}
		});

	}

	public synchronized String getBigStatusMsg() {
		return this.fld_status.getText();
	}

	public void setTimeLabel(String s) {
		lbl_time.setText(s);
	}

	public void setConnectingLockFields() {	
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				btn_send.setDisable(true);
				btn_connect.setDisable(true);
				btn_disconnect.setDisable(true);
				//				fld_username.setDisable(true);
				combo_login.setDisable(true);
				fld_password.setDisable(true);
				fld_sv_address.setDisable(true);
				fld_sv_port.setDisable(true);
			}
		});
	}

	public void setConnectedLockFields() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				btn_send.setDisable(false);
				btn_connect.setDisable(true);
				btn_disconnect.setDisable(false);
				fld_username.setDisable(true);
//				combo_login.setDisable(true);
				fld_password.setDisable(true);
				fld_sv_address.setDisable(true);
				fld_sv_port.setDisable(true);
//				btn_refresh.setDisable(false);
				combo_hist_rows.setDisable(false);
			}
		});
	}

	public void setDisconnectedLockFields() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				btn_send.setDisable(true);
				btn_connect.setDisable(false);
				btn_disconnect.setDisable(true);
				fld_username.setDisable(false);
//				combo_login.setDisable(false);
				fld_password.setDisable(false);
				fld_sv_address.setDisable(false);
				fld_sv_port.setDisable(false);
				btn_refresh.setDisable(true);
				combo_hist_rows.setDisable(true);
			}
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addNode(Node node) {
		if (node.getId().equalsIgnoreCase("btn_connect")) {
			btn_connect = (Button) node;
		} else if (node.getId().equalsIgnoreCase("btn_disconnect")) {
			btn_disconnect = (Button) node;
		} else if (node.getId().equalsIgnoreCase("btn_sv_opt")) {
			btn_sv_opt = (Button) node;
		} else if (node.getId().equalsIgnoreCase("fld_username")) {
			fld_username = (TextField) node;
		} else if (node.getId().equalsIgnoreCase("sv_address")) {
			fld_sv_address = (TextField) node;
		} else if (node.getId().equalsIgnoreCase("passwd_field")) {
			fld_password = (PasswordField) node;
		} else if (node.getId().equalsIgnoreCase("sv_port")) {
			fld_sv_port = (TextField) node;
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
		} else if (node.getId().equalsIgnoreCase("list_view")) {
			list_view = (ListView) node;
		} else if (node.getId().equalsIgnoreCase("txt_chatlog")) {
			txt_chatlog = (TextArea) node;
		} else if (node.getId().equalsIgnoreCase("message_box")) {
			message_box = (TextField) node;
		} else if (node.getId().equalsIgnoreCase("fld_login_reg")) {
			fld_login_reg = (TextField) node;
		} else if (node.getId().equalsIgnoreCase("fld_name_reg")) {
			fld_name_reg = (TextField) node;
		} else if (node.getId().equalsIgnoreCase("fld_password_reg")) {
			fld_password_reg = (TextField) node;
		} else if (node.getId().equalsIgnoreCase("fld_password2_reg")) {
			fld_password2_reg = (PasswordField) node;
		} else if (node.getId().equalsIgnoreCase("combo_sex_reg")) {
			combo_sex_reg = (ComboBox<String>) node;
		} else if (node.getId().equalsIgnoreCase("combo_college_reg")) {
			combo_college_reg = (ComboBox<String>) node;
		} else if (node.getId().equalsIgnoreCase("combo_course_reg")) {
			combo_course_reg = (ComboBox<String>) node;
		} else if (node.getId().equalsIgnoreCase("combo_courseStTr_reg")) {
			combo_courseStTr_reg = (ComboBox<String>) node;
		} else if (node.getId().equalsIgnoreCase("fld_infnetmail_reg")) {
			fld_infnetmail_reg = (TextField) node;
		} else if (node.getId().equalsIgnoreCase("fld_email_reg")) {
			fld_email_reg = (TextField) node;
		} else if (node.getId().equalsIgnoreCase("fld_whatsapp_reg")) {
			fld_whatsapp_reg = (TextField) node;
		} else if (node.getId().equalsIgnoreCase("fld_facebook_reg")) {
			fld_facebook_reg = (TextField) node;
		} else if (node.getId().equalsIgnoreCase("fld_othercol_reg")) {
			fld_othercol_reg = (TextField) node;
		} else if (node.getId().equalsIgnoreCase("lbl_addcol_reg")) {
			lbl_addcol_reg = (Label) node;
		} else if (node.getId().equalsIgnoreCase("fld_addcourse_reg")) {
			fld_addcourse_reg = (TextField) node;
		} else if (node.getId().equalsIgnoreCase("lbl_addcourse_reg")) {
			lbl_addcourse_reg = (Label) node;
		} else if (node.getId().equalsIgnoreCase("lbl_infnetid_reg")) {
			lbl_infnetid_reg = (Label) node;
		} else if (node.getId().equalsIgnoreCase("lbl_addcountry_reg")) {
			lbl_addcountry_reg = (Label) node;
		} else if (node.getId().equalsIgnoreCase("fld_new_country_reg")) {
			fld_new_country_reg = (TextField) node;
		} else if (node.getId().equalsIgnoreCase("lbl_addstate_reg")) {
			lbl_addstate_reg = (Label) node;
		} else if (node.getId().equalsIgnoreCase("fld_new_state_reg")) {
			fld_new_state_reg = (TextField) node;
		} else if (node.getId().equalsIgnoreCase("lbl_addcity_reg")) {
			lbl_addcity_reg = (Label) node;
		} else if (node.getId().equalsIgnoreCase("fld_new_city_reg")) {
			fld_new_city_reg = (TextField) node;
		} else if (node.getId().equalsIgnoreCase("lbl_coursestart_reg")) {
			lbl_coursestart_reg = (Label) node;
		} else if (node.getId().equalsIgnoreCase("combo_country_reg")) {
			combo_country_reg = (ComboBox<String>) node;
		} else if (node.getId().equalsIgnoreCase("combo_state_reg")) {
			combo_state_reg = (ComboBox<String>) node;
		} else if (node.getId().equalsIgnoreCase("combo_city_reg")) {
			combo_city_reg = (ComboBox<String>) node;
//		} else if ((node.getId().equalsIgnoreCase("combo_login"))) {
//			combo_login = (ComboBox<String>) node;
		} else if (node.getId().equalsIgnoreCase("table_chathistory")) {
			table_chathistory = (TableView) node;
		} else if (node.getId().equalsIgnoreCase("combo_hist_rows")) {
			combo_hist_rows = (ComboBox<String>) node;
		} else if (node.getId().equalsIgnoreCase("btn_refresh")) {
			btn_refresh = (Button) node;
		} else if (node.getId().equalsIgnoreCase("chkbox_mute")) {
			chkbox_mute = (CheckBox) node;
		} else if (node.getId().equalsIgnoreCase("lbl_version")) {
			lbl_version = (Label) node;
		}
		
		
		
		nodes.add(node);
	}
	public int queryLimitSelected() {
		int rows = combo_hist_rows.getSelectionModel().getSelectedIndex();
		switch (rows) {
		case 0:
			rows = 50;
			break;
		case 1:
			rows = 500;
			break;
		case 2:
			rows = 5000;
			break;
		case 3: 
			rows = 5000;
			break;
		case 4:
			rows = 0;
			break;
		}
		return rows;
	}

	public void populateHistoryTable(History serverData) {
		
		final ObservableList<MessageDataTableModel> dataTable = FXCollections.observableArrayList();
		
		// CHECK SIZES TO SAVE PROSSESSING TIME -- FASTEST WAY
		if (serverData.getColumn1().size() == serverData.getColumn2().size()
				&& serverData.getColumn3().size() == serverData.getColumn4().size()
				&& serverData.getColumn1().size() == serverData.getColumn3().size()
				&& serverData.getColumn1().size() == serverData.getColumn4().size()
				&& serverData.getColumn2().size() == serverData.getColumn3().size()
				&& serverData.getColumn2().size() == serverData.getColumn4().size()) {
			
			for (int index = 0; index < serverData.getColumn1().size(); index++) {
				dataTable.add(new MessageDataTableModel(
						serverData.getColumn1().get(index),
						serverData.getColumn2().get(index),
						serverData.getColumn3().get(index),
						serverData.getColumn4().get(index)
						));
			}
		} else {
			// COSTS 5 TIMES MORE PROCESSING TIME -- SLOWEST WAY	
			MessageDataTableModel mdtm = new MessageDataTableModel();
			for (String text : serverData.getColumn1()) {
				mdtm.setId(text);
				dataTable.add(mdtm);
			}
			for (String text : serverData.getColumn2()) {
				int index = 0;
				mdtm.setTimestamp(text);
				if (index <= serverData.getColumn2().size()) {
					dataTable.get(index).setTimestamp(text);
				} else {
					dataTable.add(mdtm);
				}
				index++;
			}
			for (String text : serverData.getColumn3()) {
				int index = 0;
				mdtm.setScreenname(text);
				if (index <= serverData.getColumn3().size()) {
					dataTable.get(index).setScreenname(text);
				} else {
					dataTable.add(mdtm);
				}
				index++;
			}
			for (String text : serverData.getColumn4()) {
				int index = 0;
				mdtm.setMessage(text);
				if (index <= serverData.getColumn4().size()) {
					dataTable.get(index).setMessage(text);
				} else {
					dataTable.add(mdtm);
				}
				index++;
			}
		}
		
		table_chathistory.setItems(dataTable);
		btn_refresh.setDisable(false);

	}

	public void addTab(Tab tab) {
		if (tab.getId().equalsIgnoreCase("tab_reg")) {
			tab_reg = tab;
		}
	}

	public Node getNode(String id) {
		for (Node node : nodes) {
			if (node != null && node.getId().equalsIgnoreCase(id)) {
				return node;
			} 
		} return null;
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

	public Object getMessage() {
		NormalMessage m = new NormalMessage();
		m.setText(this.message_box.getText());
		m.setOwnerLogin(this.getLogin());
		m.setTimestamp();
		m.setDateString();
		return m;
	}

	public synchronized void createConnectedWorker() {
		final Task<Void> task = new Task<Void>() {
			@Override 
			public Void call() {
				final int max = 100;
				for (int i=1; i<=max; i++) {
					if (isCancelled()) {
						break;
					}
					updateProgress(i, max);
					try {
						Thread.sleep(3);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				progress.progressProperty().unbind();
				return null;
			}
		};
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				progress.progressProperty().unbind();
				progress.progressProperty().bind(task.progressProperty());		
				new Thread(task).start();
			}
		});

	}

	public synchronized void createConnectingWorker() {
		Task<Void> task = new Task<Void>() {
			@Override 
			public Void call() {
				updateProgress(-1, 100);
				progress.progressProperty().unbind();
				return null;
			};		
		};
		progress.progressProperty().unbind();
		progress.progressProperty().bind(task.progressProperty());		
		new Thread(task).start();
	}

	public synchronized void createCanceledWorker() {
		Task<Void> task = new Task<Void>() {
			@Override 
			public Void call() {
				for (int i=100; i>0; i--) {
					if (isCancelled()) {
						break;
					}
					updateProgress(i, 100);
					try {
						Thread.sleep(3);
					} catch (InterruptedException e) {
					}
				}
				progress.progressProperty().unbind();
				return null;
			}
		};
		progress.progressProperty().unbind();
		progress.progressProperty().bind(task.progressProperty());		
		new Thread(task).start();
	}

	public synchronized void createSendWorker() {
		Task<Void> task = new Task<Void>() {
			@Override 
			public Void call() {
				final int max = 100;
				for (int i=1; i<=max; i++) {
					if (isCancelled()) {
						break;
					}
					updateProgress(i, max);
					try {
						Thread.sleep(3);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				progress.progressProperty().unbind();
				return null;
			}
		};
		progress.progressProperty().unbind();
		progress.progressProperty().bind(task.progressProperty());		
		new Thread(task).start();
	}

	public void clearMessageBox() {
		this.message_box.setText("");
	}

	public void addChatMessage(final Message m) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				txt_chatlog.appendText(getTimestamp() + m.getOwnerName() + " -> " + m.getText() + "\n");
			}
		});
	}
	
	public void addDisconnectionChatMessage() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				txt_chatlog.appendText(getTimestamp() + " You" + " -> " + "Disconnected"+ "\n");
			}
		});
	}

	public void startClock() {
		Timeline timeline = new Timeline(
				new KeyFrame(Duration.seconds(0),
						new EventHandler<ActionEvent>() {
					@Override 
					public void handle(ActionEvent actionEvent) {
						Calendar time = Calendar.getInstance();
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
						lbl_time.setText(simpleDateFormat.format(time.getTime()));
					}
				}
						),
						new KeyFrame(Duration.seconds(1))
				);
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}

	public void startOnlineUserList() {
		items = FXCollections.observableArrayList();
		list_view.setItems(items);
	}

	public void addOnlineUser(final String s) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				items.add(s);
			}	
		});

	}

	public void setOnlineUserList(final ObservableList<String> items) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				list_view.setItems(items);
			}	
		});
	}

	public boolean validateName() {
		if (getLogin() != null) {
			if (!getLogin().equalsIgnoreCase("")) {
				return true;
			} else return false;
		} else return false;
	}

	public boolean validateLoginFromCombo() {
		if (getLogin().length() < 21 && getLogin().length() > 4) {
			return true;
		} else return false;
	}

	public boolean validadePort() {
		if (Integer.valueOf(fld_sv_port.getText()) < 65536 && Integer.valueOf(fld_sv_port.getText()) > 0) {
			return true;
		} else return false;
	}

	public boolean validadePassword() {
		if (fld_password.lengthProperty().get() > 4 && fld_password.lengthProperty().get() < 21) {
			return true;
		} else return false;
	}

	public boolean validadeIP() {
		if (fld_sv_address.lengthProperty().get() > 0) {
			return true;
		} else return false;
	}

	public boolean validadeMessage() {
		if (message_box.getText().length() > 0) {
			return true;
		} else return false;
	}

	private String getTimestamp() {
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		String dateFormatted = formatter.format(new Date());
		return "["+dateFormatted+"]" + " ";
	}

	public void setDisableRegTab(boolean condition) {
		tab_reg.setDisable(condition);
	}

	public void setLockRegistrationFields(boolean condition) {
		fld_login_reg.setDisable(condition);
		fld_name_reg.setDisable(condition);
		fld_password_reg.setDisable(condition);
		fld_password2_reg.setDisable(condition);
		combo_sex_reg.setDisable(condition);
		combo_college_reg.setDisable(condition);
		combo_course_reg.setDisable(condition);
		combo_courseStTr_reg.setDisable(condition);
		fld_infnetmail_reg.setDisable(condition);
		fld_email_reg.setDisable(condition);
		fld_whatsapp_reg.setDisable(condition);
		fld_facebook_reg.setDisable(condition);
		lbl_addcountry_reg.setDisable(condition);
		lbl_addstate_reg.setDisable(condition);
		lbl_addcity_reg.setDisable(condition);
		lbl_addcourse_reg.setDisable(condition);
		lbl_addcol_reg.setDisable(condition);
		lbl_coursestart_reg.setDisable(condition);
		combo_college_reg.setDisable(condition);
		combo_course_reg.setDisable(condition);
		combo_courseStTr_reg.setDisable(condition);
		combo_sex_reg.setDisable(condition);
		combo_country_reg.setDisable(condition);
		combo_state_reg.setDisable(condition);
		combo_city_reg.setDisable(condition);
	}

	public boolean validateRegFields() {
		if ((getLoginReg().length() < 6) || getLoginReg().length() > 20) {
			getFld_status().setText(getTimestamp() + "LOCAL> Invalid login lenght. Use from 6 to 20 characters."); 
		} else if ((WindowDataFacade.getInstance().getNameReg().length() < 3) || (WindowDataFacade.getInstance().getNameReg().length() > 30)) {
			getFld_status().setText(getTimestamp() + "LOCAL> Invalid name lenght. Use from 3 to 30 characters."); 
		} else if ((WindowDataFacade.getInstance().getPasswordReg().length() < 4) || (WindowDataFacade.getInstance().getPasswordReg().length() > 20)) {
			getFld_status().setText(getTimestamp() + "LOCAL> Invalid password lenght. Use from 4 to 20 characters."); 
		} else if (!validatePassword()) {
			getFld_status().setText(getTimestamp() + "LOCAL> Passwords does not match."); 
		} else if (getSexReg() == null) {
			getFld_status().setText(getTimestamp() + "LOCAL> Please select your Sex.");
		} else if (getCollegeReg().equalsIgnoreCase("Other College")) {
			getFld_status().setText(getTimestamp() + "LOCAL> Please select a College.");
		} else if ((!fld_infnetmail_reg.isDisable()) && (getInfnetMailReg().equalsIgnoreCase("") || !(getInfnetMailReg().contains("@") && getInfnetMailReg().contains(".") && getInfnetMailReg().contains("infnet")))) {
			getFld_status().setText(getTimestamp() + "LOCAL> Please enter a valid infnet ID.");
		} else if (!(getEmailReg().contains("@") && getEmailReg().contains("."))) {
			getFld_status().setText(getTimestamp() + "LOCAL> Please enter a valid email.");
		} else if (getCountryReg() == null) {
			getFld_status().setText(getTimestamp() + "LOCAL> Please select a country/republic.");
		} else if (getCityReg() == null) {
			getFld_status().setText(getTimestamp() + "LOCAL> Please select a city.");
		} else if (getStateReg() == null) {
			getFld_status().setText(getTimestamp() + "LOCAL> Please select a state/province.");
		} else if (getCourseStartReg() == null) {
			getFld_status().setText(getTimestamp() + "LOCAL> Please select your start Trimester.");
		} else if ((!fld_othercol_reg.isDisable()) && (fld_othercol_reg.getText().equalsIgnoreCase("") || getInfnetMailReg().matches("\\W+"))) {
			getFld_status().setText(getTimestamp() + "LOCAL> Please select value for College.");
		} else return true;
		return false;
	}

	public void dynamicFieldsEnable() {
		if (combo_college_reg.getSelectionModel() != null) {
			if (getCollegeReg().equalsIgnoreCase("Other College")) {
				fld_othercol_reg.setDisable(false);
				fld_infnetmail_reg.setDisable(true);
				combo_course_reg.setDisable(true);
			} else {
				fld_othercol_reg.setDisable(true);
				fld_infnetmail_reg.setDisable(true);
				combo_course_reg.setDisable(true);
			}
		} 
		if (getCollegeReg().equalsIgnoreCase("INFNET")) {
			fld_othercol_reg.setDisable(true);
			fld_infnetmail_reg.setDisable(false);
			combo_course_reg.setDisable(false);
		}
	}

	public String getSelectecCollegeReg() {
		if (combo_college_reg.getSelectionModel() == null) {
			return "Other College";
		} else {
			return combo_college_reg.getSelectionModel().toString();
		}
	}

	public boolean validatePassword() {
		String login = WindowDataFacade.getInstance().getLoginReg();
		WindowDataFacade.getInstance().getPasswordReg();
		WindowDataFacade.getInstance().getPassword2Reg();
		if (getPasswordReg().equalsIgnoreCase(getPassword2Reg())) {
			return true;
		} else return false;
	}

//	public synchronized void updateCourseCombo(List<String> list) {
//		combo_course_reg.getItems().clear();
//		combo_course_reg.getItems().addAll(list);
//		combo_course_reg.getItems().add("Other Course");
//	}

//	public synchronized void updateCollegeCombo(List<String> list) {
//		combo_college_reg.getItems().clear();
//		combo_college_reg.getItems().addAll(list);
//		combo_college_reg.getItems().add("Other College");
//	}

	public void updateLoginCombo(List<String> list) {
		combo_login.getItems().clear();
		if (list.isEmpty()) {
			combo_login.getItems().add("<Refresh>");
		} else combo_login.getItems().addAll(list);
	}

	public void initialize() {
		combo_sex_reg.getItems().add("Male");
		combo_sex_reg.getItems().add("Female");
		combo_city_reg.getItems().addAll(
				"Rio de Janeiro", 
				"Niter�i", 
				"S�o Gon�alo", 
				"Maric�", 
				"Cabo Frio", 
				"Duque de Caxias", 
				"Piabet�", 
				"Itabora�", 
				"Resende");
		combo_college_reg.getItems().addAll(
				"CEFET",
				"ESPM",
				"EST�CIO DE S�",
				"FGV",
				"IBMEC",
				"IFRJ",
				"IME",
				"INFNET",
				"ITA",
				"PUC",
				"SANTA �RSULA",
				"SOUZA MARQUES",
				"UC�M",
				"UENF",
				"UERJ",
				"UFF",
				"UFRJ",
				"UFRRJ",
				"UNIRIO",
				"UNIVERCIDADE",
				"UVA");
		combo_course_reg.getItems().addAll(
				"GEC",
				"GADS",
				"GTI",
				"GSI",
				"ECDD",
				"GDG");
		combo_state_reg.getItems().addAll("RJ");
		combo_country_reg.getItems().addAll("BRA");
		combo_courseStTr_reg.getItems().addAll(
				"2009.1","2009.2","2009.3",
				"2009.4","2010.1",
				"2010.2", "2010.3", "2010.4",
				"2011.1", "2011.2", "2011.3",
				"2011.4", "2012.1", "2012.2",
				"2012.3", "2012.4", "2013.1",
				"2013.2", "2013.3", "2013.4",
				"2014.1", "2014.2", "2014.3",
				"2014.4", "2015.1", "2015.2");
		combo_hist_rows.getItems().addAll("First 50 Rows","First 500 Rows", "First 5000 Rows", "First 50000 Rows", "All History");
		combo_hist_rows.setDisable(true);
		fld_sv_port.setDisable(true);
		fld_sv_address.setDisable(true);
		lbl_version.setText("version " + ClientMain.VERSION);
		
		setTestRegisterMode(ClientMain.testRegister);
	}
	
	public void setTestRegisterMode(boolean condition) {
		if (condition) {
			fld_login_reg.setText("raphaelbgr");
			fld_name_reg.setText("Raphael Bernardo");
			fld_password_reg.setText("12345678");
			fld_password2_reg.setText("12345678");
			combo_sex_reg.getSelectionModel().select("Male");
			combo_college_reg.getSelectionModel().select("INFNET");
			fld_infnetmail_reg.setText("raphaelb.rocha@al.infnet.edu.br");
			combo_course_reg.getSelectionModel().select("GEC");
			combo_courseStTr_reg.getSelectionModel().select("2013.2");
			fld_email_reg.setText("raphaelbgr@gmail.com");
			fld_whatsapp_reg.setText("21988856697");
			fld_facebook_reg.setText("raphaelbgr");
			combo_country_reg.getSelectionModel().select("BRA");
			combo_state_reg.getSelectionModel().select("RJ");
			combo_city_reg.getSelectionModel().select("Rio de Janeiro");
			
			fld_username.setText("raphaelbgr");
			fld_password.setText("12345678");
			fld_sv_address.setText("localhost");
			fld_sv_port.setText("2001");
		}
		
	}
	
	public String getLoginReg() {
		return fld_login_reg.getText().toLowerCase();
	}

	public String getNameReg() {
		return fld_name_reg.getText();
	}

	public String getPasswordReg() {
		return fld_password_reg.getText();
	}

	public String getPassword2Reg() {
		return fld_password2_reg.getText();
	}

	public String getSexReg() {
		if (combo_sex_reg.getSelectionModel().getSelectedItem() != null) {
			return combo_sex_reg.getSelectionModel().getSelectedItem().toString();
		} else return null;
	}

	public String getCollegeReg() {
		if (combo_college_reg.getSelectionModel().getSelectedItem() != null) {
			return combo_college_reg.getSelectionModel().getSelectedItem().toString();
		} else return "Other College";	
	}

	public String getCollegeRegValue() {
		if (combo_college_reg.getSelectionModel().getSelectedItem().equalsIgnoreCase("Other College")) {
			return fld_othercol_reg.getText();
		} else return combo_college_reg.getSelectionModel().getSelectedItem().toString();
	}

	public String getCourseStartReg() {
		if (combo_courseStTr_reg.getSelectionModel().getSelectedItem() != null) {
			return combo_courseStTr_reg.getSelectionModel().getSelectedItem().toString();
		} else return null;
	}

	public String getInfnetMailReg() {
		if (fld_infnetmail_reg.isDisable()) {
			return "not@infnet.edu";
		} else return fld_infnetmail_reg.getText();
	}

	public String getEmailReg() {
		return fld_email_reg.getText();
	}

	public String getWhatsappReg() {
		return fld_whatsapp_reg.getText();
	}

	public String getFacebookReg() {
		return fld_facebook_reg.getText();
	}

	public String getNewCountryRreg() {
		return fld_new_country_reg.getText();
	}

	public String getNewStateReg() {
		return fld_new_state_reg.getText();
	}

	public String getNewCityReg() {
		return fld_new_city_reg.getText();
	}

	public void requestFocus() {
		getMainStage().toFront();
	}

	public Stage getMainStage() {
		return mainStage;
	}

	public void setMainStage(Stage mainStage) {
		this.mainStage = mainStage;
	}

	public void playSound(final String string) {
		if (!chkbox_mute.isSelected()) {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					String musicFile = string;     // For example
					Media sound;
					try {
						sound = new Media(Runnable.class.getResource(string).toURI().toString());
						MediaPlayer mediaPlayer = new MediaPlayer(sound);
						mediaPlayer.play();

					} catch (URISyntaxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}	
			});
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void loadMenuItems(List<Node> nodes) {

		for (Node node : nodes) {
			if (node.getId().equalsIgnoreCase("about_linkedin_link")) {
				about_linkedin_link = (Hyperlink) node;
			} else if (node.getId().equalsIgnoreCase("about_my_cv_link")) {
				about_my_cv_link = (Hyperlink) node;
			} else if (node.getId().equalsIgnoreCase("about_whatsapp")) {
				about_whatsapp = (Hyperlink) node;
			} else if (node.getId().equalsIgnoreCase("about_email")) {
				about_email = (Hyperlink) node;
			}
		}

		about_my_cv_link.setOnAction(new EventHandler() {
			@Override
			public void handle(Event arg0) {
				try {
					java.awt.Desktop.getDesktop().browse(new URI(ClientMain.CV));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		about_linkedin_link.setOnAction(new EventHandler() {
			@Override
			public void handle(Event arg0) {
				try {
					java.awt.Desktop.getDesktop().browse(new URI(ClientMain.LINKEDIN));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		about_whatsapp.setOnAction(new EventHandler() {
			@Override
			public void handle(Event arg0) {
				try {
					java.awt.Desktop.getDesktop().browse(new URI(ClientMain.VCF));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		about_email.setOnAction(new EventHandler() {
			@Override
			public void handle(Event arg0) {
				try {
					if (Desktop.isDesktopSupported()) {
						Desktop desktop = Desktop.getDesktop();
						if (desktop.isSupported(Desktop.Action.MAIL)) {
							desktop.mail(new URI(ClientMain.MAIL)); // alternately, pass a mailto: URI in here
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

	}

	public void setFielsStatusMessage(final String msg) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				WindowDataFacade.getInstance().getFld_status().setText(msg);
			}	
		});
	}

	public void lockConnectButton(final boolean b) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				btn_connect.setDisable(b);
			}	
		});

	}
	
	public Client buildNewClientFromForm() {
		NewClient newClient = new NewClient();
		
		newClient.setLogin(WindowDataFacade.getInstance().getLoginReg());
		newClient.setName(WindowDataFacade.getInstance().getNameReg());
		newClient.setMD5Password(codifyPassword(WindowDataFacade.getInstance().getPasswordReg()));
		newClient.setSex(WindowDataFacade.getInstance().getSexReg());
		newClient.setCollege(WindowDataFacade.getInstance().getCollegeRegValue());
		newClient.setInfnetMail(WindowDataFacade.getInstance().getInfnetMailReg());
		newClient.setCourse(WindowDataFacade.getInstance().getCourse());
		newClient.setStartTrimester(WindowDataFacade.getInstance().getCourseStartReg());
		newClient.setEmail(WindowDataFacade.getInstance().getEmailReg());
		newClient.setWhatsapp(WindowDataFacade.getInstance().getWhatsappReg());
		newClient.setFacebook(WindowDataFacade.getInstance().getFacebookReg());
		newClient.setCountry(WindowDataFacade.getInstance().getCountryReg());
		newClient.setState(WindowDataFacade.getInstance().getStateReg());
		newClient.setCity(WindowDataFacade.getInstance().getCityReg());
		newClient.setPlatform(0);
		
		System.out.println("New client built: " + newClient.getLogin() + " Password: " + newClient.getMD5Password());
		
		return newClient;
	}
	
	public String codifyPassword(String pass) {
		return MD5.getMD5(pass);
	}
}