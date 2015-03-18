package fxgui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FxManager {

	private Stage mainStage;

	public void assembleStage() {
		mainStage = new Stage();
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/fxgui.fxml"));
			Scene scene = new Scene(root);  
	        mainStage.setScene(scene);
	        mainStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}      
	}
	
	public Stage getMainStage() {
		return mainStage;
	}
	
}
