package week3.financial_manager.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.PiePlot;
import de.erichseifert.gral.plots.PiePlot.PieSliceRenderer;
import de.erichseifert.gral.plots.colors.LinearGradient;
import de.erichseifert.gral.ui.InteractivePanel;
import de.erichseifert.gral.util.Insets2D;




import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Random;
import java.awt.FlowLayout;

import javax.swing.JTree;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JToolBar;
import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import javax.swing.JTabbedPane;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import java.awt.List;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import java.awt.Toolkit;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.ListSelectionModel;

public class Dashboard {

	private JFrame frame;

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
					Dashboard window = new Dashboard();
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
	public Dashboard() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Just a man\\Desktop\\1399996127_Customer_Male_Light.png"));
		frame.setTitle("\u0424\u0438\u043D\u0430\u043D\u0441\u043E\u0432\u044B\u0439 \u043C\u0435\u043D\u0435\u0434\u0436\u0435\u0440");
		frame.setBounds(100, 100, 972, 719);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[20%,grow][50%,grow][30%,grow]", "[100%]"));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "\u0421\u0447\u0435\u0442\u0430", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(panel_1, "cell 0 0,grow");
		panel_1.setLayout(new MigLayout("", "[100%,grow]", "[10%][90%,grow]"));
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\u0421\u0447\u0435\u0442\u0430", "12300", "12800"}));
		panel_1.add(comboBox, "cell 0 0,growx");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1, "cell 0 1,grow");
		
		JTree tree = new JTree();
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("\u041A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438") {
				{
					add(new DefaultMutableTreeNode("\u041C\u0430\u0448\u0438\u043D\u0430"));
					add(new DefaultMutableTreeNode("\u0414\u0430\u0447\u0430"));
					add(new DefaultMutableTreeNode("\u041A\u0432\u0430\u0440\u0442\u0438\u0440\u0430"));
					add(new DefaultMutableTreeNode("\u0420\u0430\u0431\u043E\u0442\u0430"));
					add(new DefaultMutableTreeNode("\u0412\u0441\u0435 \u0442\u0440\u0430\u043D\u0437\u0430\u043A\u0446\u0438\u0438"));
				}
			}
		));
		scrollPane_1.setViewportView(tree);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "\u041E\u0431\u0449\u0438\u0435 \u0441\u0432\u0435\u0434\u0435\u043D\u0438\u044F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(panel_2, "cell 1 0,grow");
		panel_2.setLayout(new MigLayout("", "[100%,grow]", "[10%,grow][10%,grow][80%,grow]"));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "\u0414\u0435\u0440\u0436\u0430\u0442\u0435\u043B\u044C \u0441\u0447\u0435\u0442\u0430", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.add(panel_5, "cell 0 0,grow");
		panel_5.setLayout(new MigLayout("", "[100%]", "[100%]"));
		
		JLabel label_1 = new JLabel("\u041F\u043E\u0433\u043E\u0441\u044F\u043D \u0421\u0442\u0435\u043F\u0430\u043D");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_5.add(label_1, "cell 0 0,alignx center,aligny center");
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "\u0411\u0430\u043B\u0430\u043D\u0441", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.add(panel_4, "cell 0 1,grow");
		panel_4.setLayout(new MigLayout("", "[100%]", "[100%]"));
		
		JLabel label = new JLabel("300 000 000 \u0440\u0443\u0431\u043B\u0435\u0439");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_4.add(label, "cell 0 0,alignx center,aligny center");
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(null, "\u0413\u0440\u0430\u0444\u0438\u043A \u0440\u0430\u0441\u0445\u043E\u0434\u043E\u0432", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.add(panel_6, "cell 0 2,grow");
		panel_6.setLayout(new MigLayout("", "[100%]", "[100%]"));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "\u0422\u0440\u0430\u043D\u0437\u0430\u043A\u0446\u0438\u0438", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(panel_3, "cell 2 0,grow");
		panel_3.setLayout(new MigLayout("", "[100%,grow]", "[100%,grow]"));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_3.add(scrollPane, "cell 0 0,grow");
		
		JList list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBorder(new EmptyBorder(5, 10, 10, 10));
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"\u0422\u0440\u0430\u043D\u0437\u0430\u043A\u0446\u0438\u044F \u043F\u043E \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438", "\u0422\u0440\u0430\u043D\u0437\u0430\u043A\u0446\u0438\u044F \u043F\u043E \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438", "\u0422\u0440\u0430\u043D\u0437\u0430\u043A\u0446\u0438\u044F \u043F\u043E \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438", "\u0422\u0440\u0430\u043D\u0437\u0430\u043A\u0446\u0438\u044F \u043F\u043E \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438", "\u0422\u0440\u0430\u043D\u0437\u0430\u043A\u0446\u0438\u044F \u043F\u043E \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438", "\u0422\u0440\u0430\u043D\u0437\u0430\u043A\u0446\u0438\u044F \u043F\u043E \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438", "\u0422\u0440\u0430\u043D\u0437\u0430\u043A\u0446\u0438\u044F \u043F\u043E \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438", "\u0422\u0440\u0430\u043D\u0437\u0430\u043A\u0446\u0438\u044F \u043F\u043E \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438", "\u0422\u0440\u0430\u043D\u0437\u0430\u043A\u0446\u0438\u044F \u043F\u043E \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438", "\u0422\u0440\u0430\u043D\u0437\u0430\u043A\u0446\u0438\u044F \u043F\u043E \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438", "\u0422\u0440\u0430\u043D\u0437\u0430\u043A\u0446\u0438\u044F \u043F\u043E \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438", "\u0422\u0440\u0430\u043D\u0437\u0430\u043A\u0446\u0438\u044F \u043F\u043E \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438", "\u0422\u0440\u0430\u043D\u0437\u0430\u043A\u0446\u0438\u044F \u043F\u043E \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438", "\u0422\u0440\u0430\u043D\u0437\u0430\u043A\u0446\u0438\u044F \u043F\u043E \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438", "\u0422\u0440\u0430\u043D\u0437\u0430\u043A\u0446\u0438\u044F \u043F\u043E \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438", "\u0422\u0440\u0430\u043D\u0437\u0430\u043A\u0446\u0438\u044F \u043F\u043E \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438", "\u0422\u0440\u0430\u043D\u0437\u0430\u043A\u0446\u0438\u044F \u043F\u043E \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438", "\u0422\u0440\u0430\u043D\u0437\u0430\u043A\u0446\u0438\u044F \u043F\u043E \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438", "\u0422\u0440\u0430\u043D\u0437\u0430\u043A\u0446\u0438\u044F \u043F\u043E \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438", "\u0422\u0440\u0430\u043D\u0437\u0430\u043A\u0446\u0438\u044F \u043F\u043E \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438", "\u0422\u0440\u0430\u043D\u0437\u0430\u043A\u0446\u0438\u044F \u043F\u043E \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438", "\u0422\u0440\u0430\u043D\u0437\u0430\u043A\u0446\u0438\u044F \u043F\u043E \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438", "\u0422\u0440\u0430\u043D\u0437\u0430\u043A\u0446\u0438\u044F \u043F\u043E \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438", "\u0422\u0440\u0430\u043D\u0437\u0430\u043A\u0446\u0438\u044F \u043F\u043E \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438", "\u0422\u0440\u0430\u043D\u0437\u0430\u043A\u0446\u0438\u044F \u043F\u043E \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438", "\u0422\u0440\u0430\u043D\u0437\u0430\u043A\u0446\u0438\u044F \u043F\u043E \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438", "\u0422\u0440\u0430\u043D\u0437\u0430\u043A\u0446\u0438\u044F \u043F\u043E \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438", "\u0422\u0440\u0430\u043D\u0437\u0430\u043A\u0446\u0438\u044F \u043F\u043E \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438", "\u0422\u0440\u0430\u043D\u0437\u0430\u043A\u0446\u0438\u044F \u043F\u043E \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438", "\u0422\u0440\u0430\u043D\u0437\u0430\u043A\u0446\u0438\u044F \u043F\u043E \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438", "\u0422\u0440\u0430\u043D\u0437\u0430\u043A\u0446\u0438\u044F \u043F\u043E \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438", "\u0422\u0440\u0430\u043D\u0437\u0430\u043A\u0446\u0438\u044F \u043F\u043E \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438", "\u0422\u0440\u0430\u043D\u0437\u0430\u043A\u0446\u0438\u044F \u043F\u043E \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438", "\u0422\u0440\u0430\u043D\u0437\u0430\u043A\u0446\u0438\u044F \u043F\u043E \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438", "\u0422\u0440\u0430\u043D\u0437\u0430\u043A\u0446\u0438\u044F \u043F\u043E \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438", "\u0422\u0440\u0430\u043D\u0437\u0430\u043A\u0446\u0438\u044F \u043F\u043E \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438", "\u0422\u0440\u0430\u043D\u0437\u0430\u043A\u0446\u0438\u044F \u043F\u043E \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane.setViewportView(list);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu menu_4 = new JMenu("\u0410\u0432\u0442\u043E\u0440\u0438\u0437\u0430\u0446\u0438\u044F");
		menuBar.add(menu_4);
		
		JMenuItem menuItem_8 = new JMenuItem("\u0421\u043C\u0435\u043D\u0438\u0442\u044C \u043F\u043E\u043B\u044C\u0437\u043E\u0432\u0430\u0442\u0435\u043B\u044F");
		menu_4.add(menuItem_8);
		
		JMenuItem menuItem_9 = new JMenuItem("\u0421\u043E\u0437\u0434\u0430\u0442\u044C \u043F\u043E\u043B\u044C\u0437\u043E\u0432\u0430\u0442\u0435\u043B\u044F");
		menu_4.add(menuItem_9);
		
		JMenuItem menuItem_10 = new JMenuItem("\u0412\u044B\u0445\u043E\u0434");
		menu_4.add(menuItem_10);
		
		JMenu menu = new JMenu("\u0421\u0447\u0435\u0442\u0430");
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem("\u0421\u043E\u0437\u0434\u0430\u0442\u044C");
		menu.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("\u0423\u0434\u0430\u043B\u0438\u0442\u044C");
		menu.add(menuItem_1);
		
		JMenu menu_1 = new JMenu("\u0422\u0440\u0430\u043D\u0437\u0430\u043A\u0446\u0438\u0438");
		menuBar.add(menu_1);
		
		JMenuItem menuItem_2 = new JMenuItem("\u0421\u043E\u0437\u0434\u0430\u0442\u044C");
		menu_1.add(menuItem_2);
		
		JMenuItem menuItem_3 = new JMenuItem("\u0423\u0434\u0430\u043B\u0438\u0442\u044C");
		menu_1.add(menuItem_3);
		
		JMenu menu_2 = new JMenu("\u041A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438");
		menuBar.add(menu_2);
		
		JMenuItem menuItem_4 = new JMenuItem("\u0421\u043E\u0437\u0434\u0430\u0442\u044C");
		menu_2.add(menuItem_4);
		
		JMenuItem menuItem_5 = new JMenuItem("\u0423\u0434\u0430\u043B\u0438\u0442\u044C");
		menu_2.add(menuItem_5);
		
		JMenu menu_3 = new JMenu("\u041F\u043E\u043C\u043E\u0449\u044C");
		menuBar.add(menu_3);
		
		JMenuItem menuItem_6 = new JMenuItem("\u041E \u043F\u0440\u043E\u0433\u0440\u0430\u043C\u043C\u0435");
		menu_3.add(menuItem_6);
		
		JMenuItem menuItem_7 = new JMenuItem("\u041E\u0431 \u0430\u0432\u0442\u043E\u0440\u0435");
		menu_3.add(menuItem_7);
		
		JPanel panel = new JPanel();
		
		///start
		
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
