package clientmain;

import gui.janelas.JanelaMain;

public class ClientMain {

	public static int port 					= 0;

	public static Thread receiver			= null;

	public static String ip 				= null;		
	public static String your_name 			= null;		

	public static void main(String[] args) {	
		JanelaMain jam = JanelaMain.getInstance();		//� poss�vel tamb�m pegar a inst�ncia desses objetos
		jam.setLocationByPlatform(false);
	}
}
