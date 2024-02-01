package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CreateChamporadoView {

    private JFrame frame;
    private JButton champoradoButton;
    private JButton addIngredientsButton;

    private JButton clearSelectionButton;

    private JButton backButton;

    private JTextArea textArea;


    public CreateChamporadoView(){
        frame = new JFrame();
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textArea = new JTextArea(10, 30);
        textArea.setEditable(false);

        JLabel label = new JLabel("My champorado");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font(label.getFont().getName(), Font.PLAIN, 24));
        JPanel panel1 = new JPanel(new BorderLayout());
        panel1.add(label, BorderLayout.NORTH);
        panel1.add(textArea, BorderLayout.CENTER);

        champoradoButton = new JButton("Create a champorado");
        addIngredientsButton = new JButton("Add Ingredients");
        clearSelectionButton = new JButton("Clear Selection");
        backButton = new JButton("Back");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1, 5, 5)); // 4 rows, 1 column, 5 pixels spacing

        buttonPanel.add(champoradoButton);
        buttonPanel.add(addIngredientsButton);
        buttonPanel.add(clearSelectionButton);
        buttonPanel.add(backButton);

        JPanel panel2 = new JPanel(new BorderLayout());
        panel2.add(buttonPanel, BorderLayout.NORTH);

        frame.add(panel1, BorderLayout.NORTH);
        frame.add(panel2, BorderLayout.CENTER);

        frame.setVisible(false);
    }


    public void setTextArea(ArrayList<String> text){
        if (textArea == null) {
            textArea = new JTextArea();
        }

        StringBuilder sb = new StringBuilder();
        for (String line : text) {
            sb.append(line).append("\n");
        }
        String textToSet = sb.toString();

        textArea.setText(textToSet);
    }

    public void champoradoButtonEventListener(ActionListener actionListener){
        this.champoradoButton.addActionListener(actionListener);
    }

    public void addIngredientsButtonEventListener(ActionListener actionListener){
        this.addIngredientsButton.addActionListener(actionListener);
    }

    public void clearSelectionButtonEventListener(ActionListener actionListener){
        this.clearSelectionButton.addActionListener(actionListener);
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
