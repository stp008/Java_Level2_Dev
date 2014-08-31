package week3.financial_manager.view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Toolkit;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.DefaultComboBoxModel;

public class CreateRecord {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager
		.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateRecord window = new CreateRecord();
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
	public CreateRecord() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Just a man\\Desktop\\1399996127_Customer_Male_Light.png"));
		frame.setTitle("\u0421\u043E\u0437\u0434\u0430\u0442\u044C \u0442\u0440\u0430\u043D\u0437\u0430\u043A\u0446\u0438\u044E");
		frame.setBounds(100, 100, 410, 530);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(14, 1, 0, 0));
		
		JLabel label = new JLabel("\u0421\u0447\u0435\u0442");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(label);
		
		JComboBox comboBox = new JComboBox();
		frame.getContentPane().add(comboBox);
		
		JLabel label_1 = new JLabel("\u0422\u0438\u043F \u043E\u043F\u0435\u0440\u0430\u0446\u0438\u0438");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(label_1);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"PUT", "WITHDRAW"}));
		frame.getContentPane().add(comboBox_1);
		
		JLabel label_2 = new JLabel("\u0421\u0443\u043C\u043C\u0430");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(label_2);
		
		textField = new JTextField();
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel label_3 = new JLabel("\u0414\u0430\u0442\u0430");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(label_3);
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		frame.getContentPane().add(formattedTextField);
		
		JLabel label_4 = new JLabel("\u041E\u043F\u0438\u0441\u0430\u043D\u0438\u0435");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(label_4);
		
		textField_1 = new JTextField();
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label_5 = new JLabel("\u041A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u044F");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(label_5);
		
		JComboBox comboBox_2 = new JComboBox();
		frame.getContentPane().add(comboBox_2);
		
		JLabel lblNewLabel = new JLabel("");
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("\u0421\u043E\u0437\u0434\u0430\u0442\u044C \u0442\u0440\u0430\u0437\u043D\u0430\u043A\u0446\u0438\u044E");
		frame.getContentPane().add(btnNewButton);
	}

}
