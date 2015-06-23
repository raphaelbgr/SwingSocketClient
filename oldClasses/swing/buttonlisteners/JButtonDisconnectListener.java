package gui.swing.buttonlisteners;

import gui.swing.WindowDataFacade;
import gui.swing.janelas.JanelaMain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import serverinteraction.Disconnect;
import clientmain.Status;

public class JButtonDisconnectListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			new Disconnect();
			WindowDataFacade.getJam().getJbt_Disconn().setEnabled(false);
			WindowDataFacade.getJam().getJbt_Connect().setEnabled(true);
			WindowDataFacade.getJam().getLocalConnectionLog().setGreyMessage(getTimestamp() + "LOCAL> Disconnected succefully");
			WindowDataFacade.getJsv().unlockFields();
			WindowDataFacade.getJam().getJbt_send().setEnabled(false);
			Status.getInstance().setConnected(false);
		} catch (UnknownHostException e1) {
			WindowDataFacade.getJam().getJbt_Disconn().setEnabled(false);
			WindowDataFacade.getJam().getJbt_Connect().setEnabled(true);
			WindowDataFacade.getJam().getJbt_send().setEnabled(false);
			WindowDataFacade.getJam().getLocalConnectionLog().setGreyMessage(getTimestamp() + "LOCAL> Disconnected w/o informing server.");
			WindowDataFacade.getJsv().unlockFields();
			Status.getInstance().setConnected(false);
		} catch (IOException e1) {
			WindowDataFacade.getJam().getJbt_Disconn().setEnabled(false);
			WindowDataFacade.getJam().getJbt_Connect().setEnabled(true);
			WindowDataFacade.getJam().getLocalConnectionLog().setGreyMessage(getTimestamp() + "LOCAL> Disconnected w/o informing server.");
			WindowDataFacade.getJsv().unlockFields();
			WindowDataFacade.getJam().getJbt_send().setEnabled(false);
			Status.getInstance().setConnected(false);
		}
	}

	private String getTimestamp() {
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		String dateFormatted = formatter.format(new Date());
		return "["+dateFormatted+"]" + " ";
	}
	
	public JButtonDisconnectListener(JanelaMain jam) {
	}

}
