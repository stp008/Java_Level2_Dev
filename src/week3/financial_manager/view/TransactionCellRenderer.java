/**
 * @author clack008@gmail.com
 */

package week3.financial_manager.view;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import week3.financial_manager.controller.Controller;

@SuppressWarnings("serial")
public class TransactionCellRenderer extends DefaultListCellRenderer implements
		View {

	JTextArea area;

	private Controller controller;

	@Override
	public Component getListCellRendererComponent(
			@SuppressWarnings("rawtypes") JList list, Object value, int index,
			boolean isSelected, boolean cellHasFocus) {

		area = new JTextArea();
		area.setEditable(false);
		area.setFont(UIManager.getLookAndFeelDefaults().getFont("Label.font"));
		area.setLineWrap(true);
		area.setWrapStyleWord(true);

		value = index + ": " + value;
		value += "\nСумма: n руб.";
		value += "\nДата: 01.01.1970";

		// Prepend the index to the "even" rows (the first row is row 1)
		if ((index + 1) % 2 == 0) {
			value += "\nМетка: Пополнение";
		} else {
			value += "\nМетка: Снятие";
		}

		value += "\nКатегория: Дача\n";

		area.setText(value.toString());
		if (isSelected) {
			area.setBackground(list.getSelectionBackground());
			area.setForeground(list.getSelectionForeground());
		} else {
			area.setBackground(list.getBackground());
			area.setForeground(list.getForeground());
		}

		// If the value is a color, set the cell's background to that color.
		return area;
	}

	@Override
	public void addController(Controller controller) {
		this.controller = controller;
	}

}
