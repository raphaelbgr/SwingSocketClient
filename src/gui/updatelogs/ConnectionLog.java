package gui.updatelogs;

import gui.janelas.JanelaMain;

import java.awt.Color;

import javax.swing.JTextField;


@SuppressWarnings("serial")
public class ConnectionLog extends JTextField {
	
	private JanelaMain jam = JanelaMain.getInstance();

	public void setErrorMessage(String s) {
		jam.getCn_log().setText(s);
		jam.getCn_log().setBackground(Color.RED);
	}

	public void setGreenMessage(String s) {
		jam.getCn_log().setText(s);
		jam.getCn_log().setBackground(Color.GREEN);
	}
	
	public void setGreyMessage(String s) {
		
	}


	//SINGLETON PATTERN BLOCK
	private static ConnectionLog cl;
	public static ConnectionLog getInstance() {
		if (ConnectionLog.cl == null){
			cl = new ConnectionLog(30);
		}
		return cl;
	}
	private ConnectionLog() {
	}
	private ConnectionLog(int i) {
		super(i);
	}

}
