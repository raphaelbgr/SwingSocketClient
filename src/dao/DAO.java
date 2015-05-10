package dao;

import gui.fx.WindowDataFacade;
import gui.fx.models.MessageDataTableModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DAO {

	final private String DATABASE_URL = "jdbc:mysql://surfael.sytes.net:3307/chatdb";
	
	Connection c = null;
	
	public void connect() throws SQLException {
		c = DriverManager.getConnection(DATABASE_URL, "chatclient", "nopass");
//		System.out.println("funcionou");
		//TODO
	}
	
	public void registerUser() throws SQLException {
		if (WindowDataFacade.getInstance().validateRegFields()) {
//			System.out.println("passou");
			String query = "INSERT INTO CLIENTS (LOGIN,NAME,PASSWORD,SEX,COLLEGE,INFNETID,COURSE,COURSESTART,EMAIL,WHATSAPP,FACEBOOK,COUNTRY,STATE,CITY,ID)"
					+ " VALUES ('"+WindowDataFacade.getInstance().getLoginReg()+"','"
					+ WindowDataFacade.getInstance().getNameReg()+"','"
					+ WindowDataFacade.getInstance().getPasswordReg()+"','"
					+ WindowDataFacade.getInstance().getSexReg()+"','"
					+ WindowDataFacade.getInstance().getCollegeReg()+"','"
					+ WindowDataFacade.getInstance().getInfnetMailReg()+"','"
					+ WindowDataFacade.getInstance().getCourse()+"','"
					+ WindowDataFacade.getInstance().getCourseStartReg()+"','"
					+ WindowDataFacade.getInstance().getEmailReg()+"','"
					+ WindowDataFacade.getInstance().getWhatsappReg()+"','"
					+ WindowDataFacade.getInstance().getFacebookReg()+"','"
					+ WindowDataFacade.getInstance().getCountryReg()+"','"
					+ WindowDataFacade.getInstance().getStateReg()+"','"
					+ WindowDataFacade.getInstance().getCityReg()+"',"
					+ generateOwnerID(WindowDataFacade.getInstance().getLoginReg()) +")";
//					query = "INSERT INTO CLIENTS (LOGIN,NAME,PASSWORD,SEX,COLLEGE,INFNETID,COURSE,COURSESTART,EMAIL,WHATSAPP,FACEBOOK,COUNTRY,STATE,CITY) VALUES ('raphaelbgr','RaphaeL','tjq5uxt3','Male','INFNET','raphaelb.rocha@al.infnet.edu.br','GEC','2013.2','raphaelbgr@gmail.com','21988856697','fb.com/raphaelbgr','BRA','RJ','Rio de Janeiro')";
					Statement s = c.prepareStatement(query);
					s.execute(query);
//					JOptionPane.showMessageDialog(null, "User successfully registered on the database.");
					WindowDataFacade.getInstance().setBigStatusMsg(getTimestamp() + "SERVER> User successfully registered on the database.");
//					System.out.println(query);
		} else {
//			System.out.println("não passou");
		}
	}

	public List<String> queryCourses() throws SQLException {
		String query = "SELECT SHORTNAME FROM COURSES";
		Statement s = c.prepareStatement(query);
		ResultSet rs = s.executeQuery(query);
		List<String> list = new ArrayList<String>();
		while (rs.next()) {
			list.add(rs.getString("SHORTNAME"));
		}
//		list.add("<END>");
		return list;
	}
	
	public List<String> queryColleges() throws SQLException {
		String query = "SELECT NAME FROM COLLEGES";
		Statement s = c.prepareStatement(query);
		ResultSet rs = s.executeQuery(query);
		List<String> list = new ArrayList<String>();
		while (rs.next()) {
			list.add(rs.getString("NAME"));
		}
//		list.add("<END>");
		return list;
	}
	
	public List<String> queryLogins() throws SQLException {
		String query = "SELECT LOGIN FROM CLIENTS";
		Statement s = c.prepareStatement(query);
		ResultSet rs = s.executeQuery(query);
		List<String> list = new ArrayList<String>();
		while (rs.next()) {
			list.add(rs.getString("LOGIN"));
		}
//		list.add("<END>");
		return list;
	}
	
	public int generateOwnerID(String login) throws SQLException {
		String query = "SELECT DISTINCT COUNT(LOGIN) FROM CLIENTS AS COUNT";
		Statement st = c.createStatement();
		ResultSet rs = st.executeQuery(query);
		int id = 0;
		while(rs.next()) {
			id =  rs.getInt("COUNT(LOGIN)");
		}
		return id + 1;
	}
	
	public ObservableList<MessageDataTableModel> queryChatHistory(int rowLimit) throws SQLException {
		final ObservableList<MessageDataTableModel> data = FXCollections.observableArrayList();
		String query = null;
		if (rowLimit == 0) {
			query = "SELECT SERV_REC_TIMESTAMP, OWNERNAME, TEXT FROM MESSAGELOG";
		} else {
			query = "SELECT SERV_REC_TIMESTAMP, OWNERNAME, TEXT FROM MESSAGELOG LIMIT " + rowLimit;
		}
		
		Statement st = c.createStatement();
		ResultSet rs = st.executeQuery(query);
		while(rs.next()) {
			data.add(new MessageDataTableModel(rs.getString(1),rs.getString(2),rs.getString(3)));
		}
		return data;
	}		
	
	private String getTimestamp() {
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		String dateFormatted = formatter.format(new Date());
		return "["+dateFormatted+"]" + " ";
	}
	
	public synchronized void disconnect() throws SQLException {
		c.close();
	}
	
}
