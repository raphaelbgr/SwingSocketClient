package gui.fx.controllers;

import gui.fx.WindowDataFacade;
import gui.fx.buttons.ConnectionPerform;
import gui.fx.buttons.DisconnectionPerform;
import gui.fx.buttons.SendPerform;


public class FXController {

	WindowDataFacade wdf = null;
	
	public void setFacade(WindowDataFacade wdf) {
		this.wdf = wdf;
	}

	public void handleSendButton() {
		SendPerform sl = new SendPerform();
		sl.performAction();
	}

	public void handleDisconnectButton() {
		DisconnectionPerform dp = new DisconnectionPerform(wdf);
		dp.performAction();
		
	}

	public void handleConnectButton() {
		ConnectionPerform cp = new ConnectionPerform(wdf);
		cp.performAction();
	}

}
