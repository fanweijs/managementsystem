package com.company.ui;

import com.company.model.Salary;
import com.company.ui.Style;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by 柏欢欢 on 2017/12/19.
 */
public class MyMouseAdapter extends MouseAdapter {
    private JButton jButton;


    public MyMouseAdapter(JButton jButton) {
        this.jButton = jButton;
    }


    @Override
    public void mouseEntered(MouseEvent e) {
        Style.setChangeItem(jButton);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Style.setChangeItem(jButton);
    }

}
