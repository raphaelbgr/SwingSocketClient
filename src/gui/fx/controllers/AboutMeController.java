package gui.fx.controllers;

import gui.fx.WindowDataFacade;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;

public class AboutMeController implements Initializable {

	@FXML
	private Hyperlink about_linkedin_link		= null;
	@FXML
	private Hyperlink about_my_cv_link			= null;
	@FXML
	private Hyperlink about_whatsapp			= null;
	@FXML
	private Hyperlink about_email				= null;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		List<Node> nodes = new ArrayList<Node>();
		nodes.add(about_linkedin_link);
		nodes.add(about_my_cv_link);
		nodes.add(about_whatsapp);
		nodes.add(about_email);
		WindowDataFacade.getInstance().loadMenuItems(nodes);
	}
	
}
