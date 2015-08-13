package app;

import java.io.IOException;
import java.net.UnknownHostException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import app.control.serverinteraction.Disconnect;
import app.control.sync.Status;
import app.model.clients.Client;
import app.view.WindowDataFacade;
import app.view.controllers.FXController;
import app.view.scenes.MainScene;

public class ClientMain extends Application {
	public static final String LINKEDIN = "https://br.linkedin.com/in/raphaelbgr";
	public static final String CV = "https://drive.google.com/open?id=0BxXoGz9K8rOQeTVQQlg2eXlSRXM&authuser=0";
	public static final String VCF = "https://drive.google.com/open?id=0BxXoGz9K8rOQd3UtUlhXYlJOMTg&authuser=0";
	public static final String MAIL = "mailto:raphaelbgr@gmail.com";

	public static String ENTERED_ROOM = " has entered the room...";
	public static String LEFT_ROOM = " has left the room...";

	public static int PORT = 0;
	public static String VERSION = "0.9.21";

	public static boolean compKey = false;
	public static boolean receiveRdy = false;

	// DO NOT LEAVE THIS AS TRUE, this will print out all queries on the sysout.
	public static boolean printQueries = false;

	public static Thread receiver = null;

	public static String ip = null;
	public static String your_name = null;

	public static String DATABASE_KEY = null;
	public static String DATABASE_ADDR = null;
	public static String DATABASE_USER = null;
	public static String DATABASE_PASS = null;

	// public static String COMPILATION_KEY = "YOUR_KEY_HERE!!";
	public static String COMPILATION_KEY = "test";

	public static String defaultServer = "54.232.241.237";

	public static void main(final String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {

		Platform.runLater(new Runnable() {
			private Stage mainStage;
			private MainScene scene;
			private FXController fxControl = null;

			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public void run() {
				System.out.println("run: " + Thread.currentThread());
				mainStage = new Stage();
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource(
							"/fxgui.fxml"));
					Parent root = (Parent) loader.load();
					fxControl = loader.getController();
					scene = new MainScene(root);
					scene.setRoot(root);
					mainStage.setScene(scene);
					mainStage.setTitle("Open Source Chat Client");
					mainStage.setResizable(false);
					mainStage.toFront();
					mainStage.setOnCloseRequest(new EventHandler() {
						@Override
						public void handle(Event arg0) {
							if (Status.getInstance().isConnected()) {
								try {
									new Disconnect(new Client() {
										private static final long serialVersionUID = 5000337873561587678L;

										@Override
										public void setName(String name) {
											super.setName(WindowDataFacade
													.getInstance().getLogin());
										}
									});
								} catch (UnknownHostException e) {
									e.printStackTrace();
								} catch (IOException e) {
								}
								System.exit(1);
							} else {
								System.exit(0);
							}
						}
					});
					mainStage.show();
					fxControl.setLockedServer(false);
					fxControl.setPublicDefaultValues(true);
					WindowDataFacade.getInstance().setMainStage(mainStage);
				} catch (IOException e) {
					e.printStackTrace();
					System.exit(1);
				}
			}
		});
		System.out.println("public void start: " + Thread.currentThread());
	}

}
