package com.company.frame;

import com.company.factory.ServiceFactory;
import com.company.model.Employee;
import com.company.service.EmployeeService;
import com.company.utils.DialogDatePicker;
import com.company.utils.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.sql.Date;
import java.sql.SQLException;

/**
 * Created by 高展 on 2017/12/25.
 */
public class UpadeFrame extends JFrame{
    private JPanel mainPanel;
    private JButton 保存Button;
    private JTextField accountField;
    private JTextField nameField;
    private JButton 选择日期Button;
    private JButton 选择照片Button;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JTextField phoneField;
    private JComboBox comboBox5;
    private JComboBox comboBox6;
    private JButton 选择入职时间Button;
    private JLabel dateLabel;
    private JLabel birthdayLabel;
    private JLabel avatarLabel;
    private JTextField sexField;
    private JTextField marryField;
    private JComboBox comboBox1;
    private Employee employee;
    private byte[] b=new byte[2];
    private Integer departmentID;
    private String positionString;
    private String politiacal_statusString;
    private String educationString;
    private String native_provinceSting;
    private String native_cityString;
    private String birthdayLabelString;
    private String dateString;
    private EmployeeService employeeService= ServiceFactory.getEmployeeServiceInstance();
    private JTable table;
    private int[] rows;

    public UpadeFrame(Employee employee){
        this.employee=employee;
        ImageIcon imageIcon=new ImageIcon(employee.getAvatar());
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(75,100,Image.SCALE_DEFAULT));
        setTitle("修改员工信息");
        setSize(900,800);
        setLocationRelativeTo(null);
        setVisible(true);
        add(mainPanel);

        //获取员工原始信息
        avatarLabel.setIcon(imageIcon);
        accountField.setText(employee.getAccount());
        nameField.setText(employee.getName());
        sexField.setText(employee.getSex());
        marryField.setText(employee.getMaritalstatus());
        phoneField.setText(employee.getPhone());
        birthdayLabel.setText(employee.getBirthday().toString());
        dateLabel.setText(employee.getDate().toString());

        //头像
        选择照片Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser=new JFileChooser();
                int n=chooser.showOpenDialog(UpadeFrame.this);
                if (n == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    b = FileUtils.fileToBytes(file);
                    avatarLabel.setIcon(new ImageIcon(b));
                }
            }
        });
        //部门
        comboBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange()==ItemEvent.SELECTED){
                    departmentID=comboBox1.getSelectedIndex()+1;
                }
            }
        });
        //职务
        comboBox2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange()==ItemEvent.SELECTED){
                    positionString=comboBox2.getSelectedItem().toString();
                }
            }
        });
        //政治面貌
        comboBox3.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange()==ItemEvent.SELECTED){
                    politiacal_statusString=comboBox3.getSelectedItem().toString();
                }
            }
        });
        //学历
        comboBox4.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange()==ItemEvent.SELECTED){
                    educationString=comboBox4.getSelectedItem().toString();
                }
            }
        });
        //省份
        comboBox5.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange()==ItemEvent.SELECTED){
                    native_provinceSting=comboBox5.getSelectedItem().toString();
                }
            }
        });
        //城市
        comboBox6.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange()==ItemEvent.SELECTED){
                    native_cityString=comboBox6.getSelectedItem().toString();
                }
            }
        });
        //出生日期
        选择日期Button.addActionListener(new ActionListener() {
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
        //入职时间
        选择入职时间Button.addActionListener(new ActionListener() {
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
        //保存按钮
        保存Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accountString=accountField.getText();
                String nameString=nameField.getText();
                String sexString=sexField.getText();
                String marryString=marryField.getText();
                String phoneString=phoneField.getText();

                //将员工信息更新
                employee.setAvatar(b);
                employee.setAccount(accountString);
                employee.setDepartmentID(departmentID);
                employee.setName(nameString);
                employee.setSex(sexString);
                //employee.setBirthday();
                employee.setPosition(positionString);
                employee.setMaritalstatus(marryString);
                employee.setPolitiacal_status(politiacal_statusString);
                employee.setEducation(educationString);
                employee.setPhone(phoneString);
                employee.setNative_province(native_provinceSting);
                employee.setNative_city(native_cityString);
                //employee.setDate();
                int n=0;
                try {
                    n=employeeService.update(employee);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                if (n!=0){
                    JOptionPane.showMessageDialog(null,"修改成功");
                    //更改数据后及时在数据表中更新
                    table.setValueAt(accountString,rows[0],0);
                    table.setValueAt(departmentID,rows[1],1);
                    table.setValueAt(nameString,rows[2],2);
                    table.setValueAt(avatarLabel,rows[3],3);
                    table.setValueAt(sexString,rows[4],4);
                    table.setValueAt(birthdayLabelString,rows[5],5);
                    table.setValueAt(positionString,rows[6],6);
                    table.setValueAt(marryString,rows[7],7);
                    table.setValueAt(politiacal_statusString,rows[8],8);
                    table.setValueAt(educationString,rows[9],9);
                    table.setValueAt(phoneString,rows[10],10);
                    table.setValueAt(native_provinceSting,rows[11],11);
                    table.setValueAt(native_cityString,rows[12],12);
                    table.setValueAt(dateString,rows[13],13);
                }else {
                    JOptionPane.showMessageDialog(null,"修改失败");
                }
            }
        });
    }

}
