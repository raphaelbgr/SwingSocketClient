package clientmain;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
			private MainScene scene;

			@Override
			public void run() {
				System.out.println("run: " + Thread.currentThread());
				mainStage = new Stage();
				try {
					root = FXMLLoader.load(getClass().getResource("/fxgui.fxml"));
					scene = new MainScene(root);  
					scene.initialize(null, null);
					mainStage.setScene(scene);
					Button node = (Button) mainStage.getScene().lookup("#btn_disconnect");
					node.setDisable(true);
					mainStage.show();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		System.out.println("public void start: " + Thread.currentThread());

	}

}
