package gui.updatelogs;

import gui.janelas.JanelaMain;

import java.awt.Color;

import javax.swing.JTextField;


@SuppressWarnings("serial")
public class LocalLogUpdater extends JTextField {

	private JanelaMain jam;

	public void setErrorMessage(String s) {
		jam.getLocalConnectionLog().setText(s);
		jam.getLocalConnectionLog().setBackground(Color.RED);
		jam.getLocalConnectionLog().setForeground(Color.YELLOW);
	}

	public void setGreenMessage(String s) {
		jam.getLocalConnectionLog().setText(s);
		jam.getLocalConnectionLog().setBackground(Color.GREEN);
		jam.getLocalConnectionLog().setForeground(Color.BLACK);
	}
	
	public void setGreyMessage(String s) {
		jam.getLocalConnectionLog().setText(s);
		jam.getLocalConnectionLog().setBackground(Color.LIGHT_GRAY);
		jam.getLocalConnectionLog().setForeground(Color.BLACK);
	}

	public LocalLogUpdater(JanelaMain jam, int i) {
		super(i);
		this.jam = jam;
	}

}
