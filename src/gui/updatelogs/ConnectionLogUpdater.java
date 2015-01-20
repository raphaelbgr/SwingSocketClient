package gui.updatelogs;

import gui.janelas.JanelaMain;

import java.awt.Color;

import javax.swing.JTextField;


@SuppressWarnings("serial")
public class ConnectionLogUpdater extends JTextField {

	private JanelaMain jam;

	public void setErrorMessage(String s) {
		jam.getConnectionLog().setText(s);
		jam.getConnectionLog().setBackground(Color.RED);
		jam.getConnectionLog().setForeground(Color.YELLOW);
	}

	public void setGreenMessage(String s) {
		jam.getConnectionLog().setText(s);
		jam.getConnectionLog().setBackground(Color.GREEN);
		jam.getConnectionLog().setForeground(Color.BLACK);
	}
	
	public void setGreyMessage(String s) {
		jam.getConnectionLog().setText(s);
		jam.getConnectionLog().setBackground(Color.LIGHT_GRAY);
		jam.getConnectionLog().setForeground(Color.BLACK);
	}

	public ConnectionLogUpdater(JanelaMain jam, int i) {
		super(i);
		this.jam = jam;
	}

}
