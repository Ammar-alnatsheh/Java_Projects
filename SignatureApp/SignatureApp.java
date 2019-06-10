import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SignatureApp extends JFrame {
    private int pointCount = 0;
    private Point points[] = new Point[5000];

    public SignatureApp(){
        super("Simple Signature App");
        getContentPane().add(new JLabel("Drag the mouse to draw"), BorderLayout.SOUTH);

        addMouseMotionListener(new MouseMotionAdapter(){
            public void mouseDragged(MouseEvent e) {
                if( pointCount < points.length){
                    points[pointCount] = e.getPoint();
                    pointCount ++;
                    repaint();
                }
             }
        });

        setSize(500, 200);
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width  - getSize().width) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);
        setVisible(true);

    }

    public void paint(Graphics g) {
        super.paint(g);
        for( int i = 0; i < points.length && points[i] != null; i++){
            g.fillOval(points[i].x, points[i].y, 4, 4);
        }
    }

    public static void main(String args[]){
        SignatureApp app = new SignatureApp();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
