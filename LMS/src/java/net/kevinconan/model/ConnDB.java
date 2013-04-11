/*
 * To change this template, choose Tools | Templates and open the
 * template in the editor.
 */
package net.kevinconan.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diluka
 */
public class ConnDB {

	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private int execUpdateNum = 0;
	private String driver = "com.mysql.jdbc.Driver";
	private String user = "root";
	private String pw = "4sfyea6z";
	private String url = "jdbc:mysql://localhost:3306/";
	private String db = "librarymanage";

	public void connect() {
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url + db, user, pw);
		} catch (Exception ex) {
			Logger.getLogger(ConnDB.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void setSqlStatement(String sql) {
		try {
			preparedStatement = connection.prepareStatement(sql);
		} catch (SQLException ex) {
			Logger.getLogger(ConnDB.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void setString(int i, String s) {
		try {
			preparedStatement.setString(i, s);
		} catch (SQLException ex) {
			Logger.getLogger(ConnDB.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void execUpdate() {
		try {
			execUpdateNum = preparedStatement.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(ConnDB.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	public void execQuery() {
		try {
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException ex) {
			Logger.getLogger(ConnDB.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public ResultSet getResultSet() {
		return resultSet;
	}

	public int getExecUpdateNum() {
		return execUpdateNum;
	}

	public void close() {
		try {
			if (resultSet != null) {
				if (!resultSet.isClosed()) {
					resultSet.close();
				}
			}
		} catch (SQLException ex) {
			Logger.getLogger(ConnDB.class.getName()).log(Level.SEVERE, null, ex);
		}
		try {
			if (!preparedStatement.isClosed()) {
				preparedStatement.close();
			}
		} catch (SQLException ex) {
			Logger.getLogger(ConnDB.class.getName()).log(Level.SEVERE, null, ex);
		}
		try {
			if (!connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException ex) {
			Logger.getLogger(ConnDB.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setDb(String db) {
		this.db = db;
	}
}
