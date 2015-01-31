package gui.buttonlisteners;

import gui.WindowDataFacade;
import gui.janelas.JanelaMain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import serverinteraction.Disconnect;

public class JButtonDisconnectListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			new Disconnect();
			WindowDataFacade.getJam().getJbt_Disconn().setEnabled(false);
			WindowDataFacade.getJam().getJbt_Connect().setEnabled(true);
			WindowDataFacade.getJam().getConnectionLog().setGreyMessage("LOCAL> Disconnected succefully");
			WindowDataFacade.getJsv().unlockFields();
			WindowDataFacade.getJam().getJbt_send().setEnabled(false);
		} catch (UnknownHostException e1) {
			WindowDataFacade.getJam().getJbt_Disconn().setEnabled(false);
			WindowDataFacade.getJam().getJbt_Connect().setEnabled(true);
			WindowDataFacade.getJam().getJbt_send().setEnabled(false);
			WindowDataFacade.getJam().getConnectionLog().setGreyMessage("LOCAL> Disconnected w/o informing server.");
			WindowDataFacade.getJsv().unlockFields();
		} catch (IOException e1) {
			WindowDataFacade.getJam().getJbt_Disconn().setEnabled(false);
			WindowDataFacade.getJam().getJbt_Connect().setEnabled(true);
			WindowDataFacade.getJam().getConnectionLog().setGreyMessage("LOCAL> Disconnected w/o informing server.");
			WindowDataFacade.getJsv().unlockFields();
			WindowDataFacade.getJam().getJbt_send().setEnabled(false);
		}
	}

	public JButtonDisconnectListener(JanelaMain jam) {
	}

}
