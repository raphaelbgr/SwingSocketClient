package app.view.models;

import javafx.beans.property.SimpleStringProperty;

public class MessageDataTableModel {

	private SimpleStringProperty id 			= new SimpleStringProperty();
	private SimpleStringProperty timestamp 		= new SimpleStringProperty();
    private SimpleStringProperty screenname 	= new SimpleStringProperty();
    private SimpleStringProperty message 		= new SimpleStringProperty();
 
    public MessageDataTableModel() {
    	
    }
    
    public MessageDataTableModel(String id, String timestamp, String screenname, String message) {
    	this.id 			= new SimpleStringProperty(id);
        this.timestamp 		= new SimpleStringProperty(timestamp);
        this.screenname 	= new SimpleStringProperty(screenname);
        this.message 		= new SimpleStringProperty(message);
    }

    public String getId() {
		return id.getValue();
	}

	public void setId(String id) {
		this.id.set(id);
	}
    
	public String getTimestamp() {
		return timestamp.getValue();
	}

	public void setTimestamp(String timestamp) {
		this.timestamp.set(timestamp);
	}

	public String getScreenname() {
		return screenname.getValue();
	}

	public void setScreenname(String screenname) {
		this.screenname.set(screenname);
	}

	public String getMessage() {
		return message.getValue();
	}

	public void setMessage(String message) {
		this.message.set(message);
	}

}
