/**
 * @author clack008@gmail.com
 */

package week3.financial_manager.view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Toolkit;
import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import week3.financial_manager.controller.Controller;

public class CreateAccount implements View {

	private JDialog frame;
	private JTextField textField;
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
					CreateAccount window = new CreateAccount();
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
	public CreateAccount() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog();
		frame.setResizable(false);
		frame.setTitle("\u0421\u043E\u0437\u0434\u0430\u0442\u044C \u0441\u0447\u0435\u0442");
		frame.setIconImage(Toolkit
				.getDefaultToolkit()
				.getImage(
						"C:\\Users\\Just a man\\Desktop\\1399996127_Customer_Male_Light.png"));
		frame.setBounds(100, 100, 370, 231);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(4, 1, 0, 0));

		JLabel label = new JLabel(
				"\u041E\u043F\u0438\u0441\u0430\u043D\u0438\u0435");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(label);

		textField = new JTextField();
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel);

		JButton button = new JButton(
				"\u0421\u043E\u0437\u0434\u0430\u0442\u044C \u0441\u0447\u0435\u0442");
		frame.getContentPane().add(button);
	}

	@Override
	public void addController(Controller controller) {
		this.controller = controller;
	}

}
