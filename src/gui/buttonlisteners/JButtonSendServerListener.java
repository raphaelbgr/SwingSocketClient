package gui.buttonlisteners;
import gui.janelas.JanelaMain;
import gui.janelas.JanelaSelectServer;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.SocketException;

import sendable.Message;
import clientmain.ClientMain;

public class JButtonSendServerListener implements ActionListener {

	Message m = null;
	private JanelaMain jam;
	private JanelaSelectServer jsv;

	//Monta o objeto mensagem
	public Message assembleMessage() {
		m = new Message();
		m.setText(((JanelaMain) jam).getTextField().getText());
		m.setOwner(((JanelaSelectServer) jsv).getField_name().getText());
		m.setIp(ClientMain.sock.getLocalAddress().toString());
		m.setType("normal");
		m.setPcname(ClientMain.sock.getInetAddress().getCanonicalHostName());
		m.setTimestamp();
		m.setDate();
		return m;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (clientmain.ClientMain.sock == null || clientmain.ClientMain.sock.isConnected() == false) {
				jam.getJtxt_cnlog().setText("A connection is needed first");
				jam.getJtxt_cnlog().setBackground(Color.RED);
			} else if (((JanelaMain) jam).getTextField().getText().length() == 0) {
				jam.getJtxt_cnlog().setText("Empty messages not permitted");
				jam.getJtxt_cnlog().setBackground(Color.RED);
			} else {
				clientmain.ClientMain.oos.writeObject(this.assembleMessage());
//				jam.getJtxt_cnlog().setText("Message succefully sent to server");
//				jam.getJtxt_cnlog().setBackground(Color.GREEN);
				((JanelaMain) jam).getTextField().setText("");
			}
		} catch (SocketException e2) {
			//e2.printStackTrace();
			jam.getJtxt_cnlog().setText("Currently not conencted to any host");
			jam.getJtxt_cnlog().setBackground(Color.RED);
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public JButtonSendServerListener(JanelaMain jam, JanelaSelectServer jsv) {
		this.jam = jam;
		this.jsv = jsv;
	}

}
