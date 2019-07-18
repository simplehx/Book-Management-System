package view;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import org.jb2011.lnf.beautyeye.ch6_textcoms.BETextAreaUI;

import control.BookOperator;
import model.Book;
import model.BookOperatorResult;
import tool.UITool;

public class AddBookPanel extends JPanel {
	public AddBookPanel(Component parentComponent) {
		//构建界面
		setLayout(new GridBagLayout());
		
		JLabel iconLabel = new JLabel();
		ImageIcon icon = new ImageIcon("img/add.png");
		icon.setImage(icon.getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT));
		iconLabel.setIcon(icon);
		add(iconLabel, new GBC(0, 0, 6, 1).setInsets(0, 0, 5, 0));
		
		JTextField bookNameField = new JTextField();
		JTextField bookPriceField = new JTextField();
		JTextField bookAuthorField = new JTextField();
		JTextField bookPressField = new JTextField();
		JLabel changBookLabel = new JLabel("添加图书");
		changBookLabel.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		add(changBookLabel, new GBC(0, 1, 6, 1).setInsets(0, 0, 15, 0));
		add(new JLabel("书名："), new GBC(0, 2).setAnchor(GBC.EAST));
		add(bookNameField, new GBC(1, 2).setFill(GBC.HORIZONTAL).setWeight(100, 0).setInsets(6, 0, 6, 0));
		add(new JLabel("价格："), new GBC(2, 2).setAnchor(GBC.EAST));
		add(bookPriceField, new GBC(3, 2).setFill(GBC.HORIZONTAL).setWeight(100, 0).setInsets(6, 0, 6, 0));
		add(new JLabel("作者："), new GBC(0, 3).setAnchor(GBC.EAST));
		add(bookAuthorField, new GBC(1, 3).setFill(GBC.HORIZONTAL).setWeight(100, 0).setInsets(6, 0, 6, 0));
		add(new JLabel("出版社："), new GBC(2, 3).setAnchor(GBC.EAST));
		add(bookPressField, new GBC(3, 3).setFill(GBC.HORIZONTAL).setWeight(100, 0).setInsets(6, 0, 6, 0));
		JTextArea bookDetailsTextArea = new JTextArea();
		add(new JLabel("详情："), new GBC(0, 5).setAnchor(GBC.EAST));
		add(new JScrollPane(bookDetailsTextArea), new GBC(1, 5, 5, 1).setFill(GBC.BOTH).setWeight(100, 100).setInsets(6, 0, 6, 0));
		
		JButton submitButton = new JButton("插入");
		submitButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
		add(submitButton, new GBC(1, 6, 6, 1).setFill(GBC.HORIZONTAL).setWeight(100, 0));
		//为提交按钮添加监听器
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				//对各字段进行非空检测,使用LinkedHashMap实现顺序检测
				Map<String, String> fieldMap = new LinkedHashMap<String, String>();
				fieldMap.put("书名", bookNameField.getText().trim());
				fieldMap.put("价格", bookPriceField.getText().trim());
				fieldMap.put("作者", bookAuthorField.getText().trim());
				fieldMap.put("出版社", bookPressField.getText().trim());
				fieldMap.put("详情", bookDetailsTextArea.getText().trim());
				
				String fieldCheckResult = UITool.allFieldNonEmptyCheck(fieldMap);
				//所有字段检测通过
				if(fieldCheckResult.equals("success")) {
					int confirmResule = JOptionPane.showConfirmDialog(parentComponent, "确定插入？");
					//确定插入
					if(confirmResule == JOptionPane.YES_OPTION) {
						BookOperator dbOperator = new BookOperator();
						Book book = new Book();
						book.setName(bookNameField.getText().trim());
						book.setPrice(Double.parseDouble(bookPriceField.getText().trim()));
						book.setAuthor(bookAuthorField.getText().trim());
						book.setDetails(bookDetailsTextArea.getText().trim());
						book.setPress(bookPressField.getText().trim());
						BookOperatorResult result = dbOperator.addBook(book);
						
						//使用对话框显示操作结果
						JOptionPane.showMessageDialog(parentComponent, result.getResultString());
					}
				} else {
					JOptionPane.showMessageDialog(parentComponent, fieldCheckResult + "不能为空" );
				}
			}
		});
	}
}
