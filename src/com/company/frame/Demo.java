package com.company.frame;

import javax.swing.*;
import java.awt.*;

public class Demo {
    private JPanel mainPanel;
    private JPanel panel2;
    private JButton button1;
    private JButton button2;
    public Demo(){

    }
    public JPanel getPanel2(){
        return panel2;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Demo");
        frame.setLayout(new GridLayout(1,1));
        JPanel jPanel = new Demo().getPanel2();
        frame.add(jPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
