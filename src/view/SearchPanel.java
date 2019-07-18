package view;


import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class SearchPanel extends JPanel {
	private JTextField bookNameField;
	private JTextField bookAuthorField;
	private JTextField bookPressField;

	public JTextField getBookNameField() {
		return bookNameField;
	}
	public JTextField getBookAuthorField() {
		return bookAuthorField;
	}
	public JTextField getBookPressField() {
		return bookPressField;
	}

	public SearchPanel(String imagePath, String methodName) {
		setLayout(new GridBagLayout());
		
		JLabel iconLabel = new JLabel();
		ImageIcon icon = new ImageIcon(imagePath);
		icon.setImage(icon.getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT));
		iconLabel.setIcon(icon);
		add(iconLabel, new GBC(0, 0, 4, 1).setInsets(0, 0, 20, 0));
		
		bookNameField = new JTextField();
		JLabel searchBookLabel = new JLabel(methodName);
		searchBookLabel.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		
		add(searchBookLabel, new GBC(0, 1, 4, 1).setInsets(0, 0, 15, 0));
		add(new JLabel("书名："), new GBC(0, 2).setAnchor(GBC.EAST));
		add(bookNameField, new GBC(1, 2, 3, 1).setFill(GBC.HORIZONTAL).setWeight(100, 0).setInsets(8, 0, 8, 0));
		add(new JLabel("作者："), new GBC(0, 3).setAnchor(GBC.EAST));
		bookAuthorField = new JTextField();
		add(bookAuthorField, new GBC(1, 3).setFill(GBC.HORIZONTAL).setWeight(100, 0).setInsets(8, 0, 8, 0));
		add(new JLabel("出版社："), new GBC(2, 3).setAnchor(GBC.EAST));
		bookPressField = new JTextField();
		add(bookPressField, new GBC(3, 3).setFill(GBC.HORIZONTAL).setWeight(100, 0).setInsets(8, 0, 8, 0));
	}
}
