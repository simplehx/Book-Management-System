package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import control.UserOperator;
import model.User;
import tool.UITool;


public class MainFrame extends JFrame {
	private JPanel optionPanel;
	private JPanel contentPanel;
	private int screenHeight;
	private int screenWidth;
	
	public static void main(String[] args) {
		try {
			//解决jdk透明API bug导致的输入法切换白屏问题
			System.setProperty("sun.java2d.noddraw", "true");
			BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;
			//加载beautyeye
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			UIManager.put("RootPane.setupButtonVisible", false);
		} catch (Exception exception) {
			//如果加载不成功，使用系统主题
			exception.printStackTrace();
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (UnsupportedLookAndFeelException e) {
				e.printStackTrace();
			}
		}
		
		EventQueue.invokeLater(() -> {
			JFrame frame = new MainFrame();
			frame.setTitle("图书管理系统");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}
	
	public MainFrame() {
		//设置窗口大小
		setUISize();

		//构建菜单
		setMenu();
		
		//构建界面
		setTitle("图书管理系统");
		setLayout(new BorderLayout());
		
		//选项面板
		optionPanel = new JPanel();
		JPanel generalUserOptionPanel = new JPanel();
		makeButton(generalUserOptionPanel, "查找图书");
		makeButton(generalUserOptionPanel, "退出");
		updateOptionPanel(generalUserOptionPanel);
		
		//内容面板
		contentPanel = new JPanel(new GridBagLayout());
		JLabel iconLabel = new JLabel();
		ImageIcon icon = new ImageIcon("img/book.png");
		icon.setImage(icon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
		iconLabel.setIcon(icon);
		contentPanel.add(iconLabel, new GBC(0, 0));
		JLabel welcomeLabel = new JLabel("欢迎使用图书管理系统");
		welcomeLabel.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		contentPanel.add(welcomeLabel, new GBC(0, 1).setAnchor(GBC.NORTH).setInsets(40, 0, 0, 0));
		add(optionPanel, BorderLayout.NORTH);
		add(contentPanel, BorderLayout.CENTER);
	}
	
	/**
	 * 制作按钮
	*/
	public void makeButton(JPanel panel, String name) {
		JButton button = new JButton(name);
		button.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		panel.add(button);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				String command = event.getActionCommand();
				switch (command) {
					case "添加图书":
						addBook();
						break;
					case "删除图书":
						deleteBook();
						break;
					case "查找图书":
						searchBook();
						break;
					case "修改图书":
						changeBook();
						break;
					case "退出":
						exitSystem();
						break;
				}
			}
		});
	}
	
	/**
	 * 添加图书
	*/
	public void addBook() {
		JPanel addBookPanel = new AddBookPanel(new MainFrame());
		updateContentPanel(addBookPanel);
	}
	
	/**
	 * 删除图书
	*/
	public void deleteBook() {
		JPanel deleteBookPanel = new DeleteBookPanel(MainFrame.this, MainFrame.this);
		updateContentPanel(deleteBookPanel);
	}
	
	/**
	 * 查找图书
	*/
	public void searchBook() {
		JPanel searchBookPanel = new SearchBookPanel(MainFrame.this, MainFrame.this);
		updateContentPanel(searchBookPanel);
	}
	
	/**
	 *修改图书 
	 */
	public void changeBook() {
		JPanel changeBookPanel = new ChangeBookPanel(MainFrame.this, MainFrame.this);
		updateContentPanel(changeBookPanel);
	}
	
	/**
	 *退出系统 
	 */
	public void exitSystem() {
		int confirmDialog = JOptionPane.showConfirmDialog(MainFrame.this, "确定退出图书管理系统？");
		if(confirmDialog == JOptionPane.OK_OPTION) {
			System.exit(0);
		}
	}
	
	/**
	 * 设置窗口大小和位置
	*/
	public void setUISize() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		screenHeight = screenSize.height;
		screenWidth = screenSize.width;
		setSize(screenWidth / 2, screenHeight / 2 + 50);
		setLocationRelativeTo(null);
	}

	/**
	 * 更新内容面板
	*/
	public void updateContentPanel(JPanel panel) {
		contentPanel.removeAll();
		contentPanel.add(panel, new GBC(0, 0).setFill(GBC.BOTH).setWeight(100, 100).setInsets(20, 130, 50, 130).setAnchor(GBC.NORTH));
		contentPanel.updateUI();
	}

	/**
	 * 更新选项面板
	*/
	public void updateOptionPanel(JPanel panel) {
		optionPanel.removeAll();
		optionPanel.add(panel);
		optionPanel.updateUI();
	}
	
	/**
	 * 设置菜单
	*/
	public void setMenu() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu userMenu = new JMenu("用户");
		menuBar.add(userMenu);
		JMenuItem loginItem = new JMenuItem("登录");
		JMenuItem logoutItem = new JMenuItem("退出登录");
		userMenu.add(loginItem);
		
		//为登录选项添加监听器
		loginItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//登录对话框
				JDialog loginDialog = new JDialog(MainFrame.this, "用户登陆", true);
				UITool.setCentralLocation(MainFrame.this, loginDialog, 400 , 400);
				LoginPanel loginPanel = new LoginPanel();
				JButton submitButton = new JButton("登陆");
				submitButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
				//为登录按钮添加监听器
				submitButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent event) {
						UserOperator userOperator = new UserOperator();
						User user = new User();
						user.setUsername(loginPanel.getUsernameField().getText().trim());
						user.setPassword(String.valueOf(loginPanel.getPasswordField().getPassword()).trim());
						boolean loginResult = userOperator.login(user);
						//如果登录成功
						if(loginResult) {
							JOptionPane.showMessageDialog(loginDialog, "登录成功");
							loginDialog.dispose();
							JPanel adminOptionPanel = new JPanel();
							makeButton(adminOptionPanel, "添加图书");
							makeButton(adminOptionPanel, "删除图书");
							makeButton(adminOptionPanel, "查找图书");
							makeButton(adminOptionPanel, "修改图书");
							makeButton(adminOptionPanel, "退出");
							//更新管理员选项面板
							updateOptionPanel(adminOptionPanel);
							userMenu.remove(loginItem);
							userMenu.add(logoutItem);
						} else {
							//登录失败，重置用户名密码文本框
							JOptionPane.showMessageDialog(loginDialog, "登录失败，请重试");
							loginPanel.getUsernameField().setText("");
							loginPanel.getPasswordField().setText("");
						}
					}
				});
				loginPanel.add(submitButton, new GBC(0, 3, 2, 1).setFill(GBC.HORIZONTAL).setWeight(100, 0).setInsets(6, 40, 6, 40));
				loginDialog.add(loginPanel);
				loginDialog.setSize(400, 400);
				loginDialog.setVisible(true);
			}
		});
		
		//为退出选项添加监听器
		logoutItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel generalUserOptionPanel = new JPanel();
				makeButton(generalUserOptionPanel, "查找图书");
				makeButton(generalUserOptionPanel, "退出");
				updateOptionPanel(generalUserOptionPanel);
				userMenu.remove(logoutItem);
				userMenu.add(loginItem);
				JPanel searchBookPanel = new SearchBookPanel(MainFrame.this, MainFrame.this);
				updateContentPanel(searchBookPanel);
			}
		});
	
	}
}