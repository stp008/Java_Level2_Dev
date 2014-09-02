/**
 * @author clack008@gmail.com
 */

package week3.financial_manager.view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Toolkit;

import net.miginfocom.swing.MigLayout;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import week3.financial_manager.controller.Controller;

public class CreateUser implements View {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

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
		UIManager
				.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateUser window = new CreateUser();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CreateUser() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setEnabled(false);
		frame.setTitle("\u0421\u043E\u0437\u0434\u0430\u043D\u0438\u0435 \u043D\u043E\u0432\u043E\u0433\u043E \u043F\u043E\u043B\u044C\u0437\u043E\u0432\u0430\u0442\u0435\u043B\u044F");
		frame.setIconImage(Toolkit
				.getDefaultToolkit()
				.getImage(
						"C:\\Users\\Just a man\\Desktop\\1399996127_Customer_Male_Light.png"));
		frame.setBounds(100, 100, 292, 453);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(12, 1, 1, 1));

		JLabel label = new JLabel("\u041B\u043E\u0433\u0438\u043D");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(label);

		textField = new JTextField();
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JLabel label_1 = new JLabel("\u041F\u0430\u0440\u043E\u043B\u044C");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(label_1);

		textField_1 = new JTextField();
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		JLabel label_2 = new JLabel("\u0418\u043C\u044F");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(label_2);

		textField_2 = new JTextField();
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);

		JLabel label_3 = new JLabel(
				"\u0424\u0430\u043C\u0438\u043B\u0438\u044F");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(label_3);

		textField_3 = new JTextField();
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);

		JLabel label_4 = new JLabel(
				"\u041E\u0442\u0447\u0435\u0441\u0442\u0432\u043E");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(label_4);

		textField_4 = new JTextField();
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);

		JLabel lblNewLabel = new JLabel("");
		frame.getContentPane().add(lblNewLabel);

		JButton btnNewButton = new JButton(
				"\u0421\u043E\u0437\u0434\u0430\u0442\u044C \u043F\u043E\u043B\u044C\u0437\u043E\u0432\u0430\u0442\u0435\u043B\u044F");
		frame.getContentPane().add(btnNewButton);
	}

	@Override
	public void addController(Controller controller) {
		this.controller = controller;
	}
}
