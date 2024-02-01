package View;

import javax.swing.*;
import java.awt.event.ActionListener;

public class CustomerFeaturesView {

    private JFrame frame;
    private JButton selectItemButton;
    private JButton insertCoinsButton;
    private JButton insertBillsButton;
    private JButton DispenseSelectionButton;
    private JButton refundPaymentButton;
    private JButton backButton;
    private JButton button7;

    public CustomerFeaturesView() {
        frame = new JFrame("Feature Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS)); // Set vertical BoxLayout


        JPanel panel2 = new JPanel();
        selectItemButton = new JButton("Select  Item");
        panel2.add(selectItemButton);

        JPanel panel3 = new JPanel();
        insertCoinsButton = new JButton("Insert Coin");
        panel3.add(insertCoinsButton);

        JPanel panel4 = new JPanel();
        insertBillsButton = new JButton("InsertBills");
        panel4.add(insertBillsButton);

        // Add four more buttons
        JPanel panel5 = new JPanel();
        DispenseSelectionButton = new JButton("Dispense Selected Item");
        panel5.add(DispenseSelectionButton);

        JPanel panel6 = new JPanel();
        refundPaymentButton = new JButton("Refund Payment");
        panel6.add(refundPaymentButton);

        JPanel panel7 = new JPanel();
        backButton = new JButton("Back");
        panel7.add(backButton);


        // Add the button panels to the frame
        frame.add(panel2);
        frame.add(panel3);
        frame.add(panel4);
        frame.add(panel5);
        frame.add(panel6);
        frame.add(panel7);

        frame.setVisible(false);
    }

    public void setSelectItemButtonEventListener(ActionListener actionListener) {
        this.selectItemButton.addActionListener(actionListener);
    }

    public void setInsertCoinsButtonEventListener(ActionListener actionListener) {
        this.insertCoinsButton.addActionListener(actionListener);
    }

    public void setInsertBillsButtonEventListener(ActionListener actionListener) {
        this.insertBillsButton.addActionListener(actionListener);
    }


    public void setDispenseSelectionButtonEventListener(ActionListener actionListener) {
        this.DispenseSelectionButton.addActionListener(actionListener);
    }

    public void setRefundPaymentButtonEventListener(ActionListener actionListener) {
        this.refundPaymentButton.addActionListener(actionListener);
    }

    public void setBackButtonEventListener(ActionListener actionListener) {
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
