package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FluidContainersView {

        private JFrame frame;
        private DefaultListModel<String> listModel;
        private JList<String> myList;
        private JTextField amountField;
        private JButton backButton;
        private JButton fillAllButton;
        private JButton confirmButton;


    public FluidContainersView() {
            frame = new JFrame("List with Header Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 500);
            frame.setLocationRelativeTo(null);

            // Header label for the list
            JLabel headerLabel = new JLabel("List Items");
            headerLabel.setFont(new Font("Arial", Font.BOLD, 16));

            // Create the list and its model
            listModel = new DefaultListModel<>();
            myList = new JList<>(listModel);

            // Text fields for input
            amountField = new JTextField(10);

            // Buttons: Add, Edit, and Delete
            backButton = new JButton("Back");
            fillAllButton = new JButton("Fill all");
            confirmButton = new JButton("Confirm");

            // Panel for buttons
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(backButton);
            buttonPanel.add(fillAllButton);
            buttonPanel.add(confirmButton);

            // Panel for text fields
            JPanel textFieldPanel = new JPanel();
            textFieldPanel.add(new JLabel("Set amount"));
            textFieldPanel.add(amountField);

            // Set layout using BorderLayout for the main content panel
            JPanel contentPanel = new JPanel(new BorderLayout());

            // Add components to the main content panel using BorderLayout
            contentPanel.add(headerLabel, BorderLayout.NORTH);
            contentPanel.add(new JScrollPane(myList), BorderLayout.CENTER);
            contentPanel.add(textFieldPanel, BorderLayout.PAGE_START);
            contentPanel.add(buttonPanel, BorderLayout.PAGE_END);

            // Set the main content panel to the frame
            frame.setContentPane(contentPanel);

            frame.setVisible(false);
        }

        public void setBackButtonEventListener(ActionListener actionListener){
            this.backButton.addActionListener(actionListener);
        }

        public void setFillAllButtonEventListener(ActionListener actionListener){
            this.fillAllButton.addActionListener(actionListener);
        }
        public void setConfirmButtonEventListener(ActionListener actionListener){
            this.confirmButton.addActionListener(actionListener);
        }

        public void setMylist(String[] list){
            listModel.clear(); // Clear the existing elements from the list model
            for (String item : list) {
                listModel.addElement(item); // Add each item from the input array to the list model
            }
        }

        public void changeVisibility() {
            if (this.frame.isVisible()) {
                this.frame.setVisible(false);
            } else {
                this.frame.setVisible(true);
            }
        }

        public int getIndexField(){
            return myList.getSelectedIndex();
        }

        public String getAmountField(){
            return this.amountField.getText();
        }











}



