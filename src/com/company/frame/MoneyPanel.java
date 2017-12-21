package com.company.frame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MoneyPanel extends JPanel implements ActionListener {
    private List<String>moneyList = new ArrayList<>();

    public MoneyPanel(List<String>moneyList){

        this.moneyList= moneyList;
        init();
    }

    public void init(){
        JButton[]jButtons = new JButton[moneyList.size()];
        for(int i=0;i<jButtons.length;i++){
            jButtons[i]=new JButton();

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
