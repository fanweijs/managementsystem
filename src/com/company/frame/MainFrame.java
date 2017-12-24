package com.company.frame;

import com.company.factory.ServiceFactory;
import com.company.service.UserService;
import com.company.utils.Style;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainFrame extends JFrame implements ActionListener {
    private JPanel mainPanel;
    private JPanel northPanel;
    private JPanel westPanel;
    private JPanel centerPanel;
    private CardLayout card =  new CardLayout();
    private JPanel menuPanel;
    private JButton 个人信息Button;
    private JButton 修改密码Button;
    private String account;
    private UserService userService = ServiceFactory.getUserSerivceInstance();
    private JButton[]permissionButtonGroup;
    private  List<String>infos=new ArrayList<>();//通知
    private  List<String>record=new ArrayList<>();//档案
    private  List<String>money=new ArrayList<>();//财务
    private  List<String>checkings=new ArrayList<>();//考勤
    private  List<String>rps=new ArrayList<>();//奖惩
    private  List<String>permission = new ArrayList<>();//权限

    public MainFrame(String account){
        this.account =account;
        add(mainPanel);
        init();
        setSize(new Dimension(800,1100));
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void init() {
        /**
         * 权限组
         */
        List<String> list = userService.userGetPermissionGroup(account);
        menuPanel.setLayout( new FlowLayout(FlowLayout.LEFT,50,70));
        menuPanel.setBackground(new Color(30 ,144 ,255));
        permissionButtonGroup = new JButton[list.size()+1];
        permissionButtonGroup[permissionButtonGroup.length-1]= new JButton("首页");
        menuPanel.add(permissionButtonGroup[permissionButtonGroup.length-1]);
        permissionButtonGroup[permissionButtonGroup.length-1].addActionListener(this);
        for(int i=0;i<list.size();i++){
            System.out.println(i);
            permissionButtonGroup[i]= new JButton(list.get(i));
            permissionButtonGroup[i].addActionListener(this);
            menuPanel.add(permissionButtonGroup[i]);
        }
        Style.setGroupButtonStyle(permissionButtonGroup);
        //面板添加
        Map<String,List<String>>map = new HashMap<>();
        map = userService.userGetPermissinItem(account);
        centerPanel.setLayout(card);

       JPanel firstPanel = new FirstPanel().getMainPanel();
        centerPanel.add(firstPanel,"0");

        infos =  map.get("通知");
        if(infos.size()!=0){
            JPanel jPanel = new JPanel();
            centerPanel.add(jPanel,"1");

        }


        record = map.get("档案");
        if(record.size()!=0){
            JPanel jPanel = new JPanel();
            jPanel.setBackground(Color.pink);
            centerPanel.add(jPanel,"2");
        }
        money= map.get("财务");
        if(money.size()!=0){
           SalaryPanel salaryPanel = new SalaryPanel(money,account);
            centerPanel.add(salaryPanel,"3");
        }

        checkings= map.get("考勤");
        if(checkings.size()!=0){
            CheckPanel checkPanel = new CheckPanel(checkings,account);
            centerPanel.add(checkPanel,"4");
        }

        rps =map.get("奖惩");
        if(rps.size()!=0){
            JPanel jPanel = new JPanel();
            centerPanel.add(jPanel,"5");
        }
        permission =map.get("权限");
        if(rps.size()!=0){
            JPanel jPanel = new JPanel();
            centerPanel.add(jPanel,"6");
        }


    }

    public static void main(String[] args) {
         new MainFrame("1001");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("首页")){
            card.show(centerPanel,"0");
        }
        if(e.getActionCommand().equals("档案管理")){
            card.show(centerPanel,"2");
        }
        if(e.getActionCommand().equals("通知管理")){
            card.show(centerPanel,"1");
        }
        if(e.getActionCommand().equals("权限管理")){
            card.show(centerPanel,"6");
        }
        if(e.getActionCommand().equals("考勤管理")){
            card.show(centerPanel,"4");
        }
        if(e.getActionCommand().equals("奖惩管理")){
            card.show(centerPanel,"5");
        }
        if(e.getActionCommand().equals("财务管理")){
                card.show(centerPanel,"3");
        }

    }


}
