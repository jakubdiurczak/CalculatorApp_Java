package com.mypackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SourceCopy extends JPanel implements ActionListener {

    JPanel panelDisplay = new JPanel();
    JPanel panelButtons = new JPanel();
    JTextField textDisplay = new JTextField("0");

    JButton button0 = new JButton("0");
    JButton button1 = new JButton("1");
    JButton button2 = new JButton("2");
    JButton button3 = new JButton("3");
    JButton button4 = new JButton("4");
    JButton button5 = new JButton("5");
    JButton button6 = new JButton("6");
    JButton button7 = new JButton("7");
    JButton button8 = new JButton("8");
    JButton button9 = new JButton("9");
    JButton buttonComa = new JButton(",");
    JButton buttonChange = new JButton("+/-");
    JButton buttonCount = new JButton("=");
    JButton buttonPlus = new JButton("+");
    JButton buttonMinus = new JButton("-");
    JButton buttonMultiply = new JButton("*");
    JButton buttonDivide = new JButton("/");
    JButton buttonClean = new JButton("C");
    JButton buttonCleanAll = new JButton("CE");
    JButton buttonBack = new JButton("<-");

    public SourceCopy(){
        this.setLayout(new BorderLayout());

        this.add(panelDisplay, BorderLayout.NORTH);
        panelDisplay.setLayout(new BorderLayout());
        panelDisplay.add(textDisplay, BorderLayout.CENTER);
        textDisplay.setEditable(false);
        textDisplay.setBackground(Color.WHITE);
        textDisplay.setFont(new Font("Arial", Font.BOLD, 12));
        textDisplay.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        this.add(panelButtons, BorderLayout.CENTER);
        panelButtons.setLayout(new GridLayout(5,4,10,10));
        panelButtons.add(buttonClean);
        panelButtons.add(buttonCleanAll);
        panelButtons.add(buttonBack);
        panelButtons.add(buttonDivide);
        panelButtons.add(button7);
        panelButtons.add(button8);
        panelButtons.add(button9);
        panelButtons.add(buttonMultiply);
        panelButtons.add(button4);
        panelButtons.add(button5);
        panelButtons.add(button6);
        panelButtons.add(buttonMinus);
        panelButtons.add(button1);
        panelButtons.add(button2);
        panelButtons.add(button3);
        panelButtons.add(buttonPlus);
        panelButtons.add(buttonChange);
        panelButtons.add(buttonComa);
        panelButtons.add(button0);
        panelButtons.add(buttonCount);

        button0.addActionListener(this);
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);
        button6.addActionListener(this);
        button7.addActionListener(this);
        button8.addActionListener(this);
        button9.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button0 || e.getSource() == button1 || e.getSource() == button2
                || e.getSource() == button3 || e.getSource() == button4 || e.getSource() == button5
                || e.getSource() == button6 || e.getSource() == button7 || e.getSource() == button8
                || e.getSource() == button9){
            String getFromDisplay = textDisplay.getText();
            String value = e.getSource().toString();
            String valueFromButton = Character.toString(value.charAt(value.indexOf("text=") + 5));
            if (getFromDisplay.equals("0")) {
                textDisplay.setText(valueFromButton);
            } else {
                textDisplay.setText(getFromDisplay + valueFromButton);
            }
        }
    }
}
