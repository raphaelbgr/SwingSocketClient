package gui.buttonlisteners;
import gui.janelas.JanelaMain;
import gui.janelas.JanelaSelectServer;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

import sendable.Message;
import sync.ClientStream;

public class JButtonSendServerListener implements ActionListener {

	Message m = null;
	private JanelaMain jam;
	private JanelaSelectServer jsv;
	private Socket sock = ClientStream.getInstance().getSock();
	private ClientStream stream = ClientStream.getInstance();

	//Monta o objeto mensagem
	public Message assembleMessage() {
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

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (sock == null || sock.isConnected() == false) {
				jam.getCn_log().setText("A connection is needed first");
				jam.getCn_log().setBackground(Color.RED);
			} else if (((JanelaMain) jam).getTextField().getText().length() == 0) {
				jam.getCn_log().setText("Empty messages not permitted");
				jam.getCn_log().setBackground(Color.RED);
			} else {
				stream.sendMessage(this.assembleMessage());
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
	
	public JButtonSendServerListener(JanelaMain jam) {
		this.jam = jam;
		this.jsv = jam.getJsv();
	}

}
