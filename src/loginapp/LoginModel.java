package loginapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbUtil.dbConnection;

public class LoginModel {

	Connection connection;
	
	public LoginModel() {
		try {
			this.connection = dbConnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (this.connection == null) {
			System.exit(1);
		}
	}
	public boolean isDatabaseConnected() {
		return this.connection != null;
	}
	
	public boolean isLogin(String user, String pass, String opt) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM login WHERE username=? AND password=? AND division=?;";
		
		try {
			ps = this.connection.prepareStatement(sql);
			ps.setString(1, user);
			ps.setString(2, pass);
			ps.setString(3, opt);
			
			rs = ps.executeQuery(sql);
			
			boolean bolll;
			
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
			
		} catch (SQLException e) {
			return false;
		} finally {
			ps.close();
			rs.close();
		}
	}

}
