package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainView {


    private JFrame frame;
    private JButton createVendingMachineButton;
    private JButton testVendingMachineButton;
    private JButton exitButton;

    private JLabel errorMsg;


    public MainView(){
        frame = new JFrame("Vending Machine Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);

        JPanel panel1 = new JPanel(new BorderLayout());

        JPanel panel2 = new JPanel();
        createVendingMachineButton = new JButton("Create a vending machine");
        panel2.add(createVendingMachineButton);

        JPanel panel3 = new JPanel();

        testVendingMachineButton = new JButton("Test a vending machine");
        panel3.add(testVendingMachineButton);

        errorMsg = new JLabel(" ");


        JPanel panel4 = new JPanel();
        panel4.setLayout(new BorderLayout());
        exitButton = new JButton("Exit");
        panel4.add(exitButton,BorderLayout.NORTH);

        JPanel errPanel = new JPanel();
        errPanel.add(errorMsg);

        panel4.add(errPanel,BorderLayout.SOUTH);

        panel1.add(panel2,BorderLayout.NORTH);
        panel1.add(panel3,BorderLayout.CENTER);
        panel1.add(panel4,BorderLayout.SOUTH);

        frame.add(panel1);
        frame.setVisible(true);

    }

    public void setCreateVMBtnListener(ActionListener actionListener){
        this.createVendingMachineButton.addActionListener(actionListener);
    }

    public void setTestVendingMachineButtonListener(ActionListener actionListener){
        this.testVendingMachineButton.addActionListener(actionListener);
    }
    public void setExitButtonListener(ActionListener actionListener){
        this.exitButton.addActionListener(actionListener);
    }

    public void setErrorMsg(String s){
        this.errorMsg.setText(s);
    }

    public void changeVisibility(){
        if(this.frame.isVisible()){
            this.frame.setVisible(false);
        }
        else{
            this.frame.setVisible(true);
        }
    }

    public void exit(){
        System.exit(0);
    }






}
