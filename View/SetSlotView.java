package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SetSlotView {

    private JFrame frame;
    private JList<String> indexList;
    private DefaultListModel<String> indexListModel;
    private JList<String> slotList;
    private DefaultListModel<String> slotListModel;
    private JButton backButton;
    private JButton confirmButton;
    private JLabel indexLabel;
    private JLabel slotLabel;
    public SetSlotView(){
        // Create the JFrame
        frame = new JFrame("Dual List Selection App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 500);
        frame.setLayout(new GridLayout(3, 1));
        frame.setLocationRelativeTo(null); // Center the frame on the screen

        // Create the index list model and populate it with some sample data
        indexListModel = new DefaultListModel<>();

        // Create the index JList with the index list model
        indexList = new JList<>(indexListModel);
        indexList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Create the slot list model and populate it with some sample data
        slotListModel = new DefaultListModel<>();


        // Create the slot JList with the slot list model
        slotList = new JList<>(slotListModel);
        slotList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Create labels for each JList
        indexLabel = new JLabel("Index Selection:");
        slotLabel = new JLabel("Slot Selection:");

        // Create the back button
        backButton = new JButton("Back");


        // Create the confirm button
        confirmButton = new JButton("Confirm");


        // Add components to the frame
        JPanel indexPanel = new JPanel(new BorderLayout());
        indexPanel.add(indexLabel, BorderLayout.NORTH);
        indexPanel.add(new JScrollPane(indexList), BorderLayout.CENTER);
        frame.add(indexPanel);

        JPanel slotPanel = new JPanel(new BorderLayout());
        slotPanel.add(slotLabel, BorderLayout.NORTH);
        slotPanel.add(new JScrollPane(slotList), BorderLayout.CENTER);
        frame.add(slotPanel);

        JPanel selectionPanel = new JPanel(new GridLayout(2, 1));
        frame.add(selectionPanel);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(backButton);
        buttonPanel.add(confirmButton);
        frame.add(buttonPanel);

        // Make the frame visible
        frame.setVisible(false);
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

    public void setConfirmButtonEventListener(ActionListener actionListener){
        this.confirmButton.addActionListener(actionListener);
    }

    public void setItemList(String[] list){
        indexListModel.clear();

        for (String item : list) {
            indexListModel.addElement(item); // Add each item from the input array to the list model
        }
    }

    public void setSlotList(String[] list){
        slotListModel.clear();
        for(String item : list){
            slotListModel.addElement(item);
        }
    }


    public int getItemIndex(){
        return this.indexList.getSelectedIndex();
    }

    public int getSlotIndex(){
        return this.slotList.getSelectedIndex();
    }








}
