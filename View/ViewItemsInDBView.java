package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ViewItemsInDBView {

    private JFrame frame;
    private DefaultListModel<String> listModel;
    private JList<String> textBoxList;
    private JButton backButton;

    public ViewItemsInDBView() {
        // Create the JFrame
        frame = new JFrame("Text Box List App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());


        // Create the list model and populate it with some sample data
        listModel = new DefaultListModel<>();

        // Create the JList with the list model
        textBoxList = new JList<>(listModel);
        textBoxList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Create the back button
        backButton = new JButton("Back");

        // Add components to the frame
        frame.add(new JScrollPane(textBoxList), BorderLayout.CENTER);
        frame.add(backButton, BorderLayout.SOUTH);

        // Make the frame visible
        frame.setVisible(false);



        }

    public void setBackButtonEventListener(ActionListener actionListener) {
        this.backButton.addActionListener(actionListener);
    }
    public void changeVisibility() {
        if (this.frame.isVisible()) {
            this.frame.setVisible(false);
        } else {
            this.frame.setVisible(true);
        }
    }

    public void setTextBoxList(String[] input){
        listModel.clear(); // Clear the existing elements from the list model
        for (String item : input) {
            listModel.addElement(item); // Add each item from the input array to the list model
        }
    }
}
