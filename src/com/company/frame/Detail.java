package com.company.frame;


import com.company.factory.ServiceFactory;
import com.company.model.Employee;
import com.company.service.EmployeeService;


import javax.swing.*;
import java.awt.*;

/**
 * Created by 高展 on 2017/12/23.
 */
public class Detail extends JFrame {
    private EmployeeService employeeService= ServiceFactory.getEmployeeServiceInstance();
    private ArciviesPanel arciviesPanel;
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

    public Detail(ArciviesPanel arciviesPanel) {
        this.arciviesPanel=arciviesPanel;
    }

    public void init(){
        JLabel[]jLabels=new JLabel[14];
        setLayout(new BorderLayout());
       //north  0
        jLabels[0]= new JLabel("照片:");
        ImageIcon imageIcon1 = new ImageIcon(employee.getAvatar());
        imageIcon1.setImage(imageIcon1.getImage().getScaledInstance(75,100,Image.SCALE_DEFAULT));
        jPanels[0].add(jLabels[0]);
        JLabel jLabel = new JLabel();
        jLabel.setIcon(imageIcon1);
        jPanels[0].add(jLabel);
        add(BorderLayout.NORTH,jPanels[0]);

        //centerPanel
        JPanel centerPanel = new JPanel(new GridLayout(13,1,0,10));
        //1
        jLabels[1]=new JLabel("工号");
        JTextField accountField=new JTextField(employee.getAccount());
        jPanels[1].add(jLabels[1]);
        jPanels[1].add(accountField);
        //2
        jLabels[2]=new JLabel("部门:");
        JTextField departmentField=new JTextField(employee.getDepartmentID());
        departmentField.setPreferredSize(new Dimension(120,30));
        jPanels[2].add(jLabels[2]);
        jPanels[2].add(departmentField);
        //3
        jLabels[3]=new JLabel("姓名:");
        JTextField nameField=new JTextField(employee.getName());
        nameField.setPreferredSize(new Dimension(120,30));
        jPanels[3].add(jLabels[3]);
        jPanels[3].add(nameField);
        //4
        jLabels[4]=new JLabel("性别:");
        JRadioButton sexButton=new JRadioButton(employee.getSex());
        jPanels[4].add(jLabels[4]);
        jPanels[4].add(sexButton);
        //5
        jLabels[5]=new JLabel("出生日期：");
        JTextField birthdayField=new JTextField(employee.getBirthday().toString());
        jPanels[5].add(jLabels[5]);
        jPanels[5].add(birthdayField);
        //6
        jLabels[6]=new JLabel("职务:");
        JTextField positionField=new JTextField(employee.getPosition());
        jPanels[6].add(jLabels[6]);
        jPanels[6].add(positionField);
        //7
        jLabels[7]=new JLabel("婚姻状况:");
        JRadioButton marryButton=new JRadioButton(employee.getMaritalstatus());
        jPanels[7].add(jLabels[7]);
        jPanels[7].add(marryButton);
        //8
        jLabels[8]=new JLabel("政治面貌:");
        JTextField politiacalField=new JTextField(employee.getPolitiacal_status());
        jPanels[8].add(jLabels[8]);
        jPanels[8].add(politiacalField);
        //9
        jLabels[9]=new JLabel("学历:");
        JTextField educationField=new JTextField(employee.getEducation());
        jPanels[9].add(jLabels[9]);
        jPanels[9].add(educationField);
        //10
        jLabels[10]=new JLabel("联系方式:");
        JTextField phoneField=new JTextField(employee.getPhone());
        jPanels[10].add(jLabels[10]);
        jPanels[10].add(phoneField);
        //11
        jLabels[11]=new JLabel("省份:");
        JTextField provinceField=new JTextField(employee.getNative_province());
        jPanels[11].add(jLabels[11]);
        jPanels[11].add(provinceField);
        //12
        jLabels[12]=new JLabel("城市:");
        JTextField cityField=new JTextField(employee.getNative_city());
        jPanels[12].add(jLabels[12]);
        jPanels[12].add(cityField);
        //13
        jLabels[13]=new JLabel("入职时间:");
        JTextField dataField=new JTextField(employee.getDate().toString());
        jPanels[13].add(jLabels[13]);
        jPanels[13].add(dataField);
        for (int i=1;i<size;i++){
            centerPanel.add(jPanels[i]);
        }
        add(BorderLayout.CENTER,centerPanel);
    }


    public static void main(String[] args) {
        EmployeeService employeeService= ServiceFactory.getEmployeeServiceInstance();
        Employee employee=employeeService.getEmployee("1002");
        Detail detail=new Detail(employee);
    }

}
