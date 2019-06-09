import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;

public class ReadServerFile extends JFrame {
    private JTextField enterField;
    private JEditorPane contentsArea;

    public ReadServerFile(){
        super("Simple File Reader");
        Container container = getContentPane();

        enterField = new JTextField("Enter file URL here");
        contentsArea = new JEditorPane();
        contentsArea.setEditable(false);

        // get Files specified by user
        enterField.addActionListener( new ActionListner() {
            public void actionPerformed( ActionEvent event) {
                getFile(event.getActionCommand());

            }
        });

        contentsArea.addHyperlinkListener( new HyperlinkListener() {
            // if user click on HyperLink go to specified page
            public void HyperlinkUpdate( HyperlinkEvent event) {
                if(event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    getFile(event.getURL().toString());
                }

            }
        });

        container.add(enterField, BorderLayout.NORTH);
        container.add(new ScrollPane(contentsArea), BorderLayout.CENTER);

        setSize(500, 400);
        setVisible(true);

    }

    private void getFile( String location) {
        try {
            enterField.setText(location);
            contentsArea.setPage(location);

        } catch(IOException e) {
            JOptionPane.showMessageDialog(this, "Error getting this file", "Bad URL", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String args[]){
        ReadServerFile app = new ReadServerFile();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
