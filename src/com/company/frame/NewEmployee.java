package com.company.frame;

import com.company.factory.ServiceFactory;
import com.company.model.Employee;
import com.company.service.EmployeeService;
import com.company.utils.DialogDatePicker;
import com.company.utils.FileUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by 高展 on 2017/12/24.
 */
public class NewEmployee extends JFrame{
    private JButton 保存Button;
    private JTextField accountField;
    private JTextField departmentField;
    private JTextField nameField;
    private JRadioButton 男RadioButton;
    private JRadioButton 女RadioButton;
    private JButton 选择时间Button1;
    private JComboBox comboBox1;
    private JRadioButton 已婚RadioButton;
    private JRadioButton 未婚RadioButton;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JTextField phoneField;
    private JComboBox comboBox4;
    private JComboBox comboBox5;
    private JButton 选择时间Button;
    private JButton 选择照片Button;
    private JPanel mainPanel;
    private JLabel pictureLabel;
    private JLabel birthdayLabel;
    private JLabel dateLabel;
    private JComboBox comboBox6;
    private byte[] b=new byte[2];
    private EmployeeService employeeService= ServiceFactory.getEmployeeServiceInstance();
    private ArciviesPanel arciviesPanel;
    private String dateString;
    private String politiacal_statusString;
    private String positionString;
    private String educationString;
    private String birthdayLabelString;
    private String native_provinceSting;
    private String native_cityString;
    private Integer departmentID;

    private List<String>[]lists;
    private Map<String ,List<String>>map;

    public NewEmployee(ArciviesPanel arciviesPanel){
        this.arciviesPanel=arciviesPanel;
        setTitle("添加员工");
        setSize(900,800);
        setLocationRelativeTo(null);
        setVisible(true);
        add(mainPanel);

        //二级联动菜单
        lists=new List[2];
        lists[0]=new ArrayList<>();
        lists[0].add("南京");
        lists[0].add("苏州");
        lists[1]=new ArrayList<>();
        lists[1].add("杭州");
        lists[1].add("嘉兴");
        map=new HashMap<>();
        map.put("江苏",lists[0]);
        map.put("浙江",lists[1]);
        for (String str:map.keySet()){
            comboBox4.addItem(str);
            for (String str1:map.get("浙江")){
                comboBox5.addItem(str1);
            }
        }

        ButtonGroup group=new ButtonGroup();
        group.add(男RadioButton);
        group.add(女RadioButton);
        ButtonGroup group1=new ButtonGroup();
        group1.add(已婚RadioButton);
        group1.add(未婚RadioButton);
        选择照片Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser=new JFileChooser();
                int n=chooser.showOpenDialog(NewEmployee.this);
                if (n == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    b = FileUtils.fileToBytes(file);
                    pictureLabel.setIcon(new ImageIcon(b));
                }
            }
        });
        //选择出生日期按钮
        选择时间Button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField txtDate=new JTextField();
                //弹出时间选择框
                new DialogDatePicker(true,txtDate,750,450);
                //获得日期
                birthdayLabelString=txtDate.getText();
                System.out.println(birthdayLabelString);
                birthdayLabel.setText(birthdayLabelString);
            }
        });
        //选择入职时间按钮
        选择时间Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField txtDate=new JTextField();
                //弹出时间选择框
                new DialogDatePicker(true,txtDate,750,450);
                //获得日期
                dateString=txtDate.getText();
                dateLabel.setText(dateString);
            }
        });
        //职务
        comboBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange()==ItemEvent.SELECTED){
                    positionString=comboBox1.getSelectedItem().toString();
                }
            }
        });
        //政治面貌
        comboBox2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange()==ItemEvent.SELECTED){
                    politiacal_statusString=comboBox2.getSelectedItem().toString();
                }
            }
        });
        //学历
        comboBox3.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange()==ItemEvent.SELECTED){
                    educationString=comboBox3.getSelectedItem().toString();
                }
            }
        });
        //省市二级联动菜单
        comboBox4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboBox5.removeAllItems();
                for (String str:map.get(comboBox4.getSelectedItem())){
                    comboBox5.addItem(str);
                }
                native_provinceSting=comboBox4.getSelectedItem().toString();
                native_cityString=comboBox5.getSelectedItem().toString();
            }
        });
//        comboBox4.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent e) {
//                if (e.getStateChange()==ItemEvent.SELECTED){
//                    native_provinceSting=comboBox4.getSelectedItem().toString();
//                }
//            }
//        });
//        //城市
//        comboBox5.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent e) {
//                if (e.getStateChange()==ItemEvent.SELECTED){
//                    native_cityString=comboBox5.getSelectedItem().toString();
//                }
//            }
//        });

        //部门
        comboBox6.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange()==ItemEvent.SELECTED){
                   departmentID = comboBox6.getSelectedIndex()+1;
                }
            }
        });
        保存Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accountString=accountField.getText();

                String nameString=nameField.getText();
                String genderString=null;
                String marrySting=null;
                if (男RadioButton.isSelected()){
                    genderString="男";
                }
                if (女RadioButton.isSelected()){
                    genderString="女";
                }
                if (已婚RadioButton.isSelected()) {
                    marrySting = "已婚";
                }
                if (未婚RadioButton.isSelected()){
                    marrySting="未婚";
                }
                String phoneString=phoneField.getText();
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                Date date=null;
                Date date1=null;
                try{
                    date1=sdf.parse(birthdayLabelString);
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
                try{
                    date=sdf.parse(dateString);
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
                java.sql.Date birthdayString=new java.sql.Date(date1.getTime());
                java.sql.Date data1=new java.sql.Date(date.getTime());
                Employee employee=new Employee(
                        accountString,
                        departmentID,
                        nameString,
                        b,
                        genderString,
                        birthdayString,
                        positionString,
                        marrySting,
                        politiacal_statusString,
                        educationString,
                        phoneString,
                        native_provinceSting,
                        native_cityString,
                        data1);

                int n=0;
                try{
                    n=employeeService.insert(employee);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                if (n!=0){
                   JOptionPane.showMessageDialog(null,"添加成功");
                   NewEmployee.this.dispose();
                   arciviesPanel.addEmployee(employee);
                }else {
                    JOptionPane.showMessageDialog(null,"添加失败");
                }
            }
        });

    }
}
