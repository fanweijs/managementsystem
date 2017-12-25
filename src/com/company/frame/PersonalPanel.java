package com.company.frame;

import com.company.factory.ServiceFactory;
import com.company.model.Employee;
import com.company.service.EmployeeService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by 高展 on 2017/12/25.
 */
public class PersonalPanel extends JPanel{
    private JPanel mainPanel;
    private JPanel northPanel;
    private JPanel centerPanel;
    private JLabel accountLabel;
    private JLabel departmentLabel;
    private JLabel nameLabel;
    private JLabel sexLabel;
    private JLabel birthdayLabel;
    private JLabel positionLabel;
    private JLabel marryLabel;
    private JLabel politiacal_statusLabel;
    private JLabel educationLabel;
    private JLabel phoneLabel;
    private JLabel native_provinceLabel;
    private JLabel native_cityLabel;
    private JLabel dataLabel;
    private JLabel avatarLabel;
    private String account;
    private EmployeeService employeeService= ServiceFactory.getEmployeeServiceInstance();

    public PersonalPanel(String account){
        this.account=account;
        Employee employee=employeeService.getEmployee(account);
        ImageIcon imageIcon=new ImageIcon(employee.getAvatar());
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(75,100,Image.SCALE_DEFAULT));

        avatarLabel.setIcon(imageIcon);
        accountLabel.setText(employee.getAccount());
        departmentLabel.setText(employee.getDepartmentID().toString());
        nameLabel.setText(employee.getName());
        sexLabel.setText(employee.getSex());
        birthdayLabel.setText(employee.getBirthday().toString());
        positionLabel.setText(employee.getPosition());
        marryLabel.setText(employee.getMaritalstatus());
        politiacal_statusLabel.setText(employee.getPolitiacal_status());
        educationLabel.setText(employee.getEducation());
        phoneLabel.setText(employee.getPhone());
        native_provinceLabel.setText(employee.getNative_province());
        native_cityLabel.setText(employee.getNative_city());
        dataLabel.setText(employee.getDate().toString());


    }
    public JPanel getMainPanel(){
        return mainPanel;
    }
}
