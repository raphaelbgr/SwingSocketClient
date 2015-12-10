package app.control.services;

import java.io.EOFException;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import app.ClientMain;
import app.view.WindowDataFacade;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.sytes.surfael.api.control.sync.ClientStream;
import net.sytes.surfael.api.control.sync.Status;
import net.sytes.surfael.api.model.clients.Client;
import net.sytes.surfael.api.model.exceptions.ServerException;
import net.sytes.surfael.api.model.messages.BroadCastMessage;
import net.sytes.surfael.api.model.messages.DisconnectionMessage;
import net.sytes.surfael.api.model.messages.History;
import net.sytes.surfael.api.model.messages.Message;
import net.sytes.surfael.api.model.messages.NormalMessage;
import net.sytes.surfael.api.model.messages.RegistrationMessage;
import net.sytes.surfael.api.model.messages.ServerMessage;

public class FXReceiveFromServerThread implements Runnable {

	private ClientStream stream = ClientStream.getInstance();

	@Override
	public void run() {
		while (true) {
			if ((stream.getSock() != null) && (stream.getSock().isConnected() == true)) {
				try {
					ClientMain.receiveRdy = true;
					final Object o = stream.receiveMessage();
					if (o != null) {
						if (o instanceof Client) {
							
						} else if (o instanceof Message) {
							if (o instanceof ServerMessage) {
								final ServerMessage sm = (ServerMessage) o;
								if (sm.getServresponse() != null) {
									if (sm.isConnect()) {
										WindowDataFacade.getInstance().setConnectedLockFields();
										WindowDataFacade.getInstance().setFielsStatusMessage(sm.toString());
									}
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
								if (!nm.getOwnerLogin().equalsIgnoreCase(WindowDataFacade.getInstance().getLogin())) {
									Platform.runLater(new Runnable() {
										@Override
										public void run() {
											WindowDataFacade.getInstance().getFld_status().setText("[" + nm.getTimestamp() + "]" + " SERVER> " + "Broadcast from " + nm.getOwnerName());
											WindowDataFacade.getInstance().requestFocus();
											WindowDataFacade.getInstance().playSound("/sounds-854-quiet-knock.mp3");
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
											} else if (((Message) o).getOwnerLogin().equalsIgnoreCase(WindowDataFacade.getInstance().getLogin())) {
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
							} else if (o instanceof BroadCastMessage) {
								final BroadCastMessage bm = (BroadCastMessage) o;
								WindowDataFacade.getInstance().addChatMessage(bm);
								if (bm.isConnect() && !bm.getOwnerLogin().equalsIgnoreCase(WindowDataFacade.getInstance().getLogin())) {
									WindowDataFacade.getInstance().playSound("/sounds-847-office-2.mp3");
								} else if (bm.isDisconnect() && !bm.getOwnerLogin().equalsIgnoreCase(WindowDataFacade.getInstance().getLogin())) {
									WindowDataFacade.getInstance().playSound("/sounds-896-all-of-a-sudden.mp3");
									
								}
								if (bm != null) {
									if (!bm.getOwnerLogin().equalsIgnoreCase(WindowDataFacade.getInstance().getLogin())) {
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
									}	
								});
							}
						} else if (o instanceof History) {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									WindowDataFacade.getInstance().populateHistoryTable((History)o);
								}	
							});
						}
					} else {
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								WindowDataFacade.getInstance().getFld_status().setText("LOCAL> Connected but cannot confirm.");
							}	
						});
					}
				} catch (StreamCorruptedException e) {
					e.printStackTrace();
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							if (Status.getInstance().isConnected()) {
								WindowDataFacade.getInstance().setBigStatusMsg(getTimestamp() + "LOCAL> Connection to the server was aborted.");
							}
							WindowDataFacade.getInstance().setDisconnectedLockFields();	
						}	
					});
					Status.getInstance().setConnected(false);
					e.printStackTrace();
					break;
				} catch (EOFException e) {
					e.printStackTrace();
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							WindowDataFacade.getInstance().setBigStatusMsg(getTimestamp() + "LOCAL> The server broke the connection.");
							WindowDataFacade.getInstance().setDisconnectedLockFields();
						}	
					});
					Status.getInstance().setConnected(false);
					e.printStackTrace();
					break;
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
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
					e.printStackTrace();
					break;
				} catch (Throwable e) {
					WindowDataFacade.getInstance().setDisconnectedLockFields();
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
