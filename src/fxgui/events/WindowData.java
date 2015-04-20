package fxgui.events;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import sendable.Client;
import sendable.Message;

public class WindowData {

	private List<Node> nodes = new ArrayList<Node>();
	
	private Integer port = null;
	
	private String server = null;
	private String userName = null;
	private String password = null;
	
	private Message message = null;
	private Client client = null;

	public WindowData() {
		
	}
	
	public WindowData(String server, Integer port, String userName, String password) {
		this.server = server;
		this.port = port;
		this.userName = userName;
		this.password = password;
	}
	
	public WindowData(Message message) {
		this.message = message;
	}
	
	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public void addNode(Node node) {
		this.nodes.add(node);
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
}
