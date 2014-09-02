/**
 * @author clack008@gmail.com
 */

package week3.financial_manager.view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Toolkit;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import week3.financial_manager.controller.Controller;

public class DeleteAccount implements View {

	private JFrame frame;

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
					DeleteAccount window = new DeleteAccount();
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
	public DeleteAccount() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setIconImage(Toolkit
				.getDefaultToolkit()
				.getImage(
						"C:\\Users\\Just a man\\Desktop\\1399996127_Customer_Male_Light.png"));
		frame.setTitle("\u0423\u0434\u0430\u043B\u0438\u0442\u044C \u0441\u0447\u0435\u0442");
		frame.setBounds(100, 100, 319, 167);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(3, 1, 0, 0));

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(
				new String[] { "\u0412\u044B\u0431\u0435\u0440\u0438\u0442\u0435 \u0441\u0447\u0435\u0442" }));
		comboBox.setToolTipText("\u0412\u044B\u0431\u0435\u0440\u0438\u0442\u0435 \u0441\u0447\u0435\u0442");
		frame.getContentPane().add(comboBox);

		JLabel lblNewLabel = new JLabel("");
		frame.getContentPane().add(lblNewLabel);

		JButton btnNewButton = new JButton(
				"\u0423\u0434\u0430\u043B\u0438\u0442\u044C \u0441\u0447\u0435\u0442");
		frame.getContentPane().add(btnNewButton);
	}

	@Override
	public void addController(Controller controller) {
		this.controller = controller;
	}

}
