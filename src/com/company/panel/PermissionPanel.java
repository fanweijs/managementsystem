package com.company.panel;



import com.company.model.Employee;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PermissionPanel  extends JPanel{
    private JPanel mainPanel;
    private JPanel northPanel;
    private JTextField textField1;
    private JButton button1;
    private JTable table1;
    private List<String> list=new ArrayList<>();
    private String account;
    public PermissionPanel(List<String>list,String account){
        this.list = list;
        this.account = account;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }


}
