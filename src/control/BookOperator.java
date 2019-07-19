package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.Book;
import model.BookOperatorResult;
import tool.MySQLConnect;

public class BookOperator {
	/**
	 * 添加图书
	 * */
	public BookOperatorResult addBook(Book book) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		BookOperatorResult result = new BookOperatorResult();
		try {
			connection = MySQLConnect.getConnection();
			String sql="INSERT INTO book (name, price, author, press, details) VALUES ( ?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, book.getName());
			preparedStatement.setDouble(2, book.getPrice());
			preparedStatement.setString(3, book.getAuthor());
			preparedStatement.setString(4, book.getPress());
			preparedStatement.setString(5, book.getDetails());
			int status = preparedStatement.executeUpdate();
			if(status == 0) {
				result.setStatus(false);
				result.setResultString("插入失败");
			} else {
				result.setStatus(true);
				result.setResultString("插入成功");
			}
		} catch (SQLIntegrityConstraintViolationException e) {
			//几乎不会发生，主键id自增由MySQL维护
			result.setStatus(false);
			result.setResultString("主键已存在");
		} catch (SQLException e) {
			result.setStatus(false);
			result.setResultString("数据库异常");
			e.printStackTrace();
		} finally {
			//释放资源
			MySQLConnect.closeDBResource(connection, preparedStatement);
		}
		return result;
	}
	
	/**
	 * 修改图书
	 * */
	public BookOperatorResult changeBook(Map<String, Book> changeBookMap) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		BookOperatorResult result = new BookOperatorResult();
		List<Book> errorResultList = new ArrayList<Book>();
		
		try {
			connection = MySQLConnect.getConnection();
			
			for(Book book : changeBookMap.values()) {
				String sql = "UPDATE book SET name = ?, price = ?, author = ?, press = ?, details = ? WHERE id = ?";
				preparedStatement = connection.prepareStatement(sql);
				
				preparedStatement.setString(1, book.getName());
				preparedStatement.setDouble(2, book.getPrice());
				preparedStatement.setString(3, book.getAuthor());
				preparedStatement.setString(4, book.getPress());
				preparedStatement.setString(5, book.getDetails());
				preparedStatement.setInt(6, book.getId());
				int status = preparedStatement.executeUpdate();
				if(status == 0) {
					result.setStatus(false);
					result.setResultString("修改失败");
					errorResultList.add(book);
				} else {
					result.setStatus(true);
					result.setResultString("修改成功");
				}
			}
		} catch (SQLException e) {
			result.setStatus(false);
			result.setResultString("数据库异常");
			e.printStackTrace();
		} finally {
			result.setResultBookList(errorResultList);
			//释放资源
			MySQLConnect.closeDBResource(connection, preparedStatement);
		}
		return result;
	}
	
	/**
	 * 删除图书
	 * */
	public BookOperatorResult deleteBook(List<Book> deleteBookList) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		BookOperatorResult result = new BookOperatorResult();
		try {
			connection = MySQLConnect.getConnection();
			for(Book book : deleteBookList) {
				String sql = "DELETE FROM book WHERE id = ?";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, book.getId());
				int status =preparedStatement.executeUpdate();
				if(status == 0) {
					result.setStatus(false);
					result.setResultString("删除失败");
				}else {
					result.setStatus(true);
					result.setResultString("删除成功");
				}
			}
		} catch (SQLException e) {
			result.setStatus(true);
			result.setResultString("数据库异常");
			e.printStackTrace();
		} finally {
			//释放资源
			MySQLConnect.closeDBResource(connection, preparedStatement);
		}
		return result;
	}
	
	/**
	 * 查找图书
	 * */
	public BookOperatorResult searchBook(Book searchBook) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet searchResult = null;
		BookOperatorResult result = new BookOperatorResult();
		List<Book> resultList = new ArrayList<Book>();
		try {
			connection = MySQLConnect.getConnection();
			String sql = "SELECT * FROM book WHERE";
			boolean firstFlag = true;
			if(searchBook.getName() != null && !searchBook.getName().equals("")) {
				if(!firstFlag) {
					sql += " AND ";
				}
				sql += " name LIKE '%"+ searchBook.getName() + "%'";
				firstFlag = false;
			}
			if(searchBook.getAuthor() != null && !searchBook.getAuthor().equals("")) {
				if(!firstFlag) {
					sql += " AND ";
				}
				sql += " author LIKE '%"+ searchBook.getAuthor() + "%'";
				firstFlag = false;
			}
			if(searchBook.getPress() != null && !searchBook.getPress().equals("")) {
				if(!firstFlag) {
					sql += " AND ";
				}
				sql += " press LIKE '%"+ searchBook.getPress() + "%'";
				firstFlag = false;
			}
			preparedStatement = connection.prepareStatement(sql);
			searchResult = preparedStatement.executeQuery();
			while(searchResult.next()) {
				Book book = new Book();
				book.setId(searchResult.getInt("id"));
				book.setName(searchResult.getString("name"));
				book.setPrice(searchResult.getDouble("price"));
				book.setPress(searchResult.getString("press"));
				book.setAuthor(searchResult.getString("author"));
				book.setDetails(searchResult.getString("details"));
				resultList.add(book);
			}
			result.setStatus(true);
			result.setResultString("查询成功");
			result.setResultBookList(resultList);
		} catch (SQLException e) {
			result.setStatus(false);
			result.setResultString("数据库异常");
			result.setResultBookList(resultList);
			e.printStackTrace();
		} finally {
			//释放资源
			MySQLConnect.closeDBResource(connection, preparedStatement, searchResult);
		}
		return result;
	}
}