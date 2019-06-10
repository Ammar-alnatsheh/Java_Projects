import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SignatureApp extends JFrame {
    private int pointCount = 0;
    private Point points[] = new Point[5000];

    public SignatureApp(){
        super("Simple Signature App");

        addMouseMotionListener(new MouseMotionAdapter(){
            public void mouseDragged(MouseEvent e) {
                if( pointCount < points.length){
                    points[pointCount] = e.getPoint();
                    ++ pointCount;
                    repaint();
                }
             }
        });

        JButton clear = new JButton(" Clear ");
        clear.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
                points = new Point[5000];
                pointCount = 0;
                repaint();
            }
        });

        JPanel bar = new JPanel();
        bar.setLayout(new GridLayout(1,3,5,5));
        bar.add(new JLabel("Drag the mouse to draw"));
        bar.add(new JLabel(""));
        bar.add(clear);

        getContentPane().add(bar, BorderLayout.SOUTH);

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
