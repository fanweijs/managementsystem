package com.company.frame;

import com.company.dao.PermissionDAO;
import com.company.dao.impl.PermissionDAOImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainFrame extends JFrame implements ActionListener{
    private JPanel mainPanel;
    private JPanel northPanel;
    private JPanel centerPanel;
    private JPanel westPanel;
    private JPanel menuPanel;
    private String account = null;
    private PermissionDAO permissionDAO = new PermissionDAOImpl();
    private List<String>infos=new ArrayList<>();
    private List<String>record=new ArrayList<>();
    private List<String>money=new ArrayList<>();
    private List<String>checkings=new ArrayList<>();
    private List<String>rps=new ArrayList<>();
    private List<String>permission = new ArrayList<>();
    private CardLayout card = new CardLayout();


    public MainFrame(String account) {
        this.account = account;
        init();
        add(mainPanel);
        setSize(new Dimension(1200,720));
        setVisible(true);
    }
    public void init(){
        menuPanel.setLayout(new FlowLayout(FlowLayout.LEFT,50,50));
        try {
            /**
             * 获取权限组 及 对应权限项
             */
            List<Integer> list = permissionDAO.getPermissionItem(account);
           for(int a:list){
               String id = permissionDAO.getOnePermissionGroup(a);
               String itemName = permissionDAO.getNamePermissionItem(a);
               if(id.equals("1")){
                   record.add(itemName);
               }
               if(id.equals("2")){
                   checkings.add(itemName);
               }
               if(id.equals("3")){
                   rps.add(itemName);
               }
               if(id.equals("4")){
                   money.add(itemName);
               }
               if(id.equals("5")){
                   infos.add(itemName);
               }
               if(id.equals("6")){
                   permission.add(itemName);
               }
           }
           infos.forEach(s -> System.out.println(s));
           record.forEach(s -> System.out.println(s));
            money.forEach(s -> System.out.println(s));
            checkings.forEach(s -> System.out.println(s));
            permission.forEach(s -> System.out.println(s));
            rps.forEach(s -> System.out.println(s));
            /**
             * 权限组
             */
            Set<Integer> set= new HashSet<>();
            set=permissionDAO.getAllPermissionGroup(list);
            JButton[]jButtons = new JButton[set.size()];
            for(int i=0;i<set.size();i++){
                jButtons[i]= new JButton();
                jButtons[i].setPreferredSize(new Dimension(100,40));
                //jButtons[i].setBackground(new Color(0,0,0,0));
                jButtons[i].setOpaque(true);
                jButtons[i].addActionListener(this);
            }
            int i=0;
            JButton firstButton = new JButton("首页");
            menuPanel.add(firstButton);
           for(int a:set){
              String  name =  permissionDAO.getNamePermissionGroup(a);
              jButtons[i].setText(name);
              menuPanel.add(jButtons[i]);
              i++;
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /**
         * 如果数组非空就创建相应的面板
         */
        centerPanel.setLayout(card);
        /**
         * cenerPanel 面板
         *  首页  通知 档案 财务 考勤 奖惩 权限
         */
        JPanel firstPanel = new JPanel();
        centerPanel.add("0",firstPanel);
        if(!infos.isEmpty()){
            InfoPanel infoPanel = new InfoPanel(infos);
            centerPanel.add("1", infoPanel);

        }
        if(!record.isEmpty()){
            JPanel recordPanel =  new JPanel();
            centerPanel.add("2",recordPanel);
        }
        if(!money.isEmpty()){
            JPanel moneyPanel = new JPanel();
            centerPanel.add("3",moneyPanel);
        }
        if(!checkings.isEmpty()){
            JPanel checkingPanel = new JPanel();
            centerPanel.add("4",checkingPanel);
        }
        if(!rps.isEmpty()){
            JPanel rpsPanel = new JPanel();
            centerPanel.add("5",rpsPanel);
        }
        if(!permission.isEmpty()){
            JPanel permissionPanel = new JPanel();
            centerPanel.add("6",permissionPanel);
        }




    }

    public static void main(String[] args) {
        new MainFrame("1002");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("首页"))
            card.show(centerPanel,"0");
        if(e.getActionCommand().equals("通知管理"))
          card.show(centerPanel,"1");
        if(e.getActionCommand().equals("档案管理"))
            card.show(centerPanel,"2");
        if(e.getActionCommand().equals("财务管理"))
            card.show(centerPanel,"3");
        if(e.getActionCommand().equals("考勤管理"))
            card.show(centerPanel,"4");
        if(e.getActionCommand().equals("奖惩管理"))
            card.show(centerPanel,"5");
        if(e.getActionCommand().equals("权限管理"))
            card.show(centerPanel,"6");
    }
}
