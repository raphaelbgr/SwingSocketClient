package gui.swing.buttonlisteners;

import gui.swing.janelas.JanelaMain;
import gui.swing.janelas.JanelaSelectServer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JButtonSelectServerOKListener implements ActionListener {

	@SuppressWarnings("unused")
	private JanelaMain jam;
	private JanelaSelectServer jsv;

	@Override
	public void actionPerformed(ActionEvent arg0) {
		((JanelaSelectServer) jsv).setVisible(false);	
	}
	
	public JButtonSelectServerOKListener(JanelaMain jam, JanelaSelectServer jsv) {
		this.jam = jam;
		this.jsv = jsv;
	}
	
}
