package gui.updatelogs;

import gui.janelas.JanelaMain;

import java.awt.Color;

import javax.swing.JTextField;


@SuppressWarnings("serial")
public class ConnectionLog extends JTextField {

	private JanelaMain jam;

	public void setErrorMessage(String s) {
		jam.getCn_log().setText(s);
		jam.getCn_log().setBackground(Color.RED);
	}

	public void setGreenMessage(String s) {
		jam.getCn_log().setText(s);
		jam.getCn_log().setBackground(Color.GREEN);
	}
	
	public void setGreyMessage(String s) {
		jam.getCn_log().setText(s);
		jam.getCn_log().setBackground(Color.LIGHT_GRAY);
	}

	public ConnectionLog(JanelaMain jam, int i) {
		super(i);
		this.jam = jam;
	}

}
