/**
 * @author clack008@gmail.com
 */

package week3;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@SuppressWarnings("serial")
public class PaintFrame extends JFrame {

    Map<Line2D.Float, Color> lines = new HashMap<>();
	//List<Line2D.Float> lines = new ArrayList<>();
    Point lastPoint;
    JPanel pane = new DrawPane();
    Color color = Color.RED;

    public PaintFrame() {
        super("Paint Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new BorderLayout());
        add(pane, BorderLayout.CENTER);
        add(new ButtonPanel(), BorderLayout.NORTH);

        DrawListener listener = new DrawListener();
        pane.addMouseListener(listener);
        pane.addMouseMotionListener(listener);
    }


    // class to handle mouse action from DrawPane
    private class DrawListener extends MouseAdapter {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                if (lastPoint == null) {
                    lastPoint = e.getPoint();
                    return;
                }
                lines.put(new Line2D.Float(lastPoint, e.getPoint()), PaintFrame.this.color);
                lastPoint = e.getPoint();

                pane.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                lastPoint = null;
            }
    }

    class DrawPane extends JPanel{
        public void paintComponent(Graphics g){
            // delete line below :)
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            //g2d.setColor(color);
            for (Line2D.Float line : lines.keySet()) {
                g2d.setColor(lines.get(line));
            	g2d.draw(line);
            }
        }
    }

    class ButtonPanel extends JPanel implements ActionListener {
        JButton btnRed;
        JButton btnBlue;
        JButton btnGreen;
        JButton btnKey;

        private static final String RED_BTN = "red_btn";
        private static final String BLUE_BTN = "blue_btn";
        private static final String GREEN_BTN = "green_btn";
        private static final String BLACK_BTN = "black_btn";

        public ButtonPanel() {
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            btnRed = new JButton("red");
            btnRed.setBackground(Color.RED);
            btnRed.setActionCommand(RED_BTN);
            btnRed.addActionListener(this);

            btnBlue = new JButton("blue");
            btnBlue.setBackground(Color.BLUE);
            btnBlue.setActionCommand(BLUE_BTN);
            btnBlue.addActionListener(this);

            btnGreen = new JButton("green");
            btnGreen.setBackground(Color.GREEN);
            btnGreen.setActionCommand(GREEN_BTN);
            btnGreen.addActionListener(this);

            btnKey = new JButton("black");
            btnKey.setBackground(Color.BLACK);
            btnKey.setActionCommand(BLACK_BTN);
            btnKey.addActionListener(this);

            add(btnRed);
            add(btnGreen);
            add(btnBlue);
            add(btnKey);

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            switch (command) {
                case RED_BTN:
                    color = Color.RED;
                    break;
                case BLUE_BTN:
                    color = Color.BLUE;
                    break;
                case GREEN_BTN:
                    color = Color.GREEN;
                    break;
                case BLACK_BTN:
                    color = Color.BLACK;
                    break;
            }
        }
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        javax.swing.JFrame frame = new PaintFrame();
        frame.setVisible(true);
    }
}
