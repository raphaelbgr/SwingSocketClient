package gui;

import gui.janelas.JanelaMain;
import gui.janelas.JanelaSelectServer;

public class WindowDataFacade {
	
	private static JanelaMain jam = null;
	private static JanelaSelectServer jsv = null;
	
	//SINGLETON BLOCK
	public static WindowDataFacade getInstance() {
		if (instance == null) {
			instance = new WindowDataFacade(jam, jsv);
		}
		return instance;
	}
	private static WindowDataFacade instance;
	
	//SINGLETON CONSTRUCTOR
	private WindowDataFacade(JanelaMain jam, JanelaSelectServer jsv) {
		
	}
	
	public void loadInstance(JanelaMain jam, JanelaSelectServer jsv) {
		WindowDataFacade.jam = jam;
		WindowDataFacade.jsv = jsv;
	}

	public static JanelaMain getJam() {
		return jam;
	}

	public static void setJam(JanelaMain jam) {
		WindowDataFacade.jam = jam;
	}

	public static JanelaSelectServer getJsv() {
		return jsv;
	}

	public static void setJsv(JanelaSelectServer jsv) {
		WindowDataFacade.jsv = jsv;
	}
	
}
