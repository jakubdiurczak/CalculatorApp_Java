package com.mypackage;

import javax.swing.*;

public class Window extends JFrame {
    Window(){
        this.add(new Source());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(250,300);
        this.setTitle("CalculatorApp");
    }
}
