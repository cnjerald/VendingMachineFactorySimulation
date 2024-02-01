package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VMCreationView {

    private JFrame frame;
    private JTextField nameTextField;
    private JTextField slotsTextField;
    private JTextField capacityTextField;

    private JLabel errorMessage;
    private JTextArea regularMachineList;

    private JButton backButton;
    private JButton confirmButton ;




    public VMCreationView(){
        frame = new JFrame("Vending Machine Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new FlowLayout());


        JPanel panel1 = new JPanel(new BorderLayout());
        JPanel panel2 = new JPanel(new BorderLayout());
        JPanel panel3 = new JPanel(new BorderLayout());
        JPanel panel4 = new JPanel(new BorderLayout());
        JPanel panel5 = new JPanel(new BorderLayout());

        JLabel nameLabel = new JLabel("Name:");
        nameTextField = new JTextField();
        nameTextField.setColumns(10);

        JLabel slotsLabel = new JLabel("Slots (Minimum 8):");
        slotsTextField = new JTextField();
        slotsTextField.setColumns(10);
        JLabel capacityLabel = new JLabel("CAPACITY(Minimum 10):");
        capacityTextField = new JTextField();
        capacityTextField.setColumns(10);

        panel2.add(nameLabel,BorderLayout.WEST);
        panel2.add(nameTextField,BorderLayout.CENTER);
        panel3.add(slotsLabel,BorderLayout.WEST);
        panel3.add(slotsTextField,BorderLayout.CENTER);
        panel4.add(capacityLabel,BorderLayout.WEST);
        panel4.add(capacityTextField,BorderLayout.CENTER);

        panel5.add(panel2,BorderLayout.NORTH);
        panel5.add(panel3,BorderLayout.CENTER);
        panel5.add(panel4,BorderLayout.SOUTH);

        JPanel panel7 = new JPanel();
        JPanel panel6 = new JPanel();
        errorMessage = new JLabel("\n");
        regularMachineList = new JTextArea(10,30);
        regularMachineList.setSize(50,50);
        panel6.setLayout(new BoxLayout(panel6, BoxLayout.Y_AXIS));

        panel7.add(regularMachineList);
        panel6.add(errorMessage);
        panel6.add(panel7);





        JPanel panel8 = new JPanel(new BorderLayout());
        backButton = new JButton("Back");
        confirmButton = new JButton("Confirm");
        panel8.add(backButton,BorderLayout.WEST);
        panel8.add(confirmButton,BorderLayout.EAST);


        panel1.add(panel5,BorderLayout.NORTH);
        panel1.add(panel6,BorderLayout.SOUTH);
        panel1.add(panel8,BorderLayout.CENTER);


        frame.add(panel1);
        frame.setVisible(false);
    }



    public void setConfirmButtonListener(ActionListener actionListener){
        this.confirmButton.addActionListener(actionListener);
    }

    public void setErrorMessage(String text){
        this.errorMessage.setText(text);
    }

    public void setBackButtonListener(ActionListener actionListener){
        this.backButton.addActionListener(actionListener);
    }

    public void setMachineList(String text){
        this.regularMachineList.setText(text);
    }

    public String getNameTextField() {
        return nameTextField.getText();
    }

    public String getSlotsTextField() {
        return slotsTextField.getText();
    }

    public String getCapacityTextField() {
        return capacityTextField.getText();
    }

    public void changeVisibility() {
        if (this.frame.isVisible()) {
            this.frame.setVisible(false);
        } else {
            this.frame.setVisible(true);
        }
    }




}
