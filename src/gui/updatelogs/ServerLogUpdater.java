package gui.updatelogs;

import gui.janelas.JanelaMain;

import java.awt.Color;

import javax.swing.JTextField;


@SuppressWarnings("serial")
public class ServerLogUpdater extends JTextField {

	private JanelaMain jam;

	public void setErrorMessage(String s) {
		jam.getServerConnectionLog().setText(s);
		jam.getServerConnectionLog().setBackground(Color.RED);
		jam.getServerConnectionLog().setForeground(Color.YELLOW);
	}

	public void setGreenMessage(String s) {
		jam.getServerConnectionLog().setText(s);
		jam.getServerConnectionLog().setBackground(Color.GREEN);
		jam.getServerConnectionLog().setForeground(Color.BLACK);
	}

	public void setGreyMessage(String s) {
		jam.getServerConnectionLog().setText(s);
		jam.getServerConnectionLog().setBackground(Color.LIGHT_GRAY);
		jam.getServerConnectionLog().setForeground(Color.BLACK);
	}

	public ServerLogUpdater(JanelaMain jam, int i) {
		super(i);
		this.jam = jam;
	}

}

