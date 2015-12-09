package api;

import api.model.clients.Client;
import api.model.exceptions.ServerException;
import api.model.messages.DisconnectionMessage;
import api.model.messages.NormalMessage;
import api.model.messages.ServerMessage;

public interface ApiReceiveInterface {
	
	public void onReceive(Object o);

	public void onReceiveNormalMessage(NormalMessage o);

	public void onReceiveDisconnectionMessage(DisconnectionMessage o);

	public void onReceiveServerMessage(ServerMessage o);

	public void onReceiveClient(Client o);
	
	public void onReceiveServerException(ServerException o);
}
