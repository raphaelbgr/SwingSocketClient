package clientmain;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import fxgui.FXController;
import fxgui.scenes.MainScene;

public class ClientMain extends Application {

	//	public static boolean CONNECTED			= false;

	public static int port 					= 0;
	public static int version				= 13;

	public static Thread receiver			= null;

	public static String ip 				= null;		
	public static String your_name 			= null;

	public static void main(final String[] args) {	
		//		JanelaMain jam = new JanelaMain(new JanelaSelectServer("Address Input"));

		/*		Thread t1 = new Thread(new ReceiveFromServerThread(WindowDataFacade.getJam()));
		t1.start();
		 */
		
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {

		Platform.runLater(new Runnable() {
			private Stage mainStage;
			private Parent root;
			private Scene scene;
			private FXMLLoader fxmllLoader;

			@Override
			public void run() {
				System.out.println("run: " + Thread.currentThread());
				mainStage = new Stage();
				try {
					root = FXMLLoader.load(getClass().getResource("/fxgui.fxml"));
//					fxmllLoader = new FXMLLoader(getClass().getResource("/fxgui.fxml"));
//					root = fxmllLoader.load();
//					fxmllLoader.setController(new FXController(mainStage));
					scene = new MainScene(root);  
					mainStage.setScene(scene);
					mainStage.show();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		System.out.println("public void start: " + Thread.currentThread());
		
	}

}
