package threads;

import exceptions.ServerException;
import gui.fx.WindowDataFacade;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import sendable.BroadCastMessage;
import sendable.DisconnectionMessage;
import sendable.Message;
import sendable.NormalMessage;
import sendable.ServerMessage;
import sync.ClientStream;
import clientmain.Status;

public class FXReceiveFromServerThread implements Runnable {

	private ClientStream stream = ClientStream.getInstance();
//	private LocalLogUpdater localLog = null;
//	private ServerLogUpdater serverLog = WindowDataFacade.getJam().getServerConnectionLog();
//	private TextLog tlog = WindowDataFacade.getJam().getMsg_list();

	@Override
	public void run() {

		while (true) {
			if ((stream.getSock() != null) && (stream.getSock().isConnected() == true)) {
				try {
					Object o = stream.receiveMessage();
					if (o != null) {
						if (o instanceof Message) {
							if (o instanceof ServerMessage) {
								ServerMessage sm = (ServerMessage) o;
								if (sm.getServresponse() != null) {
									WindowDataFacade.getInstance().getFld_status().setText(sm.toString());
								}
								if (sm.getOnlineUserList() != null) {
//									WindowDataFacade.getJam().getLe().updateOnlineList(sm.getOnlineUserList());
								}
							} else if (o instanceof NormalMessage) {
								NormalMessage nm = (NormalMessage) o;
								if (!nm.getOwner().equalsIgnoreCase(WindowDataFacade.getInstance().getUserName())) {
									WindowDataFacade.getInstance().getFld_status().setText("[" + nm.getTimestamp() + "]" + " SERVER> " + "Broadcast from " + nm.getOwner());
								} else {
									WindowDataFacade.getInstance().getFld_status().setText("[" + nm.getTimestamp() + "]" + " " + nm.getServresponse());
								}
//								tlog.addMessage(nm.toString());
								if (nm.getOnlineUserList() != null) {
//									WindowDataFacade.getJam().getLe().updateOnlineList(nm.getOnlineUserList());
								}
							} else if (o instanceof DisconnectionMessage) {
//								new Disconnect();
								WindowDataFacade.getInstance().setDisconnectedLockFields();
								Status.getInstance().setConnected(false);
								if (((DisconnectionMessage)o).getOnlineUserList() != null) {
//									WindowDataFacade.getJam().getLe().updateOnlineList(((DisconnectionMessage)o).getOnlineUserList());
								}
//								break; //NOT SURE IF THIS LINE IS NEEDED
							} else if (o instanceof BroadCastMessage) {
								BroadCastMessage bm = (BroadCastMessage) o;
								if (!bm.getOwner().equalsIgnoreCase(WindowDataFacade.getInstance().getUserName())) {
									WindowDataFacade.getInstance().getFld_status().setText("[" + bm.getTimestamp() + "]" + " SERVER> " + "Broadcast from " + bm.getOwner());
								} else {
									WindowDataFacade.getInstance().getFld_status().setText("[" + bm.getTimestamp() + "]" + " " + bm.getServresponse());
								}
//								tlog.addMessage(bm.toString());
								if (bm.getOnlineUserList() != null) {
//									WindowDataFacade.getJam().getLe().updateOnlineList(bm.getOnlineUserList());
								}
							}
						} else if (o instanceof ServerException) {
							ServerException se = (ServerException) o;
							WindowDataFacade.getInstance().getFld_status().setText(se.getMessage()); //ERROR MSG
							if (se.isToDisconnect()) {
								stream.getSock().close();
								stream.setSock(null);
								WindowDataFacade.getInstance().setDisconnectedLockFields();
								WindowDataFacade.getInstance().getFld_status().setText(getTimestamp() + " LOCAL> Disconnected");
								Status.getInstance().setConnected(false);
							}
						}
					} else {
						WindowDataFacade.getInstance().getFld_status().setText("LOCAL> Connected but cannot confirm.");
					}
				} catch (ClassNotFoundException e) {
					WindowDataFacade.getInstance().getFld_status().setText("LOCAL> Report this to dev: \"ClassNotFoundException\".");
				} catch (IOException e) {
					Status.getInstance().setConnected(false);
				} finally {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} else {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private String getTimestamp() {
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		String dateFormatted = formatter.format(new Date());
		return "["+dateFormatted+"]" + " ";
	}

	public FXReceiveFromServerThread() {

	}

}
