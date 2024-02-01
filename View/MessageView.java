package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessageView extends JDialog {
    private JTextArea messageTextArea;

    public MessageView(String label , int delay) {
        this.setModal(true);
        this.setTitle("Message GUI");

        messageTextArea = new JTextArea();
        messageTextArea.setText(label);
        messageTextArea.setEditable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        mainPanel.add(messageTextArea);

        this.add(mainPanel);
        this.pack(); // Pack the components to fit their preferred sizes

        // Set a minimum size to ensure both message and button are visible
        int minWidth = 300;
        int minHeight = 300;
        this.setMinimumSize(new Dimension(minWidth, minHeight));

        this.setLocationRelativeTo(null);

        // Automatically close the dialog after 5 seconds (5000 milliseconds)
        Timer timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                destroy();
            }
        });
        timer.setRepeats(false);
        timer.start();

        this.setVisible(true);
    }


    public void changeModal(){
        this.setModal(false);
    }

    public void destroy() {
        this.dispose();
    }
}
