package com.comcast.crm.generic.databaseutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DataBaseUtility {
	Connection conn;

	public void getConnection(String url, String username, String password) throws SQLException {
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
		}
	}

	public void getConnection() throws SQLException {
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sanctoria", "root", "root");
		} catch (Exception e) {
		}
	}

	public ResultSet executeSelectQuary(String quary) throws Throwable {
		ResultSet result = null;
		try {
			Statement stat = conn.createStatement();
			result = stat.executeQuery(quary);
		} catch (Exception e) {
		}
		return result;
	}

	public int executeNonSelectQuary(String quary) throws Throwable {
		int result = 0;
		try {
			Statement con = conn.createStatement();
			result = con.executeUpdate(quary);
		} catch (Exception e) {
		}
		return result;
	}

	public void closeDataBaseConnection() throws SQLException {
		try {
			conn.close();
		} catch (Exception e) {
		}
	}
}
