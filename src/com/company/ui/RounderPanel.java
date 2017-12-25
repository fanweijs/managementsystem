package com.company.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by 柏欢欢 on 2017/12/20
 */
public class RounderPanel extends JPanel {
    private Color backColor;
    private Integer arc;

    public RounderPanel(Color backColor, Integer arc){
        super();
        this.backColor = backColor;
        this.arc = arc;
        this.revalidate();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(backColor);
        g.fillRoundRect(0, 0, getSize().width - 1, getSize().height - 1, arc ,arc);

    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(new Color(255,255,255,100));
        g.drawRoundRect(0, 0, getSize().width - 1, getSize().height - 1, arc ,arc);
        super.paintBorder(g);
    }


    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setSize(400,300);
        f.getContentPane().setBackground(Color.WHITE);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
        RounderPanel rounderPanel = new RounderPanel(Color.LIGHT_GRAY,200);
        f.add(rounderPanel);
    }
}
