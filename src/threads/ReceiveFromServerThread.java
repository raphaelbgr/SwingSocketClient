package threads;

import exceptions.ServerException;
import gui.janelas.JanelaMain;

import java.awt.Color;
import java.io.IOException;

import javax.swing.JTextField;

import sendable.Message;
import clientmain.ClientMain;

public class ReceiveFromServerThread implements Runnable {

	private JanelaMain jam;

	@Override
	public void run() {

		while (true) {

			try {
				Object o = ClientMain.ois.readObject();
				System.out.println("isexecuting?");
				if (o != null) {
					if (o instanceof Message) {
						if (((Message) o).getType().equals("normal") && ((Message) o).getType() != null) {
							jam.getJtxt_cnlog().setText("SERVER> " + ((Message) o).getServresponse());
							jam.getJtxt_cnlog().setBackground(Color.GREEN);
						} else if (((Message) o).getType().equals("broadcast") && ((Message) o).getType() != null) {
							JanelaMain.msg_list.add(((Message) o).getText(), new JTextField()); 					// TERMINAR ISSO DAQUI
						} else if (((Message) o).getType().equals("connectok") && ((Message) o).getType() != null) {
							jam.getJtxt_cnlog().setText(((Message) o).getServresponse() + " on " + ClientMain.ip + " on port " + ClientMain.port);
							jam.getJtxt_cnlog().setBackground(Color.GREEN);
						}
					} else if (o instanceof ServerException) {
						jam.getJtxt_cnlog().setText(((ServerException) o).getMessage());
						jam.getJtxt_cnlog().setBackground(Color.RED);
					}
				} else {
					jam.getJtxt_cnlog().setText("Connected but not responding.");
					jam.getJtxt_cnlog().setBackground(Color.RED);
				}
			} catch (ClassNotFoundException e) {
				// break;
			} catch (IOException e) {
				// break;
			} finally {

			}
		}
	}

	public ReceiveFromServerThread(JanelaMain jam) {
		this.jam = jam;
	}

}
