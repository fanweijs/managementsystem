package com.company.frame;

import com.company.factory.ServiceFactory;
import com.company.model.Info;
import com.company.service.InfoService;


import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by lihongyu on 2017/12/24.
 */
public class InfoPanel extends JPanel {
    private JPanel northPanel, southPanel;
    private JScrollPane sp;
    private JTextArea ta1,ta2;
    private JComboBox cb;
    private JButton button;
    private JList<Info> infoList;
    private DefaultListModel<Info> model = new DefaultListModel<>();
    private InfoService infoService = ServiceFactory.getInfoServiceInstance();
    private List<String>list = new ArrayList<>();
    private String account ;

    public InfoPanel(List<String>list,String account)  {
        this.list = list;
        this.account = account;

        init();
        if(list.size()==1){
            southPanel.setVisible(false);
            this.setLayout(new FlowLayout(FlowLayout.CENTER,251,352));
        }
    }

    public void init() {

        this.setLayout(new BorderLayout());
        //northPanel
        JPanel northPanel = new JPanel();
        infoList = new JList<>();
        java.util.List<Info> list = new ArrayList<>();
        list = infoService.getAll();
        for(Info info4:list){
            model.addElement(info4);
        }
        infoList.setModel(model);
        sp = new JScrollPane(infoList);
        sp.setPreferredSize(new Dimension(450, 200));
        northPanel.add(sp);
        this.add(northPanel, BorderLayout.NORTH);

        //southPanel
        southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(4, 1));
        JPanel[] panels = new JPanel[4];
        for (int i = 0; i < panels.length; i++) {
            panels[i] = new JPanel();
            panels[i].setLayout(new FlowLayout(FlowLayout.CENTER, 20, 50));
        }
        //第一行面板
        JTextArea ta1 = new JTextArea();
        ta1.setPreferredSize(new Dimension(400, 50));
        panels[0].add(ta1);
        //第二行面板
        JTextArea ta2 = new JTextArea();
        ta2.setPreferredSize(new Dimension(400, 50));
        panels[1].add(ta2);
        //第三行面板
        JComboBox cb = new JComboBox();
        cb.setPreferredSize(new Dimension(100, 35));
        cb.addItem("人事部");
        cb.addItem("财务部");
        panels[2].add(cb);
        //第四行面板
        JButton button = new JButton("发布");
        button.setBackground(Color.green);
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(75, 35));
        panels[3].add(button);
        for (int i = 0; i < panels.length; i++) {
            southPanel.add(panels[i]);
        }
        this.add(southPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

