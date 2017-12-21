package com.company.frame;


import com.company.factory.ServiceFactory;
import com.company.model.Employee;
import com.company.service.EmployeeService;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 高展 on 2017/12/21.
 * 档案管理
 *  有部分错误
 */
public class ArciviesPanel extends JPanel implements ActionListener{
    private List<String> arciviesList=new ArrayList<>();
    private CardLayout card=new CardLayout();
    private JPanel centerPanel=new JPanel();
    private DefaultTableModel dtm;
    private JTable table;
    private Employee employee=null;
    private List<Employee>employees;
    private EmployeeService employeeService= ServiceFactory.getEmployeeServiceInstance();
    private int[] rows;
    private String account;
    //private JScrollPane

    public ArciviesPanel(List<String>arciviesList, String account) throws SQLException {
        this.arciviesList=arciviesList;
        this.account=account;
        init();
        showTable();
    }

    public void init(){

        JButton[]jButtons=new JButton[arciviesList.size()];
        int i=0;
        for (String str: arciviesList){
            jButtons[i]=new JButton(str);
            jButtons[i].addActionListener(this);
            i++;
        }
        this.setLayout(new BorderLayout());
        JPanel westPanel=new JPanel();
        westPanel.setLayout(new GridLayout(7,1,0,50));
        JLabel jLabel=new JLabel();
        westPanel.add(jLabel);
        for (int c=0;c<jButtons.length;c++){
            westPanel.add(jButtons[c]);
        }
        add(BorderLayout.WEST,westPanel);
        centerPanel.setLayout(card);
        //个人档案  panel
        JPanel card1=new JPanel();
        card1.setBackground(Color.DARK_GRAY);

        //所有员工档案
        JPanel card2=new JPanel();
        card2.setBackground(Color.red);
//
//        DefaultTableModel model=new DefaultTableModel();//建立一个数据模型
//        JTable table=new JTable(model);  //建立一个表格
//        JScrollPane jsp=new JScrollPane(table); //将表格添加到面板中

        //添加员工档案
        JPanel card3=new JPanel();
        card3.setBackground(Color.blue);

        centerPanel.add(card1,"1");
        centerPanel.add(card2,"2");
        centerPanel.add(card3,"3");
        add(BorderLayout.CENTER,centerPanel);

    }

    public void showTable()throws SQLException{
        dtm=new DefaultTableModel();
        String[] titles={"工号","部门","姓名","性别","出生日期","职务","婚姻状况","政治面貌","学历","联系方式","省份","城市","入职时间"};
        //设置表头标题
        dtm.setColumnIdentifiers(titles);
        //给表格设置数据模型
        table.setModel(dtm);
        //将单元格内容居中
        DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
        //设置水平居中
        renderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class,renderer);
        //将表头居中
        DefaultTableCellRenderer renderer1=new DefaultTableCellRenderer();
        renderer1.setHorizontalAlignment(JLabel.CENTER);
        renderer1.setBackground(Color.LIGHT_GRAY);
        table.getTableHeader().setDefaultRenderer(renderer1);
        //内容字符串数组
        String[] content=new String[14];
        //获取数据库中所有用户信息
        employees=employeeService.getAll();
        Iterator<Employee>iterator=employees.iterator();
        while (iterator.hasNext()){
            Employee employee=iterator.next();
            content[0]=employee.getAccount();
            content[1]= String.valueOf(employee.getDepartmentID());
            content[2]=employee.getName();
            content[3]=employee.getSex();
            content[4]= String.valueOf(employee.getBirthday());
            content[5]=employee.getPosition();
            content[6]=employee.getMaritalstatus();
            content[7]=employee.getPolitiacal_status();
            content[8]=employee.getEducation();
            content[9]=employee.getPhone();
            content[10]=employee.getNative_province();
            content[11]=employee.getNative_city();
            content[12]= String.valueOf(employee.getDate());

            dtm.addRow(content);
        }




    }
    public static void main(String[] args) throws SQLException {
        JFrame frame=new JFrame("档案管理");
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);
        List<String>list=new ArrayList<>();
        list.add("个人档案");
        list.add("所有员工档案");
        list.add("添加员工档案");
        frame.add(new ArciviesPanel(list,"1001"));
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("个人档案")){
            card.show(centerPanel,"1");
        }
        if (e.getActionCommand().equals("所有员工档案")){
            card.show(centerPanel,"2");
        }
        if (e.getActionCommand().equals("添加员工档案")){
            card.show(centerPanel,"3");
        }
    }
}
