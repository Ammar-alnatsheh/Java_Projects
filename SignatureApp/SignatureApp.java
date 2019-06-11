import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

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

        JButton save = new JButton(" Save ");
        clear.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
                save();
            }
        });

        JPanel bar = new JPanel();
        bar.setLayout(new GridLayout(1,5,5,5));
        bar.add(new JLabel("Drag the mouse to draw"));
        bar.add(clear);
        bar.add(save);

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

    private void save()
    {
        JFileChooser filechooser = new JFileChooser();
        filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int action = filechooser.showSaveDialog(null);

        if ( action == JFileChooser.CANCEL_OPTION ) {
            return;
        }
        
        File file = filechooser.getSelectedFile();
        if ( file == null || file.getName().equals("") ) {
            JOptionPane.showMessageDialog(null,"Invalid file name","Invalid file name",JOptionPane.ERROR_MESSAGE);
        
        } else {
            try
            {
                BufferedImage img = new BufferedImage(500, 200, BufferedImage.TYPE_INT_ARGB);
                Graphics2D graph = img.createGraphics();
                graph.setBackground(Color.white);
                graph.setColor(Color.white);
                graph.fillRect(0, 0, 500, 200);
                graph.setColor(Color.black);

                for( int i = 0; i < points.length && points[i] != null; i++){
                    graph.fillOval(points[i].x, points[i].y, 4, 4);
                }
                
                graph.dispose();
                //Export the result to a file
                ImageIO.write(img, "PNG", file);
                
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,"Error saving file","Error saving file",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
