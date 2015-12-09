package app.control.dao;

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

import app.ClientMain;

public class DAO {

//	final private String DATABASE_URL = "jdbc:mysql://surfael.sytes.net:3307/chatdb";
	final private String DATABASE_URL = ClientMain.DATABASE_ADDR;
//	final private String DATABASE_KEY = ClientMain.DATABASE_KEY;
	final private String DATABASE_USER = ClientMain.DATABASE_USER;
	final private String DATABASE_PASS = ClientMain.DATABASE_PASS;
	
	Connection c = null;
	
	public void connect() throws SQLException {
		c = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASS);
	}
	
	
//	public boolean registerUser() throws SQLException {
//		boolean result = false;
//		if (WindowDataFacade.getInstance().validateRegFields()) {
//			String query = "INSERT INTO CLIENTS (LOGIN,NAME,PASSWORD,CRYPTPASSWORD,SEX,COLLEGE,INFNETID,COURSE,COURSESTART,EMAIL,WHATSAPP,FACEBOOK,COUNTRY,STATE,CITY,ID,registrationdate)"
//					+ " VALUES ('"+WindowDataFacade.getInstance().getLoginReg()+"','"
//					+ WindowDataFacade.getInstance().getNameReg()+"',"
//					+ "'nopass',"
////					+ "AES_ENCRYPT(\"" + WindowDataFacade.getInstance().getPasswordReg()+"\",'"+ ClientMain.DATABASE_KEY +"'),'"
//					+ codifyPassword(WindowDataFacade.getInstance().getPasswordReg()) +"','"
//					+ WindowDataFacade.getInstance().getSexReg()+"','"
//					+ WindowDataFacade.getInstance().getCollegeRegValue()+"','"
//					+ WindowDataFacade.getInstance().getInfnetMailReg()+"','"
//					+ WindowDataFacade.getInstance().getCourse()+"','"
//					+ WindowDataFacade.getInstance().getCourseStartReg()+"','"
//					+ WindowDataFacade.getInstance().getEmailReg()+"','"
//					+ WindowDataFacade.getInstance().getWhatsappReg()+"','"
//					+ WindowDataFacade.getInstance().getFacebookReg()+"','"
//					+ WindowDataFacade.getInstance().getCountryReg()+"','"
//					+ WindowDataFacade.getInstance().getStateReg()+"','"
//					+ WindowDataFacade.getInstance().getCityReg()+"',"
//					+ generateOwnerID(WindowDataFacade.getInstance().getLoginReg()) +",'"
//					+ new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis()) + "')";
//					System.out.println(query);
//					Statement s = c.prepareStatement(query);
//					result = s.execute(query);
//					WindowDataFacade.getInstance().setBigStatusMsg(getTimestamp() + "SERVER> User successfully registered on the database.");
//		}
//		return result;
//	}
	
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
	
//	public ObservableList<MessageDataTableModel> queryChatHistory(int rowLimit) throws SQLException {
//		final ObservableList<MessageDataTableModel> data = FXCollections.observableArrayList();
//		String query = null;
//		if (rowLimit == 0) {
//			query = "SELECT SERV_REC_TIMESTAMP, OWNERNAME, TEXT FROM MESSAGELOG";
//		} else {
//			query = "SELECT SERV_REC_TIMESTAMP, OWNERNAME, TEXT FROM MESSAGELOG LIMIT " + rowLimit;
//		}
////		System.out.println(query);
//		Statement st = c.createStatement();
//		ResultSet rs = st.executeQuery(query);
//		while(rs.next()) {
//			data.add(new MessageDataTableModel(rs.getString(1),rs.getString(2),rs.getString(3)));
//		}
//		return data;
//	}		
	
	private String getTimestamp() {
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		String dateFormatted = formatter.format(new Date());
		return "["+dateFormatted+"]" + " ";
	}
	
	public synchronized void disconnect() throws SQLException {
		c.close();
	}
	
}
