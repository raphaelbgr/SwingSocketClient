package fxgui;

import fxgui.events.ConnectListener;
import fxgui.events.SendListener;

public class FXController {

	public void handleSendButton() {
		SendListener sl = new SendListener();
		sl.performAction();
	}
	
	public void handleConnectButton() {
		ConnectListener sl = new ConnectListener();
		sl.performAction();
	}
	
}
