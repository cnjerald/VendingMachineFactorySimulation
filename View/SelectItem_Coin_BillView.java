package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SelectItem_Coin_BillView {

    private JFrame frame;
    private JLabel headerLabel;
    private JList<String> itemList;
    private DefaultListModel<String> listModel;
    private JButton backButton;
    private JButton confirmButton;

    private String text;

    public SelectItem_Coin_BillView(){
        // Create the frame
        frame = new JFrame("Swing Frame Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);

        // Create a panel for the content
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());

        // Header label
        headerLabel = new JLabel(text);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(headerLabel, BorderLayout.NORTH);

        // DefaultListModel for the list
        listModel = new DefaultListModel<>();

        // JList with the DefaultListModel
        itemList = new JList<>(listModel);
        JScrollPane listScrollPane = new JScrollPane(itemList);
        contentPanel.add(listScrollPane, BorderLayout.CENTER);

        // Create a panel for the input and buttons
        JPanel inputAndButtonPanel = new JPanel();
        inputAndButtonPanel.setLayout(new BorderLayout());

        // Input selection

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 10, 0)); // 1 row, 2 columns, horizontal gap: 10px

        // Back button
        backButton = new JButton("Back");
        buttonPanel.add(backButton);

        // Confirm button
        confirmButton = new JButton("Confirm");
        buttonPanel.add(confirmButton);

        // Add the button panel to the input and button panel
        inputAndButtonPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the input and button panel to the content panel
        contentPanel.add(inputAndButtonPanel, BorderLayout.SOUTH);

        // Add the content panel to the frame
        frame.add(contentPanel);
        frame.setVisible(false);
    }


    public int getSelection(){
        return itemList.getSelectedIndex();
    }

    public void setListModel(String[] text){
        listModel.clear();
        for(String item : text){
            listModel.addElement(item);
        }
    }

    public void setBackButtonEventListener(ActionListener actionListener) {
        this.backButton.addActionListener(actionListener);
    }

    public void setConfirmButtonEventListener(ActionListener actionListener){
        this.confirmButton.addActionListener(actionListener);
    }

    public void setText(String text){
        this.text = text;
    }

    public void changeVisibility() {
        if (this.frame.isVisible()) {
            this.frame.setVisible(false);
        } else {
            this.frame.setVisible(true);
        }
    }

}
