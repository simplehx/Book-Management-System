package view;

import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * 登录面板
 * */
public class LoginPanel extends JPanel {
	private JTextField usernameField;
	private JPasswordField passwordField;
	public JTextField getUsernameField() {
		return usernameField;
	}
	public JPasswordField getPasswordField() {
		return passwordField;
	}
	public LoginPanel() {
		setLayout(new GridBagLayout());
		usernameField = new JTextField();
		passwordField = new JPasswordField();
		JLabel iconLabel = new JLabel();
		ImageIcon icon = new ImageIcon("img/user.png");
		icon.setImage(icon.getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT));
		iconLabel.setIcon(icon);
		add(iconLabel, new GBC(0, 0, 2, 1));
		add(new JLabel("账户："), new GBC(0, 1).setAnchor(GBC.EAST).setInsets(6, 40, 6, 0));
		add(usernameField, new GBC(1, 1).setFill(GBC.HORIZONTAL).setWeight(100, 0).setInsets(6, 10, 6, 40));
		add(new JLabel("密码："), new GBC(0, 2).setAnchor(GBC.EAST).setInsets(6, 40, 6, 0));
		add(passwordField, new GBC(1, 2).setFill(GBC.HORIZONTAL).setWeight(100, 0).setInsets(6, 10, 6, 40));
	}
}