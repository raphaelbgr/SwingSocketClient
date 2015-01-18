package gui.buttonlisteners;

import gui.janelas.JanelaMain;
import gui.janelas.JanelaSelectServer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JButtonSelectServerListener implements ActionListener {	
	
	@SuppressWarnings("unused")
	private JanelaMain jam;
	private JanelaSelectServer jsv;

	public void actionPerformed(ActionEvent e) {	
		jsv.setVisible(true);
	}

	public JButtonSelectServerListener(JanelaMain jam) {
		this.jam = jam;
		this.jsv = jam.getJsv();
	}
	
}
