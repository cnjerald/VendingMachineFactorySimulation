package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RestockSlotView {
    private JFrame frame;
    private JList<String> slotList;
    private DefaultListModel<String> slotListModel;
    private JLabel label;
    private JTextField amountTextField;
    private JButton backButton;
    private JButton confirmButton;
    public RestockSlotView(){
        // Create the JFrame
        frame = new JFrame("Slot Selection App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // Create the label above the list
        label = new JLabel("Select an item from the list:");
        label.setHorizontalAlignment(SwingConstants.CENTER);

        // Create the item list model and populate it with some sample data
        slotListModel = new DefaultListModel<>();

        // Create the item JList with the item list model
        slotList = new JList<>(slotListModel);
        slotList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Create the text fields for slot selection and amount
        amountTextField = new JTextField(15);

        // Create the back button
        backButton = new JButton("Back");

        // Create the confirm button
        confirmButton = new JButton("Confirm");

        // Create a panel for the list and label
        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.add(label, BorderLayout.NORTH);
        listPanel.add(new JScrollPane(slotList), BorderLayout.CENTER);

        // Create a panel for slot selection and amount text fields
        JPanel textFieldPanel = new JPanel(new GridLayout(2, 2));
        textFieldPanel.add(new JLabel("Add amount:"));
        textFieldPanel.add(amountTextField);

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(backButton);
        buttonPanel.add(confirmButton);

        // Add components to the frame
        frame.add(listPanel, BorderLayout.CENTER);
        frame.add(textFieldPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Make the frame visible
        frame.setVisible(false);
    }

    public void setConfirmButtonEventListener(ActionListener actionListener){
        this.confirmButton.addActionListener(actionListener);
    }

    public String getAmount(){
        return this.amountTextField.getText();
    }
    public int getSlotIndex(){
        return this.slotList.getSelectedIndex();
    }

    public void setSlotList(String[] text){
        slotListModel.clear();

        for (String item : text) {
            slotListModel.addElement(item); // Add each item from the input array to the list model
        }
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
}

