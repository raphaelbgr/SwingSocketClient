package serverinteraction;

import exceptions.LocalException;
import gui.swing.WindowDataFacade;
import gui.swing.janelas.JanelaMain;
import gui.swing.janelas.JanelaSelectServer;

import java.io.IOException;
import java.net.UnknownHostException;

import sendable.Message;
import sendable.NormalMessage;
import sync.ClientStream;
import clientmain.Status;

public class Send {

	JanelaMain jam 			= null;
	ClientStream stream 	= ClientStream.getInstance();
	JanelaSelectServer jsv 	= WindowDataFacade.getJsv();

	public boolean send(Object o) throws UnknownHostException, IOException, LocalException {
		/*if (stream.checkOnlineStatus()) {
			if (o instanceof NormalMessage) {
				if(stream.checkOnlineStatus()) {
					stream.sendObject(assembleMessage());	//SENDS THE MESSAGE
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			throw new LocalException("Not connected.");
		}
	}*/
		
		if (Status.getInstance().isConnected()) {
			if (o instanceof NormalMessage) {
				if(Status.getInstance().isConnected()) {
					stream.sendObject(assembleMessage());	//SENDS THE MESSAGE
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			throw new LocalException("Not connected.");
		}
	}

	//Monta o objeto mensagem
	private Message assembleMessage() {
		NormalMessage nm = new NormalMessage();
		nm.setOwner(WindowDataFacade.getJsv().getNameFieldText());
		nm.setPcname(stream.getSock().getInetAddress().getCanonicalHostName());
		nm.setText(WindowDataFacade.getJam().getTextField().getText());
		nm.setTimestamp();
		nm.setDate();
		return nm;
	}

}
