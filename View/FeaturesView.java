package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FeaturesView {


        private JFrame frame;
        private JButton vmFeaturesButton;
        private JButton mFeaturesButton;
        private JButton backButton;
        public FeaturesView(){
            frame = new JFrame("Feature Menu");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 500);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new FlowLayout());


            JPanel panel1 = new JPanel(new BorderLayout());

            JPanel panel2 = new JPanel();
            vmFeaturesButton = new JButton("Vending Machine Features");
            panel2.add(vmFeaturesButton);

            JPanel panel3 = new JPanel();

            mFeaturesButton = new JButton("Maintainance Features");
            panel3.add(mFeaturesButton);

            JPanel panel4 = new JPanel();
            backButton = new JButton("Back");
            panel4.add(backButton);

            panel1.add(panel2,BorderLayout.NORTH);
            panel1.add(panel3,BorderLayout.CENTER);
            panel1.add(panel4,BorderLayout.SOUTH);

            frame.add(panel1);
            frame.setVisible(false);
        }

        public void setVmFeaturesButtonEventListener(ActionListener actionListener){
            this.vmFeaturesButton.addActionListener(actionListener);
        }

        public void setmFeaturesButtonListener(ActionListener actionListener){
            this.mFeaturesButton.addActionListener(actionListener);
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


    }

