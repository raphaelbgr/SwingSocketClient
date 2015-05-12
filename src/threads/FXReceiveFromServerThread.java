package threads;

import exceptions.ServerException;
import gui.fx.WindowDataFacade;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sendable.BroadCastMessage;
import sendable.Client;
import sendable.DisconnectionMessage;
import sendable.Message;
import sendable.NormalMessage;
import sendable.RegistrationMessage;
import sendable.ServerMessage;
import sync.ClientStream;
import clientmain.ClientMain;
import clientmain.Status;

public class FXReceiveFromServerThread implements Runnable {

	private ClientStream stream = ClientStream.getInstance();
	private Client cl = null;

	@Override
	public void run() {

		while (true) {
			if ((stream.getSock() != null) && (stream.getSock().isConnected() == true)) {
				try {
					ClientMain.receiveRdy = true;
					final Object o = stream.receiveMessage();
					if (o != null) {
						if (o instanceof Client) {
							cl = (Client)o;
						} else if (o instanceof Message) {
							if (o instanceof ServerMessage) {
								final ServerMessage sm = (ServerMessage) o;
								if (sm.getServresponse() != null) {
									Platform.runLater(new Runnable() {
										@Override
										public void run() {
											WindowDataFacade.getInstance().getFld_status().setText(sm.toString());
											if (sm.isConnect()) {
												WindowDataFacade.getInstance().setConnectedLockFields();
												WindowDataFacade.getInstance().getFld_status().setText(sm.toString());
											}
										}	
									});
								}
								if (sm.getOnlineUserList() != null) {
									ObservableList<String> items = FXCollections.observableArrayList();;
									for (String s : sm.getOnlineUserList()) {
										items.add(s);
									}
									WindowDataFacade.getInstance().setOnlineUserList(items); 
								}
							} else if (o instanceof NormalMessage) {
								final NormalMessage nm = (NormalMessage) o;
								WindowDataFacade.getInstance().addChatMessage(nm);
								if (!nm.getOwnerLogin().equalsIgnoreCase(WindowDataFacade.getInstance().getComboLogin())) {
									Platform.runLater(new Runnable() {
										@Override
										public void run() {
											WindowDataFacade.getInstance().getFld_status().setText("[" + nm.getTimestamp() + "]" + " SERVER> " + "Broadcast from " + nm.getOwnerName());
										}	
									});
								} else {
									Platform.runLater(new Runnable() {
										@Override
										public void run() {
											WindowDataFacade.getInstance().getFld_status().setText("[" + nm.getTimestamp() + "]" + " " + nm.getServresponse());
										}	
									});
								}
								if (nm.getOnlineUserList() != null) {
									ObservableList<String> items = FXCollections.observableArrayList();;
									for (String s : nm.getOnlineUserList()) {
										items.add(s);
									}
									WindowDataFacade.getInstance().setOnlineUserList(items);
								}
							} else if (o instanceof DisconnectionMessage) {
								if (((Message) o).isDisconnect()) {
									Platform.runLater(new Runnable() {
										@Override
										public void run() {
											WindowDataFacade.getInstance().setDisconnectedLockFields();
										}
									});
									break;
								} else {
									Platform.runLater(new Runnable() {
										@Override
										public void run() {
											if (((Message) o).isError()) {
												WindowDataFacade.getInstance().setBigStatusMsg(getTimestamp() + "SERVER> " + ((Message) o).getOwnerName() + "Had a connection problem.");
											} else if (((Message) o).getOwnerLogin().equalsIgnoreCase(WindowDataFacade.getInstance().getComboLogin())) {
												WindowDataFacade.getInstance().setBigStatusMsg(getTimestamp() + "SERVER> " + "Disconnected.");
											} else {
												WindowDataFacade.getInstance().setBigStatusMsg(getTimestamp() + "SERVER> " + ((Message) o).getOwnerName() + "Disconnected.");
											}

										}	
									});
								}
								if (((DisconnectionMessage)o).getOnlineUserList() != null) {
									if (((DisconnectionMessage)o).getOnlineUserList() != null) {
										ObservableList<String> items = FXCollections.observableArrayList();;
										for (String s : ((DisconnectionMessage)o).getOnlineUserList()) {
											items.add(s);
										}
										WindowDataFacade.getInstance().setOnlineUserList(items); 
									}
								}
//								break; //NOT SURE IF THIS LINE IS NEEDED
							} else if (o instanceof BroadCastMessage) {
								final BroadCastMessage bm = (BroadCastMessage) o;
								WindowDataFacade.getInstance().addChatMessage(bm);
								if (bm != null) {
									if (!bm.getOwnerLogin().equalsIgnoreCase(WindowDataFacade.getInstance().getComboLogin())) {
										Platform.runLater(new Runnable() {
											@Override
											public void run() {
												WindowDataFacade.getInstance().getFld_status().setText("[" + bm.getTimestamp() + "]" + " SERVER> " + "Broadcast from " + bm.getOwnerName());
											}	
										});
									} else {
										Platform.runLater(new Runnable() {
											@Override
											public void run() {
												WindowDataFacade.getInstance().getFld_status().setText("[" + bm.getTimestamp() + "]" + " " + bm.getServresponse());
											}	
										});
									}
								}
//								WindowDataFacade.getInstance().addChatMessage(bm);
//								tlog.addMessage(bm.toString());
								if (bm.getOnlineUserList() != null) {
									if (bm.getOnlineUserList() != null) {
										ObservableList<String> items = FXCollections.observableArrayList();;
										for (String s : bm.getOnlineUserList()) {
											items.add(s);
										}
										WindowDataFacade.getInstance().setOnlineUserList(items); 
									}
								}
							} else if (o instanceof RegistrationMessage) {
								ClientMain.compKey = true;
								RegistrationMessage rm = (RegistrationMessage)o;
								ClientMain.DATABASE_ADDR = rm.getDbAddr();
								ClientMain.DATABASE_KEY = rm.getDbCryptKey();
								ClientMain.DATABASE_PASS = rm.getDbPass();
								ClientMain.DATABASE_USER = rm.getDbUser();

								WindowDataFacade.getInstance().createConnectedWorker();
								WindowDataFacade.getInstance().setBigStatusMsg(getTimestamp() + "LOCAL> " + "Got server database keys.");
								
								Platform.runLater(new Runnable() {
									@Override
									public void run() {
										WindowDataFacade.getInstance().getFld_status().setText(getTimestamp() + "SERVER> Received database information from server.");
									}
								});
								break;
							}
						} else if (o instanceof ServerException) {
							final ServerException se = (ServerException) o;
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									WindowDataFacade.getInstance().getFld_status().setText(se.getMessage()); //ERROR MSG
								}	
							});
							if (se.isToDisconnect()) {
								stream.getSock().close();
								stream.setSock(null);
								Platform.runLater(new Runnable() {
									@Override
									public void run() {
										WindowDataFacade.getInstance().setDisconnectedLockFields();
										Status.getInstance().setConnected(false);
									}	
								});
							}
						}
					} else {
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								WindowDataFacade.getInstance().getFld_status().setText("LOCAL> Connected but cannot confirm.");
							}	
						});
					}
				} catch (ClassNotFoundException e) {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							WindowDataFacade.getInstance().getFld_status().setText(getTimestamp() + "LOCAL> Report this to dev: \"ClassNotFoundException\".");
						}	
					});
				} catch (IOException e) {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							if (Status.getInstance().isConnected()) {
								WindowDataFacade.getInstance().setBigStatusMsg(getTimestamp() + "LOCAL> The server broke the connection.");
							}
							WindowDataFacade.getInstance().setDisconnectedLockFields();								
						}	
					});
					Status.getInstance().setConnected(false);
					break;
				} finally {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (!Status.getInstance().isConnected()) {
						break;
					}
				}
			} else {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (!Status.getInstance().isConnected()) {
					break;
				}
			}
		}
	}

	private String getTimestamp() {
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		String dateFormatted = formatter.format(new Date());
		return "["+dateFormatted+"]" + " ";
	}
}
