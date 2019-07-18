package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import control.BookOperator;
import model.Book;
import model.BookOperatorResult;
import tool.UITool;

public class SearchBookPanel extends SearchPanel {

	public SearchBookPanel(Component parentComponent, JFrame owner) {
		super("img/search.png", "查找图书");
		
		JButton submitButton = new JButton("搜索");
		submitButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
		add(submitButton, new GBC(1, 5, 4, 1).setFill(GBC.HORIZONTAL).setWeight(100, 0));
		
		//为搜索按钮添加监听器
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				//对各字段进行非空检测
				Map<String, String> fieldMap = new LinkedHashMap<String, String>();
				fieldMap.put("书名", getBookNameField().getText().trim());
				fieldMap.put("作者", getBookAuthorField().getText().trim());
				fieldMap.put("出版社", getBookPressField().getText().trim());
				
				String fieldCheckResult = UITool.searchFieldCheck(fieldMap);
				if(fieldCheckResult.equals("success")) {
					BookOperator dbOperator = new BookOperator();
					Book book = new Book();
					book.setName(getBookNameField().getText().trim());
					book.setAuthor(getBookAuthorField().getText().trim());
					book.setPress(getBookPressField().getText().trim());
					BookOperatorResult searchResult = dbOperator.searchBook(book);
					List<Book> searchBookResult = searchResult.getResultBookList();
					if(searchBookResult.isEmpty()) {
						JOptionPane.showMessageDialog(parentComponent, "没找到您输入的图书");
					} else {
						JDialog searchResultDialog = new JDialog(owner, "查询结果", true);
						UITool.setCentralLocation(owner, searchResultDialog, 500, 600);
						searchResultDialog.setLayout(new BorderLayout());
						JPanel searchResultPanel = new ResultSelectPanel(searchBookResult);
						searchResultDialog.add(searchResultPanel);
						searchResultDialog.setSize(500, 600);
						searchResultDialog.setVisible(true);
					}
				} else {
					JOptionPane.showMessageDialog(parentComponent, "请至少输入一个条件");
				}
			}
		});
	}
}
