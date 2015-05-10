package gui.fx.models;

import javafx.beans.property.SimpleStringProperty;

public class MessageDataTableModel {

	private SimpleStringProperty timestamp = new SimpleStringProperty();
    private SimpleStringProperty screenname = new SimpleStringProperty();
    private SimpleStringProperty message = new SimpleStringProperty();
 
    public MessageDataTableModel() {
    	
    }
    
    public MessageDataTableModel(String timestamp, String screenname, String message) {
        this.timestamp = new SimpleStringProperty(timestamp);
        this.screenname = new SimpleStringProperty(screenname);
        this.message = new SimpleStringProperty(message);
    }

	public SimpleStringProperty getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp.set(timestamp);
	}

	public SimpleStringProperty getScreenname() {
		return screenname;
	}

	public void setScreenname(String screenname) {
		this.screenname.set(screenname);
	}

	public SimpleStringProperty getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message.set(message);
	}

}
