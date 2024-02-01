package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CreateVMView {

    JFrame frame = new JFrame();
    JButton regularButton;
    JButton specialButton;
    JButton backButton;
    public CreateVMView(){
        frame = new JFrame("Vending Machine Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new FlowLayout());


        JPanel panel1 = new JPanel(new BorderLayout());

        JPanel panel2 = new JPanel();
        regularButton = new JButton("Regular Vending Machine");
        panel2.add(regularButton);

        JPanel panel3 = new JPanel();

        specialButton = new JButton("Special Vending Machine");
        panel3.add(specialButton);

        JPanel panel4 = new JPanel();
        backButton = new JButton("Back");
        panel4.add(backButton);

        panel1.add(panel2,BorderLayout.NORTH);
        panel1.add(panel3,BorderLayout.CENTER);
        panel1.add(panel4,BorderLayout.SOUTH);

        frame.add(panel1);
        frame.setVisible(false);
    }

    public void setRegularButtonEventListener(ActionListener actionListener){
        this.regularButton.addActionListener(actionListener);
    }



    public void setBackButtonEventListener(ActionListener actionListener){
        this.backButton.addActionListener(actionListener);
    }

    public void setSpecialButtonListener(ActionListener actionListener){
        this.specialButton.addActionListener(actionListener);
    }

    public void changeVisibility() {
        if (this.frame.isVisible()) {
            this.frame.setVisible(false);
        } else {
            this.frame.setVisible(true);
        }
    }
}
