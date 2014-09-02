/**
 * @author clack008@gmail.com
 */

package week3.financial_manager.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import week3.financial_manager.controller.Controller;

public class Authorization implements View {

	private JFrame frmFinancialManager;
	private JTextField textField_1;
	private JLabel lblNewLabel_2 = new JLabel("");
	private JLabel label = new JLabel("");
	private JLabel lblNewLabel = new JLabel(
			"\u041F\u0430\u0440\u043E\u043B\u044C");
	private JPasswordField passwordField;
	private Controller controller;

	/**
	 * Launch the application.
	 * 
	 * @throws UnsupportedLookAndFeelException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {
		// UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		UIManager
				.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Authorization window = new Authorization();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Authorization() {
		initialize();
		this.frmFinancialManager.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFinancialManager = new JFrame();
		frmFinancialManager
				.setIconImage(Toolkit
						.getDefaultToolkit()
						.getImage(
								"C:\\Users\\Just a man\\Desktop\\1399996127_Customer_Male_Light.png"));
		frmFinancialManager.setResizable(false);
		frmFinancialManager
				.setTitle("\u0424\u0438\u043D\u0430\u043D\u0441\u043E\u0432\u044B\u0439 \u043C\u0435\u043D\u0435\u0434\u0436\u0435\u0440");
		frmFinancialManager.setBounds(100, 100, 277, 340);
		frmFinancialManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFinancialManager.getContentPane().setLayout(
				new GridLayout(8, 1, 3, 3));

		JLabel lblNewLabel_1 = new JLabel("\u041B\u043E\u0433\u0438\u043D");
		lblNewLabel_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		frmFinancialManager.getContentPane().add(lblNewLabel_1);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		frmFinancialManager.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		label.setForeground(Color.RED);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		frmFinancialManager.getContentPane().add(label);

		lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frmFinancialManager.getContentPane().add(lblNewLabel);

		passwordField = new JPasswordField();
		frmFinancialManager.getContentPane().add(passwordField);

		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		frmFinancialManager.getContentPane().add(lblNewLabel_2);

		JButton btnNewButton = new JButton("\u0412\u0445\u043E\u0434");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Authorization.this.handleLogin();
			}
		});
		btnNewButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		frmFinancialManager.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton(
				"\u0420\u0435\u0433\u0438\u0441\u0442\u0440\u0430\u0446\u0438\u044F");
		btnNewButton_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		frmFinancialManager.getContentPane().add(btnNewButton_1);
	}

	private void handleLogin() {
		if (this.passwordField.getText().equals("password")
				&& this.textField_1.getText().equals("login")) {
			frmFinancialManager.dispose();
			new Dashboard();
		} else {
			label.setText("Неправильное имя.");
			lblNewLabel_2.setText("Неправильный пароль");
		}
	}

	@Override
	public void addController(Controller controller) {
		this.controller = controller;
	}

}
