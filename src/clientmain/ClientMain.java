package clientmain;

import gui.janelas.JanelaMain;
import gui.janelas.JanelaSelectServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientMain {
	
	public static int port 					= 0;
	public static ObjectOutputStream oos 	= null;		//É possível também pegar a instância desses objetos
	public static ObjectInputStream  ois 	= null;		//É possível também pegar a instância desses objetos
	public static Socket sock 				= null;		//É possível também pegar a instância desses objetos
	
	public static Thread receiver			= null;

	public static String ip 				= null;		
	public static String your_name 			= null;		
	
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {	
		JanelaSelectServer jsv			= new JanelaSelectServer("Address Input");	//É possível também pegar a instância desses objetos
		JanelaMain jam 					= new JanelaMain(jsv);						//É possível também pegar a instância desses objetos
	}
}
