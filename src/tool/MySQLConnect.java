package tool;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;


public class MySQLConnect {
	private static String JDBC_DRIVER = null;  
	private static String DB_URL = null;
	private static String USERNAME = null;
	private static String PASSWORD = null;
	
	/**
	 * 静态代码块，加载驱动
	 * */
	static {
		try {
			File file = new File("");
			Properties prop = new Properties();
			FileReader fr = new FileReader(file.getAbsolutePath() + "/db.properties");
			prop.load(fr);
			JDBC_DRIVER = prop.getProperty("JDBC_DRIVER");
			DB_URL = prop.getProperty("DB_URL");
			USERNAME = prop.getProperty("USERNAME");
			PASSWORD = prop.getProperty("PASSWORD");
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("驱动加载失败");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("配置文件加载失败");
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取数据库连接
	 * */
	public static Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	/**
	 * 释放资源，不包括ResultSet
	 * */
	public static void closeDBResource(Connection connection , PreparedStatement preparedStatement) {
		try {
			if(preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if(connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 释放资源，包括ResultSet
	 * */
	public static void closeDBResource(Connection connection , PreparedStatement preparedStatement, ResultSet resultSet) {
		try {
			if(preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if(connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if(resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}