package fxgui;

import fxgui.events.individual.ConnectListener;
import fxgui.events.individual.SendListener;

public class FXController {
	
	public void handleSendButton() {
		SendListener sl = new SendListener();
		sl.performAction();
	}
	
	public void handleConnectButton() {
		ConnectListener sl = new ConnectListener();
		sl.performAction();
	}
	
	public FXController() {
	}
}
