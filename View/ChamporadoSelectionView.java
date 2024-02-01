package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ChamporadoSelectionView {

    private JFrame frame;
    private JList<String> itemList;
    private DefaultListModel<String> listModel;
    private JButton backButton;
    private JButton confirmButton;
    private JLabel amountLabel;
    private JPanel inputPanel;



    public ChamporadoSelectionView(String labelText, String backButtonText){
        frame = new JFrame("List Selection App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // Create the list model and populate it with some sample data
        listModel = new DefaultListModel<>();

        // Create the JList with the list model
        itemList = new JList<>(listModel);
        itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Create the amount components
        amountLabel = new JLabel(labelText);

        // Create the back button
        backButton = new JButton(backButtonText);

        // Create the confirm button
        confirmButton = new JButton("Confirm");

        // Create a panel for the amount label and center it
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerPanel.add(amountLabel);

        // Create a panel for index selection and amount components
        inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(centerPanel, BorderLayout.CENTER);

        // Add components to the frame
        frame.add(new JScrollPane(itemList), BorderLayout.NORTH);
        frame.add(inputPanel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(backButton);
        buttonPanel.add(confirmButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Make the frame visible
        frame.setVisible(false);
    }


    public void setConfirmButtonEventListener(ActionListener actionListener){
        this.confirmButton.addActionListener(actionListener);
    }

    public void setBackButtonEventListener(ActionListener actionListener){
        this.backButton.addActionListener(actionListener);
    }


    public void setListModel(String[] text){
        listModel.clear(); // Clear the existing elements from the list model
        for (String item : text) {
            listModel.addElement(item); // Add each item from the input array to the list model
        }
    }

    public int getSelection(){
        return this.itemList.getSelectedIndex();
    }

    public void changeVisibility() {
        if (this.frame.isVisible()) {
            this.frame.setVisible(false);
        } else {
            this.frame.setVisible(true);
        }
    }



}
