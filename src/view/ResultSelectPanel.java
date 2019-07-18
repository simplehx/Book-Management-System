package view;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import model.Book;

public class ResultSelectPanel extends JPanel {
	private List<Book> selectedBookList;
	public List<Book> getSelectedBookList() {
		return selectedBookList;
	}
	public ResultSelectPanel(List<Book> bookList) {
		setLayout(new BorderLayout());
		Object[] columnNames = {"id", "书名", "价格", "作者", "出版社", "详情"};
		
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		
		//不允许编辑
		JTable resultTable = new JTable(model) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		
		//向表中填充数据
		for(Book resultBook : bookList) {
			model.addRow(resultBook.getPropertyArray());
		}
		
		//添加监听器
		ListSelectionModel selectionModel = resultTable.getSelectionModel();
		selectionModel.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent event) {
				int[] rows = resultTable.getSelectedRows();
				selectedBookList = new ArrayList<Book>();
				for(int row : rows) {
					int bookId = Integer.parseInt(String.valueOf(resultTable.getModel().getValueAt(row, 0)));
					Book book = new Book();
					book.setId(bookId);
					selectedBookList.add(book);
				}
				
			}
		});
		add(new JScrollPane(resultTable));
	}
}
