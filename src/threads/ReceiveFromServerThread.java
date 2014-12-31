package threads;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JTextField;

import exceptions.ServerException;
import gui.janelas.JanelaMain;
import sendable.Message;
import clientmain.ClientMain;

public class ReceiveFromServerThread implements Runnable {

	private JanelaMain jam;

	@Override
	public void run() {

		while (true) {
			try {
				clientmain.ClientMain.ois = new ObjectInputStream(clientmain.ClientMain.sock.getInputStream());
			} catch (IOException e1) {
				//e1.printStackTrace();
			} catch (Throwable e0) {
				
			}
			try {	
				Object o = ClientMain.ois.readObject();
				if (o instanceof Message) {
					if (((Message)o).getType().equals("normal")) {
						jam.getJtxt_cnlog().setText("SERVER> "+((Message) o).getServresponse());
						jam.getJtxt_cnlog().setBackground(Color.GREEN);
					} else if (((Message)o).getType().equals("broadcast")) {
						JanelaMain.msg_list.add(((Message) o).getText(), new JTextField());
					}
				} else if (o instanceof ServerException) {
					jam.getJtxt_cnlog().setText(((ServerException) o).getMessage());
					jam.getJtxt_cnlog().setBackground(Color.RED);
				}
			} catch (ClassNotFoundException e) {
				break;
			} catch (IOException e) {
				break;
			}
		}	
	}

	public ReceiveFromServerThread(JanelaMain jam) {
		this.jam = jam;
	}

}
