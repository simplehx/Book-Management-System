package model;

import java.util.List;

/**
 *数据库操作结果类
 *boolean status 操作是否成功
 *String resultString 操作结果详情
 *List<Book> resultBookList 界面需要的操作结果
*/

public class BookOperatorResult {
	private boolean status;
	private String resultString;
	private List<Book> resultBookList;
	public BookOperatorResult() {
		super();
	}
	public BookOperatorResult(boolean status, String resultString, List<Book> resultBookList) {
		super();
		this.status = status;
		this.resultString = resultString;
		this.resultBookList = resultBookList;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getResultString() {
		return resultString;
	}
	public void setResultString(String resultString) {
		this.resultString = resultString;
	}
	public List<Book> getResultBookList() {
		return resultBookList;
	}
	public void setResultBookList(List<Book> resultBookList) {
		this.resultBookList = resultBookList;
	}
}
