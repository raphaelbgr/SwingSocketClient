package gui.jmenuListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class JMenuExitListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			clientmain.ClientMain.oos.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			System.exit(0);
		}
	}

}
