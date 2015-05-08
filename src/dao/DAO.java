package dao;

import gui.fx.WindowDataFacade;

import java.awt.Dialog;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

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
			System.out.println("passou");
			String query = "INSERT INTO CLIENTS (LOGIN,NAME,PASSWORD,SEX,COLLEGE,INFNETID,COURSE,COURSESTART,EMAIL,WHATSAPP,FACEBOOK,COUNTRY,STATE,CITY)"
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
					+ WindowDataFacade.getInstance().getCityReg()+"')";
//					query = "INSERT INTO CLIENTS (LOGIN,NAME,PASSWORD,SEX,COLLEGE,INFNETID,COURSE,COURSESTART,EMAIL,WHATSAPP,FACEBOOK,COUNTRY,STATE,CITY) VALUES ('raphaelbgr','RaphaeL','tjq5uxt3','Male','INFNET','raphaelb.rocha@al.infnet.edu.br','GEC','2013.2','raphaelbgr@gmail.com','21988856697','fb.com/raphaelbgr','BRA','RJ','Rio de Janeiro')";
					Statement s = c.prepareStatement(query);
					s.execute(query);
					JOptionPane.showMessageDialog(null, "User successfully registered on the database.");
					System.out.println(query);
		} else {
			System.out.println("não passou");
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
}
