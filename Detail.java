package com.company.frame;

import com.company.Factory.ServiceFactory;
import com.company.model.Employee;
import com.company.service.EmployeeService;
import com.company.utils.ImagePanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by 高展 on 2017/12/23.
 */
public class Detail extends JFrame {

    private Employee employee;
    int size=14;
    JPanel jPanels[]=new JPanel[size];

    public Detail(Employee employee){
        this.employee=employee;
        for (int i=0;i<size;i++){
            jPanels[i]=new JPanel();
            jPanels[i].setLayout(new FlowLayout(FlowLayout.CENTER,10,0));
        }
        init();
        setTitle("员工信息");
        setSize(400,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void init(){
        JLabel[]jLabels=new JLabel[2];
        setLayout(new BorderLayout());
       //north
        jLabels[0]= new JLabel("照片:");
        ImagePanel imagePanel=new ImagePanel(75,100,employee.getAvatar());
        jPanels[0].add(jLabels[0]);
        jPanels[0].add(imagePanel);
        add(BorderLayout.NORTH,jPanels[0]);

        //centerPanel
        JPanel centerPanel = new JPanel(new GridLayout(13,1,0,10));
        //1
        jLabels[1]=new JLabel("部门:");
        JTextField departmentField=new JTextField();
        departmentField.setPreferredSize(new Dimension(120,30));
        jPanels[1].add(jLabels[1]);
        jPanels[1].add(departmentField);


        for (int i=1;i<size;i++){
            centerPanel.add(jPanels[i]);
        }
        add(BorderLayout.CENTER,centerPanel);
    }


    public static void main(String[] args) {
        EmployeeService employeeService= ServiceFactory.getEmployeeServiceInstance();
        Employee employee=employeeService.getEmployee("1001");
        Detail detail=new Detail(employee);
    }

}
