package sendable;

import java.util.ArrayList;
import java.util.List;

public class LocalMessageCenter {
	private List<Message> mList = null;
	
	public LocalMessageCenter(ArrayList<Message> mList) {
		this.mList = mList;
	}
	
	public void pushMessage(Message m){
		this.mList.add(m);
	}
	
	public Message pullMessage() {
		//((ArrayList) mList).//PAREI AQUI
		return null;
	}
}
