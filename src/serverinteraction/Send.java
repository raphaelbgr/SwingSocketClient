package serverinteraction;

import gui.janelas.JanelaMain;
import gui.janelas.JanelaSelectServer;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import sendable.Message;

public class Send {

	Message m = null;
	JanelaMain jam = null;

	private Socket sock;

	public void send(Socket sock, Object o, JanelaMain jam) throws IOException {
		
		ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());
		oos.writeObject(o);
		oos.flush();
		//oos.close();
	}

	//Monta o objeto mensagem
	public Message assembleMessage() {
		JanelaSelectServer jsv = jam.getJsv();
		m = new Message();
		m.setText(((JanelaMain) jam).getTextField().getText());
		m.setOwner(((JanelaSelectServer) jsv).getField_name().getText());
		m.setIp(sock.getLocalAddress().toString());
		m.setType("normal");
		m.setPcname(sock.getInetAddress().getCanonicalHostName());
		m.setTimestamp();
		m.setDate();
		return m;
	}

	public Send(Socket sock) {
		this.sock = sock;
	}

	public void sendAndhandleLog(JanelaMain jam) {
		try {
			if (sock == null || sock.isConnected() == false) {
				jam.getCn_log().setText("A connection is needed first");
				jam.getCn_log().setBackground(Color.RED);
			} else if (((JanelaMain) jam).getTextField().getText().length() == 0) {
				jam.getCn_log().setText("Empty messages not permitted");
				jam.getCn_log().setBackground(Color.RED);
			} else {
				this.send(sock,this.assembleMessage(),jam);
				jam.getCn_log().setText("Message succefully sent to server");
				jam.getCn_log().setBackground(Color.GREEN);
				(jam).getTextField().setText("");
			}
		} catch (SocketException e2) {
			jam.getCn_log().setText("Currently not conencted to any host");
			jam.getCn_log().setBackground(Color.RED);

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
