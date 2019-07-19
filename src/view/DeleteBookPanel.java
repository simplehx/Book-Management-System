package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import control.BookOperator;
import model.Book;
import model.BookOperatorResult;
import tool.UITool;

public class DeleteBookPanel extends SearchPanel {
	
	public DeleteBookPanel(Component parentComponent, JFrame owner) {
		super("img/delete.png", "删除图书");
		
		JButton submitButton = new JButton("删除");
		submitButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.red));
		add(submitButton, new GBC(1, 5, 4, 1).setFill(GBC.HORIZONTAL).setWeight(100, 0));
		
		//为删除按钮添加监听器
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
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
					List<Book> deleteBookResult = searchResult.getResultBookList();
					
					//根据书名查到的结果不为空
					if(deleteBookResult.isEmpty()) {
						JOptionPane.showMessageDialog(parentComponent, "没找到您要删除的图书");
					} else {
						//显示查询到的结果
						JDialog deleteResultDialog = new JDialog(owner, "查询结果", true);
						UITool.setCentralLocation(owner, deleteResultDialog, 500, 600);
						ResultSelectPanel deleteResultPanel = new ResultSelectPanel(deleteBookResult);
						
						JButton deleteButton = new JButton("删除");
						deleteButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.red));
						//为删除按钮添加监听器
						deleteButton.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent event) {
								int confirmDialog = JOptionPane.showConfirmDialog(parentComponent, "确定删除？");
								if(confirmDialog == JOptionPane.OK_OPTION) {
									//执行多选的删除操作
									List<Book> deleteBookList = deleteResultPanel.getSelectedBookList();
									BookOperatorResult result = dbOperator.deleteBook(deleteBookList);
									deleteResultDialog.dispose();
									JOptionPane.showMessageDialog(parentComponent, result.getResultString());
								}
							}
						});
						deleteResultPanel.add(deleteButton, BorderLayout.SOUTH);
						JLabel tips = new JLabel("Tips: 可多选批量删除");
						tips.setFont(new Font("微软雅黑", Font.ITALIC, 17));
						deleteResultDialog.add(tips, BorderLayout.NORTH);
						deleteResultDialog.add(deleteResultPanel);
						deleteResultDialog.setSize(500, 600);
						deleteResultDialog.setVisible(true);
					}
				} else {
					JOptionPane.showMessageDialog(parentComponent, "请至少输入一个条件");
				}
			}
		});
	}
}
