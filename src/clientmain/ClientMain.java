package clientmain;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import fxgui.FxManager;

public class ClientMain extends Application {

//	public static boolean CONNECTED			= false;
	
	public static int port 					= 0;
	public static int version				= 13;

	public static Thread receiver			= null;

	public static String ip 				= null;		
	public static String your_name 			= null;

	public static void main(final String[] args) {	
//		JanelaMain jam = new JanelaMain(new JanelaSelectServer("Address Input"));
		
		launch(args);
		
/*		Thread t1 = new Thread(new ReceiveFromServerThread(WindowDataFacade.getJam()));
		t1.start();
*/
	}

	@Override
	public void start(Stage arg0) throws Exception {
		Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
		    	new JFXPanel();
		    	FxManager fxmgr = new FxManager();
				fxmgr.assembleStage();
		    }
		});
	}

}
