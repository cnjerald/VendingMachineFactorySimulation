package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetSlotAddChoiceView extends JDialog{
    private JLabel label;
    private JButton yesButton;
    private JButton noButton;
    private boolean decision = false;


    public SetSlotAddChoiceView() {
        this.setModal(true);
        this.setTitle("Decision Dialog");
        this.setSize(500, 200); // Set the size of the dialog
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        label = new JLabel("There is an item in this slot, do you want to replace it?", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 12));

        noButton = new JButton("No");
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decision = false;
                dispose(); // Close the dialog
            }
        });

        yesButton = new JButton("Yes");
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decision = true;
                dispose(); // Close the dialog
            }
        });

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);

        // Add components to the dialog
        this.add(label, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

        // Make the dialog visible
        this.setVisible(true);
    }

    public boolean getDecision(){
        return this.decision;
    }
}
