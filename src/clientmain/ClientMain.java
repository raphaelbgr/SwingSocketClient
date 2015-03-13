package clientmain;

import gui.WindowDataFacade;
import gui.janelas.JanelaMain;
import gui.janelas.JanelaSelectServer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import threads.ReceiveFromServerThread;

public class ClientMain extends Application {

//	public static boolean CONNECTED			= false;
	
	public static int port 					= 0;
	public static int version				= 13;

	public static Thread receiver			= null;

	public static String ip 				= null;		
	public static String your_name 			= null;
	
	private Stage mainStage					= new Stage();
	private ClientMain cmain				= this;

	@SuppressWarnings("unused")
	public static void main(String[] args) {	
		//JanelaMain jam = new JanelaMain(new JanelaSelectServer("Address Input"));

		cmain.start(new Stage());
		launch(args);
		
		Thread t1 = new Thread(new ReceiveFromServerThread(WindowDataFacade.getJam()));
		t1.start();

	}
	
	@Override
	public void start(Stage arg0) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("CHATFXML.fxml"));
        Scene scene = new Scene(root);
        
        mainStage.setScene(scene);
        mainStage.show();
		
	}
	
	public
	
}
