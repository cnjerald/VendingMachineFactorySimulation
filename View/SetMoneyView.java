package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;



public class SetMoneyView {
    private JFrame frame;
    private JList<String> list1;
    private DefaultListModel<String> list1Model = new DefaultListModel<>();
    private JList<String> list2;
    private DefaultListModel<String> list2Model= new DefaultListModel<>();
    private JLabel label1;
    private JLabel label2;
    private JTextField indexTextField;
    private JTextField amountTextField;
    private JButton backButton;
    private JButton confirmButton;


    public SetMoneyView(){
        // Create the JFrame
        frame = new JFrame("Dual List Selection App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // Create the label above the first list
        label1 = new JLabel("Coins: ");
        label1.setHorizontalAlignment(SwingConstants.CENTER);

        // Create the list1 model and populate it with some sample data
        list1Model = new DefaultListModel<>();


        // Create the list1 JList with the list1 model
        list1 = new JList<>(list1Model);
        list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Create the label above the second list
        label2 = new JLabel("Bills:");
        label2.setHorizontalAlignment(SwingConstants.CENTER);

        // Create the list2 model and populate it with some sample data
        list2Model = new DefaultListModel<>();


        // Create the list2 JList with the list2 model
        list2 = new JList<>(list2Model);
        list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Create the text fields for index and amount input
        indexTextField = new JTextField(10);
        amountTextField = new JTextField(10);

        // Create the back button
        backButton = new JButton("Back");

        // Create the confirm button
        confirmButton = new JButton("Confirm");


        // Create a panel for the first list and label1
        JPanel list1Panel = new JPanel(new BorderLayout());
        list1Panel.add(label1, BorderLayout.NORTH);
        list1Panel.add(new JScrollPane(list1), BorderLayout.CENTER);

        // Create a panel for the second list and label2
        JPanel list2Panel = new JPanel(new BorderLayout());
        list2Panel.add(label2, BorderLayout.NORTH);
        list2Panel.add(new JScrollPane(list2), BorderLayout.CENTER);

        // Create a panel for index and amount input fields
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        inputPanel.add(new JLabel("Input Index:"));
        inputPanel.add(indexTextField);
        inputPanel.add(new JLabel("Input Amount:"));
        inputPanel.add(amountTextField);

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(backButton);
        buttonPanel.add(confirmButton);

        // Add components to the frame
        JPanel listAndInputPanel = new JPanel(new GridLayout(1, 2));
        listAndInputPanel.add(list1Panel);
        listAndInputPanel.add(list2Panel);

        frame.add(listAndInputPanel, BorderLayout.CENTER);
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Make the frame visible
        frame.setVisible(false);
    }


    public void setCoins(String[] text){
        list1Model.clear();

        for (String item : text) {
            list1Model.addElement(item); // Add each item from the input array to the list model
        }
    }
    public void setBills(String[] text){
        list2Model.clear();
        for(String item : text){
            list2Model.addElement(item);
        }
    }

    public String getIndex(){
        return this.indexTextField.getText();
    }
    public String getAmount(){
        return this.amountTextField.getText();
    }

    public void setBackButtonEventListener(ActionListener actionListener){
        this.backButton.addActionListener(actionListener);
    }
    public void changeVisibility() {
        if (this.frame.isVisible()) {
            this.frame.setVisible(false);
        } else {
            this.frame.setVisible(true);
        }
    }
    public void setConfirmButtonEventListener(ActionListener actionListener){
        this.confirmButton.addActionListener(actionListener);
    }






}
