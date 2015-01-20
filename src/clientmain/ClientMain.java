package clientmain;

import gui.janelas.JanelaMain;
import gui.janelas.JanelaSelectServer;

public class ClientMain {

	public static boolean CONNECTED			= false;
	
	public static int port 					= 0;

	public static Thread receiver			= null;

	public static String ip 				= null;		
	public static String your_name 			= null;		

	@SuppressWarnings("unused")
	public static void main(String[] args) {	
		JanelaMain jam = new JanelaMain(new JanelaSelectServer("Address Input"));
	}
}
