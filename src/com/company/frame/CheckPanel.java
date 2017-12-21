package com.company.frame;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xian on 2017/12/19.
 */
public class CheckPanel extends JPanel implements ActionListener {
    private List<String> checkList = new ArrayList<>();
    private CardLayout card = new CardLayout();
    private JPanel centerPanel = new JPanel();
    private JPanel card1;
    private JPanel card2;
    private JTable table1,table2;
    private DefaultTableModel dtm,dtm2;
    private String account;

    public CheckPanel(List<String> checkList,String account) {
        this.checkList = checkList;
        this.account =account;
        init();
    }

    public void init() {
        showPersonTable();
//        showStuffTable();
        JButton[] jButtons = new JButton[checkList.size()];
        int i = 0;
        for (String str : checkList) {
            jButtons[i] = new JButton(str);
            jButtons[i].addActionListener(this);
            i++;
        }
        this.setLayout(new BorderLayout());
        JPanel westPanel = new JPanel();
        westPanel.setLayout(new GridLayout(7, 10, 10, 50));
        westPanel.setBackground(Color.GRAY);
        JLabel jLabel = new JLabel();
        westPanel.add(jLabel);
        for (int c = 0; c < jButtons.length; c++) {
            westPanel.add(jButtons[c]);
        }
        add(BorderLayout.WEST, westPanel);
        centerPanel.setLayout(card);

        /**
         * 个人考勤
         */
        card1 = getCard1();
        centerPanel.add(card1, "1");

        /**
         * 考勤管理
         */
        card2 = getCard2();
        centerPanel.add(card2, "2");
        add(BorderLayout.CENTER, centerPanel);
    }

    public JPanel getCard1() {
        card1 = new JPanel();
        card1.setLayout(new BorderLayout());
        card1.setBackground(Color.CYAN);
        //northPanel
        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        JButton searchButton = new JButton("搜索");
        JTextField searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(150, 35));
        searchButton.setBackground(Color.CYAN);
        northPanel.add(searchField);
        northPanel.add(searchButton);
        card1.add(BorderLayout.NORTH, northPanel);

        //centerPanel
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,60,60));
        centerPanel.setBackground(Color.WHITE);

//        Object[][] playerInfo = {
//                //创建表格中的数据
//                {  "姓名", "工号", "时间", "考勤", "加班时间/h"  },
//                {  "王鹏", new Integer(12345678), new String("2017-12-20"), "全勤",new Integer(10)}
//        };
//
//        String[] Names = { "姓名","工号","时间","考勤","加班时间/h"};
//        JTable table = new JTable(playerInfo,Names);
//        table.setPreferredSize(new Dimension(550,30));

//        table = new JTable(10,5);



        card1.add(BorderLayout.CENTER, centerPanel);
        centerPanel.add(table1);
        return card1;
    }

    public JPanel getCard2() {
        JPanel card2 = new JPanel();
        card2.setBackground(Color.gray);


        return card2;
    }
    public void showPersonTable(){
        dtm = new DefaultTableModel();
        String[] titles = {"考勤编号","工号","考勤日期","考勤状况"};
        //设置表头的标题列
        dtm.setColumnIdentifiers(titles);
        //给表格设置数据模型
        table1.setModel(dtm);
        //设置单元格内容
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        //设置水平方向居中
        r.setHorizontalAlignment(JLabel.CENTER);
        table1.setDefaultRenderer(Object.class,r);
        //将表头居中
        DefaultTableCellRenderer r1 = new DefaultTableCellRenderer();
        r1.setHorizontalAlignment(JLabel.CENTER);
        r1.setBackground(Color.LIGHT_GRAY);
        table1.getTableHeader().setDefaultRenderer(r1);
        //内容字符串数组
        String[] content = new String[7];
        //获取到数据库中所有用户信息
//        students = adminService.getAll();
//        Iterator<Student> iterator = students.iterator();
//        while (iterator.hasNext()) {
//            Student student = iterator.next();
//            content[0] = student.getStudentID();
//            content[1] = student.getStudentName();
//            content[2] = student.getGender();
//            content[3] = student.getAddress();
//            content[4] = student.getMobile();
//            content[5] = student.getEnrollment().toString();
//            dtm.addRow(content);
//        }
        card1.revalidate();
        centerPanel.revalidate();
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("测试窗体");
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        List<String> list = new ArrayList<>();
        list.add("个人考勤");
        list.add("考勤管理");
        frame.add(new CheckPanel(list,"1001"));
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("个人考勤"))
            card.show(centerPanel, "1");
        if (e.getActionCommand().equals("考勤管理"))
            card.show(centerPanel, "2");
    }
}


