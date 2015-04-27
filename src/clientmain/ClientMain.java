package clientmain;

import gui.fx.WindowDataFacade;
import gui.fx.controllers.FXController;
import gui.fx.scenes.MainScene;

import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sendable.Client;
import serverinteraction.Disconnect;
import threads.FXReceiveFromServerThread;

public class ClientMain extends Application {

	//	public static boolean CONNECTED			= false;

	public static int port 					= 0;
	public static int version				= 13;

	public static Thread receiver			= null;

	public static String ip 				= null;		
	public static String your_name 			= null;

	public static void main(final String[] args) {	
		//		JanelaMain jam = new JanelaMain(new JanelaSelectServer("Address Input"));

		//		Thread t1 = new Thread(new ReceiveFromServerThread(WindowDataFacade.getJam()));
		Thread t1 = new Thread(new FXReceiveFromServerThread()); //NOT ON FX THREAD, NEEDS TO FIX THIS
		t1.start();


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
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxgui.fxml"));
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
										private static final long serialVersionUID = 296589332172289191L;
										@Override
										public void setName(String name) {
											super.setName(WindowDataFacade.getInstance().getUserName());
										}
									});
								} catch (UnknownHostException e) {
									System.out.println("Onclose2");
									e.printStackTrace();
								} catch (IOException e) {
									System.out.println("Onclose1");
									e.printStackTrace();
								}
								System.exit(0);
								} else {
									System.exit(0);
								}
							}	
					});
					mainStage.show();
					fxControl.setDebug(true);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		System.out.println("public void start: " + Thread.currentThread());

	}

}
