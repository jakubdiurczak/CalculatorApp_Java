package com.mypackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class Source extends JPanel implements ActionListener {

    JPanel panelDisplay = new JPanel();
    JPanel panelBody = new JPanel();
    JPanel panelNumbers = new JPanel();
    JPanel panelSpecial = new JPanel();
    JPanel panelOperation = new JPanel();
    JTextField textDisplay = new JTextField("0");
    JTextField textDisplayHint = new JTextField("");

    String[] numbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    JButton[] jButtonsNumber = new JButton[numbers.length];
    java.util.List<String> numberList = new ArrayList<>(Arrays.asList(numbers));

    String[] special = {",", "R", "C", "CE"};
    JButton[] jButtonsSpecial = new JButton[special.length];
    java.util.List<String> specialList = new ArrayList<>(Arrays.asList(special));

    String[] operation = {"+", "-", "*", "/", "S", "P", "="};
    JButton[] jButtonsOperation = new JButton[operation.length];
    java.util.List<String> operationList = new ArrayList<>(Arrays.asList(operation));

    String operationBuffer = null;
    String operationSign = null;
    String operationSignFinal = null;

    public Source(){
        this.setLayout(new BorderLayout());

        this.add(panelDisplay, BorderLayout.NORTH);
        panelDisplay.setLayout(new BorderLayout());
        panelDisplay.add(textDisplayHint, BorderLayout.NORTH);
        textDisplayHint.setEditable(false);
        panelDisplay.add(textDisplay, BorderLayout.CENTER);
        textDisplay.setEditable(false);
        textDisplay.setBackground(Color.WHITE);
        textDisplay.setFont(new Font("Arial", Font.BOLD, 35));

        this.add(panelBody, BorderLayout.CENTER);
        panelBody.setLayout(new GridLayout(3,1));

        for (int i = 0; i < jButtonsNumber.length; i++) {
            jButtonsNumber[i] = new JButton(numbers[i]);
            panelNumbers.add(jButtonsNumber[i]);
            jButtonsNumber[i].addActionListener(this);
        }
        panelBody.add(panelNumbers);

        for (int i = 0; i < jButtonsOperation.length; i++) {
            jButtonsOperation[i] = new JButton(operation[i]);
            panelOperation.add(jButtonsOperation[i]);
            jButtonsOperation[i].addActionListener(this);
        }
        panelBody.add(panelOperation);

        for (int i = 0; i < jButtonsSpecial.length; i++) {
            jButtonsSpecial[i] = new JButton(special[i]);
            panelSpecial.add(jButtonsSpecial[i]);
            jButtonsSpecial[i].addActionListener(this);
        }
        panelBody.add(panelSpecial);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String getFromDisplay = textDisplay.getText();
        // get label from button to know what to do
        String value = e.getSource().toString();
        int getStringFrom = value.indexOf("text=") + 5;
        String valueFromButton = Character.toString(value.charAt(getStringFrom));

        // insert number to display (only 10 length)
        if(numberList.contains(valueFromButton) && getFromDisplay.length() < 10){
            // start and when choose sign, we need reset display
            if (getFromDisplay.equals("0") || operationSign != null) {
                textDisplay.setText(valueFromButton);
                // need to take sign to final operation, and only one
                if(operationSign != null) {
                    operationSignFinal = operationSign;
                }
                operationSign = null;
            } else {
                textDisplay.setText(getFromDisplay + valueFromButton);
            }
        }

        if(specialList.contains(valueFromButton)){
            // C clean input, CE clean everything
            if(valueFromButton.equals("C")){
                String valueExtend = value.substring(getStringFrom, getStringFrom + 2);
                textDisplay.setText("0");
                if(valueExtend.equals("CE")){
                    textDisplayHint.setText("");
                    operationBuffer = null;
                    operationSign = null;
                    operationSignFinal = null;
                }
            }
            if(valueFromButton.equals(",")){
                if(!getFromDisplay.contains(".")) {
                    textDisplay.setText(getFromDisplay + ".");
                }
            }
            if(valueFromButton.equals("R")){
                float valueRotate = (-1) * Float.parseFloat(getFromDisplay);
                textDisplay.setText(Float.toString(valueRotate));
            }
        }

        if(operationList.contains(valueFromButton)){
            if(valueFromButton.equals("+") || valueFromButton.equals("-")
                    || valueFromButton.equals("*") || valueFromButton.equals("/")) {
                textDisplayHint.setText(getFromDisplay + valueFromButton);
                // if buffer is full, and we put new number, we can calculate when chose another operation
                if (operationBuffer != null && operationSign == null) {
                    String doCalculation = calculation(operationSignFinal, operationBuffer, getFromDisplay);
                    textDisplay.setText(doCalculation);
                    textDisplayHint.setText(doCalculation + valueFromButton);
                    operationBuffer = doCalculation;
                } else{
                    operationBuffer = getFromDisplay;
                }
                operationSign = valueFromButton;
            } else{
                switch (valueFromButton) {
                    case "S" -> {
                        textDisplayHint.setText("sqrt(" + getFromDisplay + ")=");
                        textDisplay.setText(calculation(valueFromButton, getFromDisplay, "0"));
                    }
                    case "P" -> {
                        textDisplayHint.setText("pow(" + getFromDisplay + ")=");
                        textDisplay.setText(calculation(valueFromButton, getFromDisplay, "2"));
                    }
                    case "=" -> {
                        textDisplayHint.setText(textDisplayHint.getText() + getFromDisplay + "=");
                        textDisplay.setText(calculation(operationSignFinal, operationBuffer, getFromDisplay));
                    }
                }
                operationBuffer = null;
                operationSign = null;
                operationSignFinal = null;
            }
        }
    }

    public String calculation(String sign, String number1, String number2){
        // have some imprecision because of float
        float x = Float.parseFloat(number1);
        float y = Float.parseFloat(number2);
        return switch (sign) {
            case "+" -> Float.toString(x + y);
            case "-" -> Float.toString(x - y);
            case "*" -> Float.toString(x * y);
            case "/" -> y != 0 ? Float.toString(x / y) : "divide by zero";
            case "S" -> Float.toString((float) Math.sqrt(x));
            case "P" -> Float.toString((float) Math.pow(x, y));
            default -> null;
        };
    }
}
