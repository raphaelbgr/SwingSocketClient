package serverinteraction;

import gui.WindowDataFacade;
import gui.janelas.JanelaMain;
import gui.janelas.JanelaSelectServer;

import java.io.IOException;
import java.net.UnknownHostException;

import sendable.Message;
import sendable.NormalMessage;
import sync.ClientStream;

public class Send {

	JanelaMain jam 			= null;
	ClientStream stream 	= ClientStream.getInstance();
	JanelaSelectServer jsv 	= WindowDataFacade.getJsv();

	public boolean send(Object o) throws UnknownHostException, IOException {
		if (o instanceof NormalMessage) {
			if(stream.checkOnlineStatus()) {
				stream.sendMessage(assembleMessage());	//SENDS THE MESSAGE
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	//Monta o objeto mensagem
	public Message assembleMessage() {
		NormalMessage nm 	= new NormalMessage();
		nm.setOwner(jsv.getNameField());
		nm.setPcname(stream.getSock().getInetAddress().getCanonicalHostName());
		nm.setText(jsv.getIpText());
		nm.setTimestamp();
		nm.setDate();
		return nm;
	}

}
