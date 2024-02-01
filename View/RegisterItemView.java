package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RegisterItemView {

    private JFrame frame;
    private JTextField itemNameField;
    private JTextField itemPriceField;
    private JTextField itemCaloriesField;
    private DefaultListModel<String> registeredItemsModel;
    private JList<String> registeredItemsList;
    private JButton backButton;
    private JButton confirmButton;

    private JLabel errorMessage;
    public RegisterItemView(){
        // Create the JFrame
        frame = new JFrame("Item Registration App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // Create the panel for item registration
        JPanel registrationPanel = new JPanel(new GridLayout(3, 2));
        itemNameField = new JTextField();
        itemPriceField = new JTextField();
        itemCaloriesField = new JTextField();
        registrationPanel.add(new JLabel("Item Name:"));
        registrationPanel.add(itemNameField);
        registrationPanel.add(new JLabel("Item Price:"));
        registrationPanel.add(itemPriceField);
        registrationPanel.add(new JLabel("Item Calories:"));
        registrationPanel.add(itemCaloriesField);

        // Create the list model for registered items
        registeredItemsModel = new DefaultListModel<>();
        registeredItemsList = new JList<>(registeredItemsModel);
        JScrollPane listScrollPane = new JScrollPane(registeredItemsList);

        // Create the back button
        backButton = new JButton("Back");


        // Create the confirm button
        confirmButton = new JButton("Confirm");

        // Add components to the frame
        frame.add(registrationPanel, BorderLayout.NORTH);
        frame.add(listScrollPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(backButton);
        buttonPanel.add(confirmButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Make the frame visible
        frame.setVisible(false);
    }


    public void addConfirmButtonActionListener(ActionListener actionListener){
        this.confirmButton.addActionListener(actionListener);
    }

    public String getItemNameField(){
        return this.itemNameField.getText();
    }
    public String getItemPriceField(){
        return this.itemPriceField.getText();
    }
    public String getItemCaloriesField(){
        return this.itemCaloriesField.getText();
    }

    public void changeVisibility() {
        if (this.frame.isVisible()) {
            this.frame.setVisible(false);
        } else {
            this.frame.setVisible(true);
        }
    }

    public void setBackButtonEventListener(ActionListener actionListener){
        this.backButton.addActionListener(actionListener);
    }

    public void setTextBoxList(String[] input){
        registeredItemsModel.clear(); // Clear the existing elements from the list model
        for (String item : input) {
            registeredItemsModel.addElement(item); // Add each item from the input array to the list model
        }
    }







}
