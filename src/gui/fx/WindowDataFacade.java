package gui.fx;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import sendable.Message;
import sendable.NormalMessage;

public class WindowDataFacade<E> {

	private Button btn_connect 			= null;
	private Button btn_disconnect 		= null;
	private Button btn_serv_opt 		= null;
	private TextField fld_username		= null;
	private TextField sv_address 		= null;
	private PasswordField passwd_field 	= null;
	private TextField sv_port 			= null;
	private ProgressBar progress 		= null;
	private ProgressIndicator indicator = null;
	private TextField fld_status  		= null;
	private Label lbl_status  			= null;
	private Label lbl_time 				= null;
	private Button btn_exit 			= null;
	private Button btn_send 			= null;
	private CheckBox chkbox_autocon 	= null;
	private ListView<String> list_view 	= null;
	private TextArea txt_chatlog 		= null;

	private List<Node> nodes 			= new ArrayList<Node>();
	private Parent root 				= null;
	private Task<Void> task 			= null;
	private TextField message_box		= null;
	
	ObservableList<String> items 		= null;


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

	public void setBigStatusMsg(final String s) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				fld_status.setText(s);
			}
		});

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
				fld_username.setDisable(true);
				passwd_field.setDisable(true);
				sv_address.setDisable(true);
				sv_port.setDisable(true);
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
				passwd_field.setDisable(true);
				sv_address.setDisable(true);
				sv_port.setDisable(true);
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
				passwd_field.setDisable(false);
				sv_address.setDisable(false);
				sv_port.setDisable(false);
			}
		});
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
		} else if (node.getId().equalsIgnoreCase("list_view")) {
			list_view = (ListView) node;
		} else if (node.getId().equalsIgnoreCase("txt_chatlog")) {
			txt_chatlog = (TextArea) node;
		} else if (node.getId().equalsIgnoreCase("message_box")) {
			message_box = (TextField) node;
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
		m.setOwner(this.getUserName());
		m.setTimestamp();
		m.setDate();
		return m;
	}

	public void createConnectedWorker() {
		//		Platform.runLater(new Runnable() {
		//			@Override
		//			public void run() {
		//				for (double i = 0; i <= 100; i = i + 0.01) {
		//					progress.setProgress(i);
		//					wdf.setConnectedLockFields();
		//				}
		//			}	
		//		});

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
						// TODO Auto-generated catch block
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

	public void createConnectingWorker() {
		//		Platform.runLater(new Runnable() {
		//			@Override
		//			public void run() {
		//				progress.setProgress(-1);
		//				wdf.setConnectingLockFields();
		//				try {
		//					Thread.sleep(1);
		//				} catch (InterruptedException e) {
		//				}
		//			}	
		//		});

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

	public void createCanceledWorker() {
		//		Platform.runLater(new Runnable() {
		//			@Override
		//			public void run() {
		//				progress.setProgress(0);
		//				wdf.setDisconnectedLockFields();
		//				try {
		//					Thread.sleep(1);
		//				} catch (InterruptedException e) {
		//				}
		//			}
		//		});

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
						// TODO Auto-generated catch block
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

	public void createSendWorker() {
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
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				progress.progressProperty().unbind();
				return null;
			}
		};
		progress.progressProperty().unbind();
//		progress.setProgress(0);
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
				txt_chatlog.appendText(getTimestamp() + m.getOwner()+ " -> " + m.getText() + "\n");
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
	
	private String getTimestamp() {
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		String dateFormatted = formatter.format(new Date());
		return "["+dateFormatted+"]" + " ";
	}
}