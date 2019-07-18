package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.User;
import tool.MySQLConnect;

public class UserOperator {
	
	public boolean login(User user) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet loginResult = null;
		try {
			connection = MySQLConnect.getConnection();
			String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
			loginResult = preparedStatement.executeQuery();
			if(loginResult.next()) {
				return true;
			} else {
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MySQLConnect.closeDBResource(connection, preparedStatement, loginResult);
		}
		return false;
	}
	
}
