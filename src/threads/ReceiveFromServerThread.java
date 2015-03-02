package threads;

import exceptions.ServerException;
import gui.WindowDataFacade;
import gui.janelas.JanelaMain;
import gui.updatelogs.LocalLogUpdater;
import gui.updatelogs.ServerLogUpdater;
import gui.updatelogs.TextLog;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import sendable.BroadCastMessage;
import sendable.DisconnectionMessage;
import sendable.Message;
import sendable.NormalMessage;
import sendable.ServerMessage;
import serverinteraction.Disconnect;
import sync.ClientStream;
import clientmain.Status;

public class ReceiveFromServerThread implements Runnable {

	private ClientStream stream = ClientStream.getInstance();
	private LocalLogUpdater localLog = null;
	private ServerLogUpdater serverLog = WindowDataFacade.getJam().getServerConnectionLog();
	private TextLog tlog = WindowDataFacade.getJam().getMsg_list();

	//	private void printClientList(Set<Client> clist) {
	//		for (Client client : clist) {
	//			System.out.println(client.getName());
	//		}
	//	}

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
									serverLog.setGreenMessage(sm.toString());
								}
								if (sm.getOnlineUserList() != null) {
									WindowDataFacade.getJam().getLe().updateOnlineList(sm.getOnlineUserList());
								}
							} else if (o instanceof NormalMessage) {
								NormalMessage nm = (NormalMessage) o;
								if (!nm.getOwner().equalsIgnoreCase(WindowDataFacade.getJsv().getNameFieldText())) {
									serverLog.setGreenMessage("[" + nm.getTimestamp() + "]" + " SERVER> " + "Broadcast from " + nm.getOwner());
								} else {
									serverLog.setGreenMessage("[" + nm.getTimestamp() + "]" + " " + nm.getServresponse());
								}
								tlog.addMessage(nm.toString());
								if (nm.getOnlineUserList() != null) {
									WindowDataFacade.getJam().getLe().updateOnlineList(nm.getOnlineUserList());
								}
							} else if (o instanceof DisconnectionMessage) {
								new Disconnect();
								WindowDataFacade.getJsv().unlockFields();
								WindowDataFacade.getJam().getJbt_send().setEnabled(false);
								WindowDataFacade.getJam().getJbt_Disconn().setEnabled(false);
								WindowDataFacade.getJam().getJbt_Connect().setEnabled(true);
								Status.getInstance().setConnected(false);
								if (((DisconnectionMessage)o).getOnlineUserList() != null) {
									WindowDataFacade.getJam().getLe().updateOnlineList(((DisconnectionMessage)o).getOnlineUserList());
								}
							} else if (o instanceof BroadCastMessage) {
								BroadCastMessage bm = (BroadCastMessage) o;
								if (!bm.getOwner().equalsIgnoreCase(WindowDataFacade.getJsv().getNameFieldText())) {
									serverLog.setGreenMessage("[" + bm.getTimestamp() + "]" + " SERVER> " + "Broadcast from " + bm.getOwner());
								} else {
									serverLog.setGreenMessage("[" + bm.getTimestamp() + "]" + " " + bm.getServresponse());
								}
								tlog.addMessage(bm.toString());
								if (bm.getOnlineUserList() != null) {
									WindowDataFacade.getJam().getLe().updateOnlineList(bm.getOnlineUserList());
								}
							}
						} else if (o instanceof ServerException) {
							ServerException se = (ServerException) o;
							serverLog.setErrorMessage(se.getMessage());
							if (se.isToDisconnect()) {
								stream.getSock().close();
								stream.setSock(null);
								WindowDataFacade.getJam().getJbt_Disconn().setEnabled(false);
								WindowDataFacade.getJam().getJbt_Connect().setEnabled(true);
								WindowDataFacade.getJam().getJbt_send().setEnabled(false);
								WindowDataFacade.getJam().getLocalConnectionLog().setGreyMessage(getTimestamp() + " LOCAL> Disconnected");
								WindowDataFacade.getJsv().unlockFields();
								Status.getInstance().setConnected(false);
							}
						}
					} else {
						localLog.setGreyMessage("LOCAL> Connected but cannot confirm.");
					}
				} catch (ClassNotFoundException e) {
					localLog.setErrorMessage("LOCAL> Report this to dev: \"ClassNotFoundException\".");
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

	public ReceiveFromServerThread(JanelaMain jam) {
		this.localLog = jam.getLocalConnectionLog();
	}

}
