package view;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import model.Book;

/**
 * 结果修改面板
 * */
public class ResultChangePanel extends JPanel {
	private int selectedBookId;
	private Map<String, Book> changedBookMap;
	
	public int getSelectedBookId() {
		return selectedBookId;
	}
	public Map<String, Book> getChangedBookMap() {
		return changedBookMap;
	}

	public ResultChangePanel(List<Book> bookList) {
		changedBookMap = new HashMap<String, Book>();
		setLayout(new BorderLayout());
		//表头
		Object[] columnNames = {"id", "书名", "价格", "作者", "出版社", "详情"};
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		JTable resultTable = new JTable(model);
		//填装数据
		for(Book resultBook : bookList) {
			model.addRow(resultBook.getPropertyArray());
		}
		ListSelectionModel selectionModel = resultTable.getSelectionModel();
		//不允许多行选中
		selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		selectionModel.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent event) {
				int rowIndex = resultTable.getSelectedRow();
				selectedBookId = Integer.parseInt(String.valueOf(resultTable.getModel().getValueAt(rowIndex, 0)));
			}
		});
		
		model.addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent event) {
				Book book = new Book();
				//最后被修改的行
				int lastChangedRow = event.getLastRow();
				book.setId(Integer.parseInt(String.valueOf(model.getValueAt(lastChangedRow, 0))));
				book.setName(String.valueOf(model.getValueAt(lastChangedRow, 1)));
				book.setPrice(Double.valueOf(String.valueOf(model.getValueAt(lastChangedRow, 2))));
				book.setAuthor(String.valueOf(model.getValueAt(lastChangedRow, 3)));
				book.setPress(String.valueOf(model.getValueAt(lastChangedRow, 4)));
				book.setDetails(String.valueOf(model.getValueAt(lastChangedRow, 5)));
				
				//利用key唯一的特性
				changedBookMap.put(String.valueOf(resultTable.getModel().getValueAt(lastChangedRow, 0)), book);
			}
		});
		
		add(new JScrollPane(resultTable),BorderLayout.NORTH);
	}
}
