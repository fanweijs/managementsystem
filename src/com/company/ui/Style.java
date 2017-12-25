package com.company.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by 柏欢欢 on 2017/12/19.
 */
public class Style {
    private final static Dimension GROUP_BUTTON = new Dimension(100,40);
    private final static Dimension ITEM_BUTTON = new Dimension(150,30);
    private final static Dimension NOMAL_BUTTON = new Dimension(110,30);
    private final static Dimension FIELD_SIZE = new Dimension(180,30);

    private final static Font GROUP_FONT = new Font("微软雅黑",Font.PLAIN,25);
    private final static Font ITEM_FONT = new Font("微软雅黑",Font.PLAIN,22);
    private final static Font NOMAL_FONT = new Font("微软雅黑",Font.PLAIN,20);
    private final static Font SMALL_FONT = new Font("微软雅黑",Font.PLAIN,18);

    private final static Color COLOR1 = new Color(4,173,132);
    private final static Color COLOR2 = new Color(255,255,255);
    private final static Color COLOR3 = new Color(58,94,173);

    public static void setGroupButtonStyle(JButton[] jButtons){
        for(JButton jButton :jButtons) {
            jButton.setPreferredSize(GROUP_BUTTON);
            jButton.setFont(GROUP_FONT);
            jButton.setBackground(COLOR1);
            jButton.setBorder(null);
            jButton.setForeground(Color.BLACK);
            //jButton.setOpaque(false);
        }
    }

    public static void setNomalButtonStyle(JButton jButton){
        jButton.setPreferredSize(NOMAL_BUTTON);
        jButton.setBackground(COLOR1);
        jButton.setFont(NOMAL_FONT);
        jButton.setForeground(COLOR2);

    }

    public static void setItemButtonStyle(JButton jButton){

            jButton.setPreferredSize(ITEM_BUTTON);
            jButton.setBackground(COLOR1);
            jButton.setFont(ITEM_FONT);
            jButton.setForeground(COLOR2);
            jButton.setBorder(null);


    }

    public static void setFieldStyle(JComponent jComponent){
        jComponent.setPreferredSize(FIELD_SIZE);
        jComponent.setFont(SMALL_FONT);
    }

    public static void setLabelStyle(JComponent jComponent){
        jComponent.setFont(NOMAL_FONT);
    }

    public static void setChangeGroup(JComponent jComponent){

            jComponent.setBackground(COLOR3);
            jComponent.setForeground(Color.WHITE);


    }

    public static void setChangeItem(JComponent jComponent){

            jComponent.setBackground(COLOR3);
            jComponent.setForeground(Color.BLACK);


    }
}
