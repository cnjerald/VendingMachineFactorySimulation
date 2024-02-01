package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessagePromptView extends JDialog{

    private JButton confirmButton;


    public MessagePromptView(String label){
        this.setModal(true);
        this.setTitle("Message GUI");

        JTextArea messageTextArea = new JTextArea();
        messageTextArea.setText(label);
        messageTextArea.setEditable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        mainPanel.add(messageTextArea);

        confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        mainPanel.add(confirmButton);

        this.add(mainPanel);
        this.pack(); // Pack the components to fit their preferred sizes

        // Set a minimum size to ensure both message and button are visible
        int minWidth = 300;
        int minHeight = 300;
        this.setMinimumSize(new Dimension(minWidth, minHeight));

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
